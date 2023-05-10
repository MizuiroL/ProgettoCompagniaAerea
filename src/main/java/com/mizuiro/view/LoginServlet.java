package com.mizuiro.view;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.mizuiro.air.exception.UserNotFoundException;
import com.mizuiro.air.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	@EJB
	LoginService loginService;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer customerId = Integer.valueOf(request.getParameter("customer_id"));
		
		System.out.println("VisitorLoginServlet: id " + customerId);

		try {
			HttpSession session = loginService.login(customerId, request);
		} catch (UserNotFoundException e) {
			request.getRequestDispatcher("failed_login.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
