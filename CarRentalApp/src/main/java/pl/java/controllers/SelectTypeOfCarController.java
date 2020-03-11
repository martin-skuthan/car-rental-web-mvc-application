package pl.java.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.model.enums.TypeOfCar;

@WebServlet("/selectTypeOfCar")
public class SelectTypeOfCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeOfCarFromRequest = request.getParameter("typeOfCar");
		TypeOfCar typeOfCar = TypeOfCar.getFromDescription(typeOfCarFromRequest);
		switch (typeOfCar) {
		case PASSENGER_CAR:
			response.sendRedirect("new-passenger-car.jsp");
			break;
		case LIGHT_COMMERCIAL_CAR:
			response.sendRedirect("new-light-commercial-car.jsp");
			break;
		default:
			response.sendError(403);
		}
	}

}
