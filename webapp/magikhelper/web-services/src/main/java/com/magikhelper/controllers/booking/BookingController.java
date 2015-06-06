package com.magikhelper.controllers.booking;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.magikhelper.services.BookingService;
import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;

/**
 *
 * @author aahmed
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Log log = LogFactory.getLog(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody BookingVO booking, HttpServletRequest request, HttpServletResponse response) {
		bookingService.createBooking(booking);	
    	response.setHeader("Location", request.getRequestURL().append("/").append(booking.getBookingId()).toString());
    }
    
    @RequestMapping(value="/{bookingId}", method = RequestMethod.GET,headers="Accept=application/json")
    public List<BookingListVO> getBooking(@PathVariable("bookingId") Integer bookingId) {
    	List<String> columnNames = new ArrayList<String>();
    	List<String> values = new ArrayList<String>();
    	columnNames.add("bookingId");
    	values.add(String.valueOf(bookingId));
    	
        List<BookingListVO> bookings = bookingService.getBookings(columnNames, values, null);
        return bookings;
    }
    
    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<BookingListVO> getBookings(
    		@RequestParam(value = "booking", required = false) Integer bookingId,
    		@RequestParam(value = "client", required = false) Integer clientId,
    		@RequestParam(value = "email", required = false) String email,
    		@RequestParam(value = "status", required = false) String status,
    		@RequestParam(value = "op", required = false) String operator, 
    		@RequestParam(value = "date", required = false) String dateValue, 
    		HttpServletRequest request) {
    	String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    	log.debug("path=========>"+path);
    	
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher apm = new AntPathMatcher();
        String exactPath = apm.extractPathWithinPattern(bestMatchPattern, path);
        log.debug("exactpath=========>"+exactPath);

        log.debug("bookingId: "+bookingId);
        log.debug("clientId: "+clientId);
        log.debug("email: "+email);
        log.debug("status: "+status);
        log.debug("operator: "+operator);
        log.debug("dateValue: "+dateValue);
        
       	List<String> columnNames = new ArrayList<String>();
    	List<String> values = new ArrayList<String>();

    	if(bookingId != null){
    		columnNames.add("bookingId");
        	values.add(String.valueOf(bookingId));
    	}
    	if(clientId != null){
    		columnNames.add("userId");
        	values.add(String.valueOf(clientId));
    	}
    	if(StringUtils.isNotEmpty(email)){
    		columnNames.add("email");
        	values.add(email);
    	}
    	if(StringUtils.isNotEmpty(status)){
    		columnNames.add("status");
        	values.add(status);    		
    	}
    	if(StringUtils.isNotEmpty(dateValue)){
    		columnNames.add("date");
        	values.add(dateValue);    		
    	}
    	
    	List<BookingListVO> bookings = bookingService.getBookings(columnNames, values, operator);
    	return bookings;
    }
    
   
}
