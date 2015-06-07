/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.VendorBookingRouteDao;
import com.magikhelper.entities.VendorBookingRoute;

@Repository(VendorBookingRouteDao.name)
public class VendorBookingRouteJpaImpl extends GenericDaoJpaImpl<VendorBookingRoute, Integer> implements VendorBookingRouteDao {

    private static final Log log = LogFactory.getLog(VendorBookingRouteJpaImpl.class);

    public VendorBookingRouteJpaImpl() {
        super(VendorBookingRoute.class);
    }

	@Override
	public List<Object[]> getRoute(Integer bookingId) {
		StringBuilder qryStr=new StringBuilder();
		qryStr.append("SELECT ");
		qryStr.append("	    booking_id, vendor_id, longitude, latitude,  ");
		qryStr.append("	    c.first_name, c.last_name  ");
		qryStr.append("	FROM  ");
		qryStr.append("	    vendor_booking_route a,  ");
		qryStr.append("	    user b,  ");
		qryStr.append("	    contact c  ");
		qryStr.append("	WHERE a.vendor_id = b.row_id  ");
		qryStr.append("	AND b.row_id = c.row_id  ");
		qryStr.append("	AND a.booking_id = :booking_id ");
		
		Query query = entityManager.createNativeQuery(qryStr.toString());
		query.setParameter("booking_id", bookingId);
		return query.getResultList();
	}
}
