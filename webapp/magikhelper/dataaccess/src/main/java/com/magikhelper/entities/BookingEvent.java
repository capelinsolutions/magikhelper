package com.magikhelper.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the booking_event database table.
 * 
 */
@Entity
@Table(name="booking_event")
@NamedQueries({
	@NamedQuery(name="BookingEvent.findAll", query="SELECT b FROM BookingEvent b")
})
public class BookingEvent extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rowId;
	private String comments;
	private ApplicationProperty status;
	private Booking booking;

	public BookingEvent() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_id")
	public int getRowId() {
		return this.rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}


	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	//bi-directional many-to-one association to ApplicationProperty
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="status_id")
	public ApplicationProperty getStatus() {
		return this.status;
	}

	public void setStatus(ApplicationProperty status) {
		this.status = status;
	}


	//bi-directional many-to-one association to Booking
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="booking_id")
	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}