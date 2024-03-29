package pl.java.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.services.CustomerService;

@WebServlet("/deleteCustomer")
public class DeleteCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerService customerService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/hidden-views/delete-item.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pesel = request.getParameter("itemToDelete");
		if (pesel.isEmpty()) {
			response.sendRedirect("printCustomers");
		}else {
			customerService.deleteCustomerByPesel(pesel);
			final String operation = "Deleting customer";
			request.setAttribute("operation", operation);
			final String formAction = "printCustomers";
			request.setAttribute("formAction", formAction);
			request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
		}
	}

}
