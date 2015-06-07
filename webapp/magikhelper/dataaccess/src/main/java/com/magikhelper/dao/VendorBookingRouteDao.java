/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao;

import java.util.List;

import com.magikhelper.entities.VendorBookingRoute;

public interface VendorBookingRouteDao extends GenericDao<VendorBookingRoute, Integer> {
    public static final String name = "vendorBookingRouteDao";
    
    public List<Object[]> getRoute(Integer bookingId);
}
