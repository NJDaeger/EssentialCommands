package com.configapi.configuration.enums;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;


public enum Path {
	
	ENABLE_GM {
		@Override
		public String getPath() {
			return "enable.groupmanager";
		}

		@Override
		public Object defValue() {
			return true;
		}
	},
	ENABLE_BM {
		@Override
		public String getPath() {
			return "enable.bannermanager";
		}

		@Override
		public Object defValue() {
			return true;
		}
	},
	ENABLE_CC {

		@Override
		public String getPath() {
			return "enable.chatcolor";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	ENABLE_HOMES {

		@Override
		public String getPath() {
			return "enable.homes";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	ENABLE_WARPS {

		@Override
		public String getPath() {
			return "enable.warps";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	};

	/**
	 * @return path.
	 */
	public abstract String getPath();
	public abstract Object defValue();
	/**
	 * @param path What path in the configuration section to grab.
	 * @return String of path.
	 */
	public static String get(Path path) {
		return path.getPath();
	}
	public static Path[] get(Path[] path) {
		return path;
	}
	public static void checkExist() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		for (Path path : Path.values()) {
			if (!c.contains(path.getPath())) {
				c.set(path.getPath(), path.defValue());
			}
			return;
		}
	}
}
