package com.njdaeger.java.command.util;

import org.bukkit.plugin.Plugin;

public class BukkitCommonLib {
	
	//The name of the host plugin.
	private static Plugin plugin;
	
	/**
	 * This should only be used in your onEnable.
	 * 
	 * Creates a new instance of the plugin for the BukkitCommonLib.
	 * 
	 * @param plugin Returns the plugin currently utilizing this library.
	 */
	public BukkitCommonLib(Plugin plugin) {
		System.out.println("[" + plugin.getName() + "] BukkitCommonLib hooked");
		BukkitCommonLib.plugin = plugin;
	}
	
	/**
	 * Gets the plugin the library is currently in.
	 * 
	 * @return Retuns the plugin the Library is in.
	 */
	public static Plugin getPlugin() {
		return plugin;
	}
	
	/**
	 * Gets a list of all the registered tab completions through the BukkitCommonLib
	 * 
	 * @return A list of tab completions. 
	 */
	/*public static HashMap<String, Method> getCompletions() {
		return completions;
	}

	public static HashMap<String, Class<?>> getCompletionClass() {
		return completionclass;
	}*/
}
