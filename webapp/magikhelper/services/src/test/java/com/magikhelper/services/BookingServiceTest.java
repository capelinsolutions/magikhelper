package com.magikhelper.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.utils.MagikHelperConstants;
import com.magikhelper.vo.BookingFeedbackVO;
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
    	vo.setComments("comments");
    	
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
    public void assignToVendor(){
    	BookingVO vo = new BookingVO();
    	vo.setBookingId(new Integer(1));
    	vo.setVendorId(new Integer(4));
    	
//    	bookingService.assignToVendor(vo);
    }
    
    @Test
    public void updateBooking(){
    	BookingVO vo = new BookingVO();
    	vo.setBookingId(new Integer(1));
    	vo.setStartDateTime("06/07/2015 17:15:00");
    	vo.setFinishDateTime("06/07/2015 17:30:00");
    	vo.setStatusId(MagikHelperConstants.BOOKING_STATUS_IN_PROGRESS);
    	vo.setStatusDesc("booking Accepted by vendor");
//    	bookingService.updateBooking(vo);
    }
    
    @Test
    public void addFeedback(){
    	BookingFeedbackVO vo = new BookingFeedbackVO(new Integer(1), "Comments-----1");
//    	bookingService.addFeedback(vo);
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
