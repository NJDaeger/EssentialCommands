package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.Groups;
import com.njdaeger.java.configuration.Location;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.IPlayerConfig;

public class PlayerConfigData extends PlayerConfig implements IPlayerConfig{
	
	private File path = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+player.getUniqueId());
	private File file = new File(path+File.separator+"user.yml");
	
	@Override
	public void setValue(String path, Object value) {
		if (!file.exists()) {
			this.createConfig();
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(path, value);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public YamlConfiguration getValue() {
		if (!file.exists()) {
			this.createConfig();
		}
		return YamlConfiguration.loadConfiguration(file);
	}
	@Override
	public void createConfig() {
		if(!path.exists()) {
			path.mkdirs();
			try {
				file.createNewFile();
				PlayerPaths.checkExist(player);
				System.out.println("Player configuration has been created for " + player.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
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
		setNick(getValue().getString(PlayerPaths.DISPLAYNAME.getPath()));
		setMuted();
		setSpying();
		setGod();
		setMessageable();
		setTpToggled();
		
	}

	@Override
	public void setMuted() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isMuted() == false) {
			if (Groups.muted.contains(player)) {
				Groups.muted.remove(player);
			}
			return;
		}
		else {
			if (!Groups.muted.contains(player)) {
				Groups.muted.add(player);
				this.setValue(PlayerPaths.MUTED.getPath(), true);
			}
			return;
		}
	}

	@Override
	public void setSpying() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isSpying() == false) {
			if (Groups.socialspy.contains(player)) {
				Groups.socialspy.remove(player);
			}
			return;
		}
		else {
			if (!Groups.socialspy.contains(player)) {
				Groups.socialspy.add(player);
				this.setValue(PlayerPaths.SOCIALSPY.getPath(), false);
				YamlConfiguration.loadConfiguration(file).set(PlayerPaths.SOCIALSPY.getPath(), false);
				try {
					YamlConfiguration.loadConfiguration(file).save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		}
		
	}

	@Override
	public void setGod() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isGod() == false) {
			if (Groups.god.contains(player)) {
				Groups.god.remove(player);
			}
			return;
		}
		else {
			if (!Groups.god.contains(player)) {
				Groups.god.add(player);
				YamlConfiguration.loadConfiguration(file).set(PlayerPaths.GOD.getPath(), false);
				try {
					YamlConfiguration.loadConfiguration(file).save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		}
	}

	@Override
	public void setMessageable() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isMessageable() == true) {
			if (Groups.nomessaging.contains(player)) {
				Groups.nomessaging.remove(player);
				this.setValue(PlayerPaths.MESSAGEABLE.getPath(), true);
			}
			return;
		}
		else {
			if (!Groups.nomessaging.contains(player)) {
				Groups.nomessaging.add(player);
				this.setValue(PlayerPaths.MESSAGEABLE.getPath(), false);
			}
			return;
		}
		
	}

	@Override
	public void setAfk() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isAfk() == true) {
			Groups.afk.remove(player);
			Groups.afkloc.remove(player.getName());
			player.setCollidable(true);
			System.out.println("1");
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is no longer AFK.");
			this.setValue(PlayerPaths.AFK.getPath(), false);
		}
		else {
			Groups.afk.add(player);
			Groups.afkloc.put(player.getName(), player.getLocation());
			player.setCollidable(false);
			System.out.println("2");
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getDisplayName() + " is now AFK.");
			this.setValue(PlayerPaths.AFK.getPath(), true);
		}
	}

	@Override
	public void setTpToggled() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isTpToggled() == true) {
			if (Groups.tptoggled.contains(player)) {
				Groups.tptoggled.remove(player);
			}
			return;
		}
		else {
			if (!Groups.tptoggled.contains(player)) {
				Groups.tptoggled.add(player);
				
				YamlConfiguration.loadConfiguration(file).set(PlayerPaths.TPTOGGLED.getPath(), false);
				try {
					YamlConfiguration.loadConfiguration(file).save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		}
	}

	@Override
	public void setGroup(String group) {
		if (!file.exists()) {
			this.createConfig();
		}
		YamlConfiguration.loadConfiguration(file).set(PlayerPaths.RANK.getPath(), group);
		try {
			YamlConfiguration.loadConfiguration(file).save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setNick(String nickname) {
		if (!file.exists()) {
			this.createConfig();
		}
		if (nickname.equals(player.getName())) {
			this.setValue(PlayerPaths.DISPLAYNAME.getPath(), null);
			return;
		}
		this.setValue(PlayerPaths.DISPLAYNAME.getPath(), nickname + "&r");
		player.setDisplayName(ChatColor.translateAlternateColorCodes('&', nickname + "&r"));
	}
	
	@Override
	public void setNickAuto() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (getValue().getString(PlayerPaths.DISPLAYNAME.getPath()) == null) {
			return;
		}
		if (player.getDisplayName() == player.getName()) {
			this.setValue(PlayerPaths.DISPLAYNAME.getPath(), null);
			return;
		}
		else {
			player.setDisplayName(ChatColor.translateAlternateColorCodes('&', PlayerPaths.DISPLAYNAME.getPath()) + ChatColor.WHITE);
			return;
		}
	}

	@Override
	public void setFlying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGamemode(String gamemode) {
		if (gamemode.equalsIgnoreCase("creative") || gamemode.equalsIgnoreCase("1")) {
			player.setGameMode(GameMode.CREATIVE);
			YamlConfiguration.loadConfiguration(file).set(PlayerPaths.GAMEMODE.getPath(), GameMode.CREATIVE);
			try {
				YamlConfiguration.loadConfiguration(file).save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (gamemode.equalsIgnoreCase("survival") || gamemode.equalsIgnoreCase("0")) {
			player.setGameMode(GameMode.SURVIVAL);
			YamlConfiguration.loadConfiguration(file).set(PlayerPaths.GAMEMODE.getPath(), GameMode.SURVIVAL);
			try {
				YamlConfiguration.loadConfiguration(file).save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (gamemode.equalsIgnoreCase("adventure") || gamemode.equalsIgnoreCase("2")) {
			player.setGameMode(GameMode.ADVENTURE);
			YamlConfiguration.loadConfiguration(file).set(PlayerPaths.GAMEMODE.getPath(), GameMode.ADVENTURE);
			try {
				YamlConfiguration.loadConfiguration(file).save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (gamemode.equalsIgnoreCase("spectator") || gamemode.equalsIgnoreCase("3")) {
			player.setGameMode(GameMode.SPECTATOR);
			YamlConfiguration.loadConfiguration(file).set(PlayerPaths.GAMEMODE.getPath(), GameMode.SPECTATOR);
			try {
				YamlConfiguration.loadConfiguration(file).save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		return;
	}
	
	@Override
	public void setFlySpeed(double speed) {
		if (speed > 10)  {
			double b = speed - 10;
			speed -= b;
		}
		if (speed < 0) {
			speed -= speed;
		}
		double a = speed / 10;
		float value = (float) a;
		player.setFlySpeed(value);
		this.setValue(PlayerPaths.FLYSPEED.getPath(), speed);
	}

	@Override
	public void setWalkingSpeed(double speed) {
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
		this.setValue(PlayerPaths.WALKSPEED.getPath(), speed);
	}

	@Override
	public void setOp() {
		if (!file.exists()) {
			this.createConfig();
		}
		else {
			player.setOp(true);
			YamlConfiguration.loadConfiguration(file).set(PlayerPaths.OP.getPath(), true);
			try {
				YamlConfiguration.loadConfiguration(file).save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void setDeopped() {
		if (!file.exists()) {
			this.createConfig();
		}
		else {
			player.setOp(false);
			YamlConfiguration.loadConfiguration(file).set(PlayerPaths.OP.getPath(), false);
			try {
				YamlConfiguration.loadConfiguration(file).save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void setOpAuto() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (player.isOp() && isOp() == false) {
			setDeopped();
			return;
		}
		if (!player.isOp() && isOp() == true) {
			setOp();
			return;
		}
		return;
	}

	@Override
	public boolean isMuted() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getBoolean(PlayerPaths.MUTED.getPath());
	}

	@Override
	public boolean isSpying() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getBoolean(PlayerPaths.SOCIALSPY.getPath());
	}

	@Override
	public boolean isGod() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getBoolean(PlayerPaths.GOD.getPath());
	}

	@Override
	public boolean isMessageable() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getBoolean(PlayerPaths.MESSAGEABLE.getPath());
	}

	@Override
	public boolean isAfk() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getBoolean(PlayerPaths.AFK.getPath());
	}

	@Override
	public boolean isTpToggled() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getBoolean(PlayerPaths.TPTOGGLED.getPath());
	}

	@Override
	public String getGroup() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getString(PlayerPaths.RANK.getPath());
	}

	@Override
	public String getNick() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getString(PlayerPaths.DISPLAYNAME.getPath());
	}

	@Override
	public boolean isFlying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getGamemode() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getString(PlayerPaths.GAMEMODE.getPath());
	}

	@Override
	public int getFlySpeed() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getInt(PlayerPaths.FLYSPEED.getPath());
	}

	@Override
	public int getWalkingSpeed() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getInt(PlayerPaths.WALKSPEED.getPath());
	}

	@Override
	public boolean isOp() {
		if (!file.exists()) {
			this.createConfig();
		}
		return this.getValue().getBoolean(PlayerPaths.OP.getPath());
	}

	@Override
	public Location getLocations() {
		return new Location();
	}

	@Override
	public boolean isBubbleMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBubbleMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBubbleAuto() {
		// TODO Auto-generated method stub
		
	}
}
