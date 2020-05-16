package pl.java.model.enums;

import pl.java.exceptions.NoSuchTypeException;

public enum TypeOfCar {
	PASSENGER_CAR("Passenger"),
	LIGHT_COMMERCIAL_CAR("LightCommercial");
	
	String description;
	
	TypeOfCar(String description) {
		this.description = description;
	}	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static TypeOfCar getFromDescription(String description) {
		TypeOfCar[] values = values();
		TypeOfCar typeOfCar = null;
		for (TypeOfCar value : values) {
			if (value.getDescription().equals(description)) {
				typeOfCar = value;
			}
		}
		
		if (typeOfCar != null) {
			return typeOfCar;
		}else {
			throw new NoSuchTypeException("There is no type:" + description);
		}	
	}
	
	public static TypeOfCar getTypeOfCar(String typeOfCarString) {
		try {
			return TypeOfCar.valueOf(typeOfCarString.toUpperCase());
		}catch (NoSuchTypeException ex) {
			throw new NoSuchTypeException("There is no type:" + typeOfCarString);
		}
	}
	
	@Override
	public String toString() {
		return description;
	}
}
