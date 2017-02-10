package com.njdaeger.java.command.util;

public class CmdAnnotationMissing extends Exception {

	private static final long serialVersionUID = 1L;

	public CmdAnnotationMissing(Class<?> clazz) {
		super("Cmd annotation not present in " + clazz.getName());
	}
}
