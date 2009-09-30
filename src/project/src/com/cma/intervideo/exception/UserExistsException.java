package com.cma.intervideo.exception;

public class UserExistsException extends Exception {
	public UserExistsException(){
		super();
	}
	public UserExistsException(String message){
		super(message);
	}
	public UserExistsException(String message, Throwable e){
		super(message,e);
	}
}
