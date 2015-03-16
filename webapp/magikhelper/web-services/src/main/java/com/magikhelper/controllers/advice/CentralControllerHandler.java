package com.magikhelper.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.magikhelper.exceptions.UserNotRegisteredException;

@ControllerAdvice
public class CentralControllerHandler {

	
	@ExceptionHandler({UserNotRegisteredException.class})
	public ResponseEntity<String> handlePersonNotFound(UserNotRegisteredException pe) {
		pe.printStackTrace();
		return new ResponseEntity<String>(pe.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> handlePersonNotFound(Exception pe) {
		pe.printStackTrace();
		return new ResponseEntity<String>(pe.getMessage(), HttpStatus.BAD_REQUEST);
	}
	/*@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException pe) {
		return new ResponseEntity<String>(pe.getMessage(), HttpStatus.BAD_REQUEST);
	}*/
}
