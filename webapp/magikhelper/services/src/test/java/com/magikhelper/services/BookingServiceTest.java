package com.magikhelper.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;
import com.magikhelper.vo.ContactVO;
import com.magikhelper.vo.UserVO;

public class BookingServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(BookingServiceTest.class);

    @Autowired
    private BookingService bookingService;

    public BookingServiceTest() {
    }

    @Test
    public void createBooking() {
    	BookingVO vo = new BookingVO();
    	ContactVO contact = new ContactVO();
    	vo.setBookedDate("02/07/2015");
    	vo.setBookedTime("16:00");
    	vo.setDuration(2);
    	vo.setClientId(3);
    	vo.setServiceId(1);
    	vo.setRate(new BigDecimal("20.5"));
    	vo.setBookingContact(contact);
    	
    	contact.setFirstName("Test");
    	contact.setLastName("UserVO");
    	contact.setMobilePhone("Mobile");
    	contact.setStreet("Street");
    	contact.setAdditional("Additional");
    	contact.setCity("City");
    	contact.setZip("Zip");
    	contact.setState("State");
    	contact.setCountry("Country");
    	
    	bookingService.createBooking(vo);
    }
    
    @Test
    public void getClientBookings(){
    	List<UserVO> bookings = bookingService.getClientBookings(null, null, null);
    	System.out.println(bookings);
    }
    
    @Test 
    public void getBookings(){
    	List<BookingListVO> bookings = bookingService.getBookings(null, null, null);
    	System.out.println(bookings);
    }
}
