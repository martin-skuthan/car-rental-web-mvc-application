package pl.java.comparators;

import java.util.Comparator;

import pl.java.model.Car;

public class CarModelComparator implements Comparator<Car> {

	@Override
	public int compare(Car car1, Car car2) {
		return car1.getModel().compareTo(car2.getModel());
	}

}
