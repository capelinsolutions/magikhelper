package com.magikhelper.vo;

public class BookingFeedbackVO {
	private Integer bookingId;
	private String comments;
	
	public BookingFeedbackVO() {

	}
	public BookingFeedbackVO(Integer bookingId, String comments) {
		this.bookingId = bookingId;
		this.comments = comments;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
