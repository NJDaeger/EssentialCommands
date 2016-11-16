package com.configapi.configuration.interfaces;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public interface IConfiguration {
	
	/**
	 * Generates a new configuration file if it exists. 
	 * @throws IOException 
	 */
	void newConfig() throws IOException;
	
	/**
	 * Gets the configuration.
	 * @return
	 */
	YamlConfiguration getConfig();
}
