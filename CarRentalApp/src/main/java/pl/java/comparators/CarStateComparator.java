package pl.java.comparators;

import java.util.Comparator;

import pl.java.model.Car;

public class CarStateComparator implements Comparator<Car> {

	@Override
	public int compare(Car car1, Car car2) {
		if (car1.getUser() == null && car2.getUser() == null) {
			return 0;
		}else if(car1.getUser() == null) {
			return -1;
		}
		
		return 1;
	}
	
}
