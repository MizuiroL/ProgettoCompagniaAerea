package com.mizuiro.air.service;

import com.mizuiro.air.dao.CustomerEntityManager;
import com.mizuiro.air.exception.UserNotFoundException;
import com.mizuiro.air.model.Customer;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginService {
	@EJB
	private CustomerEntityManager customerEntityManager;
	
	public HttpSession login(Integer customerId, HttpServletRequest request) throws UserNotFoundException {
		Customer customer = customerEntityManager.getCustomer(customerId); // Throws UserNotFoundException
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		//session.setAttribute("loggedIn", Boolean.FALSE);
		return session;
	}
}
