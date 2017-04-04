package com.njdaeger.java.configuration.interfaces;

public interface IWarpHandler {

	/**
	 * 
	 * Checks if the warp exists.
	 * 
	 * @return Returns true if exists, false otherwise.
	 * 
	 */
	boolean exists();

	/**
	 * Creates the new specified warp
	 */
	void create();

	/**
	 * Removes the warp specified
	 */
	void remove();

	/**
	 * Lists all of the server warps. (should make this world compatable) (have
	 * so you can list the warps in a world)
	 * 
	 * @return Returns the amount of warps in a world(?) or on the server
	 */
	String listWarps();

	/**
	 * Sends a player to a warp.
	 */
	void sendWarp();
}
