package com.njdaeger.java.configuration.data;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Warnings;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.configuration.exceptions.homes.HomeNotFound;
import com.njdaeger.java.configuration.interfaces.IOfflineHome;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;
import com.njdaeger.java.essentials.enums.Error;

public class OfflineHomeData extends Homes implements IValues, ISetValues, IOfflineHome {

	String offpl = Database.getDatabase("playerdata").getEntry(offlinetarget);
	File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
			+ offpl + File.separator + "homes");
	File homes = new File(dir + File.separator + home + ".yml");

	@Override
	public void remove() {
		if (exists()) {
			homes.delete();
			return;
		} else
			try {
				throw new HomeNotFound();
			} catch (HomeNotFound e) {
				Warnings.warn("The home \"" + home + "\" could not be deleted.", new HomeNotFound(), true);
			}
	}

	@Override
	public String listHomes() {
		if (dir.exists()) {
			if (dir.list() == null) {
				return null;
			}
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

	@Override
	public void sendOtherHome(Player target) {
		World world = Bukkit.getWorld(getWorld());
		if (world == null) {
			target.sendMessage(Error.WORLD_NOT_FOUND.sendError());
			return;
		}
		Location location = new Location(world, getX(), getY(), getZ(), getYaw(), getPitch());
		target.teleport(location);
		return;
	}

	@Override
	public double getX() {
		return YamlConfiguration.loadConfiguration(homes).getDouble("x");
	}

	@Override
	public double getY() {
		return YamlConfiguration.loadConfiguration(homes).getDouble("y");
	}

	@Override
	public double getZ() {
		return YamlConfiguration.loadConfiguration(homes).getDouble("z");
	}

	@Override
	public int getYaw() {
		return YamlConfiguration.loadConfiguration(homes).getInt("yaw");
	}

	@Override
	public int getPitch() {
		return YamlConfiguration.loadConfiguration(homes).getInt("pitch");
	}

	@Override
	public String getWorld() {
		return YamlConfiguration.loadConfiguration(homes).getString("world");
	}

	@Override
	public void setX(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("x", value);
		return;
	}

	@Override
	public void setY(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("y", value);
		return;
	}

	@Override
	public void setZ(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("z", value);
		return;
	}

	@Override
	public void setYaw(float value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("yaw", value);
		return;
	}

	@Override
	public void setPitch(float value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("pitch", value);
		return;
	}

	@Override
	public void setWorld(String value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("world", value);
		return;
	}

	@Override
	public boolean exists() {
		if (homes.exists()) {
			return true;
		} else
			return false;
	}
}
