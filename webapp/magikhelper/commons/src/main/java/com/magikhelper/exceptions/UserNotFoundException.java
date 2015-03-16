package com.magikhelper.exceptions;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3447548053977020902L;

	public UserNotFoundException(String message) {
        super(message);
    }
    
}
