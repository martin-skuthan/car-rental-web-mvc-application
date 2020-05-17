package pl.java.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.dao.MySqlCustomerDao;
import pl.java.model.Customer;
import pl.java.services.CustomerService;

@WebServlet("/addCustomer")
public class AddCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private CustomerService userService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Warszawa wschodnia");
		request.setCharacterEncoding("UTF-8");
		String firstName = request.getParameter("inputFirstName");
		String lastName = request.getParameter("inputLastName");
		String pesel = request.getParameter("inputPesel");
		userService.createCustomer(firstName, lastName, pesel);
		System.out.println("Dziala");
	}

}
