package net.win.exception;

public class NoRightsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRightsException(Exception e) {
		super(e);
	}

	public NoRightsException(String msg) {
		super(msg);
	}

}
