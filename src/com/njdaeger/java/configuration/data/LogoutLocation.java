package com.njdaeger.java.configuration.data;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;

public class LogoutLocation implements IValues, ISetValues {

	private PlayerConfigData config;

	public LogoutLocation(Player player) {
		config = new PlayerConfigData(player);
	}

	@Override
	public double getX() {
		return (double) config.getValue(PlayerPaths.LOGOUT_X.getPath());
	}

	@Override
	public double getY() {
		return (double) config.getValue(PlayerPaths.LOGOUT_Y.getPath());
	}

	@Override
	public double getZ() {
		return (double) config.getValue(PlayerPaths.LOGOUT_Z.getPath());
	}

	@Override
	public int getYaw() {
		return (int) config.getValue(PlayerPaths.LOGOUT_YAW.getPath());
	}

	@Override
	public int getPitch() {
		return (int) config.getValue(PlayerPaths.LOGOUT_PITCH.getPath());
	}

	@Override
	public String getWorld() {
		return (String) config.getValue(PlayerPaths.LOGOUT_WORLD.getPath());
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
		config.setValue(PlayerPaths.LOGOUT_X.getPath(), value);
	}

	@Override
	public void setY(double value) {
		config.setValue(PlayerPaths.LOGOUT_Y.getPath(), value);
	}

	@Override
	public void setZ(double value) {
		config.setValue(PlayerPaths.LOGOUT_Z.getPath(), value);
	}

	@Override
	public void setYaw(float value) {
		config.setValue(PlayerPaths.LOGOUT_YAW.getPath(), value);
	}

	@Override
	public void setPitch(float value) {
		config.setValue(PlayerPaths.LOGOUT_PITCH.getPath(), value);
	}

	@Override
	public void setWorld(String value) {
		config.setValue(PlayerPaths.LOGOUT_WORLD.getPath(), value);
	}
}