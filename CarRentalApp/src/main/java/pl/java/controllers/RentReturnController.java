package pl.java.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.java.controllers.enums.ControllerAction;
import pl.java.exceptions.NoSuchActionException;
import pl.java.services.CarService;

@WebServlet("/rentReturnCar")
public class RentReturnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarService carService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/hidden-views/rent-return-car.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String controllerActionDescription = request.getParameter("controllerAction");
			ControllerAction controllerAction = ControllerAction.getFromDescription(controllerActionDescription);
			HttpSession session = request.getSession(true);
			String operation = null;
			switch (controllerAction) {
			case SELECT_CUSTOMER:			
				selectCustomer(request, response, session);
				return;
			case SELECT_DATE:
				selectDateRange(request, response, session);
				return;
			case RENT:
				rentCar(session, request);
				operation = "Renting car";
				break;
			case RETURN:
				returnCar(request);
				operation = "Returning car";
				break;
			default:
				throw new NoSuchActionException("There is no action:" + controllerAction);
			}
			request.setAttribute("operation", operation);
			request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
		}catch(NoSuchActionException | NullPointerException ex) {
			ex.printStackTrace();
		}
 	}
	
	private void selectCustomer(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		request.setAttribute("controllerAction", ControllerAction.SELECT_CUSTOMER);
		String  startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		session.setAttribute("startDate", startDate);
		session.setAttribute("endDate", endDate);
		request.getRequestDispatcher("printCustomers").forward(request, response);
	}
	
	private void selectDateRange(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		String registrationNumber = request.getParameter("registrationNumber");
		session.setAttribute("registrationNumber", registrationNumber);
		request.getRequestDispatcher("/WEB-INF/hidden-views/date-range.jsp").forward(request, response);
	}
	
	private void rentCar(HttpSession session, HttpServletRequest request) {
		String registrationNumber = (String)session.getAttribute("registrationNumber");
		String startDate = (String)session.getAttribute("startDate");
		String endDate = (String)session.getAttribute("endDate");
		String customerPesel = request.getParameter("customerPesel");
		carService.rentCar(registrationNumber, startDate, endDate, customerPesel);	
	}
	
	private void returnCar(HttpServletRequest request) {
		String registrationNumber = request.getParameter("registrationNumber");
		carService.returnCar(registrationNumber);
	}
}
