package com.magikhelper.services;

import java.util.List;

import com.magikhelper.vo.BookingFeedbackVO;
import com.magikhelper.vo.BookingListVO;
import com.magikhelper.vo.BookingVO;
import com.magikhelper.vo.UserVO;



public interface BookingService {
	public void createBooking(BookingVO booking);
	
	public void updateBooking(BookingVO vo);

	public void assignToVendor(BookingVO booking);
	
	public void addFeedback(BookingFeedbackVO vo);
	
    public List<UserVO> getClientBookings(List<String> columnNames, List<String> values, String dateOperator);

	public List<BookingListVO> getBookings(List<String> columnNames, List<String> values, String dateOperator);
	
}
