package org.jsp.api.exception;

import org.jsp.api.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class SpringBootAppExceptionHandler extends ResponseEntityExceptionHandler{
//	@ExceptionHandler(InvalidCredentialException.class)
//	public ResponseEntity<ResponseStructure<String>> handleInvalidCredentialException(InvalidCredentialException e){
//		ResponseStructure<String> structure = new ResponseStructure<>();
//		structure.setData("Invalid Input");
//		structure.setMessage(e.getMessage());
//		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
//		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
//	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException e){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Not Found");
		structure.setMessage(e.getMessage());
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
