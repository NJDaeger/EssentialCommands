package com.njdaeger.java.configuration.enums;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.Core;

public enum PlayerPaths {

	CFG_VERSION("conf-version", Core.getInstance().getDescription().getVersion()),
	PLAYERNAME("playername", null),
	DISPLAYNAME("displayname", null),
	AFK("afk", false),
	HIDDEN("hidden", false),
	MESSAGEABLE("messageable", true),
	RANK("rank", "default"),
	MUTED("muted", false),
	SOCIALSPY("socialspy", false),
	TPTOGGLED("tptoggled", false),
	IP("ip", null),
	FLYING("flying", null),
	GAMEMODE("gamemode", null),
	GOD("god", false),
	FLYSPEED("flyspeed", 1),
	WALKSPEED("walkspeed", 1),
	OPPED("opped", null),
	LOGIN("last-login", null),
	LOGOUT("last-logout", null),
	LAST_WORLD("lastlocation.world", null),
	LAST_X("lastlocation.x", null),
	LAST_Y("lastlocation.y", null),
	LAST_Z("lastlocation.z", null),
	LAST_YAW("lastlocation.yaw", null),
	LAST_PITCH("lastlocation.pitch", null),
	LOGOUT_WORLD("logoutlocation.world", null),
	LOGOUT_X("logoutlocation.x", null),
	LOGOUT_Y("logoutlocation.y", null),
	LOGOUT_Z("logoutlocation.z", null),
	LOGOUT_YAW("logoutlocation.yaw", null),
	LOGOUT_PITCH("logoutlocation.world", null),
	BUBBLED("bubbled", false),
	LASTMOVE("lastmove", null);

	public String path;
	public Object value;

	PlayerPaths(String path, Object value) {
		this.path = path;
		this.value = value;
	}

	public Object defValue() {
		return this.value;
	}

	public String getPath() {
		return this.path;
	}

	public static void checkExist(Player player) {
		UUID userID = player.getUniqueId();
		File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
				+ userID);
		File dir1 = new File(dir + File.separator + "user.yml");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!dir1.exists()) {
			try {
				dir1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration c = YamlConfiguration.loadConfiguration(dir1);
		for (PlayerPaths path : PlayerPaths.values()) {
			if (!c.contains(path.getPath())) {
				c.set(path.getPath(), path.defValue());
				try {
					c.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
