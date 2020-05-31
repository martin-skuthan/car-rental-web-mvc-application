package pl.java.dao;

import java.util.List;

import pl.java.model.Car;
import pl.java.model.enums.TypeOfCar;

public interface CarDao {
	
	public void create(Car car);
	
	public void update(Car car );
	
	public void deleteByRegistrationNumber(String registrationNumber);
	
	public Car readCarByRegistrationNumber(String registrationNumber);
	
	public List<Car> readRangeOfCars(TypeOfCar typeOfCar, int noOfRecords, int firstResult);
	
	public List<Car> readAllCars(TypeOfCar typeOfCar);
	
	public boolean checkIfCarExists(String registrationNumber);
	
	//public TypeOfCar readTypeOfCarByRegistrationNumber(String registrationNumber);
}
