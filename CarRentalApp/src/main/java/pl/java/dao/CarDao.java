package pl.java.dao;

import java.util.List;

import pl.java.model.Car;
import pl.java.model.enums.TypeOfCar;

public interface CarDao {
	
	public void create(Car car);
	
	public Car read(String carId);
	
	public void update(Car car );
	
	public void delete(String carId);
	
	public List<Car> readRangeOfCars(TypeOfCar typeOfCar, int noOfRecords, int firstResult);
	
	public List<Car> readAllCars(TypeOfCar typeOfCar);
}
