package pl.java.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.controllers.enums.ControllerAction;
import pl.java.exceptions.NoSuchActionException;
import pl.java.model.Customer;
import pl.java.services.CustomerService;

@WebServlet("/updateCustomer")
public class UpdateCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerService customerService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String controllerActiondescription = request.getParameter("controllerAction");
			ControllerAction controllerAction = ControllerAction.getFromDescription(controllerActiondescription);
			switch (controllerAction) {
			case UPDATE:
				String pesel = request.getParameter("pesel");
				String firstName = request.getParameter("inputFirstName");
				String lastName = request.getParameter("inputLastName");
				customerService.updateCustomer(pesel, firstName, lastName);
				final String operation = "Updating customer";
				request.setAttribute("operation", operation);
				request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
				break;
			case FORWARD:
				forwardCarDataToFormInOrderToUpdate(request, response);
				break;
			default:
				throw new NoSuchActionException("There is no action:" + controllerAction);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void forwardCarDataToFormInOrderToUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pesel = request.getParameter("idOfItemToUpdate");
		Customer customer = customerService.readCustomerByPesel(pesel);
		request.setAttribute("customer", customer);
		request.setAttribute("controllerAction", ControllerAction.UPDATE);
		request.getRequestDispatcher("/WEB-INF/hidden-views/new-customer.jsp").forward(request, response);
	}
}
