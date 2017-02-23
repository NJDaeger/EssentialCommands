package com.njdaeger.java.wrapper;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class User {

	private Player player;

	//private HashMap<UUID, User> onlinePlayers = new HashMap<>();

	/**
	 * Creates a new User instance.
	 * 
	 * @param player The player.
	 */
	public User(Player player) {
		this.player = player;
	}

	/**
	 * Get the player represented by this user.
	 * 
	 * @return The User as a player.
	 */
	public Player getBase() {
		return player;
	}

	/**
	 * Get the world a user is in.
	 * 
	 * @return The world the user is currently in.
	 */
	public World getWorld() {
		return player.getWorld();
	}

	/**
	 * Forces a user to run a command.
	 * 
	 * @param command The command for the user to run.
	 */
	public void sudo(String command) {
		player.chat("/" + command);
	}

	/**
	 * Sends a message to the specified user.
	 * 
	 * @param message The message to send to the user.
	 */
	public void sendMessage(String message) {
		player.sendMessage(message);
	}
}
