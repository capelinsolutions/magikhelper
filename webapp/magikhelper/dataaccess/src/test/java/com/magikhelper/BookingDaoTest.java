package com.magikhelper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.magikhelper.dao.BookingDao;
import com.magikhelper.entities.Booking;

public class BookingDaoTest extends AbstractDaoTest {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource(name = BookingDao.name)
    private BookingDao bookingDao;

    @Test
    public void testBookings() {
    	/*List<String> columnNames = new ArrayList<String>();
    	List<String> values = new ArrayList<String>();
//    	columnNames.add("bookingId");
//    	values.add("2");
    	
    	columnNames.add("status");
    	values.add("active");
    	
    	columnNames.add("userId");
    	values.add("4");
    	
    	columnNames.add("email");
    	values.add("client2@hotmail.com");

    	columnNames.add("date");
    	values.add("02/07/2015");
    	String dateOperator = ">";

    	List<Booking> bookings = bookingDao.getBookings(columnNames,values,dateOperator);
    	log.debug(bookings);*/
    }
    
    @Test
    public void testClientBookings() {
    	List<Object[]> clients = bookingDao.getClientBookings(null,null,null);
    	for (Object[] data : clients) {
    		String row = "";
    		for (Object obj : data){
    			row += obj + ", ";
    		}
    		log.debug(row);
		}
    }
}
