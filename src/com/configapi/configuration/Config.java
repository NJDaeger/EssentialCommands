package com.configapi.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.configapi.configuration.enums.Path;
import com.configapi.configuration.interfaces.IConfiguration;

public class Config implements IConfiguration{

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IConfiguration#newConfig()
	 */
	public void newConfig() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				Path.checkExist();
				Bukkit.getLogger().warning("Config.yml was not found... Creating!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			Path.checkExist();
			return;
		}
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IConfiguration#getConfig()
	 */
	public YamlConfiguration getConfig() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (file.exists()) {
			return YamlConfiguration.loadConfiguration(file);
		}
		return null;
	}

	public boolean isGroupmanagerEnabled() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_GM));
		}
		else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.get(Path.ENABLE_GM))) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_GM));
			}
			else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_GM));
			}
		}
	}

	public void setGroupmanagerEnabled(boolean enable) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.get(Path.ENABLE_GM), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_GM)+"\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.get(Path.ENABLE_GM), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_GM)+"\" has been changed to " + enable);
		return;
	}

	public boolean isBannermanagerEnabled() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_BM));
		}
		else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.get(Path.ENABLE_BM))) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_BM));
			}
			else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_BM));
			}
		}
	}

	public void setBannermanagerEnabled(boolean enable) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.get(Path.ENABLE_BM), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_BM)+"\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.get(Path.ENABLE_BM), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_BM)+"\" has been changed to " + enable);
		return;
	}

	public boolean isChatcolorEnabled() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_CC));
		}
		else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.get(Path.ENABLE_CC))) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_CC));
			}
			else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_CC));
			}
		}
	}

	public void setChatcolorEnabled(boolean enable) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.get(Path.ENABLE_CC), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_CC)+"\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.get(Path.ENABLE_CC), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_CC)+"\" has been changed to " + enable);
		return;
		
	}

	public boolean isHomesEnabled() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_HOMES));
		}
		else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.get(Path.ENABLE_HOMES))) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_HOMES));
			}
			else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_HOMES));
			}
		}
	}

	public void setHomesrEnabled(boolean enable) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.get(Path.ENABLE_HOMES), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_HOMES)+"\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.get(Path.ENABLE_HOMES), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_HOMES)+"\" has been changed to " + enable);
		return;
		
	}

	public boolean isWarpsEnabled() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_WARPS));
		}
		else {
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			if (c.contains(Path.get(Path.ENABLE_WARPS))) {
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_WARPS));
			}
			else {
				Path.checkExist();
				return YamlConfiguration.loadConfiguration(file).getBoolean(Path.get(Path.ENABLE_WARPS));
			}
		}
	}

	public void setWarpsEnabled(boolean enable) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			this.newConfig();
			YamlConfiguration.loadConfiguration(file).set(Path.get(Path.ENABLE_WARPS), enable);
			Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_WARPS)+"\" has been changed to " + enable);
			return;
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		c.set(Path.get(Path.ENABLE_WARPS), enable);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getLogger().info("[EssentialCommands] Configuration option \""+Path.get(Path.ENABLE_WARPS)+"\" has been changed to " + enable);
		return;
		
	}

	public boolean isWarpLimit() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setWarpLimitEnabled(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	public String getWarpLimit() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setWarpLimit(String value) {
		// TODO Auto-generated method stub
		
	}

	public boolean isWarpLimitWorld() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setWorldWarpLimitEnabled(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	public String getWorldWarpLimit() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setWorldWarpLimit(String value) {
		// TODO Auto-generated method stub
		
	}
}
