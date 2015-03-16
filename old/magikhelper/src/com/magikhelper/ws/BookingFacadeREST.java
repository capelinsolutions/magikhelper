package com.magikhelper.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.magikhelper.dao.pojo.Booking;


@Path("/booking")
public class BookingFacadeREST extends AbstractREST<Booking> {

	public BookingFacadeREST() {
		super(Booking.class);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String AddBooking(Booking booking) {
		super.upsert(booking);
		return "Success";
	}
}
