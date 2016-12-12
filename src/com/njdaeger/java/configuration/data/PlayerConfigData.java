package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginUpdate() {
		setMuted();
		
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
				YamlConfiguration.loadConfiguration(file).set(PlayerPaths.MUTED.getPath(), false);
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
		if (isSpying() == false) {
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
		if (isSpying() == false) {
			if (Groups.nomessaging.contains(player)) {
				Groups.nomessaging.remove(player);
			}
			return;
		}
		else {
			if (!Groups.nomessaging.contains(player)) {
				Groups.nomessaging.add(player);
				YamlConfiguration.loadConfiguration(file).set(PlayerPaths.MESSAGEABLE.getPath(), false);
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
	public void setAfk() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isSpying() == false) {
			if (Groups.afk.contains(player)) {
				Groups.afk.remove(player);
			}
			return;
		}
		else {
			if (!Groups.afk.contains(player)) {
				Groups.afk.add(player);
				YamlConfiguration.loadConfiguration(file).set(PlayerPaths.AFK.getPath(), false);
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
	public void setTpToggled() {
		if (!file.exists()) {
			this.createConfig();
		}
		if (isSpying() == false) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNick(String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFlying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGamemode(String gamemode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFlySpeed(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWalkingSpeed(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastLocation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLogoutLocation() {
		
	}

	@Override
	public boolean isMuted() {
		if (!file.exists()) {
			this.createConfig();
		}
		return YamlConfiguration.loadConfiguration(file).getBoolean(PlayerPaths.MUTED.getPath());
	}

	@Override
	public boolean isSpying() {
		if (!file.exists()) {
			this.createConfig();
		}
		return YamlConfiguration.loadConfiguration(file).getBoolean(PlayerPaths.SOCIALSPY.getPath());
	}

	@Override
	public boolean isGod() {
		if (!file.exists()) {
			this.createConfig();
		}
		return YamlConfiguration.loadConfiguration(file).getBoolean(PlayerPaths.GOD.getPath());
	}

	@Override
	public boolean isMessageable() {
		if (!file.exists()) {
			this.createConfig();
		}
		return YamlConfiguration.loadConfiguration(file).getBoolean(PlayerPaths.MESSAGEABLE.getPath());
	}

	@Override
	public boolean isAfk() {
		if (!file.exists()) {
			this.createConfig();
		}
		return YamlConfiguration.loadConfiguration(file).getBoolean(PlayerPaths.AFK.getPath());
	}

	@Override
	public boolean isTpToggled() {
		if (!file.exists()) {
			this.createConfig();
		}
		return YamlConfiguration.loadConfiguration(file).getBoolean(PlayerPaths.TPTOGGLED.getPath());
	}

	@Override
	public String getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFlying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getGamemode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFlySpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWalkingSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isOp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Location getLocations() {
		return new Location();
	}

}
