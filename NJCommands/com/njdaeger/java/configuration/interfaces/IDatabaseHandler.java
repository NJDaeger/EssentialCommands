package com.njdaeger.java.configuration.interfaces;

import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.configuration.exceptions.db.DatabaseNotFound;

public interface IDatabaseHandler {

	/**
	 * Gets an entry from the database.
	 * 
	 * @param entry
	 *            Entry to get.
	 * @return Returns null if the entry doesn't exist, otherwise it returns the
	 *         entry.
	 * @throws Exception
	 * @throws DatabaseNotFound
	 */
	String getEntry(String entry) throws Exception;

	/**
	 * Removes an entry from the database.
	 * 
	 * @param entry
	 *            Entry to remove.
	 */
	void removeEntry(String entry);

	/**
	 * Adds an entry to the database.
	 * 
	 * @param entry
	 *            Entry you want to add.
	 * @param value
	 *            What to set that entry to.
	 */
	void addEntry(String entry, String value);

	/**
	 * Clears the entire database.
	 */
	void clear();

	/**
	 * Creates a backup of the database.
	 * <p>
	 * <li>Located at: EssentialCommands/database/backups/</li>
	 * <li>Stored as: DATABASE_NAME-DATE.yml</li>
	 * </p>
	 */
	void backup();

	/**
	 * Creates a new database.
	 * <p>
	 * <li>Located at: EssentialCommands/database/</li>
	 * </p>
	 */
	void create();

	/**
	 * Deletes a database.
	 */
	void delete();

	/**
	 * Attempts to get the database.
	 * 
	 * @return Returns null if the database doesn't exist, otherwise it returns
	 *         the database.
	 */
	YamlConfiguration getBase();

}
