package com.njdaeger.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Groups {
	public static ArrayList<Player> afk = new ArrayList<Player>();

	public static ArrayList<Player> vanish = new ArrayList<Player>();

	public static ArrayList<Player> tptoggled = new ArrayList<Player>();

	public static ArrayList<Player> muted = new ArrayList<Player>();

	public static HashMap<String, Location> afkloc = new HashMap<String, Location>();

	public static ArrayList<Player> nomessaging = new ArrayList<Player>();

	public static ArrayList<Player> socialspy = new ArrayList<Player>();

	public static ArrayList<Player> god = new ArrayList<Player>();

	//Default blacklisted Commands
	public static List<String> blacklistedCommands = Arrays.asList("nick", "god");

	//Default blacklisted blocks
	public static List<String> blacklistedBlocks = Arrays.asList("7", "166");

	//Default spied commands
	public static List<String> spiedCommands = Arrays.asList("message", "write", "r", "reply", "pl", "plugins");
}
