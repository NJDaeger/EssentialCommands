package com.njdaeger.java.configuration.enums;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.wrapper.User;

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

	public static void checkExist(UUID id, File dir, File file) {
		User user = Core.getUser(id);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		UserFile c = user.getUserFile();
		for (PlayerPaths path : PlayerPaths.values()) {
			if (c.getValue(path) == null && path.defValue() != null) {
				c.setValue(path, path.defValue());
			}
		}
	}
}
