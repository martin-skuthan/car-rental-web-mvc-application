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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeOfCarFromRequest = request.getParameter("typeOfCar");
		TypeOfCar typeOfCar = TypeOfCar.getFromDescription(typeOfCarFromRequest);
		switch (typeOfCar) {
		case PASSENGER_CAR:
			request.getRequestDispatcher("/WEB-INF/hidden-views/new-passenger-car.jsp").forward(request, response);
			break;
		case LIGHT_COMMERCIAL_CAR:
			request.getRequestDispatcher("/WEB-INF/hidden-views/new-light-commercial-car.jsp").forward(request, response);
			break;
		default:
			response.sendError(403);
		}
	}

}
