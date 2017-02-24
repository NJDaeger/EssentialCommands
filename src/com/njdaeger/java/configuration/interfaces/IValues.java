package com.njdaeger.java.configuration.interfaces;

import org.bukkit.Location;

public interface IValues {

	/**
	 * Returns the players x value
	 * 
	 * @return
	 */
	double getX();

	/**
	 * Returns the players y value
	 * 
	 * @return
	 */
	double getY();

	/**
	 * Returns the players z value
	 * 
	 * @return
	 */
	double getZ();

	/**
	 * Returns the players yaw value
	 * 
	 * @return
	 */
	int getYaw();

	/**
	 * Returns the players pitch value
	 * 
	 * @return
	 */
	int getPitch();

	/**
	 * Returns the players world
	 * 
	 * @return
	 */
	String getWorld();

	/**
	 * Returns all the values in {@link IValues} as one location.
	 * 
	 * @return A bukkit location.
	 */
	Location getAsLocation();

}
