package pl.java.exceptions;

public class ItemWithThisIdAlreadyExistsExcpetion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ItemWithThisIdAlreadyExistsExcpetion(String message) {
		super(message);
	}
}
