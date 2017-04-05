package com.njdaeger.java.configuration.data;

import com.njdaeger.java.configuration.interfaces.IEntry;

public class Entry implements IEntry {
	
	private String key;
	private Object value;
	
	public Entry(String key) {
		
	}
	
	public Entry(String key, Object value) {
		
	}
	
	@Override
	public String getPath() {
		
	}
	
	@Override
	public Object getValue() {
		
	}
	
	@Override
	public IEntry setPath(String newpath) {
		return this;
	}
	
	@Override
	public IEntry setValue(Object value) {
		return this;
	}
	
}
