package pl.java.dao;

import pl.java.model.Car;

public interface CarDao {
	
	public void create(Car car);
	
	public Car read(String carId);
	
	public void update(Car car );
	
	public void delete(String carId);
}
