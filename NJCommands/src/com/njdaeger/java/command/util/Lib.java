package com.njdaeger.java.command.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import com.njdaeger.java.command.util.commands.BaseCommand;
import com.njdaeger.java.command.util.commands.CommandInfo;
import com.njdaeger.java.command.util.commands.CommandReg;
import com.njdaeger.java.command.util.commands.completer.TabComplete;

public class Lib {

	//String is the command name and method is the tab completion method.
	private static HashMap<String, Method> completions = new HashMap<>();
	private static HashMap<String, Class<?>> completionclass = new HashMap<>();

	/**
	 * Gets the bukkit command map.
	 * 
	 * @return CommandMap
	 */
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
	 * Grabs commands from a class. A class can have more than one command.
	 * 
	 * @param cls The class that contains the command(s)
	 */
	public static void addCommand(Class<?> cls) {
		//CommandReg reg = null;
		try {
			new CommandReg(cls.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		for (CommandInfo command : CommandReg.commands.values()) {
			Lib.getMap().register(BukkitCommonLib.getPlugin().getName(), new BaseCommand(command));
		}
	}

	public static void addCompletion(Class<?> cls) {
		for (Method method : cls.getMethods()) {
			if (method.isAnnotationPresent(TabComplete.class)) {
				String command = method.getAnnotation(TabComplete.class).value();
				completionclass.put(command, cls);
				completions.put(command, method);
			}
		}
	}

	public static HashMap<String, Method> getCompletions() {
		return completions;
	}

	public static HashMap<String, Class<?>> getCompletionClass() {
		return completionclass;
	}
}
