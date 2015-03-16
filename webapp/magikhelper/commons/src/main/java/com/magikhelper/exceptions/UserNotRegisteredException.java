package com.magikhelper.exceptions;

public class UserNotRegisteredException extends RuntimeException{

	private static final long serialVersionUID = 3447548053977020902L;

	public UserNotRegisteredException(String message) {
        super(message);
    }
    
}
