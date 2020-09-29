package pl.java.comparators;

import java.util.Comparator;

import pl.java.model.Car;

public class CarBrandComparator implements Comparator<Car> {

	@Override
	public int compare(Car car1, Car car2) {
		if (car1.getBrand().compareTo(car2.getBrand()) == 0) {
			return car1.getModel().compareTo(car2.getModel());
		}
		return car1.getBrand().compareTo(car2.getBrand());
	}
 
}
