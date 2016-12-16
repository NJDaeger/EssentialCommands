package com.njdaeger.java.configuration.controllers;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.exceptions.DatabaseNotFound;
import com.njdaeger.java.configuration.exceptions.HomeNotFoundException;
import com.njdaeger.java.configuration.interfaces.IHomeHandler;
import com.njdaeger.java.essentials.enums.Error;

public class Homes extends Database implements IHomeHandler{
	
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#createHome(java.lang.String, org.bukkit.entity.Player)
	 */
	public void createHome(String name, Player target) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"homes");
		File dir2 = new File(dir1+File.separator+name+".yml");
		if (dir.exists()) {
			if (dir1.exists()) {
				if (dir2.exists()) {
					return;
				}
				else {
					try {
						dir2.createNewFile();
						YamlConfiguration home = YamlConfiguration.loadConfiguration(dir2);
						home.set("x", target.getLocation().getX());
						home.set("y", target.getLocation().getY());
						home.set("z", target.getLocation().getZ());
						home.set("pitch", target.getLocation().getPitch());
						home.set("yaw", target.getLocation().getYaw());
						home.set("world", target.getLocation().getWorld().getName());
						home.save(dir2);
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				dir1.mkdirs();
				try {
					dir2.createNewFile();
					YamlConfiguration home = YamlConfiguration.loadConfiguration(dir2);
					home.set("x", target.getLocation().getX());
					home.set("y", target.getLocation().getY());
					home.set("z", target.getLocation().getZ());
					home.set("pitch", target.getLocation().getPitch());
					home.set("yaw", target.getLocation().getYaw());
					home.set("world", target.getLocation().getWorld().getName());
					home.save(dir2);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			dir.mkdirs();
			dir1.mkdirs();
			try {
				dir2.createNewFile();
				YamlConfiguration home = YamlConfiguration.loadConfiguration(dir2);
				home.set("x", target.getLocation().getX());
				home.set("y", target.getLocation().getY());
				home.set("z", target.getLocation().getZ());
				home.set("pitch", target.getLocation().getPitch());
				home.set("yaw", target.getLocation().getYaw());
				home.set("world", target.getLocation().getWorld().getName());
				home.save(dir2);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#getHome(java.lang.String, org.bukkit.entity.Player)
	 */
	public YamlConfiguration getHome(String name, Player target) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID+File.separator+"homes"+File.separator+name+".yml");
		if (dir.exists()) {
			YamlConfiguration home = YamlConfiguration.loadConfiguration(dir);
			return home;
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#removeHome(java.lang.String, org.bukkit.entity.Player)
	 */
	public void removeHome(String name, Player target) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID+File.separator+"homes"+File.separator+name+".yml");
		if (dir.exists()) {
			dir.delete();
			return;
		}
		throw new HomeNotFoundException();
		
		
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#listHomes(org.bukkit.entity.Player)
	 */
	public String listHomes(Player target) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID+File.separator+"homes");
		if (dir.exists()) {
			String[] homes = dir.list();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < homes.length; i++) {
				sb.append(homes[i]).append(" ");
			}
			String message = sb.toString().trim();
			String finalmsg = message.replace(".yml", "");
			String wcommas = finalmsg.replaceAll(" ", ", ");
			return wcommas;
		}
		throw new HomeNotFoundException();
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#sendHome(java.lang.String, org.bukkit.entity.Player)
	 */
	public void sendHome(String name, Player target) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID+File.separator+"homes"+File.separator+name+".yml");
		if (dir.exists()) {
			YamlConfiguration home = YamlConfiguration.loadConfiguration(dir);
			double x = Double.parseDouble(home.get("x").toString());
			double y = Double.parseDouble(home.get("y").toString());
			double z = Double.parseDouble(home.get("z").toString());
			double w = Double.parseDouble(home.get("yaw").toString());
			double p = Double.parseDouble(home.get("pitch").toString());
			int fw = (int) w;
			int fp = (int) p;
			World world = Bukkit.getWorld(home.get("world").toString());
			if (world == null) {
				target.sendMessage(Error.WORLD_NOT_FOUND.sendError());
				return;
			}
			Location location = new Location(world, x, y, z, fw, fp);
			target.teleport(location);
		}
		else {
			throw new HomeNotFoundException();
		}
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#sendOtherHome(java.lang.String, org.bukkit.entity.Player, org.bukkit.entity.Player)
	 */
	public void sendOtherHome(String name, Player target, Player sender) {
		UUID userID = target.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID+File.separator+"homes"+File.separator+name+".yml");
		if (dir.exists()) {
			YamlConfiguration home = YamlConfiguration.loadConfiguration(dir);
			double x = Double.parseDouble(home.get("x").toString());
			double y = Double.parseDouble(home.get("y").toString());
			double z = Double.parseDouble(home.get("z").toString());
			double w = Double.parseDouble(home.get("yaw").toString());
			double p = Double.parseDouble(home.get("pitch").toString());
			int fw = (int) w;
			int fp = (int) p;
			World world = Bukkit.getWorld(home.get("world").toString());
			if (world == null) {
				sender.sendMessage(Error.WORLD_NOT_FOUND.sendError());
				return;
			}
			Location location = new Location(world, x, y, z, fw, fp);
			sender.teleport(location);
		}
		throw new HomeNotFoundException();
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#getOfflineHome(java.lang.String, java.lang.String)
	 */
	public YamlConfiguration getOfflineHome(String home, String target) {
		String userID = "";
		try {
			userID = getDatabaseEntry("playerdata", target);
			if (userID == null) {
				return null;
			}
		} catch (DatabaseNotFound e) {}
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID.toString()+File.separator+"homes"+File.separator+home+".yml");
		
		if (dir.exists()) {
			YamlConfiguration homelocation = YamlConfiguration.loadConfiguration(dir);
			return homelocation;
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#removeOfflineHome(java.lang.String, java.lang.String)
	 */
	public void removeOfflineHome(String home, String target) {
		String userID = "";
		try {
			userID = getDatabaseEntry("playerdata", target).toString();
		} catch (DatabaseNotFound e) {}
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID+File.separator+"homes"+File.separator+home+".yml");
		if (userID == null) {
			return;
		}
		if (dir.exists()) {
			dir.delete();
			return;
		}
		throw new HomeNotFoundException();
		
	}
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#listOfflineHomes(java.lang.String)
	 */
	public String listOfflineHomes(String target) {
		String userID = "";
		try {
			userID = getDatabaseEntry("playerdata", target);
			if (userID == null) {
				return null;
			}
		} catch (DatabaseNotFound e) {}
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID.toString()+File.separator+"homes");
		if (dir.exists()) {
			String[] homes = dir.list();
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
	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IHomeHandler#sendOfflineHome(java.lang.String, java.lang.String)
	 */
	public void sendOfflineHome(String home, String target, Player sender) {
		String userID = "";
		try {
			userID = getDatabaseEntry("playerdata", target).toString();
		} catch (DatabaseNotFound e) {}
		if (userID == null) {
			return;
		}
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID+File.separator+"homes"+File.separator+home+".yml");
		if (dir.exists()) {
			YamlConfiguration homelocation = YamlConfiguration.loadConfiguration(dir);
			double x = Double.parseDouble(homelocation.get("x").toString());
			double y = Double.parseDouble(homelocation.get("y").toString());
			double z = Double.parseDouble(homelocation.get("z").toString());
			double w = Double.parseDouble(homelocation.get("yaw").toString());
			double p = Double.parseDouble(homelocation.get("pitch").toString());
			int fw = (int) w;
			int fp = (int) p;
			World world = Bukkit.getWorld(homelocation.get("world").toString());
			if (world == null) {
				sender.sendMessage(Error.WORLD_NOT_FOUND.sendError());
				return;
			}
			Location location = new Location(world, x, y, z, fw, fp);
			sender.teleport(location);
			return;
		}
		throw new HomeNotFoundException();
		
	}
}
