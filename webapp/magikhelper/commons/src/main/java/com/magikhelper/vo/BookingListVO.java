package com.magikhelper.vo;

import java.math.BigDecimal;

public class BookingListVO {
	private Integer bookingId;
	private Integer serviceId;
	private String bookedDate;
	private String bookedTime;
	private int duration;
	private String startDateTime;
	private String finishDateTime;
	private String status;
	private String statusDesc;
	private String serviceName;
	private BigDecimal rate;
	private String bookingComments;
	private int clientId;
	private Integer vendorId;
	private ContactVO bookingContact = new ContactVO();
	private ContactVO vendorContact = new ContactVO();
	private ContactVO clientContact = new ContactVO();
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getBookingComments() {
		return bookingComments;
	}
	public void setBookingComments(String bookingComments) {
		this.bookingComments = bookingComments;
	}
	public ContactVO getBookingContact() {
		return bookingContact;
	}
	public void setBookingContact(ContactVO bookingContact) {
		this.bookingContact = bookingContact;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public ContactVO getVendorContact() {
		return vendorContact;
	}
	public void setVendorContact(ContactVO vendorContact) {
		this.vendorContact = vendorContact;
	}
	public ContactVO getClientContact() {
		return clientContact;
	}
	public void setClientContact(ContactVO clientContact) {
		this.clientContact = clientContact;
	}
	@Override
	public String toString() {
		return "\nBookingListVO [bookingId=" + bookingId + ", serviceId="
				+ serviceId + ", bookedDate=" + bookedDate + ", bookedTime="
				+ bookedTime + ", duration=" + duration + ", startDateTime="
				+ startDateTime + ", finishDateTime=" + finishDateTime
				+ ", status=" + status + ", statusDesc=" + statusDesc
				+ ", serviceName=" + serviceName + ", rate=" + rate
				+ ", bookingComments=" + bookingComments + ", clientId="
				+ clientId + ", vendorId=" + vendorId + ", \n\t bookingContact="
				+ bookingContact + ",\n\t vendorContact=" + vendorContact
				+ ",\n\t clientContact=" + clientContact + "]";
	}
	
}
