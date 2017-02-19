package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.configuration.enums.Path;
import com.njdaeger.java.configuration.interfaces.IBaseConf;
import com.njdaeger.java.configuration.interfaces.IConfig;
import com.njdaeger.java.configuration.interfaces.Resettable;

public class Config implements IConfig, IBaseConf, Resettable {

	private File file;
	private File path;
	private YamlConfiguration ymlfile;

	public Config() {
		this.path = new File("plugins" + File.separator + "EssentialCommands");
		this.file = new File(path + File.separator + "config.yml");
		this.ymlfile = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public YamlConfiguration getYamlFile() {
		return ymlfile;
	}

	@Override
	public Object getValue(String path) {
		if (ymlfile.get(path) == null) {
			return Path.getFromString(path).defValue();
		}
		return ymlfile.get(path);
	}

	@Override
	public void setValue(String path, Object value) {
		ymlfile.set(path, value);
		try {
			ymlfile.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		return file.exists();
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
	public Config createConfig() {
		if (!path.exists()) {
			path.mkdirs();
		}
		if (!exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Path.checkExist();
		return this;
	}

	@Override
	public void deleteConfig() {
		file.delete();
	}

	@Override
	public Config resetConfog() {
		deleteConfig();
		return createConfig();
	}

	@Override
	public boolean loadInMemory() {
		return (boolean) getValue(Path.LOAD_TO_MEMORY.getPath());
	}

	@Override
	public void setLoadInMemory(boolean value) {
		setValue(Path.LOAD_TO_MEMORY.getPath(), value);
	}

	@Override
	public boolean isNJPermsEnabled() {
		return (boolean) getValue(Path.ENABLE_NJP.getPath());
	}

	@Override
	public void setNJPermsEnabled(boolean value) {
		setValue(Path.ENABLE_NJP.getPath(), value);
	}

	@Override
	public boolean isAnnotationsEnabled() {
		return (boolean) getValue(Path.ENABLE_ANNO.getPath());
	}

	@Override
	public void setAnnotationsEnabled(boolean value) {
		setValue(Path.ENABLE_ANNO.getPath(), value);
	}

	@Override
	public boolean isServerProtectEnabled() {
		return (boolean) getValue(Path.ENABLE_SP.getPath());
	}

	@Override
	public void setServerProtectEnabled(boolean value) {
		setValue(Path.ENABLE_SP.getPath(), value);
	}

	@Override
	public boolean isLoginClearanceEnabled() {
		return (boolean) getValue(Path.ENABLE_LC.getPath());
	}

	@Override
	public void setLoginClearanceEnabled(boolean value) {
		setValue(Path.ENABLE_LC.getPath(), value);
	}

	@Override
	public void setWarpLimit(int limit) {
		setValue(Path.WARPS_WARPLIMIT.getPath(), limit);
	}

	@Override
	public int getWarpLimit() {
		return (int) getValue(Path.WARPS_WARPLIMIT.getPath());
	}

	@Override
	public String getOpNameColor() {
		return (String) getValue(Path.OP_NAME_COLOR.getPath());
	}

	@Override
	public void setOpNameColor(String colorCode) {
		setValue(Path.OP_NAME_COLOR.getPath(), colorCode);
	}

	@Override
	public void setNickPrefix(String prefix) {
		setValue(Path.NICKNAME_PREFIX.getPath(), prefix);
	}

	@Override
	public String getNickPrefix() {
		return (String) getValue(Path.NICKNAME_PREFIX.getPath());
	}

	@Override
	public void setMaxNickLength(int max) {
		setValue(Path.NICKNAME_MAXLENGTH.getPath(), max);
	}

	@Override
	public int getMaxNickLength() {
		return (int) getValue(Path.NICKNAME_MAXLENGTH.getPath());
	}

	@Override
	public List<String> getBlacklistedCommands() {
		String[] commands;
		commands = (String[]) getValue(Path.BLACKLIST_COMMANDS.getPath());
		return Arrays.asList(commands);
	}

	@Override
	public void addBlacklistedCommands(List<String> commands) {
		List<String> lastCommands = getBlacklistedCommands();
		lastCommands.addAll(commands);
		commands.clear();
		setValue(Path.BLACKLIST_COMMANDS.getPath(), lastCommands);
		lastCommands.clear();
	}

	@Override
	public void removeBlacklistedCommands(List<String> commands) {
		List<String> lastCommands = getBlacklistedCommands();
		List<List<String>> list = Arrays.asList(commands);
		for (int i = 0; i <= commands.size(); i++) {
			for (String command : list.get(i)) {
				if (lastCommands.contains(command)) {
					lastCommands.remove(command);
				}
			}
		}
		return;
	}

	@Override
	public boolean isConsoleNotified() {
		return (boolean) getValue(Path.BLACKLIST_COMMANDS_NOTIFY_CSL.getPath());
	}

	@Override
	public void setConsoleNotified(boolean enable) {
		setValue(Path.BLACKLIST_COMMANDS_NOTIFY_CSL.getPath(), enable);
	}
}
