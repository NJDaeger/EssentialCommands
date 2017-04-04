package com.njdaeger.java.configuration.data;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;
import com.njdaeger.java.wrapper.IOfflineUser;
import com.njdaeger.java.wrapper.OfflineUser;
import com.njdaeger.java.wrapper.User;

public class LogoutLocation implements IValues, ISetValues {

	private IOfflineUser user;

	private User onlineUser;

	private boolean memory;

	public LogoutLocation(OfflineUser user) {
		this.user = user;
		this.memory = false;
	}

	public LogoutLocation(User user) {
		this.user = user;
		this.onlineUser = user;
		memory = Core.getConf().loadInMemory();
	}

	@Override
	public double getX() {
		if (memory) {
			return (double) Transform.getValue(onlineUser.getBase(), PlayerPaths.LOGOUT_X);
		}
		return (double) user.getUserFile().getValue(PlayerPaths.LOGOUT_X.getPath());
	}

	@Override
	public double getY() {
		if (memory) {
			return (double) Transform.getValue(onlineUser.getBase(), PlayerPaths.LOGOUT_Y);
		}
		return (double) user.getUserFile().getValue(PlayerPaths.LOGOUT_Y.getPath());
	}

	@Override
	public double getZ() {
		if (memory) {
			return (double) Transform.getValue(onlineUser.getBase(), PlayerPaths.LOGOUT_Z);
		}
		return (double) user.getUserFile().getValue(PlayerPaths.LOGOUT_Z.getPath());
	}

	@Override
	public int getYaw() {
		if (memory) {
			return (int) Transform.getValue(onlineUser.getBase(), PlayerPaths.LOGOUT_YAW);
		}
		return (int) user.getUserFile().getValue(PlayerPaths.LOGOUT_YAW.getPath());
	}

	@Override
	public int getPitch() {
		if (memory) {
			return (int) Transform.getValue(onlineUser.getBase(), PlayerPaths.LOGOUT_PITCH);
		}
		return (int) user.getUserFile().getValue(PlayerPaths.LOGOUT_PITCH.getPath());
	}

	@Override
	public String getWorld() {
		if (memory) {
			return (String) Transform.getValue(onlineUser.getBase(), PlayerPaths.LOGOUT_WORLD);
		}
		return (String) user.getUserFile().getValue(PlayerPaths.LOGOUT_WORLD.getPath());
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
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LOGOUT_X, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LOGOUT_X.getPath(), value);
	}

	@Override
	public void setY(double value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LOGOUT_Y, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LOGOUT_Y.getPath(), value);
	}

	@Override
	public void setZ(double value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LOGOUT_Z, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LOGOUT_Z.getPath(), value);
	}

	@Override
	public void setYaw(float value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LOGOUT_YAW, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LOGOUT_YAW.getPath(), value);
	}

	@Override
	public void setPitch(float value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LOGOUT_PITCH, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LOGOUT_PITCH.getPath(), value);
	}

	@Override
	public void setWorld(String value) {
		if (!user.getUserFile().exists()) {
			user.getUserFile().createConfig();
		}
		if (memory) {
			Transform.setValue(onlineUser.getBase(), PlayerPaths.LOGOUT_WORLD, value);
			return;
		}
		user.getUserFile().setValue(PlayerPaths.LOGOUT_WORLD.getPath(), value);
	}
}
