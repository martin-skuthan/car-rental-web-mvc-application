package pl.java.comparators.enums;

import pl.java.exceptions.NoSuchTypeException;

public enum CarComparatorType {
	BRAND("brand"),
	MODEL("model"),
	STATE("state"),
	TRANSMISSION("transmission");
	
	String description;
	
	CarComparatorType(String description) {
		this.description = description;
	}
	
	public static CarComparatorType getFromDescription(String description) {
		try {
			return CarComparatorType.valueOf(description.toUpperCase());
		}catch (IllegalArgumentException ex) {
			throw new NoSuchTypeException("There is no type:" + description);
		}
		
	}
	
	@Override
	public String toString() {
		return description;
	}
}
