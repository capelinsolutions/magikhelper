package com.magikhelper.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.magikhelper.dao.VendorBookingRouteDao;
import com.magikhelper.entities.RouteKey;
import com.magikhelper.entities.VendorBookingRoute;
import com.magikhelper.services.VendorBookingRouteService;
import com.magikhelper.vo.RouteVO;

@Service(value = "vendorBookingRouteService")
public class VendorBookingRouteServiceImpl implements VendorBookingRouteService {

    private static final Log log = LogFactory.getLog(VendorBookingRouteServiceImpl.class);
    
    @Autowired
    VendorBookingRouteDao vendorBookingRouteDao;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void saveRoute(RouteVO vo) {
    	
    	Map<String, Object> queryParams = new HashMap<String, Object>();
    	queryParams.put("bookingId", vo.getBookingId());
    	List<VendorBookingRoute> existingRoute= vendorBookingRouteDao.findByNamedQuery("byBookingId", queryParams);
    	
    	if(existingRoute ==null || existingRoute.size()==0){
        	VendorBookingRoute route = new VendorBookingRoute();
        	RouteKey routeKey = new RouteKey(vo.getBookingId(),vo.getVendorId());
        	route.setRouteKey(routeKey);
        	route.setLatitude(vo.getLatitude());
        	route.setLongitude(vo.getLongitude());
        	route.populatedAuditFields("SYSTEM");
    		vendorBookingRouteDao.add(route);    		
    	}
    	else {
    		VendorBookingRoute route =existingRoute.get(0);
        	route.setLatitude(vo.getLatitude());
        	route.setLongitude(vo.getLongitude());
        	route.populatedAuditFieldsOnUpdate("SYSTEM");
    		vendorBookingRouteDao.update(route);    		
    	}
		
	}

	@Override
	public RouteVO getRoute(Integer id) {
		RouteVO vo = new RouteVO();
		List<Object[]> dataArr = vendorBookingRouteDao.getRoute(id);
		for (Object[] obj : dataArr) {

			Integer bookingId = (Integer)obj[0];
			Integer vendorId = (Integer)obj[1];
			String longitude = (String)obj[2];
			String latitude = (String)obj[3];
			String firstName =  (String)obj[4];
			String lastName = (String)obj[5];
			
			vo.setBookingId(bookingId);
			vo.setVendorId(vendorId);
			vo.setLongitude(longitude);
			vo.setLatitude(latitude);
			vo.setVendorFirstName(firstName);
			vo.setVendorLastName(lastName);
		}
		
    	return vo;
	}

}
