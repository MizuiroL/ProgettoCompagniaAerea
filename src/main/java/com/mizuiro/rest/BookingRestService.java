package com.mizuiro.rest;

import com.mizuiro.air.exception.BookingFailedException;
import com.mizuiro.air.exception.TicketNotValidException;
import com.mizuiro.air.model.Customer;
import com.mizuiro.air.model.Flight;
import com.mizuiro.air.model.Ticket;
import com.mizuiro.air.service.BookingService;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/air/api/booking")
public class BookingRestService {
	@EJB
	private BookingService bookingService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/book")
	public Response bookFlight(Flight flight, Customer customer) {
		Ticket ticket;
		try {
			ticket = bookingService.bookFlight(flight, customer);
			return Response.ok(ticket).build();
		} catch (BookingFailedException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cancel")
	public Response cancelBooking(Ticket ticket) {
		try {
			bookingService.cancelBooking(ticket);
			return Response.ok().build();
		} catch (TicketNotValidException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
