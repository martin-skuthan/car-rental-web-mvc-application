package pl.java.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.model.Car;
import pl.java.services.CarService;

@WebServlet("/deleteCar")
public class DeleteCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarService carService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String registrationNumber = request.getParameter("carToDelete");
		if (registrationNumber.isEmpty()) {
			response.sendRedirect("printCars");
		}else {
			carService.deleteCarByRegistrationNumber(registrationNumber);
			final String operation = "Deleting car";
			request.setAttribute("operation", operation);
			request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
		}	
	}

}
