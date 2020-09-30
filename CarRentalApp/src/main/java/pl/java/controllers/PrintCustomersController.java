package pl.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.java.comparators.enums.CustomerComparatorType;
import pl.java.controllers.enums.ControllerAction;
import pl.java.model.Customer;
import pl.java.services.CustomerService;

@WebServlet("/printCustomers")
public class PrintCustomersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerService customerService;
	private int numberOfRecordsPerPage;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int numberOfCustomersRecords = customerService.readAllCustomers().size();
		setNumberOfRecordsPerPage(request);
		ControllerAction controllerAction = getControllerAction(request);
		request.setAttribute("controllerAction", controllerAction);
		request.setAttribute("numberOfCustomersRecords", numberOfCustomersRecords);
		int noOfPages = getNoOfPages(numberOfCustomersRecords, numberOfRecordsPerPage);
		request.setAttribute("noOfPages", noOfPages);
		int noOfPage = getNoOfPage(request);
		request.setAttribute("noOfPage", noOfPage);
		CustomerComparatorType customerComparatorType = getComparatorTypeAndSaveInSession(request, session);
		List<Customer> customers = customerService.readRangeOfCustomers(noOfPage, numberOfRecordsPerPage, customerComparatorType);
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/WEB-INF/hidden-views/print-customers.jsp").forward(request, response);		
	}
	
	private ControllerAction getControllerAction(HttpServletRequest request) {
		ControllerAction controllerAction = ControllerAction.PRINT;
		if (request.getParameter("controllerAction") != null) {
			controllerAction = ControllerAction.getFromDescription(request.getParameter("controllerAction"));
		}
		
		return controllerAction;
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
	
	private CustomerComparatorType getComparatorTypeAndSaveInSession(HttpServletRequest request, HttpSession session) {
		String sortDescriptionFromSession = (String)session.getAttribute("sortDescription");
		String sortDescriptionFromRequest = request.getParameter("sortDescription");
		if (sortDescriptionFromSession == null || (sortDescriptionFromRequest != null && sortDescriptionFromSession != sortDescriptionFromRequest)) {
			session.setAttribute("sortDescription", sortDescriptionFromRequest);
		}
		
		CustomerComparatorType customerComparatorType = null;
		try {
			customerComparatorType = CustomerComparatorType.getFromDescription((String)session.getAttribute("sortDescription"));
		}catch (Exception ex) {
			ex.getMessage();
		}
		
		return customerComparatorType;
	}
}
