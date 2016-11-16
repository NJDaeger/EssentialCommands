package com.njdaeger.essentials.exceptions;

public class MathErrorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	static String message = "Unknown math operation in ";
	public MathErrorException(String classname) {
		super(message + classname);
	}

}
