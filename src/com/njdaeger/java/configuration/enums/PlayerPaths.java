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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	DISPLAYNAME {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	AFK {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	HIDDEN {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	MESSAGEABLE {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	RANK {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	MUTED {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	SOCIALSPY {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	TPTOGGLED {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	IP {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	FLYING {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	GAMEMODE {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	GOD {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	FLYSPEED {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	WALKSPEED {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	OP {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGIN {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGOUT {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LAST_WORLD {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LAST_X {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LAST_Y {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LAST_Z {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LAST_YAW {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LAST_PITCH {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGOUT_WORLD {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGOUT_X {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGOUT_Y {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGOUT_Z {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGOUT_YAW {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	LOGOUT_PITCH {

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	public abstract String getPath();
	public abstract Object defValue();
	
	public static void checkExist(Player player) {
		UUID userID = player.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
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
