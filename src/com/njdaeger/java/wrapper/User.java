package com.njdaeger.java.wrapper;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.essentials.enums.Error;

public class User implements IUser {

	//The base player this user represents.
	private Player player;

	private boolean memory = Core.getConf().loadInMemory();

	private boolean exists;

	private UserFile userFile;

	//private HashMap<UUID, User> onlinePlayers = new HashMap<>();

	/**
	 * Creates a new User instance.
	 * 
	 * @param player The player.
	 */
	public User(Player player) {
		this.player = player;
		this.userFile = new UserFile(player);
		this.exists = getUserFile().exists();

	}

	/**
	 * Get the player represented by this user.
	 * 
	 * @return The User as a player.
	 */
	public Player getBase() {
		return player;
	}

	/**
	 * Get the world a user is in.
	 * 
	 * @return The world the user is currently in.
	 */
	public World getWorld() {
		return player.getWorld();
	}

	@Override
	public String getName() {
		return player.getName();
	}

	@Override
	public boolean isMuted() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.MUTED);
		}
		return (boolean) userFile.getValue(PlayerPaths.MUTED.getPath());
	}

	@Override
	public void setMuted(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.MUTED, value);
			return;
		}
		userFile.setValue(PlayerPaths.MUTED.getPath(), value);
		return;
	}

	@Override
	public boolean isSpying() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.SOCIALSPY);
		}
		return (boolean) userFile.getValue(PlayerPaths.SOCIALSPY.getPath());
	}

	@Override
	public void setSpying(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.SOCIALSPY, value);
			return;
		}
		userFile.setValue(PlayerPaths.SOCIALSPY.getPath(), value);
		return;

	}

	@Override
	public boolean isGod() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.SOCIALSPY);
		}
		return (boolean) userFile.getValue(PlayerPaths.SOCIALSPY.getPath());
	}

	@Override
	public void setGod(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isMessageable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMessageable(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAfk() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAfk(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isTeleportable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTeleportable(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isGroup(String group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroup(String group) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasNickname() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNickname(String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNickname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNickname(String nickname) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFlying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlying(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFlyingMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlyingMode(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getFlyingSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFlyingSpeed(double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getWalkingSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWalkingSpeed(double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setOp(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Gamemode getGamemode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGamemode(Gamemode mode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGamemode(Gamemode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBubbled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBubbled(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isHidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHidden(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sudo(String command) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendError(Error error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserFile getUserFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LastLocation getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogoutLocation getLogout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}
