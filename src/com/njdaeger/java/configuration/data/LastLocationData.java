package com.njdaeger.java.configuration.data;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Location;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.IValues;

public class LastLocationData extends Location implements IValues {

	private Player player;

	public LastLocationData(Player player) {
		super(player);
	}

	@Override
	public double getX() {
		return (double) PlayerConfig.getConfig(player).getValue(PlayerPaths.LAST_X.getPath());
	}

	@Override
	public double getY() {
		return (double) PlayerConfig.getConfig(player).getValue(PlayerPaths.LAST_X.getPath());
	}

	@Override
	public double getZ() {
		return (double) PlayerConfig.getConfig(player).getValue(PlayerPaths.LAST_Z.getPath());
	}

	@Override
	public int getYaw() {
		return (int) PlayerConfig.getConfig(player).getValue(PlayerPaths.LAST_YAW.getPath());
	}

	@Override
	public int getPitch() {
		return (int) PlayerConfig.getConfig(player).getValue(PlayerPaths.LAST_PITCH.getPath());
	}

	@Override
	public String getWorld() {
		return (String) PlayerConfig.getConfig(player).getValue(PlayerPaths.LAST_WORLD.getPath());
	}

}
