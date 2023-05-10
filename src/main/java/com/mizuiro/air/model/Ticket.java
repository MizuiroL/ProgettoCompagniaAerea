package com.mizuiro.air.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Ticket {
	@Id
	@Column(name = "ticket_id")
	private Integer ticketId;
	
	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
