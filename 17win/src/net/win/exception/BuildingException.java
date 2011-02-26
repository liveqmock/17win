package net.win.exception;

public class BuildingException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BuildingException(Exception e) {
		super(e);
	}

	public BuildingException(String msg) {
		super(msg);
	}

}
