package com.njdaeger.java.configuration.enums;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public enum PlayerPaths {
	
	PLAYERNAME {

		@Override
		public String getPath() {
			return "playername";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	DISPLAYNAME {

		@Override
		public String getPath() {
			return "displayname";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	AFK {

		@Override
		public String getPath() {
			return "afk";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	HIDDEN {

		@Override
		public String getPath() {
			return "hidden";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	MESSAGEABLE {

		@Override
		public String getPath() {
			return "messageable";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	RANK {

		@Override
		public String getPath() {
			return "rank";
		}

		@Override
		public Object defValue() {
			return "default";
		}
		
	},
	MUTED {

		@Override
		public String getPath() {
			return "muted";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	SOCIALSPY {

		@Override
		public String getPath() {
			return "socialspy";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	TPTOGGLED {

		@Override
		public String getPath() {
			return "tptoggled";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	IP {

		@Override
		public String getPath() {
			return "ip";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	FLYING {

		@Override
		public String getPath() {
			return "flying";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	GAMEMODE {

		@Override
		public String getPath() {
			return "gamemode";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	GOD {

		@Override
		public String getPath() {
			return "god";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	FLYSPEED {

		@Override
		public String getPath() {
			return "flyspeed";
		}

		@Override
		public Object defValue() {
			return 1;
		}
		
	},
	WALKSPEED {

		@Override
		public String getPath() {
			return "walkspeed";
		}

		@Override
		public Object defValue() {
			return 1;
		}
		
	},
	OP {

		@Override
		public String getPath() {
			return "op";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGIN {

		@Override
		public String getPath() {
			return "last-login";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGOUT {

		@Override
		public String getPath() {
			return "last-logout";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LAST_WORLD {

		@Override
		public String getPath() {
			return "lastlocation.world";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LAST_X {

		@Override
		public String getPath() {
			return "lastlocation.x";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LAST_Y {

		@Override
		public String getPath() {
			return "lastlocation.y";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LAST_Z {

		@Override
		public String getPath() {
			return "lastlocation.z";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LAST_YAW {

		@Override
		public String getPath() {
			return "lastlocation.yaw";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LAST_PITCH {

		@Override
		public String getPath() {
			return "lastlocation.pitch";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGOUT_WORLD {

		@Override
		public String getPath() {
			return "logoutlocation.world";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGOUT_X {

		@Override
		public String getPath() {
			return "logoutlocation.x";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGOUT_Y {

		@Override
		public String getPath() {
			return "logoutlocation.y";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGOUT_Z {

		@Override
		public String getPath() {
			return "logoutlocation.z";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGOUT_YAW {

		@Override
		public String getPath() {
			return "logoutlocation.yaw";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	},
	LOGOUT_PITCH {

		@Override
		public String getPath() {
			return "logoutlocation.pitch";
		}

		@Override
		public Object defValue() {
			return null;
		}
		
	};
	
	public abstract String getPath();
	public abstract Object defValue();
	
	public static void checkExist(Player player) {
		UUID userID = player.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
		if(!dir.exists()) {
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
