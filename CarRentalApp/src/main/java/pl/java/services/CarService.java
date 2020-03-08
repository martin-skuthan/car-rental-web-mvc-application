package pl.java.services;

import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.java.dao.CarDao;
import pl.java.model.Car;
import pl.java.model.LightCommercialCar;
import pl.java.model.PassengerCar;
import pl.java.model.enums.CarFields;

@RequestScoped
public class CarService {
	@Inject
	private CarDao carDao;
	
	public void createCar(String firstName, String lastName, String pesel) {
		
	}
	
	public void readCar(String carId) {
		
	}
	
	public void updateCar(String firstName, String lastName, String pesel) {
		
	}
	
	public void deleteCar(String carId) {
		
	}
	
	private PassengerCar createPassengerCarFromDetails(HashMap<CarFields, String> carDetails) {
		return null;
	}
	
	private LightCommercialCar createLightCommercialCarFromDetails(HashMap<CarFields, String> carDetails) {
		return null;
	}
}
