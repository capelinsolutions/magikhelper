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
import com.magikhelper.entities.User;
import com.magikhelper.services.BookingService;
import com.magikhelper.utils.DateUtils;
import com.magikhelper.utils.MagikHelperConstants;
import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;
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
		Date date = DateUtils.convertToDate(vo.getBookedDate()+" "+vo.getBookedTime(), "MM/dd/yyyy HH:mm");
		booking.setBookedDatetime(date);
		booking.setDuration(vo.getDuration());
		booking.setStatusDesc("Booking created with created status.");
		booking.populatedAuditFields("SYSTEM");
		
		User client = usersDao.getReference(vo.getClientId());
		ApplicationProperty statusId = applicationPropertiesDao.getReference(MagikHelperConstants.BOOKING_STATUS_ACTIVE);
		ApplicationProperty serviceId = applicationPropertiesDao.getReference(vo.getServiceId());
		
		booking.setUser(client);
		booking.setStatus(statusId);
		booking.setService(serviceId);
		
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
			String email = (String)clientData[1];
			String firstName = (String)clientData[2];
			String lastName = (String)clientData[3];
			String mobilePhone = (String)clientData[4];
			String street = (String)clientData[5];
			String additional = (String)clientData[6];
			String city = (String)clientData[7];
			String zip = (String)clientData[8];
			String state = (String)clientData[9];
			String country = (String)clientData[10];
			Integer bookingId =(Integer)clientData[11];
			Timestamp bookedDateTime = (Timestamp)clientData[12];
			Integer duration = (Integer)clientData[13];
			Timestamp startDateTime = (Timestamp)clientData[14];
			Timestamp finishDateTime = (Timestamp)clientData[15];
			String status = (String)clientData[16];
			String statusDesc = (String)clientData[17];
			String serviceName = (String)clientData[18];
			BigDecimal rate = (BigDecimal)clientData[19];
			
			ClientBookingsVO booking=new ClientBookingsVO();
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
			
			
			if(clientIds.contains(clientId)==false){
				UserVO client=new UserVO();
				client.setUserId(clientId);
				client.setEmail(email);
				client.setFirstName(firstName);
				client.setLastName(lastName);
				client.setMobilePhone(mobilePhone);
				client.setStreet(street);
				client.setAdditional(additional);
				client.setCity(city);
				client.setZip(zip);
				client.setState(state);	
				client.setCountry(country);
				
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
			vo.setBookingId(b.getRowId());
			vo.setServiceId(b.getService().getPropertyId());
			vo.setBookedDate(DateUtils.convertToString(b.getBookedDatetime(), "MM/dd/yyyy"));
			vo.setBookedTime(DateUtils.convertToString(b.getBookedDatetime(), "HH:mm:ss"));
			vo.setDuration(b.getDuration());
			vo.setStartDateTime(DateUtils.convertToString(b.getStartDatetime(), "MM/dd/yyyy HH:mm:ss"));
			vo.setFinishDateTime(DateUtils.convertToString(b.getFinishDatetime(), "MM/dd/yyyy HH:mm:ss"));
			vo.setStatus(b.getStatus().getName());
			vo.setStatusDesc(b.getStatusDesc());
			vo.setServiceName(b.getService().getName());
			vo.setRate(b.getService().getServices().get(0).getRate());
			vo.setClientId(b.getUser().getRowId());
			vo.setEmail(b.getUser().getEmail());
			vo.setFirstName(b.getUser().getContact().getFirstName());
			vo.setLastName(b.getUser().getContact().getLastName());
			vo.setMobilePhone(b.getUser().getContact().getMobilePhone());
			vo.setStreet(b.getUser().getContact().getStreet());
			vo.setAdditional(b.getUser().getContact().getAdditional());
			vo.setCity(b.getUser().getContact().getCity());
			vo.setZip(b.getUser().getContact().getZip());
			vo.setState(b.getUser().getContact().getState());	
			vo.setCountry(b.getUser().getContact().getCountry());
			
			bookingsVo.add(vo);
		}
		return bookingsVo;
	}
}
