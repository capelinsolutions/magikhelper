package com.magikhelper.vo;

import java.math.BigDecimal;

public class BookingListVO {
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
	private int clientId;
	private String email;
	private String firstName;
	private String lastName;
	private String mobilePhone;
	private String street;
	private String additional;
	private String city;
	private String zip;
	private String state;	
	private String country;
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
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAdditional() {
		return additional;
	}
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "\nBookingListVO [bookingId=" + bookingId + ", bookedDate="
				+ bookedDate + ", bookedTime=" + bookedTime + ", duration="
				+ duration + ", startDateTime=" + startDateTime
				+ ", finishDateTime=" + finishDateTime + ", status=" + status
				+ ", statusDesc=" + statusDesc + ", serviceName=" + serviceName
				+ ", rate=" + rate + ", clientId=" + clientId + ", email="
				+ email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobilePhone=" + mobilePhone + ", street=" + street
				+ ", additional=" + additional + ", city=" + city + ", zip="
				+ zip + ", state=" + state + ", country=" + country + "]";
	}
	
}
