package com.mizuiro.air.dao;

import java.util.List;

import com.mizuiro.air.exception.UserNotFoundException;
import com.mizuiro.air.model.Customer;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateful
public class CustomerEntityManager {
	@PersistenceContext(unitName = "airdb")
	EntityManager entityManager;
	
	public void insertCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	public List<Customer> getAllCustomers() {
		Query query = entityManager.createQuery("SELECT c FROM customer c");
		return query.getResultList();
	}

	public Customer getCustomer(Integer id) throws UserNotFoundException {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer == null) {
			throw new UserNotFoundException("Id non valido");
		}
		return customer;
	}
	
	public void updateCustomer(Customer customer) {
		entityManager.merge(customer);
	}
	
	public void deleteCustomer(Customer customer) {
		entityManager.remove(customer);
	}
}
