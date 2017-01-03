package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.configuration.enums.Path;
import com.njdaeger.java.configuration.interfaces.IConfiguration;

public class ConfigData implements IConfiguration {

	private File file = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "config.yml");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.configapi.configuration.interfaces.IConfiguration#newConfig()
	 */
	@Override
	public void newConfig() {
		if (!file.exists()) {
			try {
				file.createNewFile();
				Path.checkExist();
				Bukkit.getLogger().warning("Config.yml was not found... Creating!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Path.checkExist();
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.configapi.configuration.interfaces.IConfiguration#getConfig()
	 */
	@Override
	public YamlConfiguration getConfig() {
		if (file.exists()) {
			return YamlConfiguration.loadConfiguration(file);
		}
		return null;
	}

	@Override
	public boolean isNJPermsEnabled() {
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_NJP.getPath());
		} else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.ENABLE_NJP.getPath())) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_NJP.getPath());
			} else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_NJP.getPath());
			}
		}
	}

	@Override
	public void setNJPermsEnabled(boolean enable) {
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.ENABLE_NJP.getPath(), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_NJP.getPath()
					+ "\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.ENABLE_NJP.getPath(), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_NJP.getPath()
				+ "\" has been changed to " + enable);
		return;
	}

	@Override
	public boolean isBannermanagerEnabled() {
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_BM.getPath());
		} else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.ENABLE_BM.getPath())) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_BM.getPath());
			} else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_BM.getPath());
			}
		}
	}

	@Override
	public void setBannermanagerEnabled(boolean enable) {
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.ENABLE_BM.getPath(), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_BM.getPath()
					+ "\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.ENABLE_BM.getPath(), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_BM.getPath()
				+ "\" has been changed to " + enable);
		return;
	}

	@Override
	public boolean isCodesEnabled() {
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_CODES.getPath());
		} else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.ENABLE_CODES.getPath())) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_CODES.getPath());
			} else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_CODES.getPath());
			}
		}
	}

	@Override
	public void setCodesEnabled(boolean enable) {
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.ENABLE_CODES.getPath(), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_CODES.getPath()
					+ "\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.ENABLE_CODES.getPath(), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_CODES.getPath()
				+ "\" has been changed to " + enable);
		return;

	}

	@Override
	public boolean isAnnotationsEnabled() {
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_ANNO.getPath());
		} else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.ENABLE_ANNO.getPath())) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_ANNO.getPath());
			} else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_ANNO.getPath());
			}
		}
	}

	@Override
	public void setAnnotationsEnabled(boolean enable) {
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.ENABLE_ANNO.getPath(), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_ANNO.getPath()
					+ "\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.ENABLE_ANNO.getPath(), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_ANNO.getPath()
				+ "\" has been changed to " + enable);
		return;

	}

	@Override
	public boolean isServerprotectEnabled() {
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_SP.getPath());
		} else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.ENABLE_SP.getPath())) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_SP.getPath());
			} else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_SP.getPath());
			}
		}
	}

	@Override
	public void setServerprotectEnabled(boolean enable) {
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.ENABLE_SP.getPath(), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_SP.getPath()
					+ "\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.ENABLE_SP.getPath(), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_SP.getPath()
				+ "\" has been changed to " + enable);
		return;

	}

	@Override
	public boolean isLoginclearanceEnabled() {
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_LC.getPath());
		} else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.ENABLE_LC.getPath())) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_LC.getPath());
			} else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.ENABLE_LC.getPath());
			}
		}
	}

	@Override
	public void setLoginclearanceEnabled(boolean enable) {
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.ENABLE_LC.getPath(), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_LC.getPath()
					+ "\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.ENABLE_LC.getPath(), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \"" + Path.ENABLE_LC.getPath()
				+ "\" has been changed to " + enable);
		return;
	}

	@Override
	public boolean isWarpLimit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWarpLimitEnabled(boolean enable) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getWarpLimit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWarpLimit(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWarpLimitWorld() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWorldWarpLimitEnabled(boolean enable) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getWorldWarpLimit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWorldWarpLimit(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOpNameColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOpNameColor(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNicknamePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNicknamePrefix(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxNicknameLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxNicknameLength(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getBlacklistedCommands() {
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getStringList(Path.BLACKLIST_COMMANDS.getPath());
		} else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (!c.contains(Path.BLACKLIST_COMMANDS.getPath())) {
				Path.checkExist();
				return c.getStringList(Path.BLACKLIST_COMMANDS.getPath());
			} else
				return c.getStringList(Path.BLACKLIST_COMMANDS.getPath());
		}
	}

	@Override
	public void addBlacklistedCommands(List<String> commands) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBlacklistedCommands(List<String> commands) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCommandNotifyCsl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCommandNotifyCsl(boolean enable) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCommandNotifyCslMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNotifyCslMessage(String message) {
		// TODO Auto-generated method stub

	}
}
