/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.BookingDao;
import com.magikhelper.entities.Booking;
import com.magikhelper.utils.DateUtils;

@Repository(BookingDao.name)
public class BookingDaoJpaImpl extends GenericDaoJpaImpl<Booking, Integer> implements BookingDao {

    private static final Log log = LogFactory.getLog(BookingDaoJpaImpl.class);

    public BookingDaoJpaImpl() {
        super(Booking.class);
    }

	@Override
	public List<Booking> getBookings(List<String> columnNames, List<String> values, String dateOperator) {
    	
		StringBuilder qryStr = new StringBuilder();
//		qryStr.append("select bookings.rowId, bookings.bookedDatetime, bookings.duration, bookings.startDatetime, bookings.finishDatetime, bookings.statusDesc, bookings.address,  ");
//		qryStr.append(" status.name, services.name,  from Booking bookings ");
		qryStr.append("select bookings from Booking bookings ");
		qryStr.append("inner join fetch bookings.service services ");
		qryStr.append("inner join fetch bookings.status status ");
		qryStr.append("inner join fetch services.services rates ");
		qryStr.append("where bookings.user.active ='1' ");
		
		if (columnNames != null && columnNames.size()>0 && values != null && values.size()>0){
			for (int i=0; i<columnNames.size(); i++){
				if (columnNames.get(i).equalsIgnoreCase("bookingId")){
					qryStr.append("        AND bookings.rowId = :bookingId ");
				}
				if (columnNames.get(i).equalsIgnoreCase("status")){
					qryStr.append("        AND lower(status.name) = :status ");
				}
				if (columnNames.get(i).equalsIgnoreCase("userId")){
					qryStr.append("        AND bookings.user.rowId = :userId ");
				}
				if (columnNames.get(i).equalsIgnoreCase("email")){
					qryStr.append("        AND bookings.user.email = :email ");
				}
				if (columnNames.get(i).equalsIgnoreCase("date")){
					qryStr.append("        AND DATE_FORMAT(bookings.bookedDatetime,'%m/%d/%Y') "+dateOperator+" :dateTime ");
				}
			}
		}
		Query query = entityManager.createQuery(qryStr.toString());
		if (columnNames != null && columnNames.size()>0 && values != null && values.size()>0){
			for (int i=0; i<values.size(); i++){
				if (columnNames.get(i).equalsIgnoreCase("bookingId")){
					query.setParameter("bookingId", new Integer(values.get(i)));
				}
				if (columnNames.get(i).equalsIgnoreCase("status")){
					query.setParameter("status", values.get(i).toLowerCase());
				}
				if (columnNames.get(i).equalsIgnoreCase("userId")){
					query.setParameter("userId", new Integer(values.get(i)));
				}
				if (columnNames.get(i).equalsIgnoreCase("email")){
					query.setParameter("email", values.get(i)).toString();
				}
				if (columnNames.get(i).equalsIgnoreCase("date")){
//					query.setParameter("dateTime", DateUtils.convertToDate(values.get(i), "MM/dd/yyyy"));
					query.setParameter("dateTime", values.get(i));
				}
			}
		}
		List<Booking> bookings = query.getResultList();
		return bookings;
	}

    @Override
    public List<Object[]> getClientBookings(List<String> columnNames, List<String> values, String dateOperator){
    	StringBuilder qryStr = new StringBuilder();
    	qryStr.append("SELECT ");
		qryStr.append("    users.row_id user_id, email, first_name, last_name,");
		qryStr.append("    mobile_phone, street, additional, city, zip, state, country,");
		qryStr.append("    bookings.row_id booking_id, booked_datetime, duration, start_datetime, finish_datetime, status.name status, status_desc,");
		qryStr.append("    services.name service, rate ");
		qryStr.append("FROM ");
		qryStr.append("    system_role roles, ");
		qryStr.append("    user_role user_roles, ");
		qryStr.append("    user users, ");
		qryStr.append("    contact contact, ");
		qryStr.append("    booking bookings, ");
		qryStr.append("    application_properties services, ");
		qryStr.append("    application_properties status, ");
		qryStr.append("    services rates ");
		qryStr.append("WHERE ");
		qryStr.append("    roles.role_id = user_roles.role_id ");
		qryStr.append("        AND user_roles.user_id = users.row_id ");
		qryStr.append("        AND users.address_id = contact.row_id ");
		qryStr.append("        AND users.row_id = bookings.client_id ");
		qryStr.append("        AND bookings.service_id = services.property_id ");
		qryStr.append("        AND bookings.status_id = status.property_id ");
		qryStr.append("        AND services.property_id = rates.row_id ");
		qryStr.append("        AND roles.title = 'CLIENT_ROLE' ");
		qryStr.append("        AND roles.is_active = '1' ");
		qryStr.append("        AND users.is_active = '1' ");
		qryStr.append("        AND bookings.is_active = '1' ");
		qryStr.append("        AND services.is_active = '1' ");
		qryStr.append("        AND rates.is_active = '1' ");
		if (columnNames != null && columnNames.size()>0 && values != null && values.size()>0){
			for (int i=0; i<columnNames.size(); i++){
				if (columnNames.get(i).equalsIgnoreCase("user_id")){
					qryStr.append("        AND users.row_id = :userId ");
				}
				if (columnNames.get(i).equalsIgnoreCase("email")){
					qryStr.append("        AND lower(users.email) = :email ");
				}
				if (columnNames.get(i).equalsIgnoreCase("status")){
					qryStr.append("        AND lower(status.name) = :status ");
				}
				if (columnNames.get(i).equalsIgnoreCase("date")){
					qryStr.append("        AND DATE_FORMAT(`booked_datetime`,'%m/%d/%Y') "+dateOperator+" :dateTime ");
				}
			}
		}
		Query query = entityManager.createNativeQuery(qryStr.toString());
		if (columnNames != null && columnNames.size()>0 && values != null && values.size()>0){
			for (int i=0; i<values.size(); i++){
				if (columnNames.get(i).equalsIgnoreCase("user_id")){
					query.setParameter("userId", new Integer(values.get(i)));
				}
				if (columnNames.get(i).equalsIgnoreCase("email")){
					query.setParameter("email", values.get(i).toLowerCase());
				}
				if (columnNames.get(i).equalsIgnoreCase("status")){
					query.setParameter("status", values.get(i).toLowerCase());
				}
				if (columnNames.get(i).equalsIgnoreCase("date")){
					query.setParameter("dateTime", values.get(i));
				}
			}
		}
		return query.getResultList();

    }
    
    
}
