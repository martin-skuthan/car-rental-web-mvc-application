package pl.java.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.controllers.enums.ControllerAction;
import pl.java.exceptions.ItemWithThisIdAlreadyExistsExcpetion;
import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.Car;
import pl.java.model.enums.CarFields;
import pl.java.model.enums.TypeOfCar;
import pl.java.services.CarService;

@WebServlet("/addCar")
public class AddCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarService carService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/hidden-views/select-type-of-car.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeOfCarFromRequest = request.getParameter("typeOfCar");
		TypeOfCar typeOfCar = TypeOfCar.getFromDescription(typeOfCarFromRequest);
		HashMap<CarFields, String> carDetailsFromRequest = null;
		carDetailsFromRequest = getCarDetails(request, typeOfCar);
		try {
			carService.createCar(carDetailsFromRequest, typeOfCar);
			final String operation = "Adding car";
			request.setAttribute("operation", operation);
			request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
		}catch (ItemWithThisIdAlreadyExistsExcpetion e) {
			request.setAttribute("controllerAction", ControllerAction.CORRECT);
			Car car = carService.createCarFromDetails(carDetailsFromRequest, typeOfCar);
			request.setAttribute("car", car);
			switch (typeOfCar) {
			case PASSENGER_CAR:
				request.getRequestDispatcher("/WEB-INF/hidden-views/new-passenger-car.jsp").forward(request, response);
				break;
			case LIGHT_COMMERCIAL_CAR:
				request.getRequestDispatcher("/WEB-INF/hidden-views/new-light-commercial-car.jsp").forward(request, response);
				break;
			default:
				throw new NoSuchTypeException("There is no typeOfCar:" + typeOfCar.toString());
			}
		}
	}
	
	private HashMap<CarFields, String> getCarDetails(HttpServletRequest request, TypeOfCar typeOfCar) {
		HashMap<CarFields, String> carDetails = new HashMap<CarFields, String>();
		carDetails.put(CarFields.REGISTRATION_NUMBER, request.getParameter("inputRegistrationNumber"));
		carDetails.put(CarFields.BRAND, request.getParameter("inputBrand"));
		carDetails.put(CarFields.MODEL, request.getParameter("inputModel"));
		carDetails.put(CarFields.SEATS, request.getParameter("inputSeats"));
		carDetails.put(CarFields.AIR_CONDITIONING, request.getParameter("inputAirConditioning"));
		carDetails.put(CarFields.TRANSMISSION, request.getParameter("inputTransmission"));
		switch (typeOfCar) {
		case PASSENGER_CAR:
			carDetails.put(CarFields.NUMBER_OF_DOORS, request.getParameter("inputNumberOfDoors"));
			carDetails.put(CarFields.TYPE_OF_DRIVE, request.getParameter("inputTypeOfDrive"));
			carDetails.put(CarFields.TRUNK_CAPACITY, request.getParameter("inputTrunkCapacity"));
			break;
		case LIGHT_COMMERCIAL_CAR:
			carDetails.put(CarFields.PAYLOAD, request.getParameter("inputTrunkPayload"));
			carDetails.put(CarFields.LOAD_VOLUME, request.getParameter("inputLoadVolume"));
			carDetails.put(CarFields.LOAD_HEIGHT, request.getParameter("inputLoadHeight"));
			carDetails.put(CarFields.LOAD_WIDTH, request.getParameter("inputLoadWidth"));
			carDetails.put(CarFields.LOAD_LENGTH, request.getParameter("inputLoadLength"));
			break;
		}
		
		return carDetails;
	}
}
