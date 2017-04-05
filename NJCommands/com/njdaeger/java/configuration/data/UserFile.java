package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.IBaseConf;
import com.njdaeger.java.configuration.interfaces.Resettable;

/**
 * @author Noah
 *
 */
public final class UserFile implements IBaseConf, Resettable {

	//Player object
	private UUID id;
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
		this.id = player.getUniqueId();
		this.path = new File("plugins" + File.separator + "NJCommands" + File.separator + "users"
				+ File.separator + id);
		this.file = new File(path + File.separator + "user.yml");
		this.yamlfile = YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Gets an offline player's configuration files.
	 * 
	 * @param uuid The offline player's uuid.
	 */
	public UserFile(UUID uuid) {
		this.id = uuid;
		this.path = new File("plugins" + File.separator + "NJCommands" + File.separator + "users" 
				+ File.separator + id);
		this.file = new File(path + File.separator + "user.yml");
		this.yamlfile = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public UserFile resetConfog() {
		deleteConfig();
		createConfig();
		return this;
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

	/**
	 * Get a value from a path in the player configuration.
	 * 
	 * @param path The path to get the value from.
	 * @return The value of the path.
	 */
	public Object getValue(PlayerPaths path) {
		if (!exists()) {
			createConfig();
		}
		return getYamlFile().get(path.getPath());
	}

	@Override
	public void setValue(String path, Object value) {
		/*if (!exists()) {
			createConfig();
		}*/
		yamlfile.set(path, value);
		try {
			yamlfile.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the value of a path in the player configuration.
	 * 
	 * @param path The value path.
	 * @param value THe new value to set it to.
	 */
	public void setValue(PlayerPaths path, Object value) {
		if (!exists()) {
			createConfig();
		}
		yamlfile.set(path.getPath(), value);
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
		PlayerPaths.checkExist(id, path, file);
		return this;
		/*if (!getPath().exists()) {
			getPath().mkdirs();
			try {
				getFile().createNewFile();
				PlayerPaths.checkExist(id);
				System.out.println("A new User configuration has been created for user " + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!getFile().exists()) {
			try {
				getFile().createNewFile();
				PlayerPaths.checkExist(id);
				System.out.println("A new User configuration has been created for user " + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this;*/
	}

	@Override
	public void deleteConfig() {
		String name = getFile().getName();
		getFile().delete();
		System.out.println("Deleted " + name);

	}

	/**
	 * Gets a User's file version.
	 * 
	 * @return The configuration version.
	 */
	public String getCfgVersion() {
		/*if (!exists()) {
			createConfig();
		}*/
		return (String) getYamlFile().get(PlayerPaths.CFG_VERSION.getPath());
	}

	/**
	 * Sets the configuration version from the core.
	 */
	public void setCfgVersion() {
		/*if (!exists()) {
			createConfig();
		}*/
		getYamlFile().set(PlayerPaths.CFG_VERSION.getPath(), Core.getInstance().getDescription().getVersion());
	}

}
