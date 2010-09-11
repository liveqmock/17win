package net.win.exception;

public class NoPageException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoPageException(Exception e) {
		super(e);
	}

	public NoPageException(String msg) {
		super(msg);
	}

}
