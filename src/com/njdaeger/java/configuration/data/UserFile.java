package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.exceptions.db.DatabaseEntryMissing;
import com.njdaeger.java.configuration.exceptions.db.DatabaseNotFound;
import com.njdaeger.java.configuration.interfaces.IBaseConf;
import com.njdaeger.java.configuration.interfaces.Resettable;

public class UserFile implements IBaseConf, Resettable {

	//Player object
	private Player player;
	//The player file path. And the main configuration file of the player.
	private File path, file;
	//The YAML file the players configuration is in.
	private YamlConfiguration yamlfile;

	/**
	 * Gets an online player's configuration files.
	 * 
	 * @param player Player to get the configuration files from.
	 */
	public UserFile(Player player) {
		this.player = player;
		this.path = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users"
				+ File.separator + this.player.getUniqueId());
		this.file = new File(path + File.separator + "user.yml");
		this.yamlfile = YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Gets an offline player's configuration files.
	 * 
	 * @param offlinePlayer The offline player to get the configuration files
	 *            from.
	 */
	public UserFile(String offlinePlayer) {
		if (Database.getDatabase("playerdata").getBase() == null) {
			Database.getDatabase("playerdata").create();
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				e.printStackTrace();
			}
		}
		UUID id = UUID.fromString(Database.getDatabase("playerdata").getEntry(offlinePlayer));
		if (id == null) {
			try {
				throw new DatabaseEntryMissing();
			} catch (DatabaseEntryMissing e) {
				e.printStackTrace();
			}
		}
		this.player = (Player) Bukkit.getOfflinePlayer(id);
		this.path = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users"
				+ File.separator + this.player.getUniqueId());
		this.file = new File(path + File.separator + "user.yml");
		this.yamlfile = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public UserFile resetConfog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YamlConfiguration getYamlFile() {
		return yamlfile;
	}

	@Override
	public Object getValue(String path) {
		if (!exists()) {
			createConfig();
		}
		return getYamlFile().get(path);
	}

	@Override
	public void setValue(String path, Object value) {
		if (!exists()) {
			createConfig();
		}
		yamlfile.set(path, value);
		try {
			yamlfile.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean exists() {
		return getFile().exists();
	}

	@Override
	public File getPath() {
		return path;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public UserFile createConfig() {
		if (!getPath().exists()) {
			getPath().mkdirs();
			try {
				getFile().createNewFile();
				PlayerPaths.checkExist(player);
				System.out.println("A new User configuration has been created for " + player.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!getFile().exists()) {
			try {
				getFile().createNewFile();
				PlayerPaths.checkExist(player);
				System.out.println("A new User configuration has been created for " + player.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	@Override
	public void deleteConfig() {
		String name = getFile().getName();
		getFile().delete();
		System.out.println("Deleted " + name);

	}

}
