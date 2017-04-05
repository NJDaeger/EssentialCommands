package com.njdaeger.java.configuration.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.configuration.enums.InternalDatabase;
import com.njdaeger.java.configuration.interfaces.IDatabase;
import com.njdaeger.java.configuration.interfaces.IEntry;

public class Database implements IDatabase {
	
	private File dir, file;
	private String name;
	private YamlConfiguration config;
	private Collection<IEntry> collection = new ArrayList<>();
	
	public Database(String name) {
		this.name = name;
	}
	
	public Database(InternalDatabase database) {
		this.name = database.getName();
	}
	
	@Override
	public IEntry getEntry(String key) {
		return new Entry(key);
	}
	
	@Override
	// Check the value of getKeys
	public Collection<IEntry> getEntries() {
		for (String entry : getBase().getKeys(true)) {
			collection.add(new Entry(entry, getBase().get(entry)));
		}
		return collection;
	}
	
	@Override
	public void addEntry(IEntry entry) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeEntry(String entry) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IDatabase getDatabase() {
		return this;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void backup() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public YamlConfiguration getBase() {
		return config;
	}
	
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
