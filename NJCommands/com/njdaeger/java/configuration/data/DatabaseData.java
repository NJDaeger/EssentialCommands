package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.io.Files;
import com.njdaeger.java.configuration.Warnings;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.exceptions.db.DatabaseBackupExists;
import com.njdaeger.java.configuration.exceptions.db.DatabaseEntryMissing;
import com.njdaeger.java.configuration.exceptions.db.DatabaseExists;
import com.njdaeger.java.configuration.exceptions.db.DatabaseNotFound;
import com.njdaeger.java.configuration.interfaces.IDatabaseHandler;

public class DatabaseData extends Database implements IDatabaseHandler {
	
	private File dir = new File("plugins" + File.separator + "NJCommands" + File.separator + "databases");
	
	@Override
	public String getEntry(String entry) {
		File file = new File(dir + File.separator + database + ".yml");
		if (!file.exists()) {
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				Warnings.warn(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.",
						new DatabaseNotFound(), false);
				Database.getDatabase(database).create();
				return null;
			}
		}
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		return base.getString(entry);
	}
	
	@Override
	public void removeEntry(String entry) {
		File file = new File(dir + File.separator + database + ".yml");
		if (!file.exists()) {
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				Warnings.warn(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.",
						new DatabaseNotFound(), false);
				Database.getDatabase(database).create();
				return;
			}
		}
		if (this.getEntry(entry) == null) {
			try {
				throw new DatabaseEntryMissing();
			} catch (DatabaseEntryMissing e) {
				Bukkit.getLogger().info("The database entry \"" + entry + "\" has been deleted, or does not exist.");
				return;
			}
		}
		save(file, entry, null);
		return;
	}
	
	/**
	 * Get an entry value from a key.
	 * 
	 * @param value
	 *            The value you are getting the entry from.
	 * @return The entry the value is on.
	 */
	public String getFromValue(String value) {
		File file = new File(dir + File.separator + database + ".yml");
		if (!file.exists()) {
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				Warnings.warn(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.",
						new DatabaseNotFound(), false);
				Database.getDatabase(database).create();
				return null;
			}
		}
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		for (String key : base.getKeys(true)) {
			if (base.getString(key).matches(value)) {
				return key;
			}
		}
		return null;
	}
	
	@Override
	public void addEntry(String entry, String value) {
		File file = new File(dir + File.separator + database + ".yml");
		if (!file.exists()) {
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				Warnings.warn(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.",
						new DatabaseNotFound(), false);
				Database.getDatabase(database).create();
			}
		}
		save(file, entry, value);
	}
	
	@Override
	public void clear() {
		File file = new File(dir + File.separator + database + ".yml");
		if (!file.exists()) {
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				Warnings.warn(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.",
						new DatabaseNotFound(), false);
				Database.getDatabase(database).create();
				return;
			}
		}
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		for (String entries : base.getKeys(true)) {
			if (entries == null) {
				break;
			}
			DatabaseData.save(file, entries, null);
		}
	}
	
	@Override
	public void backup() {
		DateFormat format = new SimpleDateFormat("yyyy/dd/MM-hh:mm:ss");
		File file = new File(dir + File.separator + database + ".yml");
		File bckp = new File(
				dir + File.separator + "backups" + File.separator + database + format.format(new Date()) + ".yml");
		if (!file.exists()) {
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				Warnings.warn(
						"The database \"" + database
								+ "\" has been moved, or does not exist. Creating it for you. Backup was not created.",
						new DatabaseNotFound(), false);
				Database.getDatabase(database).create();
				return;
			}
		}
		if (bckp.exists()) {
			try {
				throw new DatabaseBackupExists();
			} catch (DatabaseBackupExists e) {
				Warnings.warn("The database backup \"" + bckp.getName() + "\" already exists.",
						new DatabaseBackupExists(), true);
				return;
			}
		}
		try {
			Files.copy(file, bckp);
		} catch (IOException e) {
			Warnings.warn("An error occurred while creating backup \"" + bckp.getName() + "\".", new IOException(),
					true);
			return;
		}
	}
	
	@Override
	public void create() {
		File file = new File(dir + File.separator + database + ".yml");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (file.exists()) {
			try {
				throw new DatabaseExists();
			} catch (DatabaseExists e) {
				Warnings.warn("The creation of database \"" + database + "\" was stopped. Database already exists.",
						new DatabaseExists(), true);
			}
			return;
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete() {
		File file = new File(dir + File.separator + database + ".yml");
		if (!dir.exists()) {
			Warnings.warn("No databases currently exist.", null, true);
			return;
		}
		if (!file.exists()) {
			Warnings.warn("Database does not exist.", null, true);
			return;
		}
		file.delete();
		
	}
	
	@Override
	public YamlConfiguration getBase() {
		File file = new File(dir + File.separator + database + ".yml");
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		if (file.exists()) {
			return base;
		} else
			return null;
	}
	
	private static void save(File base, String entry, Object value) {
		YamlConfiguration base2 = YamlConfiguration.loadConfiguration(base);
		base2.set(entry, value);
		try {
			base2.save(base);
		} catch (IOException e) {
			Warnings.warn("An error occurred while saving " + entry + " to the file " + base.getName(),
					new IOException(), false);
		}
	}
}
