package com.mizuiro.air.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.mizuiro.air.exception.BookingFailedException;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Flight {
	@Id
	@Column(name = "flight_id")
	private Integer flightId;
	@Column(name = "available_seats")
	private Integer availableSeats;
	@Column(name = "price")
	private Double price;
	// Gli airport sono stringhe per semplificazione
	@Column(name = "departure_airport")
	private String departureAirport;
	@Column(name = "arrival_airport")
	private String arrivalAirport;
	@Column(name = "departure_date")
	private LocalDate departureDate;
	@Column(name = "departure_time")
	private LocalTime departureTime;
	@Column(name = "arrival_date")
	private LocalDate arrivalDate;
	@Column(name = "arrival_time")
	private LocalTime arrivalTime;
	@OneToMany(mappedBy = "flight")
	private List<Ticket> tickets;
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public Boolean bookSeat() throws BookingFailedException {
		if (availableSeats <= 0) {
			throw new BookingFailedException("Not enough seats");
		}
		availableSeats -= 1;
		return true;
	}
	
	public void unbookSeat() {
		availableSeats += 1;
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
