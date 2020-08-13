package pl.java.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.controllers.enums.ControllerAction;
import pl.java.exceptions.ItemWithThisIdAlreadyExistsExcpetion;
import pl.java.model.Customer;
import pl.java.services.CustomerService;

@WebServlet("/addCustomer")
public class AddCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private CustomerService userService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/hidden-views/new-customer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = null;
		String lastName = null;
		String pesel = null;
		try {
			request.setCharacterEncoding("UTF-8");
			firstName = request.getParameter("inputFirstName");
			lastName = request.getParameter("inputLastName");
			pesel = request.getParameter("inputPesel");
			userService.createCustomer(firstName, lastName, pesel);
			final String operation = "Adding customer";
			request.setAttribute("operation", operation);
			final String formAction = "addCustomer";
			request.setAttribute("formAction", formAction);
			request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
		}catch (ItemWithThisIdAlreadyExistsExcpetion e) {
			request.setAttribute("controllerAction", ControllerAction.CORRECT);
			request.setAttribute("customer", new Customer(firstName, lastName, pesel));
			request.getRequestDispatcher("/WEB-INF/hidden-views/new-customer.jsp").forward(request, response);
		}		
	}

}
