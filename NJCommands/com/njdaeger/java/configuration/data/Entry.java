package com.njdaeger.java.configuration.data;

import com.njdaeger.java.configuration.interfaces.IDatabase;
import com.njdaeger.java.configuration.interfaces.IEntry;

public class Entry implements IEntry {
	
	private String key;
	private Object value;
	private IDatabase database;
	
	public Entry(IDatabase database, String key) {
		
	}
	
	public Entry(IDatabase database, String key, Object value) {
		
	}
	
	@Override
	public String getPath() {
		return null;
	}
	
	@Override
	public Object getValue() {
		return null;
	}
	
	@Override
	public IEntry setPath(String newpath) {
		return this;
	}
	
	@Override
	public IEntry setValue(Object value) {
		return this;
	}
	
	@Override
	public IDatabase getDatabase() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
