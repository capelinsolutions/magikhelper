package com.magikhelper.vo;

import java.util.List;

public class Client {
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
	private String password;
	private List<ClientBookingsVO> bookings;
	
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

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public List<ClientBookingsVO> getBookings() {
		return bookings;
	}


	public void setBookings(List<ClientBookingsVO> bookings) {
		this.bookings = bookings;
	}


	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobilePhone=" + mobilePhone + ", street=" + street
				+ ", additional=" + additional + ", city=" + city + ", zip="
				+ zip + ", state=" + state + ", country=" + country
				+ ", password=" + password + ", bookings=" + bookings + "]\n";
	}

}
