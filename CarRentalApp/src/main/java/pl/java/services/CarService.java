package pl.java.services;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.java.dao.CarDao;
import pl.java.exceptions.NoSuchTypeException;
import pl.java.model.Car;
import pl.java.model.LightCommercialCar;
import pl.java.model.PassengerCar;
import pl.java.model.User;
import pl.java.model.enums.CarFields;
import pl.java.model.enums.Transmission;
import pl.java.model.enums.TypeOfCar;
import pl.java.model.enums.TypeOfDrive;

@ApplicationScoped
public class CarService {
	@Inject
	private CarDao carDao;
	
	public void createCar(HashMap<CarFields, String> carDetails, TypeOfCar typeOfCar) {
		Car car = null;
		switch (typeOfCar) {
		case PASSENGER_CAR:
			car = createPassengerCarFromDetails(carDetails);
			break;
		case LIGHT_COMMERCIAL_CAR:
			car = createLightCommercialCarFromDetails(carDetails);
			break;
		default:
			throw new NoSuchTypeException("There is no typeOfCar:" + typeOfCar.toString());
		}
		
		carDao.create(car);
	}
	
	public void readCar(String carId) {
		
	}
	
	public void updateCar(String firstName, String lastName, String pesel) {
		
	}
	
	public void deleteCar(String carId) {
		
	}
	
	private PassengerCar createPassengerCarFromDetails(HashMap<CarFields, String> carDetails) {
		String registrationNumber = carDetails.get(CarFields.REGISTRATION_NUMBER);
		String brand = carDetails.get(CarFields.BRAND);
		String model = carDetails.get(CarFields.MODEL);
		int seats = Integer.valueOf(carDetails.get(CarFields.SEATS));
		boolean airConditioning = Boolean.getBoolean(carDetails.get(CarFields.AIR_CONDITIONING)); 
		Transmission transmission = Transmission.getFromDescription(carDetails.get(CarFields.TRANSMISSION));
		User user = null;
		int numberOfDoors = Integer.valueOf(carDetails.get(CarFields.NUMBER_OF_DOORS));
		TypeOfDrive typeOfDrive = TypeOfDrive.getFromDescription(carDetails.get(CarFields.TYPE_OF_DRIVE));
		int trunkCapacity = Integer.valueOf(carDetails.get(CarFields.TRUNK_CAPACITY));
		
		return new PassengerCar(registrationNumber, brand, model, seats, airConditioning, transmission, user, numberOfDoors,
								typeOfDrive, trunkCapacity);
	}
	
	
	private LightCommercialCar createLightCommercialCarFromDetails(HashMap<CarFields, String> carDetails) {
		String registrationNumber = carDetails.get(CarFields.REGISTRATION_NUMBER);
		String brand = carDetails.get(CarFields.BRAND);
		String model = carDetails.get(CarFields.MODEL);
		int seats = Integer.valueOf(carDetails.get(CarFields.SEATS));
		boolean airConditioning = Boolean.getBoolean(carDetails.get(CarFields.AIR_CONDITIONING)); 
		Transmission transmission = Transmission.getFromDescription(carDetails.get(CarFields.TRANSMISSION));
		User user = null;
		double payload = Double.valueOf(carDetails.get(CarFields.PAYLOAD));
		double loadVolume = Double.valueOf(carDetails.get(CarFields.LOAD_VOLUME));
		double loadHeight = Double.valueOf(carDetails.get(CarFields.LOAD_HEIGHT));
		double loadWidth = Double.valueOf(carDetails.get(CarFields.LOAD_WIDTH));
		double loadLength = Double.valueOf(carDetails.get(CarFields.LOAD_LENGTH));
		
		return new LightCommercialCar(registrationNumber, brand, model, seats, airConditioning, transmission, user, payload,
									  loadVolume, loadHeight, loadWidth, loadLength);
	}
	
}
