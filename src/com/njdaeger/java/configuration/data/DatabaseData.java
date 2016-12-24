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
import com.njdaeger.java.configuration.exceptions.db.DatabaseNotFound;
import com.njdaeger.java.configuration.interfaces.IDatabaseHandler;

public class DatabaseData extends Database implements IDatabaseHandler {

	private File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "databases");

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
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		base.set(entry, null);
		return;
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
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		base.set(entry, value);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

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
	/*
	 * (non-Javadoc) public String getDatabaseEntry(String database, String
	 * entry) throws DatabaseNotFound { File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); YamlConfiguration base =
	 * YamlConfiguration.loadConfiguration(file); if (file.exists()) { return
	 * base.getString(entry); } throw new DatabaseNotFound();
	 * 
	 * }
	 * 
	 * 
	 * public void removeDatabaseEntry(String database, String entry) throws
	 * IOException, DatabaseNotFound { File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); if (file.exists()) { YamlConfiguration base =
	 * YamlConfiguration.loadConfiguration(file); base.set(entry, null);
	 * base.save(file); return; } throw new DatabaseNotFound();
	 * 
	 * }
	 * 
	 * public void createDatabaseEntry(String database, String entry, String
	 * value) throws IOException, DatabaseNotFound { File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); if (file.exists()) { YamlConfiguration base =
	 * YamlConfiguration.loadConfiguration(file); base.set(entry, value);
	 * base.save(file); return; } throw new DatabaseNotFound();
	 * 
	 * 
	 * }
	 * 
	 * public void clearDatabase(String database) throws DatabaseNotFound,
	 * IOException { File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); if (file.exists()) { YamlConfiguration base =
	 * YamlConfiguration.loadConfiguration(file); for (String keys :
	 * base.getKeys(true)) { base.set(keys, null); } base.save(file); return; }
	 * throw new DatabaseNotFound();
	 * 
	 * 
	 * }
	 * 
	 * public void backupDatabase(String database) throws DatabaseNotFound,
	 * IOException { File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); File backup = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+
	 * "backups"+File.separator+database+System.currentTimeMillis()+".yml"); if
	 * (file.exists()) { Files.copy(file, backup); return; } throw new
	 * DatabaseNotFound(); }
	 * 
	 * public void createDatabase(String database) throws DatabaseExists,
	 * IOException { File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); if (!file.exists()) { file.createNewFile();
	 * YamlConfiguration.loadConfiguration(file).save(file); return; } throw new
	 * DatabaseExists();
	 * 
	 * }
	 * 
	 * public void deleteDatabase(String database) throws DatabaseNotFound {
	 * File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); if (file.exists()) { file.delete(); return; } throw new
	 * DatabaseNotFound(); }
	 * 
	 * public YamlConfiguration getDatabase(String database) throws
	 * DatabaseNotFound { File file = new
	 * File("plugins"+File.separator+"EssentialCommands"+File.separator+database
	 * +".yml"); if (file.exists()) { return
	 * YamlConfiguration.loadConfiguration(file); }
	 * 
	 * return null; }
	 */

}
