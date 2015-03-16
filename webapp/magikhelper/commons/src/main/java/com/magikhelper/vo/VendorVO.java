package com.magikhelper.vo;

import java.util.List;

public class VendorVO {
	private Integer vendorId;
	private String email;
	private String firstName;
	private String lastName;
	private List<MagikHelperService> services;
	
	public VendorVO() {
	}
	
	public VendorVO(Integer vendorId, String email, String firstName,
			String lastName) {
		this.vendorId = vendorId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
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
	public List<MagikHelperService> getServices() {
		return services;
	}
	public void setServices(List<MagikHelperService> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", services=" + services + "]";
	}
	
}
