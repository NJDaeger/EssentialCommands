package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.configuration.controllers.Database;
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
				Bukkit.getLogger().info(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.");
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
				Bukkit.getLogger().info(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.");
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
				Bukkit.getLogger().info(
						"The database \"" + database + "\" has been moved, or does not exist. Creating it for you.");
				Database.getDatabase(database).create();
			}
		}
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		base.set(entry, value);
	}

	@Override
	public void clear() {
		Thread clear = new Thread(new Runnable() {
			@Override
			public void run() {
				File file = new File(dir + File.separator + database + ".yml");
				if (!file.exists()) {
					try {
						throw new DatabaseNotFound();
					} catch (DatabaseNotFound e) {
						Bukkit.getLogger().info("The database \"" + database
								+ "\" has been moved, or does not exist. Creating it for you.");
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
		});
		clear.run();
	}

	@Override
	public void backup() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	private static void save(File base, String entry, Object value) {
		YamlConfiguration base2 = YamlConfiguration.loadConfiguration(base);
		base2.set(entry, value);
		try {
			base2.save(base);
		} catch (IOException e) {
			e.printStackTrace();
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
