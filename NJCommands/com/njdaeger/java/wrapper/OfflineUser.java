package com.njdaeger.java.wrapper;

import java.io.File;
import java.util.UUID;

import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.OfflineHome;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.exceptions.db.DatabaseEntryMissing;
import com.njdaeger.java.configuration.exceptions.db.DatabaseNotFound;
import com.njdaeger.java.configuration.interfaces.IOfflineHome;

public final class OfflineUser implements IOfflineUser {

	//The user's file
	private UserFile userFile;

	//Boolean to check if the user file actually exists.
	private boolean exists;

	//The uuid of the offline user.
	private UUID uuid;

	//The name of the user.
	private String name;

	//This is the directory to the user's homes.
	private File dir;

	public OfflineUser(String name) {
		if (Database.getDatabase("playerdata").getBase() == null) {
			Database.getDatabase("playerdata").create();
			try {
				throw new DatabaseNotFound();
			} catch (DatabaseNotFound e) {
				e.printStackTrace();
			}
		}
		String entry = Database.getDatabase("playerdata").getEntry(name);
		if (entry == null) {
			try {
				throw new DatabaseEntryMissing();
			} catch (DatabaseEntryMissing e) {
				e.printStackTrace();
			}
		}
		UUID id = UUID.fromString(entry);
		this.name = name;
		this.uuid = id;
		this.userFile = new UserFile(id);
		this.exists = getUserFile().exists();
	}

	@Override
	public UUID getId() {
		return uuid;
	}

	@Override
	public boolean isMuted() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.MUTED);
	}

	@Override
	public void setMuted(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.MUTED, value);
	}

	@Override
	public boolean isSpying() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.SOCIALSPY);
	}

	@Override
	public void setSpying(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.SOCIALSPY, value);
	}

	@Override
	public boolean isGod() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.GOD);
	}

	@Override
	public void setGod(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.GOD, value);
	}

	@Override
	public boolean isMessageable() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.MESSAGEABLE);
	}

	@Override
	public void setMessageable(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.MESSAGEABLE, value);
	}

	@Override
	public boolean isAfk() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.AFK);
	}

	@Override
	public void setAfk(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.AFK, value);
	}

	@Override
	public boolean isTeleportable() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.TPTOGGLED);
	}

	@Override
	public void setTeleportable(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.TPTOGGLED, value);
	}

	@Override
	public boolean isGroup(String group) {
		if (!exists) {
			userFile.createConfig();
		}
		return getGroup().equalsIgnoreCase(group);
	}

	@Override
	public String getGroup() {
		if (!exists) {
			userFile.createConfig();
		}
		return (String) userFile.getValue(PlayerPaths.RANK);
	}

	@Override
	public void setGroup(String group) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.RANK, group);
	}

	@Override
	public boolean hasNickname() {
		if (!exists) {
			userFile.createConfig();
		}
		return getName().matches(getNickname());
	}

	@Override
	public boolean hasNickname(String nickname) {
		if (!exists) {
			userFile.createConfig();
		}
		return getNickname().matches(nickname);
	}

	@Override
	public String getNickname() {
		if (!exists) {
			userFile.createConfig();
		}
		return (String) userFile.getValue(PlayerPaths.DISPLAYNAME);
	}

	@Override
	public void setNickname(String nickname) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.DISPLAYNAME, nickname);
	}

	@Override
	public boolean isFlying() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.FLYING);
	}

	@Override
	public void setFlying(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.FLYING, value);
	}

	@Override
	public double getFlyingSpeed() {
		if (!exists) {
			userFile.createConfig();
		}
		return (double) userFile.getValue(PlayerPaths.FLYSPEED);
	}

	@Override
	public void setFlyingSpeed(double value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.FLYSPEED, value);
	}

	@Override
	public int getWalkingSpeed() {
		if (!exists) {
			userFile.createConfig();
		}
		return (int) userFile.getValue(PlayerPaths.WALKSPEED);
	}

	@Override
	public void setWalkingSpeed(double value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.WALKSPEED, value);
	}

	@Override
	public boolean isOp() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.OPPED);
	}

	@Override
	public void setOp(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.OPPED, value);
	}

	@Override
	public Gamemode getGamemode() {
		if (!exists) {
			userFile.createConfig();
		}
		return Gamemode.getAliasUsed((String) userFile.getValue(PlayerPaths.GAMEMODE));
	}

	@Override
	public boolean isGamemode(Gamemode mode) {
		if (!exists) {
			userFile.createConfig();
		}
		return getGamemode().equals(mode);
	}

	@Override
	public void setGamemode(Gamemode mode) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.GAMEMODE, mode.name());
	}

	@Override
	public void setGamemode(String mode) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.GAMEMODE, Gamemode.getAliasUsed(mode).name());
	}

	@Override
	public boolean isBubbled() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.BUBBLED);
	}

	@Override
	public void setBubbled(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.BUBBLED, value);
	}

	@Override
	public boolean isHidden() {
		if (!exists) {
			userFile.createConfig();
		}
		return (boolean) userFile.getValue(PlayerPaths.HIDDEN);
	}

	@Override
	public void setHidden(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.HIDDEN.getPath(), value);
	}

	@Override
	public void setLogoutTime() {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.LOGOUT.getPath(), System.currentTimeMillis());
	}

	@Override
	public long getLogoutTime() {
		if (!exists) {
			userFile.createConfig();
		}
		Object a = userFile.getValue(PlayerPaths.LOGOUT.getPath());
		if (a == null) {
			return -1;
		}
		return (long) a;
	}

	@Override
	public void setLoginTime() {
		if (!exists) {
			userFile.createConfig();
		}
		userFile.setValue(PlayerPaths.LOGOUT.getPath(), System.currentTimeMillis());
	}

	@Override
	public long getLoginTime() {
		if (!exists) {
			userFile.createConfig();
		}
		Object a = userFile.getValue(PlayerPaths.LOGIN.getPath());
		if (a == null) {
			return -1;
		}
		return (long) a;
	}

	@Override
	public IOfflineHome getHome(String home) {
		return new OfflineHome(this, home);
	}

	@Override
	public UserFile getUserFile() {
		return userFile;
	}

	@Override
	public LastLocation getLast() {
		return new LastLocation(this);
	}

	@Override
	public LogoutLocation getLogout() {
		return new LogoutLocation(this);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String[] getHomes() {
		this.dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
				+ getId() + File.separator + "homes");
		return dir.list();
	}

	@Override
	public String listHomes() {
		this.dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
				+ getId() + File.separator + "homes");
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
}
