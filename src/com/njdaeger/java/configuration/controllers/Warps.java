package com.njdaeger.java.configuration.controllers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.configuration.exceptions.WarpNotFound;
import com.njdaeger.java.configuration.interfaces.IWarpHandler;

public class Warps implements IWarpHandler{

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IWarpHandler#getWarp(java.lang.String)
	 */
	public YamlConfiguration getWarp(String warp){
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"warps"+File.separator+warp+".yml");
		if (file.exists()) {
			return YamlConfiguration.loadConfiguration(file);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IWarpHandler#deleteWarp(java.lang.String)
	 */
	public void deleteWarp(String warp){
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"warps"+File.separator+warp+".yml");
		if (file.exists()) {
			file.delete();
			return;
		}
		try {
			throw new WarpNotFound();
		} catch (WarpNotFound e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IWarpHandler#createWarp(java.lang.String)
	 */
	public void createWarp(String warp, Player player) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"warps"+File.separator+warp+".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			YamlConfiguration w = YamlConfiguration.loadConfiguration(file);
			w.set("x", player.getLocation().getX());
			w.set("y", player.getLocation().getY());
			w.set("z", player.getLocation().getZ());
			w.set("yaw", player.getLocation().getYaw());
			w.set("pitch", player.getLocation().getPitch());
			w.set("world", player.getLocation().getWorld().getName());
			try {
				w.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IWarpHandler#sendtoWarp(java.lang.String, org.bukkit.entity.Player)
	 */
	public void sendtoWarp(String warp, Player target) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"warps"+File.separator+warp+".yml");
		if (file.exists()) {
			YamlConfiguration w = YamlConfiguration.loadConfiguration(file);
			double x = Double.parseDouble(w.get("x").toString());
			double y = Double.parseDouble(w.get("y").toString());
			double z = Double.parseDouble(w.get("z").toString());
			double w1 = Double.parseDouble(w.get("yaw").toString());
			double p = Double.parseDouble(w.get("pitch").toString());
			int yaw = (int) w1;
			int pitch = (int) p;
			World world = Bukkit.getWorld(w.get("world").toString());
			if (world == null) {
				target.sendMessage(Error.WORLD_NOT_FOUND.sendError());
				return;
			}
			Location tplocation = new Location(world, x, y, z, yaw, pitch);
			target.teleport(tplocation);
			return;
		}
		try {
			throw new WarpNotFound();
		} catch (WarpNotFound e) {
			e.printStackTrace();
		}
		
	}

	public String listWarps() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"warps");
		if (file.exists()) {
			String[] homes = file.list();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < homes.length; i++) {
				sb.append(homes[i]).append(" ");
			}
			String message = sb.toString().trim();
			String finalmsg = message.replace(".yml", "");
			String wcommas = finalmsg.replaceAll(" ", ", ");
			return wcommas;
		}
		return null;
	}

}
