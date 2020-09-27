package pl.java.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.controllers.enums.ControllerAction;
import pl.java.exceptions.NoSuchActionException;
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
			ControllerAction controllerAction = getControllerAction(request);
			request.setAttribute("controllerAction", controllerAction);
			int numberOfCarRecords = getNumberOfRecords(typeOfCar, controllerAction);
			request.setAttribute("numberOfCarRecords", numberOfCarRecords);
			int noOfPages = getNoOfPages(numberOfCarRecords, numberOfRecordsPerPage);
			request.setAttribute("noOfPages", noOfPages);
			int noOfPage = getNoOfPage(request);
			request.setAttribute("noOfPage", noOfPage);
			String sortDescription = request.getParameter("sortDescription");
			request.setAttribute("sortDescription", sortDescription);
			List<Car> cars = getRangeOfCars(typeOfCar, noOfPage, numberOfRecordsPerPage, controllerAction, sortDescription);
			request.setAttribute("cars", cars);			
			request.getRequestDispatcher("/WEB-INF/hidden-views/print-cars.jsp").forward(request, response);
		}catch(NoSuchTypeException | NoSuchActionException ex) {
			//something went wrong
			ex.printStackTrace();
		}
	}
	
	private int getNumberOfRecords(TypeOfCar typeOfCar, ControllerAction controllerAction) {
		Stream<Car> stream = carService.readAllCars(typeOfCar).stream();
		switch (controllerAction) {
		case PRINT:
			return (int)stream.count();
		case RENT:
			return (int)stream.filter(car -> car.getUser() == null).count();
		case RETURN:
			return (int)stream.filter(car -> car.getUser() != null).count();
		default:
			throw new NoSuchActionException("There is no action:" + controllerAction);
		}	
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
	
	private List<Car> getRangeOfCars(TypeOfCar typeOfCar, int noOfPage, int numberOfRecordsPerPage, ControllerAction controllerAction, String sortDescription) {
		Stream<Car> stream = carService.readRangeOfCars(typeOfCar, noOfPage, numberOfRecordsPerPage, sortDescription).stream();
		switch (controllerAction) {
		case PRINT:
			return stream.collect(Collectors.toList());
		case RENT:
			return stream.filter(car -> car.getUser() == null).collect(Collectors.toList());
		case RETURN:
			return stream.filter(car -> car.getUser() != null).collect(Collectors.toList());
		default:
			throw new NoSuchActionException("There is no action:" + controllerAction);
		}
	}
}
