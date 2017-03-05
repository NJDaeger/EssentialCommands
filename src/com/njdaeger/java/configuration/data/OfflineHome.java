package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.configuration.interfaces.IOfflineHome;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.wrapper.IUserConf;
import com.njdaeger.java.wrapper.OfflineUser;
import com.njdaeger.java.wrapper.User;

public class OfflineHome implements IOfflineHome {

	private OfflineUser user;

	private String home;

	public OfflineHome(OfflineUser user, String home) {
		this.user = user;
		this.home = home;
	}

	File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
			+ user.getId() + File.separator + "homes");
	File homes = new File(dir + File.separator + home + ".yml");

	YamlConfiguration homefile = YamlConfiguration.loadConfiguration(homes);

	@Override
	public double getX() {
		return homefile.getDouble("x");
	}

	@Override
	public double getY() {
		return homefile.getDouble("y");
	}

	@Override
	public double getZ() {
		return homefile.getDouble("z");
	}

	@Override
	public int getYaw() {
		return homefile.getInt("yaw");
	}

	@Override
	public int getPitch() {
		return homefile.getInt("pitch");
	}

	@Override
	public String getWorld() {
		return homefile.getString("world");
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
	public void setX(double value) {
		setValue("x", value);

	}

	@Override
	public void setY(double value) {
		setValue("y", value);

	}

	@Override
	public void setZ(double value) {
		setValue("z", value);

	}

	@Override
	public void setYaw(float value) {
		setValue("yaw", value);

	}

	@Override
	public void setPitch(float value) {
		setValue("pitch", value);

	}

	@Override
	public void setWorld(String value) {
		setValue("world", value);

	}

	@Override
	public boolean exists() {
		return homes.exists();
	}

	@Override
	public void remove() {
		homes.delete();

	}

	@Override
	public String listHomes() {
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

	@Override
	public void sendOtherHome(User target) {
		World world = Bukkit.getWorld(getWorld());
		if (world == null) {
			target.sendMessage(Error.WORLD_NOT_FOUND.sendError());
			return;
		}
		target.tp(getAsLocation());
		return;

	}

	@Override
	public String getName() {
		return home;
	}

	@Override
	public IUserConf getOwner() {
		return user;
	}

	/**
	 * Quick way to set a config value.
	 * 
	 * @param key The key to set.
	 * @param value The value to set the key
	 */
	private void setValue(String key, Object value) {
		if (!exists()) {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			try {
				homes.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		homefile.set(key, value);
		try {
			homefile.save(homes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
