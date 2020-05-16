package pl.java.exceptions;

public class NoSuchActionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSuchActionException(String message) {
		super(message);
	}
}
