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
import com.njdaeger.java.configuration.data.Home;
import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.IOfflineHome;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public final class User implements IUser {

	//The base player this user represents.
	private Player player;

	//Check if memory configuration is enabled.
	private boolean memory = Core.getConf().loadInMemory();

	//If the player configuration exists or not.
	private boolean exists;

	//The user's base file. Where everything is created and reset.
	private UserFile userFile;

	//private HashMap<UUID, User> onlinePlayers = new HashMap<>();

	/**
	 * Creates a new User instance.
	 * 
	 * @param player The player.
	 */
	public User(Player player) {
		Core.getOnlineUserMap().put(player.getUniqueId(), this);
		Core.getOnlineUsers().add(this);
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
	@Override
	public UUID getId() {
		return player.getUniqueId();
	}

	@Override
	public Location getLocation() {
		return player.getLocation();
	}

	@Override
	public String getName() {
		return player.getName();
	}

	@Override
	public boolean isMuted() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.MUTED, value);
			if (value ? Groups.muted.add(player) : Groups.muted.remove(player))
				return;
		}
		userFile.setValue(PlayerPaths.MUTED.getPath(), value);
		if (value ? Groups.muted.add(player) : Groups.muted.remove(player))
			return;

	}

	@Override
	public boolean isSpying() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
		}
		if (memory) {
			Transform.setValue(getBase(), PlayerPaths.GOD, value);
			if (value) {
				Groups.god.add(player);
				player.setInvulnerable(value);
				return;
			}
			Groups.god.remove(player);
			player.setInvulnerable(value);
			return;
		}
		userFile.setValue(PlayerPaths.GOD.getPath(), value);
		if (value) {
			Groups.god.add(player);
			player.setInvulnerable(value);
			return;
		}
		Groups.god.remove(player);
		player.setInvulnerable(value);
		return;
	}

	@Override
	public boolean isMessageable() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.AFK, value);
			if (value) {
				Groups.afk.add(player);
				Groups.afkloc.put(player.getName(), player.getLocation());
				Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is now AFK.");
				return;
			} else {
				Groups.afk.remove(player);
				Groups.afkloc.remove(player.getName());
				Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is no longer AFK.");
			}
			return;
		}
		userFile.setValue(PlayerPaths.AFK.getPath(), value);
		if (value) {
			Groups.afk.add(player);
			Groups.afkloc.put(player.getName(), player.getLocation());
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is now AFK.");
			return;
		}
		Groups.afk.remove(player);
		Groups.afkloc.remove(player.getName());
		Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is no longer AFK.");
		return;
	}

	@Override
	public boolean isTeleportable() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.DISPLAYNAME, nickname);
			player.setPlayerListName(nickname);
			player.setDisplayName(nickname + "§r");
			return;
		}
		userFile.setValue(PlayerPaths.DISPLAYNAME.getPath(), nickname);
		player.setDisplayName(nickname);
		player.setPlayerListName(nickname + "§r");
		return;
	}

	@Override
	public boolean isFlying() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
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
			exists = true;
		}
		if (memory) {
			return (double) Transform.getValue(player, PlayerPaths.FLYSPEED);
		}
		return (double) userFile.getValue(PlayerPaths.FLYSPEED.getPath());
	}

	@Override
	public void setFlyingSpeed(double value) {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
	public int getWalkingSpeed() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
		}
		if (memory) {
			return (int) Transform.getValue(getBase(), PlayerPaths.WALKSPEED);
		}
		return (int) userFile.getValue(PlayerPaths.WALKSPEED.getPath());
	}

	@Override
	public void setWalkingSpeed(double value) {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
		}
		return getGamemode().equals(mode);
	}

	@Override
	public void setGamemode(Gamemode mode) {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
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
	public boolean hasInfobar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInfobar(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBubbled() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
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
			exists = true;
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
			exists = true;
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
			exists = true;
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
		return new LastLocation(this);
	}

	@Override
	public LogoutLocation getLogout() {
		return new LogoutLocation(this);
	}

	@Override
	public void setLoginTime() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.LOGIN, System.currentTimeMillis());
			return;
		}
		userFile.setValue(PlayerPaths.LOGIN.getPath(), System.currentTimeMillis());
	}

	@Override
	public long getLoginTime() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
		}
		if (memory) {
			Object a = Transform.getValue(player, PlayerPaths.LOGIN);
			if (a == null) {
				return -1;
			}
			return (long) a;
		}
		Object a = userFile.getValue(PlayerPaths.LOGIN.getPath());
		if (a == null) {
			return -1;
		}
		return (long) a;
	}

	@Override
	public void setLogoutTime() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
		}
		if (memory) {
			Transform.setValue(player, PlayerPaths.LOGOUT, System.currentTimeMillis());
			return;
		}
		userFile.setValue(PlayerPaths.LOGOUT.getPath(), System.currentTimeMillis());
	}

	@Override
	public long getLogoutTime() {
		if (!exists) {
			userFile.createConfig();
			exists = true;
		}
		if (memory) {
			Object a = Transform.getValue(player, PlayerPaths.LOGOUT);
			if (a == null) {
				return -1;
			}
			return (long) a;
		}
		Object a = userFile.getValue(PlayerPaths.LOGOUT.getPath());
		if (a == null) {
			return -1;
		}
		return (long) a;
	}

	@Override
	//Dont give the option to write to the memory config. Load the memory last.
	public IUser loginUpdate() {
		if (!exists) {
			getUserFile().createConfig();
			exists = true;
		}
		if (userFile.getValue(PlayerPaths.PLAYERNAME.getPath()) == null) {
			userFile.setValue(PlayerPaths.PLAYERNAME.getPath(), player.getName());
		}
		if (userFile.getValue(PlayerPaths.DISPLAYNAME.getPath()) == null) {
			userFile.setValue(PlayerPaths.DISPLAYNAME.getPath(), player.getName());
		}
		if (userFile.getValue(PlayerPaths.IP.getPath()) == null) {
			userFile.setValue(PlayerPaths.IP.getPath(), player.getAddress().getAddress().getHostAddress());
		}
		if (userFile.getValue(PlayerPaths.FLYING.getPath()) == null) {
			userFile.setValue(PlayerPaths.FLYING.getPath(), player.isFlying());
		}
		if (userFile.getValue(PlayerPaths.GAMEMODE.getPath()) == null) {
			userFile.setValue(PlayerPaths.GAMEMODE.getPath(), player.getGameMode().name());
		}
		if (userFile.getValue(PlayerPaths.OPPED.getPath()) == null) {
			userFile.setValue(PlayerPaths.OPPED.getPath(), player.isOp());
		}
		if (memory) {
			new Transform(player);
		}
		setLoginTime();
		getLast().setWorld(getWorld().getName());
		getLast().setX(getX());
		getLast().setY(getY());
		getLast().setZ(getZ());
		getLast().setYaw(getYaw());
		getLast().setPitch(getPitch());
		return this;
	}

	@Override
	public void logoutUpdate() {
		if (isAfk()) {
			setAfk(false);
		}
		if (isHidden()) {
			setHidden(false);
		}
		if (isBubbled()) {
			setBubbled(false);
		}
		setLogoutTime();
		getLogout().setWorld(getWorld().getName());
		getLogout().setX(getX());
		getLogout().setY(getY());
		getLogout().setZ(getZ());
		getLogout().setYaw(getYaw());
		getLogout().setPitch(getPitch());
		if (memory) {
			Transform.unload(player);
		}
		return;
	}

	@Override
	public void tp(Location location) {
		player.teleport(location);
	}

	@Override
	public IOfflineHome getHome(String home) {
		return new Home(this, home);
	}
}
