package com.mizuiro.air.service;

import com.mizuiro.air.dao.TicketEntityManager;
import com.mizuiro.air.exception.BookingFailedException;
import com.mizuiro.air.exception.TicketNotValidException;
import com.mizuiro.air.model.Customer;
import com.mizuiro.air.model.Flight;
import com.mizuiro.air.model.Ticket;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

// Potrei anche fare questo bean stateful avendo come stato il customer che fa il booking
@Stateless
public class BookingService {
	@EJB
	TicketEntityManager ticketEntityManager;

	public Ticket bookFlight(Flight flight, Customer customer) throws BookingFailedException {
		Ticket ticket = new Ticket();
		customer.bookFlight(flight); // Solleva un'eccezione nel caso di booking fallito
		ticket.setCustomer(customer);
		ticket.setFlight(flight);
		ticketEntityManager.insertTicket(ticket);
		return ticket;
	}

	public void cancelBooking(Ticket ticket) throws TicketNotValidException {
		Flight flight = ticket.getFlight();
		if (!flight.getTickets().contains(ticket)) {
			throw new TicketNotValidException("Your ticket is not valid for this flight");
		}
		flight.unbookSeat();

		flight.removeTicket(ticket);
		ticket.getCustomer().removeTicket(ticket);

		ticketEntityManager.delete(ticket);
	}
}
