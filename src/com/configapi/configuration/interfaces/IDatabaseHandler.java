package com.configapi.configuration.interfaces;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.configapi.configuration.exceptions.DatabaseExists;
import com.configapi.configuration.exceptions.DatabaseNotFound;

public interface IDatabaseHandler {
	
	/*
	 * 
	 * getDatabaseEntry(String player);
	 * removeDatabaseEntry(String player);
	 * createDatabaseEntry(String player);
	 * clearDatabase();
	 * backupDatabase();
	 * createDatabase();
	 * 
	 */
	
	/**
	 * @param database Specify database to get
	 * @param entry What entry to find.
	 * @return
	 * @throws DatabaseNotFound 
	 */
	String getDatabaseEntry(String database, String entry) throws DatabaseNotFound;
	
	/**
	 * @param database Specify database to get
	 * @param entry entry What entry to find.
	 * @throws IOException
	 * @throws DatabaseNotFound
	 */
	void removeDatabaseEntry(String database, String entry) throws IOException, DatabaseNotFound;
	
	/**
	 * @param database Specify database to get
	 * @param entry entry What entry to find.
	 * @param value what to set the entry value to.
	 * @throws IOException
	 * @throws DatabaseNotFound
	 */
	void createDatabaseEntry(String database, String entry, String value) throws IOException, DatabaseNotFound;
	
	/**
	 * @param database Specify database to get
	 * @throws DatabaseNotFound
	 * @throws IOException
	 */
	void clearDatabase(String database) throws DatabaseNotFound, IOException;
	
	/**
	 * @param database Specify database to get
	 * @throws DatabaseNotFound
	 * @throws IOException
	 */
	void backupDatabase(String database) throws DatabaseNotFound, IOException;
	
	/**
	 * @param database Specify database to get
	 * @throws DatabaseExists
	 * @throws IOException 
	 */
	void createDatabase(String database) throws DatabaseExists, IOException;
	
	/**
	 * @param database Specify database to get
	 * @throws DatabaseNotFound
	 */
	void deleteDatabase(String database) throws DatabaseNotFound;
	
	/**
	 * @param database Specify database to get
	 * @return
	 * @throws DatabaseNotFound
	 */
	YamlConfiguration getDatabase(String database) throws DatabaseNotFound;
	
}
