package com.magikhelper.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the vendor_booking_route database table.
 * 
 */
@Entity
@Table(name="vendor_booking_route")
@NamedQueries({
	@NamedQuery(name="VendorBookingRoute.findAll", query="SELECT v FROM VendorBookingRoute v"),
	@NamedQuery(name="byBookingId", query="SELECT v FROM VendorBookingRoute v where v.routeKey.bookingId=:bookingId")
})
public class VendorBookingRoute extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String latitude;
	private String longitude;
    private RouteKey routeKey;
	
//	private Booking booking;
//	private User user;

	public VendorBookingRoute() {
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@EmbeddedId
	public RouteKey getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(RouteKey routeKey) {
		this.routeKey = routeKey;
	}

	/*
	//bi-directional many-to-one association to Booking
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name="booking_id")
	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	//bi-directional many-to-one association to User
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="vendor_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
}