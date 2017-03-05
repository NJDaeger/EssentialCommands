package com.njdaeger.java.configuration.data;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;
import com.njdaeger.java.wrapper.IUserConf;
import com.njdaeger.java.wrapper.OfflineUser;
import com.njdaeger.java.wrapper.User;

public class LastLocation implements IValues, ISetValues {

	private User onlineUser;

	private IUserConf user;

	private boolean memory;

	public LastLocation(OfflineUser user) {
		this.user = user;
		this.memory = false;
	}

	public LastLocation(User user) {
		this.user = user;
		this.onlineUser = user;
		this.memory = Core.getConf().loadInMemory();
	}

	@Override
	public double getX() {
		if (memory) {
			return (double) Transform.getValue(onlineUser.getBase(), PlayerPaths.LAST_X);
		}
		return (double) user.getUserFile().getValue(PlayerPaths.LAST_X.getPath());
	}

	@Override
	public double getY() {
		if (memory) {
			return (double) Transform.getValue(onlineUser.getBase(), PlayerPaths.LAST_Y);
		}
		return (double) user.getUserFile().getValue(PlayerPaths.LAST_Y.getPath());
	}

	@Override
	public double getZ() {
		if (memory) {
			return (double) Transform.getValue(onlineUser.getBase(), PlayerPaths.LAST_Z);
		}
		return (double) user.getUserFile().getValue(PlayerPaths.LAST_Z.getPath());
	}

	@Override
	public int getYaw() {
		if (memory) {
			return (int) Transform.getValue(onlineUser.getBase(), PlayerPaths.LAST_YAW);
		}
		return (int) user.getUserFile().getValue(PlayerPaths.LAST_YAW.getPath());
	}

	@Override
	public int getPitch() {
		if (memory) {
			return (int) Transform.getValue(onlineUser.getBase(), PlayerPaths.LAST_PITCH);
		}
		return (int) user.getUserFile().getValue(PlayerPaths.LAST_PITCH.getPath());
	}

	@Override
	public String getWorld() {
		if (memory) {
			return (String) Transform.getValue(onlineUser.getBase(), PlayerPaths.LAST_WORLD);
		}
		return (String) user.getUserFile().getValue(PlayerPaths.LAST_WORLD.getPath());
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
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LAST_X, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LAST_X.getPath(), value);
	}

	@Override
	public void setY(double value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LAST_Y, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LAST_Y.getPath(), value);
	}

	@Override
	public void setZ(double value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LAST_Z, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LAST_Z.getPath(), value);
	}

	@Override
	public void setYaw(float value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LAST_YAW, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LAST_YAW.getPath(), value);
	}

	@Override
	public void setPitch(float value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LAST_PITCH, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LAST_PITCH.getPath(), value);
	}

	@Override
	public void setWorld(String value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LAST_WORLD, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LAST_WORLD.getPath(), value);
	}

}
