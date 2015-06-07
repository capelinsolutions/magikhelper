package com.magikhelper.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RouteKey implements Serializable{
	
    private int bookingId;
    private int vendorId;

	public RouteKey(int bookingId, int vendorId) {
		this.bookingId = bookingId;
		this.vendorId = vendorId;
	}

	public RouteKey() {
	}


	@Column(name = "booking_id", nullable = false)
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

    @Column(name = "vendor_id", nullable = false)
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingId;
		result = prime * result + vendorId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RouteKey other = (RouteKey) obj;
		if (bookingId != other.bookingId)
			return false;
		if (vendorId != other.vendorId)
			return false;
		return true;
	}
    
    
}
