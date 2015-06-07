package com.magikhelper.services;

import com.magikhelper.vo.RouteVO;


public interface VendorBookingRouteService {
	public void saveRoute(RouteVO route);
	public RouteVO getRoute(Integer id);
}
