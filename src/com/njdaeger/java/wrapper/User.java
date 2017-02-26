package com.njdaeger.java.wrapper;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.njdaeger.java.Core;
import com.njdaeger.java.Groups;
import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

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
			Transform.setValue(player, PlayerPaths.DISPLAYNAME, nickname);
			player.setDisplayName(nickname);
			player.setPlayerListName(nickname);
			return;
		}
		userFile.setValue(PlayerPaths.DISPLAYNAME.getPath(), nickname);
		player.setDisplayName(nickname);
		player.setPlayerListName(nickname);
		return;
	}

	@Override
	public boolean isFlying() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.FLYING);
		}
		return (boolean) userFile.getValue(PlayerPaths.FLYING.getPath());
	}

	@Override
	public void setFlying(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.FLYING, value);
			if (isFlyingMode()) {
				player.setFlying(value);
				return;
			}
			setFlyingMode(value);
			setFlying(value);
			return;
		}
		userFile.setValue(PlayerPaths.FLYING.getPath(), value);
		if (isFlyingMode()) {
			player.setFlying(value);
			return;
		}
		setFlyingMode(value);
		setFlying(value);
		return;
	}

	@Override
	public boolean isFlyingMode() {
		return player.getAllowFlight();
	}

	@Override
	public void setFlyingMode(boolean value) {
		player.setAllowFlight(value);

	}

	@Override
	public double getFlyingSpeed() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (double) Transform.getValue(getBase(), PlayerPaths.FLYSPEED);
		}
		return (double) userFile.getValue(PlayerPaths.FLYSPEED.getPath());
	}

	@Override
	public void setFlyingSpeed(double value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (value > 10) {
			double b = value - 10;
			value -= b;
		}
		if (value < 0) {
			value -= value;
		}
		double a = value / 10;
		float speed = (float) a;
		player.setFlySpeed(speed);
		if (memory) {
			Transform.setValue(player, PlayerPaths.FLYSPEED, value);
			return;
		}
		userFile.setValue(PlayerPaths.FLYSPEED.getPath(), value);
		return;
	}

	@Override
	public double getWalkingSpeed() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (double) Transform.getValue(getBase(), PlayerPaths.WALKSPEED);
		}
		return (double) userFile.getValue(PlayerPaths.WALKSPEED.getPath());
	}

	@Override
	public void setWalkingSpeed(double value) {
		if (!exists) {
			userFile.createConfig();
		}
		double a = 0;
		a += ((19 * value) - Math.pow(value, 2)) / 90;
		if (a > 1) {
			double b = a - 1;
			a -= b;
		}
		if (a < 0) {
			a -= a;
		}
		float speed = Float.parseFloat(Double.toString(a));
		player.setWalkSpeed(speed);
		if (memory) {
			Transform.setValue(player, PlayerPaths.WALKSPEED, value);
			return;
		}
		userFile.setValue(PlayerPaths.WALKSPEED.getPath(), value);
	}

	@Override
	public boolean isOp() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(player, PlayerPaths.OPPED);
		}
		return (boolean) userFile.getValue(PlayerPaths.OPPED.getPath());
	}

	@Override
	public void setOp(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		player.setOp(value);
		if (memory) {
			Transform.setValue(player, PlayerPaths.OPPED, value);
			return;
		}
		userFile.setValue(PlayerPaths.OPPED.getPath(), value);
	}

	@Override
	public Gamemode getGamemode() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return Gamemode.getAliasUsed((String) Transform.getValue(player, PlayerPaths.GAMEMODE));
		}
		return Gamemode.getAliasUsed((String) userFile.getValue(PlayerPaths.GAMEMODE.getPath()));
	}

	@Override
	public boolean isGamemode(Gamemode mode) {
		if (!exists) {
			userFile.createConfig();
		}
		return getGamemode() == mode;
	}

	@Override
	public void setGamemode(Gamemode mode) {
		if (!exists) {
			userFile.createConfig();
		}
		switch (mode) {
		case CREATIVE:
			player.setGameMode(GameMode.CREATIVE);
			break;
		case SURVIVAL:
			player.setGameMode(GameMode.SURVIVAL);
			break;
		case ADVENTURE:
			player.setGameMode(GameMode.ADVENTURE);
			break;
		case SPECTATOR:
			player.setGameMode(GameMode.SPECTATOR);
			break;
		default:
			break;
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.GAMEMODE, mode.name());
			return;
		}
		userFile.setValue(PlayerPaths.GAMEMODE.getPath(), mode.name());
	}

	@Override
	public void setGamemode(String mode) {
		if (!exists) {
			userFile.createConfig();
		}
		Gamemode gamemode = Gamemode.getAliasUsed(mode);
		switch (gamemode) {
		case CREATIVE:
			player.setGameMode(GameMode.CREATIVE);
			break;
		case SURVIVAL:
			player.setGameMode(GameMode.SURVIVAL);
			break;
		case ADVENTURE:
			player.setGameMode(GameMode.ADVENTURE);
			break;
		case SPECTATOR:
			player.setGameMode(GameMode.SPECTATOR);
			break;
		default:
			break;
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.GAMEMODE, gamemode.name());
			return;
		}
		userFile.setValue(PlayerPaths.GAMEMODE.getPath(), gamemode.name());
	}

	@Override
	public boolean isBubbled() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(getBase(), PlayerPaths.TPTOGGLED);
		}
		return (boolean) userFile.getValue(PlayerPaths.TPTOGGLED.getPath());
	}

	@Override
	public void setBubbled(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.BUBBLED, value);
			return;
		}
		userFile.setValue(PlayerPaths.BUBBLED.getPath(), value);
		return;
	}

	@Override
	public boolean isHidden() {
		if (!exists) {
			userFile.createConfig();
		}
		if (memory) {
			return (boolean) Transform.getValue(player, PlayerPaths.HIDDEN);
		}
		return (boolean) userFile.getValue(PlayerPaths.HIDDEN.getPath());
	}

	@Override
	public void setHidden(boolean value) {
		if (!exists) {
			userFile.createConfig();
		}
		PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, 100000000, 1, false, false);
		if (value) {
			for (User users : Core.getOnlineUsers()) {
				if (users.hasPermission(Permission.ESS_VANISH_OVERRIDE)) {
					return;
				} else {
					player.hidePlayer(users.getBase());
					return;
				}
			}
			player.hidePlayer(player);
			player.addPotionEffect(effect);
			return;
		}
		for (User users : Core.getOnlineUsers()) {
			if (users.hasPermission(Permission.ESS_VANISH_OVERRIDE)) {
				return;
			} else {
				player.showPlayer(users.getBase());
				return;
			}
		}
		player.removePotionEffect(effect.getType());
		if (memory) {
			Transform.setValue(player, PlayerPaths.HIDDEN, value);
			return;
		}
		userFile.setValue(PlayerPaths.HIDDEN.getPath(), value);
	}

	@Override
	public void sudo(String command) {
		player.chat("/" + command);
	}

	@Override
	public void sendError(Error error) {
		sendMessage(error.sendError());
	}

	@Override
	public void sendMessage(String message) {
		player.sendMessage(message);
	}

	@Override
	public boolean hasPermission(Permission... permission) {
		return Holder.hasPermission(player, permission);
	}

	@Override
	public boolean hasPermission(String permission) {
		return player.hasPermission(permission);
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
