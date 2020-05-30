package pl.java.controllers.enums;

import pl.java.exceptions.NoSuchActionException;

public enum ControllerAction {
	CREATE("create"),
	UPDATE("update"),
	DELETE("delete"),
	FORWARD("forward"),
	CORRECT("correct");
	
	String descritpion;
	
	private ControllerAction(String description) {
		this.descritpion = description;
	}
	
	public static ControllerAction getFromDescription(String description) {
		try {
			return ControllerAction.valueOf(description.toUpperCase());
		}catch (IllegalArgumentException ex) {
			throw new NoSuchActionException("There is no action: " + description);
		}
	}

}
