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
	private int numberOfRecordsPerPage;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numberOfCustomersRecords = customerService.readAllCustomers().size();
		setNumberOfRecordsPerPage(request);
		request.setAttribute("numberOfCustomersRecords", numberOfCustomersRecords);
		int noOfPages = getNoOfPages(numberOfCustomersRecords, numberOfRecordsPerPage);
		request.setAttribute("noOfPages", noOfPages);
		int noOfPage = getNoOfPage(request);
		request.setAttribute("noOfPage", noOfPage);
		List<Customer> customers = customerService.readRangeOfCustomers(noOfPage, numberOfRecordsPerPage);
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("print-customers.jsp").forward(request, response);		
	}
	
	private void setNumberOfRecordsPerPage(HttpServletRequest request) {
		final int defaultNumberOfRecordsPerPate = 10;
		if(numberOfRecordsPerPage == 0) {
			numberOfRecordsPerPage = defaultNumberOfRecordsPerPate;
		}
		
		if(request.getParameter("count") != null) {
			numberOfRecordsPerPage = Integer.valueOf(request.getParameter("count"));
		}	
	}
	
	private int getNoOfPages(int numberOfCustomersRecords, int numberOfRecordsPerPage) {
		int noOfPages = numberOfCustomersRecords / numberOfRecordsPerPage;
		int lastPage = numberOfCustomersRecords % numberOfRecordsPerPage;
		if (lastPage > 0) {
			noOfPages++;
		}
		
		return noOfPages;
	}
	
	private int getNoOfPage(HttpServletRequest request) {
		int noOfPage = 1;
		if(request.getParameter("page") != null) {
			noOfPage = Integer.parseInt(request.getParameter("page"));
		}
		
		return noOfPage;
	}
}
