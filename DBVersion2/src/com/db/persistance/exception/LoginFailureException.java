package com.db.persistance.exception;

public class LoginFailureException extends Exception {
	
	private String message;
	public LoginFailureException(String message)
	{
		this.message=message;
	}

}
