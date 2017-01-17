package com.njdaeger.java.command.util;

/**
 * Used in Commands to indicate who has the ability to run the command.
 */
public enum Executor {

	/**
	 * Represents a Player.
	 */
	PLAYER,

	/**
	 * Represents the Console.
	 */
	CONSOLE,

	/**
	 * Represents the Player and the Console.
	 */
	ALL;

}
