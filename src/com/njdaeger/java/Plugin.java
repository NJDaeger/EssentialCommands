package com.njdaeger.java;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
public class Plugin {
	
	/* This is the new command format */
	public static void getCommand(String command, EssCommand getCommand) {
		try {
			Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			f.setAccessible(true);
			CommandMap map = (CommandMap) f.get(Bukkit.getServer());
			map.register(command, getCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* This is the old command format */
	@Deprecated
	public static void getCommand(String command, Command getCommand) {
		try {
			Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			f.setAccessible(true);
			CommandMap map = (CommandMap) f.get(Bukkit.getServer());
			map.register(command, getCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Class<?> getNMSClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
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
