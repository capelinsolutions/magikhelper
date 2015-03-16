package com.magikhelper.dao;

import java.util.List;

import com.magikhelper.entities.Booking;

public interface BookingDao extends GenericDao<Booking, Integer> {
    public static final String name = "bookingDao";

    public List<Booking> getBookings(List<String> columnNames, List<String> values, String dateOperator);
    
    public List<Object[]> getClientBookings(List<String> columnNames, List<String> values, String dateOperator);
}
