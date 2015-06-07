package com.magikhelper.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private BigDecimal rate;
	private User user;
	private Contact contact;
	private ApplicationProperty status;
	private ApplicationProperty service;
	private List<BookingAssignment> bookingAssignments;
	private List<BookingEvent> bookingEvents;
	private List<BookingFeedback> bookingFeedbacks;

	
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
	
	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
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

	//bi-directional many-to-one association to Contact
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="contact_id")
	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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
	@OneToMany(mappedBy="booking", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<BookingAssignment> getBookingAssignments() {
		return this.bookingAssignments;
	}

	public void setBookingAssignments(List<BookingAssignment> bookingAssignments) {
		this.bookingAssignments = bookingAssignments;
	}

	//bi-directional many-to-one association to BookingEvent
	@OneToMany(mappedBy="booking", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<BookingEvent> getBookingEvents() {
		return this.bookingEvents;
	}

	public void setBookingEvents(List<BookingEvent> bookingEvents) {
		this.bookingEvents = bookingEvents;
	}

	//bi-directional many-to-one association to BookingFeedback
	@OneToMany(mappedBy="booking", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<BookingFeedback> getBookingFeedbacks() {
		return this.bookingFeedbacks;
	}

	public void setBookingFeedbacks(List<BookingFeedback> bookingFeedbacks) {
		this.bookingFeedbacks = bookingFeedbacks;
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