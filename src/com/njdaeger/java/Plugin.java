package com.njdaeger.java;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import com.njdaeger.java.command.util.EssCommand;

public class Plugin {

	/**
	 * Registers an EssCommand.
	 * 
	 * @param getCommand The command instance.
	 */
	public static void getCommand(EssCommand getCommand) {
		getMap().register("ess", getCommand);
	}

	public static CommandMap getMap() {
		CommandMap map = null;
		Field f;
		try {
			f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			f.setAccessible(true);
			map = (CommandMap) f.get(Bukkit.getServer());
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * Registers a Command.
	 * 
	 * @param command The main name of the command.
	 * @param getCommand The command instance.
	 * 
	 * @deprecated Please use the new command format. (EssCommand)
	 */
	@Deprecated
	public static void getCommand(String command, Command getCommand) {
		getMap().register("ess", getCommand);
	}

	/**
	 * Gets a NMS class.
	 * 
	 * @param name The class to get from NMS
	 * @return Returns the class.
	 */
	public static Class<?> getNMSClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a class from the CraftBukkit API
	 * 
	 * @param name The class to get
	 * @return Returns the class you are grabbing.
	 */
	public static Class<?> getCBClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
