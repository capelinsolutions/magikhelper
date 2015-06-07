package com.magikhelper.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.vo.RouteVO;

public class VendorBookingRouteServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(VendorBookingRouteServiceTest.class);

    @Autowired
    private VendorBookingRouteService vendorBookingRouteService;

    public VendorBookingRouteServiceTest() {
    }

    @Test
    public void saveRoute() {
    	RouteVO vo = new RouteVO();
    	vo.setBookingId(new Integer(1));
    	vo.setVendorId(new Integer(4));
    	vo.setLatitude("1000");
    	vo.setLongitude("2000");
    	vendorBookingRouteService.saveRoute(vo);
    }
    
    @Test
    public void getRoute() {
    	RouteVO vo = vendorBookingRouteService.getRoute(new Integer(1));
    	System.out.println(vo);
    }
}
