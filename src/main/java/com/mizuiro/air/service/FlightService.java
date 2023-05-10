package com.mizuiro.air.service;

import java.util.List;

import com.mizuiro.air.dao.FlightEntityManager;
import com.mizuiro.air.model.Flight;

import jakarta.ejb.EJB;
import jakarta.persistence.Query;

public class FlightService {
	@EJB
	FlightEntityManager flightEntityManager;
	
	public void insertFlight(Flight flight) {
		flightEntityManager.insertFlight(flight);
	}

	public List<Flight> getAllFlights() {
		return flightEntityManager.getAllFlights();
	}

	public Flight getFlight(Integer id) {
		return flightEntityManager.getFlight(id);
	}
	
	public void updateFlight(Flight flight) {
		flightEntityManager.updateFlight(flight);
	}
}
