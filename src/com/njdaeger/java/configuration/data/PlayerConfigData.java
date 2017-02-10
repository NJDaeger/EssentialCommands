package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.njdaeger.java.Groups;
import com.njdaeger.java.configuration.Location;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.IBasePlayerConf;
import com.njdaeger.java.configuration.interfaces.IPlayerConfig;
import com.njdaeger.java.essentials.commands.player.GamemodeCommand.Mode;

import net.md_5.bungee.api.ChatColor;

public class PlayerConfigData implements IPlayerConfig, IBasePlayerConf {

	//Player object
	private Player player;
	//The player file path.
	private File path = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users"
			+ File.separator + player.getUniqueId());
	//The main configuration file of the player.
	private File file = new File(path + File.separator + "user.yml");
	//The YAML file the players configuration is in.
	private YamlConfiguration yamlfile = YamlConfiguration.loadConfiguration(file);

	/**
	 * Gets an online player's configuration files.
	 * 
	 * @param player Player to get the configuration files from.
	 */
	public PlayerConfigData(Player player) {
		this.player = player;
	}

	/**
	 * Gets an offline player's configuration files.
	 * 
	 * @param offlinePlayer The offline player to get the configuration files
	 *            from.
	 */
	public PlayerConfigData(String offlinePlayer) {
		if (Database.getDatabase("playerdata").getBase() == null) {
			Database.getDatabase("playerdata").create();
			UUID id = UUID.fromString(Database.getDatabase("playerdata").getEntry(offlinePlayer));
			this.player = (Player) Bukkit.getOfflinePlayer(id);
			return;
		}
		UUID id = UUID.fromString(Database.getDatabase("playerdata").getEntry(offlinePlayer));
		this.player = (Player) Bukkit.getOfflinePlayer(id);
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public File getPath() {
		return path;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public boolean exists() {
		if (getFile().exists()) {
			return true;
		}
		return false;
	}

	@Override
	public YamlConfiguration getYamlFile() {
		return yamlfile;
	}

	@Override
	public void setValue(String path, Object value) {
		if (!exists()) {
			createConfig();
		}
		yamlfile.set(path, value);
		try {
			yamlfile.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getValue(String path) {
		if (!exists()) {
			createConfig();
		}
		return getYamlFile().get(path);
	}

	@Override
	public void createConfig() {
		if (!getPath().exists()) {
			getPath().mkdirs();
			try {
				getFile().createNewFile();
				PlayerPaths.checkExist(player);
				System.out.println("Player configuration has been created for " + player.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (!getFile().exists()) {
			try {
				getFile().createNewFile();
				PlayerPaths.checkExist(player);
				System.out.println("Player configuration has been created for " + player.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@Override
	public void logoutUpdate() {
	}

	@Override
	public void loginUpdate() {
		setNick((String) getValue(PlayerPaths.DISPLAYNAME.getPath()));
		setMuted();
		setSpying();
		setGod();
		setMessageable();
		setTpToggled();

	}

	@Override
	public void setMuted() {
		if (!exists()) {
			createConfig();
		}
		if (!isMuted()) {
			Groups.muted.add(player);
			setValue(PlayerPaths.MUTED.getPath(), true);
			return;
		}
		Groups.muted.remove(player);
		setValue(PlayerPaths.MUTED.getPath(), false);
	}

	@Override
	public void setSpying() {
		if (!exists()) {
			createConfig();
		}
		if (!isSpying()) {
			Groups.socialspy.add(player);
			setValue(PlayerPaths.SOCIALSPY.getPath(), true);
			return;
		}
		Groups.socialspy.remove(player);
		setValue(PlayerPaths.SOCIALSPY.getPath(), false);
	}

	@Override
	public void setGod() {
		if (!exists()) {
			createConfig();
		}
		if (!isGod()) {
			Groups.god.add(player);
			player.setInvulnerable(true);
			setValue(PlayerPaths.GOD.getPath(), true);
			return;
		}
		Groups.god.remove(player);
		player.setInvulnerable(false);
		setValue(PlayerPaths.GOD.getPath(), false);

	}

	@Override
	public void setMessageable() {
		if (!exists()) {
			createConfig();
		}
		if (!isMessageable()) {
			Groups.nomessaging.add(player);
			setValue(PlayerPaths.MESSAGEABLE.getPath(), true);
			return;
		}
		Groups.nomessaging.remove(player);
		setValue(PlayerPaths.MESSAGEABLE.getPath(), false);
	}

	@Override
	public void setAfk() {
		if (!exists()) {
			createConfig();
		}
		if (!isAfk()) {
			Groups.afk.add(player);
			Groups.afkloc.put(player.getName(), player.getLocation());
			player.setCollidable(false);
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is now AFK.");
			setValue(PlayerPaths.AFK.getPath(), true);
			return;
		}
		Groups.afk.remove(player);
		Groups.afkloc.remove(player.getName());
		player.setCollidable(true);
		Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is no longer AFK.");
		setValue(PlayerPaths.AFK.getPath(), false);
	}

	@Override
	public void setTpToggled() {
		if (!exists()) {
			createConfig();
		}
		if (!isTpToggled()) {
			Groups.tptoggled.add(player);
			setValue(PlayerPaths.TPTOGGLED.getPath(), true);
			return;
		}
		Groups.tptoggled.remove(player);
		setValue(PlayerPaths.TPTOGGLED.getPath(), false);
	}

	@Override
	public void setGroup(String group) {
		if (!exists()) {
			this.createConfig();
		}
		setValue(PlayerPaths.RANK.getPath(), group);
	}

	@Override
	public void setNick(String nickname) {
		if (!exists()) {
			createConfig();
		}
		if (nickname.equals(player.getName())) {
			setValue(PlayerPaths.DISPLAYNAME.getPath(), null);
			player.setDisplayName(player.getName());
			return;
		}
		setValue(PlayerPaths.DISPLAYNAME.getPath(), nickname + "&r");
		player.setDisplayName(ChatColor.translateAlternateColorCodes('&', nickname + "&r"));
	}

	@Override
	public void setFlying() {
		if (!exists()) {
			createConfig();
		}
		if (!isFlying()) {
			player.setFlying(true);
			player.setAllowFlight(true);
			setValue(PlayerPaths.FLYING.getPath(), true);
			return;
		}
		player.setFlying(false);
		player.setAllowFlight(false);
		setValue(PlayerPaths.FLYING.getPath(), false);
	}

	@Override
	public void setGamemode(String gamemode) {
		if (!exists()) {
			createConfig();
		}
		Mode mode = Mode.getAliasUsed(gamemode);
		switch (mode) {
		case CREATIVE:
			player.setGameMode(GameMode.CREATIVE);
			setValue(PlayerPaths.GAMEMODE.getPath(), GameMode.CREATIVE.name());
		case SURVIVAL:
			player.setGameMode(GameMode.SURVIVAL);
			setValue(PlayerPaths.GAMEMODE.getPath(), GameMode.SURVIVAL.name());
		case ADVENTURE:
			player.setGameMode(GameMode.ADVENTURE);
			setValue(PlayerPaths.GAMEMODE.getPath(), GameMode.ADVENTURE.name());
		case SPECTATOR:
			player.setGameMode(GameMode.SPECTATOR);
			setValue(PlayerPaths.GAMEMODE.getPath(), GameMode.SPECTATOR.name());
			return;
		default:
			return;
		}
	}

	@Override
	public void setFlySpeed(double speed) {
		if (!exists()) {
			createConfig();
		}
		if (speed > 10) {
			double b = speed - 10;
			speed -= b;
		}
		if (speed < 0) {
			speed -= speed;
		}
		double a = speed / 10;
		float value = (float) a;
		player.setFlySpeed(value);
		setValue(PlayerPaths.FLYSPEED.getPath(), speed);
	}

	@Override
	public void setWalkingSpeed(double speed) {
		if (!exists()) {
			createConfig();
		}
		double a = 0;
		a += ((19 * speed) - Math.pow(speed, 2)) / 90;
		if (a > 1) {
			double b = a - 1;
			a -= b;
			System.out.println(a + "trig");
		}
		if (a < 0) {
			a -= a;
			System.out.println(a);
		}
		System.out.println(a);
		float value = Float.parseFloat(Double.toString(a));
		player.setWalkSpeed(value);
		setValue(PlayerPaths.WALKSPEED.getPath(), speed);
	}

	@Override
	public void setOp() {
		if (!exists()) {
			createConfig();
		}
		if (!isOp()) {
			player.setOp(true);
			setValue(PlayerPaths.OPPED.getPath(), true);
			return;
		}
		player.setOp(false);
		setValue(PlayerPaths.OPPED.getPath(), false);
	}

	@Override
	public boolean isMuted() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.MUTED.getPath());
	}

	@Override
	public boolean isSpying() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.SOCIALSPY.getPath());
	}

	@Override
	public boolean isGod() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.GOD.getPath());
	}

	@Override
	public boolean isMessageable() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.MESSAGEABLE.getPath());
	}

	@Override
	public boolean isAfk() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.AFK.getPath());
	}

	@Override
	public boolean isTpToggled() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.TPTOGGLED.getPath());
	}

	@Override
	public boolean isFlying() {
		if (!exists()) {
			createConfig();
		}
		if (player.isFlying() || player.getAllowFlight()) {
			return true;
		}
		return false;
	}

	@Override
	public String getGroup() {
		if (!exists()) {
			createConfig();
		}
		return (String) getValue(PlayerPaths.RANK.getPath());
	}

	@Override
	public String getNick() {
		if (!exists()) {
			createConfig();
		}
		return (String) getValue(PlayerPaths.DISPLAYNAME.getPath());
	}

	@Override
	public String getGamemode() {
		if (!exists()) {
			createConfig();
		}
		return (String) getValue(PlayerPaths.GAMEMODE.getPath());
	}

	@Override
	public int getFlySpeed() {
		if (!exists()) {
			createConfig();
		}
		return (int) getValue(PlayerPaths.FLYSPEED.getPath());
	}

	@Override
	public int getWalkingSpeed() {
		if (!exists()) {
			createConfig();
		}
		return (int) getValue(PlayerPaths.WALKSPEED.getPath());
	}

	@Override
	public boolean isOp() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.OPPED.getPath());
	}

	@Override
	public Location getLocations() {
		return new Location(player);
	}

	@Override
	public boolean isBubbleMode() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.IS_BUBBLED.getPath());
	}

	@Override
	public void setBubbleMode() {
		if (!exists()) {
			createConfig();
		}
		if (!isBubbleMode()) {
			setValue(PlayerPaths.IS_BUBBLED.getPath(), true);
			return;
		}
		setValue(PlayerPaths.IS_BUBBLED.getPath(), false);
	}

	@Override
	public boolean isHidden() {
		if (!exists()) {
			createConfig();
		}
		return (boolean) getValue(PlayerPaths.HIDDEN.getPath());
	}

	@Override
	public void setHidden() {
		PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, 100000000, 1, false, false);
		if (!exists()) {
			createConfig();
		}
		if (!isHidden()) {
			setValue(PlayerPaths.HIDDEN.getPath(), true);
			player.hidePlayer(player);
			player.addPotionEffect(effect);
			return;
		}
		setValue(PlayerPaths.HIDDEN.getPath(), true);
		player.showPlayer(player);
		player.removePotionEffect(PotionEffectType.INVISIBILITY);
		return;
	}
}
