package org.jsp.api.exception;

public class IdNotFoundException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Exception due to Id not found.";
	}
}
