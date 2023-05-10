package com.mizuiro.air.dao;

import java.util.List;

import com.mizuiro.air.model.Customer;
import com.mizuiro.air.model.Flight;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateful
public class FlightEntityManager {
	@PersistenceContext(unitName = "airdb")
	EntityManager entityManager;
	
	public void insertFlight(Flight flight) {
		entityManager.persist(flight);
	}

	public List<Flight> getAllFlights() {
		Query query = entityManager.createQuery("SELECT f FROM flight f");
		return query.getResultList();
	}

	public Flight getFlight(Integer id) {
		return entityManager.find(Flight.class, id);
	}
	
	public void updateFlight(Flight flight) {
		entityManager.merge(flight);
	}
	
	public void deleteFlight(Flight flight) {
		entityManager.remove(flight);
	}
}