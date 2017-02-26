package com.njdaeger.java.wrapper;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.njdaeger.java.Core;
import com.njdaeger.java.Groups;
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
		Core.getOnlineUserMap().put(player.getUniqueId(), this);
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

	/**
	 * Gets the user's current X position
	 * 
	 * @return The users X position.
	 */
	public double getX() {
		return player.getLocation().getX();
	}

	/**
	 * Gets the user's current Y position
	 * 
	 * @return The users Y position.
	 */
	public double getY() {
		return player.getLocation().getY();
	}

	/**
	 * Get the user's current Z position
	 * 
	 * @return The users Z position.
	 */
	public double getZ() {
		return player.getLocation().getZ();
	}

	/**
	 * Get the user's current Yaw value
	 * 
	 * @return The users Yaw value.
	 */
	public float getYaw() {
		return player.getLocation().getYaw();
	}

	/**
	 * Get the user's current pitch value
	 * 
	 * @return The users pitch value.
	 */
	public float getPitch() {
		return player.getLocation().getPitch();
	}

	/**
	 * Gets the user's unique UUID.
	 * 
	 * @return The users uuid.
	 */
	public UUID getId() {
		return player.getUniqueId();
	}

	@Override
	public Location getLocation() {
		return new Location(getWorld(), getX(), getY(), getZ(), getYaw(), getPitch());
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
			if (value ? Groups.muted.add(player) : Groups.muted.remove(player))
				;
		}
		userFile.setValue(PlayerPaths.MUTED.getPath(), value);
		if (value ? Groups.muted.add(player) : Groups.muted.remove(player))
			;
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
			if (value ? Groups.socialspy.add(player) : Groups.socialspy.remove(player))
				;
			return;
		}
		userFile.setValue(PlayerPaths.SOCIALSPY.getPath(), value);
		if (value ? Groups.socialspy.add(player) : Groups.socialspy.remove(player))
			;
		return;

	}

	@Override
	public boolean isGod() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.GOD);
		}
		return (boolean) userFile.getValue(PlayerPaths.GOD.getPath());
	}

	@Override
	public void setGod(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.GOD, value);
			if (value ? Groups.god.add(player) : Groups.god.remove(player))
				;
			return;
		}
		userFile.setValue(PlayerPaths.GOD.getPath(), value);
		if (value ? Groups.god.add(player) : Groups.god.remove(player))
			;
		return;
	}

	@Override
	public boolean isMessageable() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.MESSAGEABLE);
		}
		return (boolean) userFile.getValue(PlayerPaths.MESSAGEABLE.getPath());
	}

	@Override
	public void setMessageable(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.MESSAGEABLE, value);
			if (value ? Groups.nomessaging.add(player) : Groups.nomessaging.remove(player))
				;
			return;
		}
		userFile.setValue(PlayerPaths.MESSAGEABLE.getPath(), value);
		if (value ? Groups.nomessaging.add(player) : Groups.nomessaging.remove(player))
			;
		return;
	}

	@Override
	public boolean isAfk() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(player, PlayerPaths.AFK);
		}
		return (boolean) userFile.getValue(PlayerPaths.AFK.getPath());
	}

	@Override
	public void setAfk(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.AFK, value);
			if (value) {
				Groups.afk.add(player);
				Groups.afkloc.put(getName(), getLocation());
				Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is now AFK.");
				return;
			} else {
				Groups.afk.remove(player);
				Groups.afkloc.remove(getName());
				Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is no longer AFK.");
			}
			return;
		}
		userFile.setValue(PlayerPaths.AFK.getPath(), value);
		if (value) {
			Groups.afk.add(player);
			Groups.afkloc.put(getName(), getLocation());
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is now AFK.");
			return;
		} else {
			Groups.afk.remove(player);
			Groups.afkloc.remove(getName());
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is no longer AFK.");
		}
		return;
	}

	@Override
	public boolean isTeleportable() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.TPTOGGLED);
		}
		return (boolean) userFile.getValue(PlayerPaths.TPTOGGLED.getPath());
	}

	@Override
	public void setTeleportable(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.TPTOGGLED, value);
			return;
		}
		userFile.setValue(PlayerPaths.TPTOGGLED.getPath(), value);
		return;

	}

	@Override
	public boolean isGroup(String group) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return Transform.getValue(getBase(), PlayerPaths.RANK).toString().equalsIgnoreCase(group);
		}
		return userFile.getValue(PlayerPaths.RANK.getPath()).toString().equalsIgnoreCase(group);
	}

	@Override
	public String getGroup() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (String) Transform.getValue(getBase(), PlayerPaths.RANK);
		}
		return (String) userFile.getValue(PlayerPaths.RANK.getPath());
	}

	@Override
	public void setGroup(String group) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.RANK, group);
			return;
		}
		userFile.setValue(PlayerPaths.RANK.getPath(), group);
		return;

	}

	@Override
	public boolean hasNickname() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return Transform.getValue(getBase(), PlayerPaths.DISPLAYNAME).toString().equalsIgnoreCase(getBase()
					.getName());
		}
		return userFile.getValue(PlayerPaths.DISPLAYNAME.getPath()).toString().equalsIgnoreCase(getBase().getName());
	}

	@Override
	public boolean hasNickname(String nickname) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return Transform.getValue(getBase(), PlayerPaths.DISPLAYNAME).toString().equalsIgnoreCase(nickname);
		}
		return userFile.getValue(PlayerPaths.DISPLAYNAME.getPath()).toString().equalsIgnoreCase(nickname);
	}

	@Override
	public String getNickname() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return Transform.getValue(getBase(), PlayerPaths.DISPLAYNAME).toString();
		}
		return userFile.getValue(PlayerPaths.DISPLAYNAME.getPath()).toString();
	}

	@Override
	public void setNickname(String nickname) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.DISPLAYNAME, nickname);
			player.setDisplayName(nickname);
			return;
		}
		userFile.setValue(PlayerPaths.DISPLAYNAME.getPath(), nickname);
		player.setDisplayName(nickname);
		return;
	}

	@Override
	public boolean isFlying() {
		return player.isFlying();
	}

	@Override
	public void setFlying(boolean value) {
		//return player.setFlying(value);
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
		return userFile;
	}

	@Override
	public LastLocation getLast() {
		return new LastLocation(player);
	}

	@Override
	public LogoutLocation getLogout() {
		return new LogoutLocation(player);
	}

	@Override
	public IUser loginUpdate() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			new Transform(getBase());
		}
		return this;
	}

	@Override
	public IUser logoutUpdate() {
		if (isAfk()) {
			setAfk(false);
		}
		if (isHidden()) {
			setHidden(false);
		}
		if (isBubbled()) {
			setBubbled(false);
		}
		if (memory) {
			Transform.unload(getBase());
		}
		return this;
	}
}
