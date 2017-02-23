package com.njdaeger.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.PlayerConfigData;

public class Groups {

	/**
	 * A list of afk players. Do not use this if you want to set someone to afk
	 * mode. Please use {@link PlayerConfigData#setAfk()}
	 */
	public static ArrayList<Player> afk = new ArrayList<Player>();

	/**
	 * A list of vanished players. Do not use this if you want to vanish
	 * someone. Please use {@link PlayerConfigData#setHidden()}
	 */
	public static ArrayList<Player> vanish = new ArrayList<Player>();

	/**
	 * A list of players who have teleportation disabled. Do not use this to
	 * disable or disable someone's teleportation. Please use
	 * {@link PlayerConfigData#setTpToggled()}
	 */
	public static ArrayList<Player> tptoggled = new ArrayList<Player>();

	/**
	 * A list of muted players. Do not use this to mute someone. Please use
	 * {@link PlayerConfigData#setMuted()}
	 */
	public static ArrayList<Player> muted = new ArrayList<Player>();

	/**
	 * A hashmap of players and their AFK locations. This is used to determine
	 * how far they've moved since they were afk.
	 */
	public static HashMap<String, Location> afkloc = new HashMap<String, Location>();

	/**
	 * A list of players who have private messaging disabled. Please use
	 * {@link PlayerConfigData#setMessageable()} to enable or disable messaging.
	 */
	public static ArrayList<Player> nomessaging = new ArrayList<Player>();

	/**
	 * A list of players who have socialspy enabled. To enable or disable
	 * socialspy for someone, please use {@link PlayerConfigData#setSpying()}
	 */
	public static ArrayList<Player> socialspy = new ArrayList<Player>();

	/**
	 * A list of players who are in god mode. To enable or disable a player in
	 * god mode, please use {@link PlayerConfigData#setGod()}
	 */
	public static ArrayList<Player> god = new ArrayList<Player>();

	/**
	 * A list of default blacklisted commands. This is strictly for the default
	 * configuration only and should not be used under any circumstances.
	 */
	public static List<String> blacklistedCommands = Arrays.asList("nick", "god");

	/**
	 * A list of default blacklisted blocks. This is strictly for the default
	 * configuration only and should not be used under any circumstances.
	 */
	public static List<String> blacklistedBlocks = Arrays.asList("7", "166");

	/**
	 * A list of default spied commands. This is strictly for the default
	 * configuration only and should not be used under any circumstances.
	 */
	public static List<String> spiedCommands = Arrays.asList("message", "write", "r", "reply", "pl", "plugins");

	/**
	 * A list of players who have the infobar enabled.
	 */
	public static ArrayList<Player> infobar = new ArrayList<Player>();

	/**
	 * A Hashmap of each players last move. (The time of the last move, not the
	 * location)
	 */
	public static HashMap<Player, Long> lastmove = new HashMap<Player, Long>();
}
