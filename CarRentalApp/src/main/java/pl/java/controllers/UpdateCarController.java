package pl.java.controllers;

import java.io.IOException;

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
import pl.java.model.LightCommercialCar;
import pl.java.model.PassengerCar;
import pl.java.model.enums.Transmission;
import pl.java.model.enums.TypeOfCar;
import pl.java.model.enums.TypeOfDrive;
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
				Car carFromDb = carService.readCarByRegistrationNumber(request.getParameter("registrationNumber"));
				updateCar(carFromDb, request);
				carService.updateCar(carFromDb);
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
		}catch(NoSuchTypeException | NullPointerException ex) {
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
			request.getRequestDispatcher("new-passenger-car.jsp").forward(request, response);
			break;
		case LIGHT_COMMERCIAL_CAR:
			request.getRequestDispatcher("new-light-commercial-car.jsp").forward(request, response);
			break;
		}
	}
	
	private void updateCar(Car carFromDb, HttpServletRequest request) {
		carFromDb.setRegistrationNumber(request.getParameter("inputRegistrationNumber"));
		carFromDb.setBrand(request.getParameter("inputBrand"));
		carFromDb.setModel(request.getParameter("inputModel"));
		carFromDb.setSeats(Integer.valueOf(request.getParameter("inputSeats")));
		carFromDb.setAirConditioning(Boolean.parseBoolean(request.getParameter("inputAirConditioning")));
		carFromDb.setTransmission(Transmission.getFromDescription(request.getParameter("inputTransmission")));
		carFromDb.setUser(null);
		
		switch (carFromDb.getTypeOfCar()) {
		case PASSENGER_CAR:
			PassengerCar passengerCarFromDb = (PassengerCar)carFromDb;
			passengerCarFromDb.setNumberOfDoors(Integer.valueOf(request.getParameter("inputNumberOfDoors")));
			passengerCarFromDb.setTypeOfDrive(TypeOfDrive.getFromDescription(request.getParameter("inputTypeOfDrive")));
			passengerCarFromDb.setTrunkCapacity(Integer.valueOf(request.getParameter("inputTrunkCapacity")));
			break;
		case LIGHT_COMMERCIAL_CAR:
			LightCommercialCar lightCommercialCar = (LightCommercialCar)carFromDb;
			lightCommercialCar.setPayload(Double.valueOf(request.getParameter("inputTrunkPayload")));
			lightCommercialCar.setLoadVolume(Double.valueOf(request.getParameter("inputLoadVolume")));
			lightCommercialCar.setLoadHeight(Double.valueOf(request.getParameter("inputLoadHeight")));
			lightCommercialCar.setLoadWidth(Double.valueOf(request.getParameter("inputLoadWidth")));
			lightCommercialCar.setLoadLength(Double.valueOf(request.getParameter("inputLoadLength")));
			break;
		}
	}
}
