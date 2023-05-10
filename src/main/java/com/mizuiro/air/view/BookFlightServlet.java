package com.mizuiro.air.view;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.mizuiro.air.exception.BookingFailedException;
import com.mizuiro.air.model.Customer;
import com.mizuiro.air.model.Flight;
import com.mizuiro.air.model.Ticket;
import com.mizuiro.air.service.BookingService;
import com.mizuiro.air.service.FlightService;

/**
 * Servlet implementation class BookFlightServlet
 */
public class BookFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FlightService flightService;
	@EJB
	private BookingService bookingService;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		Customer customer = (Customer) session.getAttribute("customer");
		Flight flight = flightService.getFlight((Integer) request.getAttribute("flight_id")); 
		Ticket ticket;
		try {
			ticket = bookingService.bookFlight(flight, customer);
			session.setAttribute("ticket", ticket);
			request.getRequestDispatcher("/flight_booked.jsp").forward(request, response);
		} catch(BookingFailedException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/failed_booking.jsp").forward(request, response);
		}

	}

}
