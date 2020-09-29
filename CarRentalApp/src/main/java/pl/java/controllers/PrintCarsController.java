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
import javax.servlet.http.HttpSession;

import pl.java.comparators.enums.CarComparatorType;
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
			TypeOfCar typeOfCar = getTypeOfCarAndSaveInSession(request);
			request.setAttribute("typeOfCar", typeOfCar);
			setNumberOfRecordsPerPage(request);
			ControllerAction controllerAction = getControllerActionAndSaveInSession(request);
			request.setAttribute("controllerAction", controllerAction);
			int numberOfCarRecords = getNumberOfRecords(typeOfCar, controllerAction);
			request.setAttribute("numberOfCarRecords", numberOfCarRecords);
			int noOfPages = getNoOfPages(numberOfCarRecords, numberOfRecordsPerPage);
			request.setAttribute("noOfPages", noOfPages);
			int noOfPage = getNoOfPage(request);
			request.setAttribute("noOfPage", noOfPage);
			CarComparatorType carComparatorType = getSortDescriptionAndSaveInSession(request);
			List<Car> cars = getRangeOfCars(typeOfCar, noOfPage, numberOfRecordsPerPage, controllerAction, carComparatorType);
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
	
	private void setNumberOfRecordsPerPage(HttpServletRequest request) {
		final int defaultNumberOfRecordsPerPate = 10;
		if(numberOfRecordsPerPage == 0) {
			numberOfRecordsPerPage = defaultNumberOfRecordsPerPate;
		}
		
		if(request.getParameter("count") != null) {
			numberOfRecordsPerPage = Integer.valueOf(request.getParameter("count"));
		}	
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
	
	private ControllerAction getControllerActionAndSaveInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String controllerActionFromSession = (String)session.getAttribute("controllerAction");
		String controllerActionFromRequest = request.getParameter("controllerAction");
		if (controllerActionFromSession == null || (controllerActionFromRequest != null && controllerActionFromSession != controllerActionFromRequest)) {
			session.setAttribute("controllerAction", controllerActionFromRequest);
		}
		
		ControllerAction controllerAction = null;
		try {
			controllerAction = ControllerAction.getFromDescription((String)session.getAttribute("controllerAction"));
		}catch (Exception e) {
			controllerAction = ControllerAction.PRINT;
		}

		
		return controllerAction;
	}
	
	private TypeOfCar getTypeOfCarAndSaveInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String typeOfCarDescriptionFromSession = (String)session.getAttribute("typeOfCar");
		String typeOfCarDescriptionFromRequest = request.getParameter("typeOfCar");
		if(typeOfCarDescriptionFromSession == null || (typeOfCarDescriptionFromRequest != null && typeOfCarDescriptionFromSession != typeOfCarDescriptionFromRequest)) {
			session.setAttribute("typeOfCar", typeOfCarDescriptionFromRequest);
		}
		
		TypeOfCar typeOfCar = null;
		try {
			typeOfCar = TypeOfCar.getTypeOfCar((String)session.getAttribute("typeOfCar"));		
		}catch (NoSuchTypeException ex) {
			typeOfCar = TypeOfCar.PASSENGER_CAR;
		}
		
		
		return typeOfCar;
	}
	
	private CarComparatorType getSortDescriptionAndSaveInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String sortDescriptionFromSession = (String)session.getAttribute("sortDescription");
		String sortDescriptionFromRequest = request.getParameter("sortDescription");
		if (sortDescriptionFromSession == null || (sortDescriptionFromRequest != null && sortDescriptionFromSession != sortDescriptionFromRequest)) {
			session.setAttribute("sortDescription", sortDescriptionFromRequest);
		}
		
		CarComparatorType carComparatorType = null;
		try {
			carComparatorType = CarComparatorType.getFromDescription((String)session.getAttribute("sortDescription"));
		}catch (NoSuchTypeException e) {
			e.getMessage();
		}
		
		return carComparatorType;		
	}
	
	
	private List<Car> getRangeOfCars(TypeOfCar typeOfCar, int noOfPage, int numberOfRecordsPerPage, ControllerAction controllerAction, CarComparatorType carComparatorType) {
		Stream<Car> stream = carService.readRangeOfCars(typeOfCar, noOfPage, numberOfRecordsPerPage, carComparatorType).stream();
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
