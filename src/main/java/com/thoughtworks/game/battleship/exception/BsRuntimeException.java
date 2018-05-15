package com.thoughtworks.game.battleship.exception;

//TODO : we can improve this by adding error code
public class BsRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public BsRuntimeException(String message, Throwable th) {
		super(message, th);
		this.message = message;
	}

	public BsRuntimeException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}