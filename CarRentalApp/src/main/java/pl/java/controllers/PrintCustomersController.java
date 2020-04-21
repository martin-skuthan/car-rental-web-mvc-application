package pl.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.model.Car;
import pl.java.model.Customer;
import pl.java.services.CustomerService;

@WebServlet("/printCustomers")
public class PrintCustomersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerService customerService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int numberOfRecordsPerPage = 10;
		int numberOfCustomersRecords = customerService.readAllCustomers().size();
		request.setAttribute("numberOfCustomersRecords", numberOfCustomersRecords);
		int noOfPages = numberOfCustomersRecords / numberOfRecordsPerPage;
		int lastPage = numberOfCustomersRecords % numberOfRecordsPerPage;
		if (lastPage > 0) {
			noOfPages++;
		}
		request.setAttribute("noOfPages", noOfPages);
		int noOfPage = 1;
		if (request.getParameter("page") != null) {
			noOfPage = Integer.parseInt(request.getParameter("page"));
		}
		List<Customer> customers = customerService.readRangeOfCustomers(noOfPage, numberOfRecordsPerPage);
		request.setAttribute("noOfPage", noOfPage);
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("print-customers.jsp").forward(request, response);		
	}
}
