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
	private int numberOfRecordsPerPage;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			TypeOfCar typeOfCar = getTypeOfCar(request);
			request.setAttribute("typeOfCar", typeOfCar);
			setNumberOfRecordsPerPage(request);
			int numberOfCarRecords = carService.readAllCars(typeOfCar).size();
			request.setAttribute("numberOfCarRecords", numberOfCarRecords);
			int noOfPages = getNoOfPages(numberOfCarRecords, numberOfRecordsPerPage);
			request.setAttribute("noOfPages", noOfPages);
			int noOfPage = getNoOfPage(request);
			request.setAttribute("noOfPage", noOfPage);
			carService.readRangeOfCars(typeOfCar, noOfPage, numberOfRecordsPerPage);
			List<Car> cars = carService.readRangeOfCars(typeOfCar, noOfPage, numberOfRecordsPerPage);
			request.setAttribute("cars", cars);			
			request.getRequestDispatcher("print-cars.jsp").forward(request, response);
		}catch(NoSuchTypeException ex) {
			ex.printStackTrace();
		}
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
	
	private TypeOfCar getTypeOfCar(HttpServletRequest request) {
		TypeOfCar typeOfCar = TypeOfCar.PASSENGER_CAR;
		if(request.getParameter("typeOfCar") != null) {
			typeOfCar = TypeOfCar.getTypeOfCar(request.getParameter("typeOfCar"));
		}
		
		return typeOfCar;
	}
	
	private int getNoOfPages(int numberOfCarRecords, int numberOfRecordsPerPage) {
		int noOfPages = numberOfCarRecords / numberOfRecordsPerPage;
		int lastPage = numberOfCarRecords % numberOfRecordsPerPage;
		if(lastPage > 0) {
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
