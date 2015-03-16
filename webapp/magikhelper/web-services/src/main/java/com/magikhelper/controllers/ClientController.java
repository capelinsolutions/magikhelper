package com.magikhelper.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.services.BookingService;
import com.magikhelper.services.UsersService;
import com.magikhelper.vo.Client;

/**
 *
 * @author aahmed
 */
@RestController
@RequestMapping("/clients")
public class ClientController {

    private static final Log log = LogFactory.getLog(ClientController.class);

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<Client> getClients() {
        List<Client> clients = usersService.getClients(null);
        return clients;
    }
    
    @RequestMapping(value="/{clientId}",method = RequestMethod.GET,headers="Accept=application/json")
    public List<Client> getClient(@PathVariable("clientId") Integer clientId) {
        List<Client> clients = usersService.getClients(clientId);
        return clients;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody Client client, HttpServletRequest request, HttpServletResponse response) {
			usersService.addClient(client);
			response.setHeader("Location", request.getRequestURL().append("/").append(client.getClientId()).toString());
    }
    
    @RequestMapping(value="/bookings/{userId}", method = RequestMethod.GET,headers="Accept=application/json")
    public List<Client> getClientBooking(@PathVariable("userId") Integer userId) {
    	List<String> columnNames = new ArrayList<String>();
    	List<String> values = new ArrayList<String>();
    	columnNames.add("user_id");
    	values.add(String.valueOf(userId));
    	
        List<Client> bookings = bookingService.getClientBookings(columnNames, values, null);
        return bookings;
    }
    
    @RequestMapping(value="/bookings", method = RequestMethod.GET,headers="Accept=application/json")
    public List<Client> getClientBookings(
    		@RequestParam(value = "client", required = false) Integer clientId,
    		@RequestParam(value = "email", required = false) String email,
    		@RequestParam(value = "status", required = false) String status,
    		@RequestParam(value = "op", required = false) String operator, 
    		@RequestParam(value = "date", required = false) String dateValue, 
    		HttpServletRequest request) {

        log.debug("clientId: "+clientId);
        log.debug("email: "+email);
        log.debug("status: "+status);
        log.debug("operator: "+operator);
        log.debug("dateValue: "+dateValue);
        
       	List<String> columnNames = new ArrayList<String>();
    	List<String> values = new ArrayList<String>();

    	if(clientId != null){
    		columnNames.add("user_id");
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
    	
    	List<Client> bookings = bookingService.getClientBookings(columnNames, values, operator);
    	return bookings;
    }
//    @ExceptionHandler (Exception.class)
//    @ResponseStatus (HttpStatus.BAD_REQUEST)
//    public ResponseEntity<JsonError> handleAllExceptions(Exception ex) {
//    	return new ResponseEntity<JsonError>(new JsonError(ex.getMessage()), HttpStatus.BAD_REQUEST);
//    }
    
//    @ExceptionHandler (Exception.class)
//    public ResponseEntity<String> handleAllExceptions(Exception ex) {
//    	return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//    }
}
