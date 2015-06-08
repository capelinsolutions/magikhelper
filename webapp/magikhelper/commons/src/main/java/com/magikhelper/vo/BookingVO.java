package com.magikhelper.vo;

import java.math.BigDecimal;


public class BookingVO {
	private Integer bookingId;
	private String bookedDate;
	private String bookedTime;
	private String startDateTime;
	private String finishDateTime;
	private int duration;
	private BigDecimal rate;
	private String comments;
	private Integer clientId;
	private Integer vendorId;
	private Integer serviceId;
	private Integer statusId;
	private String statusDesc;
	private ContactVO bookingContact = new ContactVO();
	
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
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public ContactVO getBookingContact() {
		return bookingContact;
	}
	public void setBookingContact(ContactVO bookingContact) {
		this.bookingContact = bookingContact;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getFinishDateTime() {
		return finishDateTime;
	}
	public void setFinishDateTime(String finishDateTime) {
		this.finishDateTime = finishDateTime;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
}
