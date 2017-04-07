package com.njdaeger.java.configuration.data;

import com.njdaeger.java.configuration.interfaces.IDatabase;
import com.njdaeger.java.configuration.interfaces.IEntry;

public class Entry implements IEntry {
	
	private String key;
	private Object value;
	private IDatabase database;
	
	public Entry(IDatabase database, String key) {
		this.database = database;
		this.key = key;
	}
	
	public Entry(IDatabase database, String key, Object value) {
		this.database = database;
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String getPath() {
		return key;
	}
	
	@Override
	public Object getValue() {
		return value;
	}
	
	@Override
	public IEntry setPath(String newpath) {
		database.getBase().set(key, null);
		database.getBase().set(newpath, value);
		return this;
	}
	
	@Override
	public IEntry setValue(Object value) {
		database.getBase().set(key, value);
		return this;
	}
	
	@Override
	public IDatabase getDatabase() {
		return database;
	}
	
	@Override
	public void remove() {
		database.getBase().set(key, null);
	}
	
}
