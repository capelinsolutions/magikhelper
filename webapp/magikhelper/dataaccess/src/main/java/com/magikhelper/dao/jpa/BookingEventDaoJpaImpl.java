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

import com.magikhelper.dao.BookingEventDao;
import com.magikhelper.entities.BookingEvent;

@Repository(BookingEventDao.name)
public class BookingEventDaoJpaImpl extends GenericDaoJpaImpl<BookingEvent, Integer> implements BookingEventDao {

    private static final Log log = LogFactory.getLog(BookingEventDaoJpaImpl.class);

    public BookingEventDaoJpaImpl() {
        super(BookingEvent.class);
    }

    @Override
	public BookingEvent getLastEventByBookingId(Integer id) {
    	
		StringBuilder qryStr=new StringBuilder();
		BookingEvent event = new BookingEvent();
		qryStr.append("select row_id from booking_event where booking_id=:bookingId order by row_id desc limit 1");
		
		Query query = entityManager.createNativeQuery(qryStr.toString());
		query.setParameter("bookingId", id);
		List<Integer> data= query.getResultList();
    	
		if (data!=null && data.size()>0){
			Integer rowId = (Integer)data.get(0);
			event=get(rowId);
		}

		return event;
	}
}
