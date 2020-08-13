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
import pl.java.exceptions.NoSuchActionException;
import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.Car;
import pl.java.model.enums.CarFields;
import pl.java.model.enums.TypeOfCar;
import pl.java.services.CarService;

@WebServlet("/updateCar")
public class UpdateCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarService carService;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			String controllerActionDescription = request.getParameter("controllerAction");
			ControllerAction controllerAction = ControllerAction.getFromDescription(controllerActionDescription);		
			switch (controllerAction) {
			case UPDATE:
				HashMap<CarFields, String> carDetails = getCarDetails(request);
				String idOfCar = request.getParameter("registrationNumber");
				carService.updateCar(carDetails, idOfCar);
				final String operation = "Updating car";
				request.setAttribute("operation", operation);
				request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
				break;
			case FORWARD:
				forwardCarDataToFormInOrderToUpdate(request, response);
				break;
			default:
				throw new NoSuchActionException("There is no action:" + controllerAction);		
			}
		}catch(NoSuchTypeException | NullPointerException | NoSuchActionException ex) {
			ex.printStackTrace();
		}
		
	}
	
	private void forwardCarDataToFormInOrderToUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String registrationNumber = request.getParameter("idOfItemToUpdate");
		Car car = carService.readCarByRegistrationNumber(registrationNumber);
		request.setAttribute("car", car);
		request.setAttribute("controllerAction", ControllerAction.UPDATE);
		TypeOfCar typeOfCar = car.getTypeOfCar();	
		switch (typeOfCar) {
		case PASSENGER_CAR:
			request.getRequestDispatcher("/WEB-INF/hidden-views/new-passenger-car.jsp").forward(request, response);
			break;
		case LIGHT_COMMERCIAL_CAR:
			request.getRequestDispatcher("new-light-commercial-car.jsp").forward(request, response);
			break;
		}
	}
	
	private HashMap<CarFields, String> getCarDetails(HttpServletRequest request) {
		String typeOfCarFromRequest = request.getParameter("typeOfCar");
		TypeOfCar typeOfCar = TypeOfCar.getFromDescription(typeOfCarFromRequest);
		HashMap<CarFields, String> carDetails = new HashMap<CarFields, String>();
		carDetails.put(CarFields.REGISTRATION_NUMBER, request.getParameter("inputRegistrationNumber"));
		carDetails.put(CarFields.BRAND, request.getParameter("inputBrand"));
		carDetails.put(CarFields.MODEL, request.getParameter("inputModel"));
		carDetails.put(CarFields.SEATS, request.getParameter("inputSeats"));
		carDetails.put(CarFields.AIR_CONDITIONING, request.getParameter("inputAirConditioning"));
		carDetails.put(CarFields.TRANSMISSION, request.getParameter("inputTransmission"));
		carDetails.put(CarFields.NUMBER_OF_DOORS, request.getParameter("inputNumberOfDoors"));
		carDetails.put(CarFields.TYPE_OF_DRIVE, request.getParameter("inputTypeOfDrive"));
		carDetails.put(CarFields.TRUNK_CAPACITY, request.getParameter("inputTrunkCapacity"));
		carDetails.put(CarFields.PAYLOAD, request.getParameter("inputTrunkPayload"));
		carDetails.put(CarFields.LOAD_VOLUME, request.getParameter("inputLoadVolume"));
		carDetails.put(CarFields.LOAD_HEIGHT, request.getParameter("inputLoadHeight"));
		carDetails.put(CarFields.LOAD_WIDTH, request.getParameter("inputLoadWidth"));
		carDetails.put(CarFields.LOAD_LENGTH, request.getParameter("inputLoadLength"));
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
