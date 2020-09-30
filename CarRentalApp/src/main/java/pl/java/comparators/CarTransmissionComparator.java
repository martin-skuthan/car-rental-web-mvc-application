package pl.java.comparators;

import java.util.Comparator;

import pl.java.model.Car;

public class CarTransmissionComparator implements Comparator<Car> {

	@Override
	public int compare(Car car1, Car car2) {
		return car1.getTransmission().compareTo(car2.getTransmission());
	}

}
