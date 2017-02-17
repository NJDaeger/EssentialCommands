package com.njdaeger.java.configuration.interfaces;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Interface related to any YAML based configuration. Contains the basic methods
 * needed in order for a configuration to work correctly.
 *
 */
public interface IBaseConf {

	/**
	 * Gets the raw YAML file from the player configuration.
	 * 
	 * @return
	 */
	YamlConfiguration getYamlFile();

	/**
	 * Gets the value of an object from the players YAML configuration.
	 * 
	 * @param path Path to get the value from.
	 * @return The value of that path.
	 */
	Object getValue(String path);

	/**
	 * Set the value of an object in the players YAML configuration.
	 * 
	 * @param path Path to get the value from.
	 * @param value Value to set the path to.
	 */
	void setValue(String path, Object value);

	/**
	 * Checks if the configuration exists.
	 * 
	 * @return
	 */
	boolean exists();

	/**
	 * Gets the path of the configuration.
	 * 
	 * @return The file path.
	 */
	File getPath();

	/**
	 * Gets the configuration file.
	 * 
	 * @return The file where the configuration data is stored.
	 */
	File getFile();

}
