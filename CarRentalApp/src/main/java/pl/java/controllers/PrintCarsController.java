package pl.java.controllers;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.Car;
import pl.java.model.enums.TypeOfCar;
import pl.java.services.CarService;

@WebServlet("/printCars")
public class PrintCarsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarService carService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			TypeOfCar typeOfCar = TypeOfCar.PASSENGER_CAR;
			if(request.getParameter("typeOfCar") != null) {
				typeOfCar = TypeOfCar.getFromDescription(request.getParameter("typeOfCar"));
			}
			final int numberOfRecordsPerPage = 10;
			int numberOfCarRecords = carService.readAllCars(typeOfCar).size();
			request.setAttribute("numberOfCarRecords", numberOfCarRecords);
			int noOfPages = numberOfCarRecords / numberOfRecordsPerPage;
			int lastPage = numberOfCarRecords % numberOfRecordsPerPage;
			if (lastPage > 0) {
				noOfPages++;
			}
			request.setAttribute("noOfPages", noOfPages);
			printCars(request, response, typeOfCar, numberOfRecordsPerPage);
		}catch(NoSuchTypeException ex) {
			ex.printStackTrace();
		}
	}
	
	private void printCars(HttpServletRequest request, HttpServletResponse response, TypeOfCar typeOfCar, int noOfRecordsPerPage) throws ServletException, IOException {
		int noOfPage = 1;
		if (request.getParameter("page") != null) {
			noOfPage = Integer.parseInt(request.getParameter("page"));
		}
		List<Car> cars = carService.readRangeOfCars(typeOfCar, noOfPage, noOfRecordsPerPage);
		request.setAttribute("noOfPage", noOfPage);
		request.setAttribute("cars", cars);
		request.setAttribute("typeOfCar", typeOfCar);
		request.getRequestDispatcher("print-cars.jsp").forward(request, response);
	}
}
