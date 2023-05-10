package com.mizuiro.air.model;

import java.util.List;

import com.mizuiro.air.exception.BookingFailedException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "customer")
	private List<Ticket> tickets;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
	private Wallet wallet;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public Boolean bookFlight(Flight flight) throws BookingFailedException {
		if (!wallet.charge(flight.getPrice())) {
			// Not enough funds
			throw new BookingFailedException("Not enough funds");
		}
		return flight.bookSeat();
	}
	
	public void addTicket(Ticket ticket) {
		if(!tickets.contains(ticket)) {
			tickets.add(ticket);
		}
	}
	
	public void removeTicket(Ticket ticket) {
		if(tickets.contains(ticket)) {
			tickets.remove(ticket);
		}
	}
}
