package com.njdaeger.java.configuration.interfaces;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.exceptions.WarpExistsException;
import com.njdaeger.java.configuration.exceptions.WarpNotFound;

public interface IWarpHandler {
	
	/**
	 * Get a warp.
	 * @param warp Name of warp to get.
	 * @return
	 */
	YamlConfiguration getWarp(String warp);
	
	/**
	 * Delete an existing warp.
	 * @param warp Warp to delete.
	 * @throws WarpNotFound 
	 */
	void deleteWarp(String warp) throws WarpNotFound;
	
	/**
	 * Create a new warp
	 * @param warp Warp to create.
	 * @param player Player to get coordinates from.
	 * @throws IOException 
	 * @throws WarpExistsException 
	 */
	void createWarp(String warp, Player player) throws IOException, WarpExistsException;
	
	/**
	 * Send a player to a warp.
	 * @param warp Warp to warp too.
	 * @param target Player to send to warp.
	 * @throws WarpNotFound 
	 */
	void sendtoWarp(String warp, Player target) throws WarpNotFound;
	
	/**
	 * List the server warps.
	 * @return
	 * @throws WarpNotFound
	 */
	String listWarps() throws WarpNotFound;
	
	
}
