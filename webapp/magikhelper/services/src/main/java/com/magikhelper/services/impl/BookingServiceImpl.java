package com.magikhelper.services.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.magikhelper.dao.ApplicationPropertiesDao;
import com.magikhelper.dao.BookingDao;
import com.magikhelper.dao.UsersDao;
import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.Booking;
import com.magikhelper.entities.Contact;
import com.magikhelper.entities.User;
import com.magikhelper.services.BookingService;
import com.magikhelper.utils.DateUtils;
import com.magikhelper.utils.MagikHelperConstants;
import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;
import com.magikhelper.vo.ContactVO;
import com.magikhelper.vo.UserVO;
import com.magikhelper.vo.ClientBookingsVO;
import com.magikhelper.vo.MagikHelperService;
import com.magikhelper.vo.VendorVO;

@Service(value = "bookingService")
public class BookingServiceImpl implements BookingService {

    private static final Log log = LogFactory.getLog(BookingServiceImpl.class);
    
    @Autowired
    BookingDao bookingDao;
    
    @Autowired
    UsersDao usersDao;

    @Autowired
    ApplicationPropertiesDao applicationPropertiesDao;
    
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void createBooking(BookingVO vo) {
		Booking booking = new Booking();
		Contact contact = new Contact();
		
		Date date = DateUtils.convertToDate(vo.getBookedDate()+" "+vo.getBookedTime(), "MM/dd/yyyy HH:mm");
		booking.setBookedDatetime(date);
		booking.setDuration(vo.getDuration());
		booking.setStatusDesc("Booking created with created status.");
		booking.setRate(vo.getRate());
		booking.populatedAuditFields("SYSTEM");
		
		User client = usersDao.getReference(vo.getClientId());
		ApplicationProperty statusId = applicationPropertiesDao.getReference(MagikHelperConstants.BOOKING_STATUS_ACTIVE);
		ApplicationProperty serviceId = applicationPropertiesDao.getReference(vo.getServiceId());
		
		booking.setUser(client);
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
		contact.populatedAuditFields("SYSTEM");

		booking.setContact(contact);
		bookingDao.add(booking);
		
		vo.setBookingId(booking.getRowId());
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
			ContactVO contact = new ContactVO();
			
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
			vo.setClientId(b.getUser().getRowId());
			vo.setClientEmail(b.getUser().getEmail());
			
			contact.setFirstName(b.getContact().getFirstName());
			contact.setLastName(b.getContact().getLastName());
			contact.setMobilePhone(b.getContact().getMobilePhone());
			contact.setStreet(b.getContact().getStreet());
			contact.setAdditional(b.getContact().getAdditional());
			contact.setCity(b.getContact().getCity());
			contact.setZip(b.getContact().getZip());
			contact.setState(b.getContact().getState());	
			contact.setCountry(b.getContact().getCountry());
			
			vo.setBookingContact(contact);
			
			bookingsVo.add(vo);
		}
		return bookingsVo;
	}
}
