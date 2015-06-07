package com.magikhelper.vo;


public class BookingVO {
	private Integer bookingId;
	private String bookedDate;
	private String bookedTime;
	private int duration;
	private Integer clientId;
	private Integer serviceId;

	public BookingVO() {
	}
	public BookingVO(Integer bookingId, String bookedDate, String bookedTime, int duration) {
		this.bookingId = bookingId;
		this.bookedDate = bookedDate;
		this.bookedTime = bookedTime;
		this.duration = duration;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getBookedTime() {
		return bookedTime;
	}
	public void setBookedTime(String bookedTime) {
		this.bookedTime = bookedTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
}
