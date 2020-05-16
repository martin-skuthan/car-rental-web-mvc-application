package pl.java.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.model.enums.CarFields;
import pl.java.model.enums.TypeOfCar;
import pl.java.services.CarService;

@WebServlet("/addCar")
public class AddCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarService carService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("selectTypeOfCar").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeOfCarFromRequest = request.getParameter("typeOfCar");
		TypeOfCar typeOfCar = TypeOfCar.getFromDescription(typeOfCarFromRequest);
		HashMap<CarFields, String> carDetailsFromRequest = null;
		switch (typeOfCar) {
		case PASSENGER_CAR:
			carDetailsFromRequest = getPassengerCarDetails(request);
			carService.createCar(carDetailsFromRequest, typeOfCar);
			break;
		case LIGHT_COMMERCIAL_CAR:
			carDetailsFromRequest = getLightCommercialCarDetails(request);
			carService.createCar(carDetailsFromRequest, typeOfCar);
			break;
		default:
			response.sendError(403);
		}
		
		final String operation = "Adding car";
		request.setAttribute("operation", operation);
		request.getRequestDispatcher("/WEB-INF/hidden-views/operation-success.jsp").forward(request, response);
	}
		
	private HashMap<CarFields, String> getPassengerCarDetails(HttpServletRequest request) {
		HashMap<CarFields, String> passengerCarDetails = new HashMap<CarFields, String>();
		passengerCarDetails.put(CarFields.REGISTRATION_NUMBER, request.getParameter("inputRegistrationNumber"));
		passengerCarDetails.put(CarFields.BRAND, request.getParameter("inputBrand"));
		passengerCarDetails.put(CarFields.MODEL, request.getParameter("inputModel"));
		passengerCarDetails.put(CarFields.SEATS, request.getParameter("inputSeats"));
		passengerCarDetails.put(CarFields.AIR_CONDITIONING, request.getParameter("inputAirConditioning"));
		passengerCarDetails.put(CarFields.TRANSMISSION, request.getParameter("inputTransmission"));
		passengerCarDetails.put(CarFields.NUMBER_OF_DOORS, request.getParameter("inputNumberOfDoors"));
		passengerCarDetails.put(CarFields.TYPE_OF_DRIVE, request.getParameter("inputTypeOfDrive"));
		passengerCarDetails.put(CarFields.TRUNK_CAPACITY, request.getParameter("inputTrunkCapacity"));
		return passengerCarDetails;
	}
	
	
	private HashMap<CarFields, String> getLightCommercialCarDetails(HttpServletRequest request) {
		HashMap<CarFields, String> passengerCarDetails = new HashMap<CarFields, String>();
		passengerCarDetails.put(CarFields.REGISTRATION_NUMBER, request.getParameter("inputRegistrationNumber"));
		passengerCarDetails.put(CarFields.BRAND, request.getParameter("inputBrand"));
		passengerCarDetails.put(CarFields.MODEL, request.getParameter("inputModel"));
		passengerCarDetails.put(CarFields.SEATS, request.getParameter("inputSeats"));
		passengerCarDetails.put(CarFields.AIR_CONDITIONING, request.getParameter("inputAirConditioning"));
		passengerCarDetails.put(CarFields.TRANSMISSION, request.getParameter("inputTransmission"));
		passengerCarDetails.put(CarFields.PAYLOAD, request.getParameter("inputTrunkPayload"));
		passengerCarDetails.put(CarFields.LOAD_VOLUME, request.getParameter("inputLoadVolume"));
		passengerCarDetails.put(CarFields.LOAD_HEIGHT, request.getParameter("inputLoadHeight"));
		passengerCarDetails.put(CarFields.LOAD_WIDTH, request.getParameter("inputLoadWidth"));
		passengerCarDetails.put(CarFields.LOAD_LENGTH, request.getParameter("inputLoadLength"));
		return passengerCarDetails;
	}
	

}
