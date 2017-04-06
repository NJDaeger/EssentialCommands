package com.njdaeger.java.configuration.interfaces;

import java.io.File;
import java.util.Collection;

import org.bukkit.configuration.file.YamlConfiguration;

public interface IDatabase {
	
	/**
	 * Gets an entry from the database from the entry key.
	 * 
	 * @param key
	 *            The path value of the entry.
	 * @return The entry.
	 */
	IEntry getEntry(String entry);
	
	/**
	 * Gets a list of entrys from this database.
	 * 
	 * @return An entry list.
	 */
	Collection<IEntry> getEntries();
	
	/**
	 * Creates a new entry in the database.
	 * 
	 * @param entry
	 *            The entry to add.
	 */
	void addEntry(IEntry entry);
	
	/**
	 * Removes an entry from the database.
	 * 
	 * @param entry
	 *            The entry to remove.
	 */
	void removeEntry(IEntry entry);
	
	/**
	 * Gets this current database.
	 * 
	 * @return The current database.
	 */
	IDatabase getDatabase();
	
	/**
	 * Clears the entire database.
	 */
	void clear();
	
	/**
	 * Creates a backup of the entire database.
	 */
	void backup();
	
	/**
	 * Deletes the database.
	 */
	void delete();
	
	/**
	 * Gets the YAML version of this database.
	 * 
	 * @return This database in YAML form.
	 */
	YamlConfiguration getBase();
	
	/**
	 * Checks if this database actually exists.
	 * 
	 * @return Returns true if it exists, false otherwise.
	 */
	boolean exists();
	
	/**
	 * Gets the name of the database.
	 * 
	 * @return The database name
	 */
	String getName();
	
	/**
	 * Gets the file.
	 * 
	 * @return The file
	 */
	File getFile();
	
}
