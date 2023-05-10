package com.mizuiro.view;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.mizuiro.air.dao.FlightEntityManager;
import com.mizuiro.air.model.Flight;
import com.mizuiro.air.service.FlightService;

/**
 * Servlet implementation class AvailableFlightsServlet
 */
public class AvailableFlightsServlet extends HttpServlet {
	@EJB
	FlightService flightService;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// TODO: query che ritorna flight con departureDate after now()
		List<Flight> flights = flightService.getAllFlights();
		
		request.setAttribute("flights", flights);
		// Ancora non esiste
		request.getRequestDispatcher("/available_flights.jsp").forward(request, response);
	}

}
