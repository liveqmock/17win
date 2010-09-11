package net.win.exception;

public class SystemErrorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemErrorException(Exception e) {
		super(e);
	}

	public SystemErrorException(String msg) {
		super(msg);
	}

}
