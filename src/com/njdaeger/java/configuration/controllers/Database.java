package com.njdaeger.java.configuration.controllers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.io.Files;
import com.njdaeger.java.configuration.exceptions.DatabaseExists;
import com.njdaeger.java.configuration.exceptions.DatabaseNotFound;
import com.njdaeger.java.configuration.interfaces.IDatabaseHandler;

public class Database implements IDatabaseHandler{
	
	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#getDatabaseEntry(java.lang.String, java.lang.String)
	 */
	public String getDatabaseEntry(String database, String entry) throws DatabaseNotFound {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
		if (file.exists()) {
			return base.getString(entry);
		}
		throw new DatabaseNotFound();
		
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#removeDatabaseEntry(java.lang.String, java.lang.String)
	 */
	public void removeDatabaseEntry(String database, String entry) throws IOException, DatabaseNotFound {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		if (file.exists()) {
			YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
			base.set(entry, null);
			base.save(file);
			return;
		}
		throw new DatabaseNotFound();
		
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#createDatabaseEntry(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createDatabaseEntry(String database, String entry, String value) throws IOException, DatabaseNotFound {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		if (file.exists()) {
			YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
			base.set(entry, value);
			base.save(file);
			return;
		}
		throw new DatabaseNotFound();
		
		
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#clearDatabase(java.lang.String)
	 */
	public void clearDatabase(String database) throws DatabaseNotFound, IOException {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		if (file.exists()) {
			YamlConfiguration base = YamlConfiguration.loadConfiguration(file);
			for (String keys : base.getKeys(true)) {
				base.set(keys, null);
			}
			base.save(file);
			return;
		}
		throw new DatabaseNotFound();
		
		
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#backupDatabase(java.lang.String)
	 */
	public void backupDatabase(String database) throws DatabaseNotFound, IOException {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		File backup = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"backups"+File.separator+database+System.currentTimeMillis()+".yml");
		if (file.exists()) {
			Files.copy(file, backup);
			return;
		}
		throw new DatabaseNotFound();
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#createDatabase(java.lang.String)
	 */
	public void createDatabase(String database) throws DatabaseExists, IOException {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		if (!file.exists()) {
			file.createNewFile();
			YamlConfiguration.loadConfiguration(file).save(file);
			return;
		}
		throw new DatabaseExists();
		
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#deleteDatabase(java.lang.String)
	 */
	public void deleteDatabase(String database) throws DatabaseNotFound {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		if (file.exists()) {
			file.delete();
			return;
		}
		throw new DatabaseNotFound();
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.IDatabaseHandler#getDatabase(java.lang.String)
	 */
	public YamlConfiguration getDatabase(String database) throws DatabaseNotFound {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+database+".yml");
		if (file.exists()) {
			return YamlConfiguration.loadConfiguration(file);
		}
		
		return null;
	}

}
