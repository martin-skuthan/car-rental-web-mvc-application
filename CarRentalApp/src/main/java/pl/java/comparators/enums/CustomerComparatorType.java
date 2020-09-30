package pl.java.comparators.enums;

import pl.java.exceptions.NoSuchTypeException;

public enum CustomerComparatorType {
	FIRST_NAME("first_name"),
	LAST_NAME("last_name");
	
	String description;
	
	CustomerComparatorType(String description) {
		this.description = description;
	}
	
	public static CustomerComparatorType getFromDescription(String description) {
		try {
			return CustomerComparatorType.valueOf(description.toUpperCase());
		}catch (IllegalArgumentException ex) {
			throw new NoSuchTypeException("There is no type:" + description);
		}
		
	}
	
	@Override
	public String toString() {
		return description;
	}
}