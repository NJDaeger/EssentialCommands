package com.njdaeger.java.configuration.interfaces;

public interface IEntry {
	
	String getPath();
	
	Object getValue();
	
	IEntry setPath(String newpath);
	
	IEntry setValue(Object value);
	
}
