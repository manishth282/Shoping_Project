package org.jsp.api.exception;

public class InvalidCredentialException extends Exception{
	@Override
	public String getMessage() {
		return "Exception Due To Invalid Input";
	}
}
