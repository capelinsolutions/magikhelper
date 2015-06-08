/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao;

import com.magikhelper.entities.BookingEvent;

public interface BookingEventDao extends GenericDao<BookingEvent, Integer> {
    public static final String name = "bookingEventDao";
    
    public BookingEvent getLastEventByBookingId(Integer id);
}
