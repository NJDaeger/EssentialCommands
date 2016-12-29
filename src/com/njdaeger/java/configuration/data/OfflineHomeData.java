package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
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
		homes.delete();
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
		try {
			home.save(homes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setY(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("y", value);
		try {
			home.save(homes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setZ(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("z", value);
		try {
			home.save(homes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setYaw(float value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("yaw", value);
		try {
			home.save(homes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setPitch(float value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("pitch", value);
		try {
			home.save(homes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setWorld(String value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(homes);
		home.set("world", value);
		try {
			home.save(homes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public boolean exists() {
		if (YamlConfiguration.loadConfiguration(homes) != null) {
			return true;
		} else
			return false;
	}
}