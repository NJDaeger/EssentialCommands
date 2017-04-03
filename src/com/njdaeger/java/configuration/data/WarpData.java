package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;
import com.njdaeger.java.configuration.interfaces.IWarpHandler;
import com.njdaeger.java.enums.Error;

public class WarpData extends Warps implements IWarpHandler, IValues, ISetValues {

	private File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "warps");
	private File file = new File(dir + File.separator + warp + ".yml");

	@Override
	public void setX(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(file);
		home.set("x", value);
		try {
			home.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setY(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(file);
		home.set("y", value);
		try {
			home.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setZ(double value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(file);
		home.set("z", value);
		try {
			home.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setYaw(float value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(file);
		home.set("yaw", value);
		try {
			home.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setPitch(float value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(file);
		home.set("pitch", value);
		try {
			home.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void setWorld(String value) {
		YamlConfiguration home = YamlConfiguration.loadConfiguration(file);
		home.set("world", value);
		try {
			home.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public double getX() {
		return YamlConfiguration.loadConfiguration(file).getDouble("x");
	}

	@Override
	public double getY() {
		return YamlConfiguration.loadConfiguration(file).getDouble("y");
	}

	@Override
	public double getZ() {
		return YamlConfiguration.loadConfiguration(file).getDouble("z");
	}

	@Override
	public int getYaw() {
		return YamlConfiguration.loadConfiguration(file).getInt("yaw");
	}

	@Override
	public int getPitch() {
		return YamlConfiguration.loadConfiguration(file).getInt("pitch");
	}

	@Override
	public String getWorld() {
		return YamlConfiguration.loadConfiguration(file).getString("world");
	}

	@Override
	public Location getAsLocation() {
		Validate.notNull(getX(), "X value cannot be null.");
		Validate.notNull(getY(), "Y value cannot be null.");
		Validate.notNull(getZ(), "Z value cannot be null.");
		Validate.notNull(getYaw(), "Yaw value cannot be null.");
		Validate.notNull(getPitch(), "Pitch value cannot be null.");
		return new Location(Bukkit.getWorld(getWorld()), getX(), getY(), getZ(), getYaw(), getPitch());
	}

	@Override
	public boolean exists() {
		if (!file.exists()) {
			return false;
		}
		return true;
	}

	@Override
	public void create() {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
				setPitch(player.getLocation().getPitch());
				setWorld(player.getLocation().getWorld().getName());
				setX(player.getLocation().getX());
				setY(player.getLocation().getY());
				setZ(player.getLocation().getZ());
				setYaw(player.getLocation().getYaw());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@Override
	public void remove() {
		if (exists()) {
			file.delete();
		}
		return;

	}

	@Override
	public void sendWarp() {
		if (Bukkit.getWorld(getWorld()) == null) {
			player.sendMessage(Error.WORLD_NOT_FOUND.sendError());
			return;
		}
		World world = Bukkit.getWorld(getWorld());
		Location location = new Location(world, getX(), getY(), getZ(), getYaw(), getPitch());
		player.teleport(location);
		return;

	}

	@Override
	public String listWarps() {
		File file = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "warps");
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
