package com.magikhelper.controllers.booking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.services.VendorBookingRouteService;
import com.magikhelper.vo.RouteVO;

/**
 *
 * @author aahmed
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    private static final Log log = LogFactory.getLog(RouteController.class);

    @Autowired
    private VendorBookingRouteService vendorBookingRouteService;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void saveRoute(@RequestBody RouteVO vo, HttpServletRequest request, HttpServletResponse response) {
    	vendorBookingRouteService.saveRoute(vo);
    }
    
    @RequestMapping(value="/{bookingId}", method = RequestMethod.GET,headers="Accept=application/json")
    public RouteVO getRoute(@PathVariable("bookingId") Integer bookingId) {
    	return vendorBookingRouteService.getRoute(bookingId);
    }
    
}
