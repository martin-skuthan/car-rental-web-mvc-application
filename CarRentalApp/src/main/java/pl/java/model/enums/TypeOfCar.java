package pl.java.model.enums;

import pl.java.exceptions.NoSuchTypeException;

public enum TypeOfCar {
	PASSENGER_CAR("Passenger cars"),
	LIGHT_COMMERCIAL_CAR("Light Commercial Cars");
	
	String description;
	
	TypeOfCar(String description) {
		this.description = description;
	}	

	public static TypeOfCar getFromDescription(String description) {
		try {
			return TypeOfCar.valueOf(description.toUpperCase());
		}catch (IllegalArgumentException ex) {
			throw new NoSuchTypeException("There is no option:" + description);
		}
	}
	
	@Override
	public String toString() {
		return description;
	}
}
