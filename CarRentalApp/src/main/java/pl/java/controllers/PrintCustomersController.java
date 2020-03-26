package pl.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.model.Customer;
import pl.java.services.CustomerService;

@WebServlet("/printCustomers")
public class PrintCustomersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerService customerService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Customer> customers = customerService.readAllCustomers();
		customers.forEach(System.out::println);
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("print-customers.jsp").forward(request, response);
	}
}
