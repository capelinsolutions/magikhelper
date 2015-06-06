package com.magikhelper.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@Table(name="booking")
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rowId;
	private Date bookedDatetime;
	private int duration;
	private Date finishDatetime;
	private Date startDatetime;
	private String statusDesc;
	private String address;
	private User user;
	private ApplicationProperty status;
	private ApplicationProperty service;
	private List<BookingAssignment> bookingAssignments;

	public Booking() {
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


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="booked_datetime")
	public Date getBookedDatetime() {
		return this.bookedDatetime;
	}

	public void setBookedDatetime(Date bookedDatetime) {
		this.bookedDatetime = bookedDatetime;
	}


	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="finish_datetime")
	public Date getFinishDatetime() {
		return this.finishDatetime;
	}

	public void setFinishDatetime(Date finishDatetime) {
		this.finishDatetime = finishDatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_datetime")
	public Date getStartDatetime() {
		return this.startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	@Column(name="status_desc")
	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	//bi-directional many-to-one association to User
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="client_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

	//bi-directional many-to-one association to ApplicationProperty
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="service_id")
	public ApplicationProperty getService() {
		return this.service;
	}

	public void setService(ApplicationProperty service) {
		this.service = service;
	}

	//bi-directional many-to-one association to BookingAssignment
	@OneToMany(mappedBy="booking")
	public List<BookingAssignment> getBookingAssignments() {
		return this.bookingAssignments;
	}

	public void setBookingAssignments(List<BookingAssignment> bookingAssignments) {
		this.bookingAssignments = bookingAssignments;
	}


	@Override
	public String toString() {
		return "\nBooking [rowId=" + rowId + ", bookedDatetime=" + bookedDatetime
				+ ", duration=" + duration + ", finishDatetime="
				+ finishDatetime + ", startDatetime=" + startDatetime
				+ ", statusDesc=" + statusDesc + ", user=" + user + ", status="
				+ status + ", service=" + service + "]";
	}

	
}