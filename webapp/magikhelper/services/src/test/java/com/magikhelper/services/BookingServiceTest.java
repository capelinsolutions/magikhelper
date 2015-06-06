package com.magikhelper.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;
import com.magikhelper.vo.UserVO;

public class BookingServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(BookingServiceTest.class);

    @Autowired
    private BookingService bookingService;

    public BookingServiceTest() {
    }

    @Test
    public void testCreateBooking() {
    	BookingVO vo = new BookingVO();
    	vo.setBookedDate("02/07/2015");
    	vo.setBookedTime("16:00");
    	vo.setDuration(2);
    	vo.setClientId(3);
    	vo.setServiceId(1);
    	bookingService.createBooking(vo);
    }
    
    @Test
    public void testClientBookings(){
    	List<UserVO> bookings = bookingService.getClientBookings(null, null, null);
    	log.debug(bookings);
    }
    
    @Test 
    public void testGetBookings(){
    	List<BookingListVO> bookings = bookingService.getBookings(null, null, null);
    	log.debug(bookings);
    }
}
