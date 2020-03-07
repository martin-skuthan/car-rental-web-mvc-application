package pl.java.exceptions;

public class NoSuchTypeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSuchTypeException(String message) {
		super(message);
	}
}
