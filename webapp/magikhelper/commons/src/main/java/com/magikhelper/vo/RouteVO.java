package com.magikhelper.vo;

public class RouteVO {
	private Integer vendorId;
	private Integer bookingId;
	private String latitude;
	private String longitude;
	private String vendorFirstName;
	private String vendorLastName;
	
	public RouteVO() {
	}
	public RouteVO(Integer vendorId, Integer bookingId, String latitude,
			String longitude) {
		this.vendorId = vendorId;
		this.bookingId = bookingId;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getVendorFirstName() {
		return vendorFirstName;
	}
	public void setVendorFirstName(String vendorFirstName) {
		this.vendorFirstName = vendorFirstName;
	}
	public String getVendorLastName() {
		return vendorLastName;
	}
	public void setVendorLastName(String vendorLastName) {
		this.vendorLastName = vendorLastName;
	}
	@Override
	public String toString() {
		return "RouteVO [vendorId=" + vendorId + ", bookingId=" + bookingId
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", vendorFirstName=" + vendorFirstName + ", vendorLastName="
				+ vendorLastName + "]";
	}
	
}
