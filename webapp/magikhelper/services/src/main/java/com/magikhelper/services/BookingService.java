package com.magikhelper.services;

import java.util.List;

import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;
import com.magikhelper.vo.Client;



public interface BookingService {
	public void createBooking(BookingVO booking);
	
    public List<Client> getClientBookings(List<String> columnNames, List<String> values, String dateOperator);

	public List<BookingListVO> getBookings(List<String> columnNames, List<String> values, String dateOperator);
}
