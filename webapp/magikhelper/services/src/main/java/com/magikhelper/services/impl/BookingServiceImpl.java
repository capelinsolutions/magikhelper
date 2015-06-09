package com.magikhelper.services.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.magikhelper.dao.ApplicationPropertiesDao;
import com.magikhelper.dao.BookingDao;
import com.magikhelper.dao.BookingEventDao;
import com.magikhelper.dao.BookingFeedbackDao;
import com.magikhelper.dao.UsersDao;
import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.Booking;
import com.magikhelper.entities.BookingEvent;
import com.magikhelper.entities.BookingFeedback;
import com.magikhelper.entities.Contact;
import com.magikhelper.entities.User;
import com.magikhelper.services.BookingService;
import com.magikhelper.utils.DateUtils;
import com.magikhelper.utils.MagikHelperConstants;
import com.magikhelper.vo.BookingFeedbackVO;
import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;
import com.magikhelper.vo.ClientBookingsVO;
import com.magikhelper.vo.ContactVO;
import com.magikhelper.vo.UserVO;

@Service(value = "bookingService")
public class BookingServiceImpl implements BookingService {

    private static final Log log = LogFactory.getLog(BookingServiceImpl.class);
    
    @Autowired
    BookingDao bookingDao;
    
    @Autowired
    UsersDao usersDao;

    @Autowired
    ApplicationPropertiesDao applicationPropertiesDao;
    
    @Autowired
    BookingEventDao bookingEventDao;
    
    @Autowired
    BookingFeedbackDao bookingFeedbackDao;
    
    @Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void createBooking(BookingVO vo) {
		Booking booking = new Booking();
		Contact contact = new Contact();
		BookingEvent event = new BookingEvent();
		
		Date date = DateUtils.convertToDate(vo.getBookedDate()+" "+vo.getBookedTime(), "MM/dd/yyyy HH:mm");
		booking.setBookedDatetime(date);
		booking.setDuration(vo.getDuration());
		booking.setComments(vo.getComments());
		booking.setStatusDesc("Booking created with created status.");
		booking.setRate(vo.getRate());
		booking.populatedAuditFieldsOnCreate("SYSTEM");
		
		User client = usersDao.getReference(vo.getClientId());
		ApplicationProperty statusId = applicationPropertiesDao.getReference(MagikHelperConstants.BOOKING_STATUS_CREATED);
		ApplicationProperty serviceId = applicationPropertiesDao.getReference(vo.getServiceId());
		
		booking.setClient(client);
		booking.setStatus(statusId);
		booking.setService(serviceId);
		
		contact.setFirstName(vo.getBookingContact().getFirstName());
		contact.setLastName(vo.getBookingContact().getLastName());
		contact.setStreet(vo.getBookingContact().getStreet());
		contact.setAdditional(vo.getBookingContact().getAdditional());
		contact.setCity(vo.getBookingContact().getCity());
		contact.setState(vo.getBookingContact().getState());
		contact.setZip(vo.getBookingContact().getZip());
		contact.setCountry(vo.getBookingContact().getCountry());
		contact.setMobilePhone(vo.getBookingContact().getMobilePhone());
		contact.populatedAuditFieldsOnCreate("SYSTEM");
		
		event.setStatus(statusId);
		event.setComments("Booking created with created status.");
		event.populatedAuditFieldsOnCreate("SYSTEM");

		booking.setContact(contact);
		booking.addBookingEvent(event);
		bookingDao.add(booking);
		
		vo.setBookingId(booking.getRowId());
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void addFeedback(BookingFeedbackVO vo) {
		Booking booking = bookingDao.getReference(vo.getBookingId());
		BookingFeedback feedback = new BookingFeedback();
		feedback.setComments(vo.getComments());
		feedback.setBooking(booking);
		feedback.populatedAuditFieldsOnCreate("SYSTEM");
		bookingFeedbackDao.add(feedback);
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void assignToVendor(BookingVO vo) {

		BookingEvent event = new BookingEvent();
		
		Booking booking = bookingDao.get(vo.getBookingId());
		User vendor = usersDao.getReference(vo.getVendorId());
		ApplicationProperty statusId = applicationPropertiesDao.getReference(MagikHelperConstants.BOOKING_STATUS_ASSIGNED);
		BookingEvent lastBookingEvent = bookingEventDao.getLastEventByBookingId(vo.getBookingId());

		event.setStatus(statusId);
		event.setComments("Booking assigned to vendor");
		event.populatedAuditFieldsOnCreate("SYSTEM");
		event.setBooking(booking);
		
		lastBookingEvent.populatedAuditFieldsOnUpdate("SYSTEM");
		
		booking.setStatus(statusId);
		booking.setVendor(vendor);
		booking.populatedAuditFieldsOnUpdate("SYSTEM");
		
		bookingEventDao.add(event);		
		bookingEventDao.update(lastBookingEvent);
		
		bookingDao.update(booking);
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void updateBooking(BookingVO vo) {

		Booking booking = bookingDao.get(vo.getBookingId());

		Date startDateTime=null;
		Date finishDateTime=null;
		
		if(StringUtils.isNotEmpty(vo.getStartDateTime())){
			startDateTime = DateUtils.convertToDate(vo.getStartDateTime(), "MM/dd/yyyy HH:mm:ss");
			booking.setStartDatetime(startDateTime);
		}
		
		if(StringUtils.isNotEmpty(vo.getFinishDateTime())){
			finishDateTime = DateUtils.convertToDate(vo.getFinishDateTime(), "MM/dd/yyyy HH:mm:ss");
			booking.setFinishDatetime(finishDateTime);
		}
		
		if(StringUtils.isNotEmpty(vo.getStatusDesc())){
			booking.setStatusDesc(vo.getStatusDesc());
		}
		
		if(vo.getStatusId() != null){
			ApplicationProperty statusId = applicationPropertiesDao.getReference(vo.getStatusId());
			BookingEvent lastBookingEvent = bookingEventDao.getLastEventByBookingId(vo.getBookingId());

			BookingEvent event = new BookingEvent();

			event.setStatus(statusId);
			event.setComments(vo.getStatusDesc());
			event.populatedAuditFieldsOnCreate("SYSTEM");
			event.setBooking(booking);

			lastBookingEvent.populatedAuditFieldsOnUpdate("SYSTEM");

			bookingEventDao.update(lastBookingEvent);
			bookingEventDao.add(event);		

			booking.setStatus(statusId);
		}

		booking.populatedAuditFieldsOnUpdate("SYSTEM");
		
		bookingDao.update(booking);
	}
	
	@Override
	public List<UserVO> getClientBookings(List<String> columnNames,	List<String> values, String dateOperator) {
		List<Object[]> bookingsObjs = bookingDao.getClientBookings(columnNames, values, dateOperator);
		List<UserVO> clients = new ArrayList<UserVO>();
		List<Integer> clientIds = new ArrayList<Integer>();
		
		int index=-1;
		for (Object[] clientData : bookingsObjs) {

			Integer clientId = (Integer)clientData[0];
			String clientEmail = (String)clientData[1];
			String clientFirstName = (String)clientData[2];
			String clientLastName = (String)clientData[3];
			String clientMobilePhone = (String)clientData[4];
			String clientStreet = (String)clientData[5];
			String clientAdditional = (String)clientData[6];
			String clientCity = (String)clientData[7];
			String clientZip = (String)clientData[8];
			String clientState = (String)clientData[9];
			String clientCountry = (String)clientData[10];
			Integer bookingId =(Integer)clientData[11];
			Timestamp bookedDateTime = (Timestamp)clientData[12];
			Integer duration = (Integer)clientData[13];
			Timestamp startDateTime = (Timestamp)clientData[14];
			Timestamp finishDateTime = (Timestamp)clientData[15];
			String status = (String)clientData[16];
			String statusDesc = (String)clientData[17];
			String serviceName = (String)clientData[18];
			BigDecimal rate = (BigDecimal)clientData[19];
			String bookingFirstName = (String)clientData[20];
			String bookingLastName = (String)clientData[21];
			String bookingMobilePhone = (String)clientData[22];
			String bookingStreet = (String)clientData[23];
			String bookingAdditional = (String)clientData[24];
			String bookingCity = (String)clientData[25];
			String bookingZip = (String)clientData[26];
			String bookingState = (String)clientData[27];
			String bookingCountry = (String)clientData[28];
			String bookingComments = (String)clientData[29];
			
			ClientBookingsVO booking=new ClientBookingsVO();
			ContactVO bookingContact = new ContactVO();
			booking.setBookingId(bookingId);
			booking.setBookedDate(DateUtils.convertToString(bookedDateTime, "MM/dd/yyyy"));
			booking.setBookedTime(DateUtils.convertToString(bookedDateTime, "HH:mm:ss"));
			booking.setDuration(duration);
			booking.setStartDateTime(DateUtils.convertToString(startDateTime, "MM/dd/yyyy HH:mm:ss"));
			booking.setFinishDateTime(DateUtils.convertToString(finishDateTime, "MM/dd/yyyy HH:mm:ss"));
			booking.setStatus(status);
			booking.setStatusDesc(statusDesc);
			booking.setServiceName(serviceName);
			booking.setRate(rate);
			booking.setBookingComments(bookingComments);
			
			bookingContact.setFirstName(bookingFirstName);
			bookingContact.setLastName(bookingLastName);
			bookingContact.setMobilePhone(bookingMobilePhone);
			bookingContact.setStreet(bookingStreet);
			bookingContact.setAdditional(bookingAdditional);
			bookingContact.setCity(bookingCity);
			bookingContact.setZip(bookingZip);
			bookingContact.setState(bookingState);	
			bookingContact.setCountry(bookingCountry);
			booking.setBookingContact(bookingContact);
			
			if(clientIds.contains(clientId)==false){
				UserVO client=new UserVO();
				ContactVO contact = new ContactVO();
				client.setUserId(clientId);
				client.setEmail(clientEmail);
				contact.setFirstName(clientFirstName);
				contact.setLastName(clientLastName);
				contact.setMobilePhone(clientMobilePhone);
				contact.setStreet(clientStreet);
				contact.setAdditional(clientAdditional);
				contact.setCity(clientCity);
				contact.setZip(clientZip);
				contact.setState(clientState);	
				contact.setCountry(clientCountry);
				client.setContact(contact);
				
				List<ClientBookingsVO> bookings = new ArrayList<ClientBookingsVO>();
				bookings.add(booking);
				client.setBookings(bookings);
				clients.add(client);
				
				clientIds.add(clientId);
				index++;
			}
			else {
				clients.get(index).getBookings().add(booking);
			}
		}
		return clients;
	}
	
	
	public List<BookingListVO> getBookings(List<String> columnNames, List<String> values, String dateOperator) {
		List<Booking> bookings = bookingDao.getBookings(columnNames, values, dateOperator);
		List<BookingListVO> bookingsVo = new ArrayList<BookingListVO>();
		for (Booking b : bookings) {
			BookingListVO vo = new BookingListVO();
			ContactVO bc = new ContactVO();
			ContactVO cc = new ContactVO();
			ContactVO vc = new ContactVO();
			
			vo.setBookingId(b.getRowId());
			vo.setServiceId(b.getService().getPropertyId());
			vo.setBookedDate(DateUtils.convertToString(b.getBookedDatetime(), "MM/dd/yyyy"));
			vo.setBookedTime(DateUtils.convertToString(b.getBookedDatetime(), "HH:mm:ss"));
			vo.setDuration(b.getDuration());
			vo.setStartDateTime(DateUtils.convertToString(b.getStartDatetime(), "MM/dd/yyyy HH:mm:ss"));
			vo.setFinishDateTime(DateUtils.convertToString(b.getFinishDatetime(), "MM/dd/yyyy HH:mm:ss"));
			vo.setStatus(b.getStatus().getValue());
			vo.setStatusDesc(b.getStatusDesc());
			vo.setServiceName(b.getService().getValue());
			vo.setRate(b.getRate());
			vo.setBookingComments(b.getComments());
			
			vo.setClientId(b.getClient().getRowId());
			if (b.getVendor() != null){
				vo.setVendorId(b.getVendor().getRowId());
			}
			setContact(bc, b.getContact());
			setContact(cc, b.getClient().getContact());
			if (b.getVendor() != null){
				setContact(vc, b.getVendor().getContact());
			}
			vo.setBookingContact(bc);
			vo.setClientContact(cc);
			vo.setVendorContact(vc);
			
			bookingsVo.add(vo);
		}
		return bookingsVo;
	}
	
	
	private void setContact(ContactVO vo, Contact entity){
		vo.setFirstName(entity.getFirstName());
		vo.setLastName(entity.getLastName());
		vo.setMobilePhone(entity.getMobilePhone());
		vo.setStreet(entity.getStreet());
		vo.setAdditional(entity.getAdditional());
		vo.setCity(entity.getCity());
		vo.setZip(entity.getZip());
		vo.setState(entity.getState());	
		vo.setCountry(entity.getCountry());
	}
}
