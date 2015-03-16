package com.magikhelper.vo;

import java.math.BigDecimal;

public class ClientBookingsVO {
	private Integer bookingId;
	private String bookedDate;
	private String bookedTime;
	private int duration;
	private String startDateTime;
	private String finishDateTime;
	private String status;
	private String statusDesc;
	private String serviceName;
	private BigDecimal rate;
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
	@Override
	public String toString() {
		return "\nClientBookingsVO [bookingId=" + bookingId + ", bookedDate="
				+ bookedDate + ", bookedTime=" + bookedTime + ", duration="
				+ duration + ", startDateTime=" + startDateTime
				+ ", finishDateTime=" + finishDateTime + ", status=" + status
				+ ", statusDesc=" + statusDesc + ", serviceName=" + serviceName
				+ ", rate=" + rate + "]";
	}
	
}
