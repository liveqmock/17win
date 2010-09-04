package net.win.exception;

public class IllegalityException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalityException(Exception e) {
		super(e);
	}

	public IllegalityException(String msg) {
		super(msg);
	}

}
