package com.magikhelper;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.magikhelper.dao.BookingDao;
import com.magikhelper.dao.UsersDao;
import com.magikhelper.dao.VendorBookingRouteDao;
import com.magikhelper.entities.RouteKey;
import com.magikhelper.entities.VendorBookingRoute;

public class VendorBookingRouteDaoTest extends AbstractDaoTest {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource(name = VendorBookingRouteDao.name)
    private VendorBookingRouteDao vendorBookingRouteDao;

    @Resource(name =  UsersDao.name)
    private UsersDao usersDao;

    @Resource(name =  BookingDao.name)
    private BookingDao bookingDao;

    @Test
    public void upsertRoute() {
    	VendorBookingRoute route = new VendorBookingRoute();
    	RouteKey routeKey = new RouteKey(1,4);
    	route.setRouteKey(routeKey);
    	route.setLatitude("1");
    	route.setLongitude("2");
    	
//    	vendorBookingRouteDao.add(route);
    }
}
