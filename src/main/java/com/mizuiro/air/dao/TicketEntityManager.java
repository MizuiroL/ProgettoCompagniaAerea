package com.mizuiro.air.dao;

import java.util.List;

import com.mizuiro.air.model.Flight;
import com.mizuiro.air.model.Ticket;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateful
public class TicketEntityManager {
	@PersistenceContext(unitName = "airdb")
	EntityManager entityManager;
	
	public void insertTicket(Ticket ticket) {
		entityManager.persist(ticket);
	}

	public List<Ticket> getAllTickets() {
		Query query = entityManager.createQuery("SELECT t FROM ticket t");
		return query.getResultList();
	}

	public Ticket getTicket(Integer id) {
		return entityManager.find(Ticket.class, id);
	}
	
	public void updateTicket(Ticket ticket) {
		entityManager.merge(ticket);
	}
	
	public void delete(Ticket ticket) {
		entityManager.remove(ticket);
	}
}
