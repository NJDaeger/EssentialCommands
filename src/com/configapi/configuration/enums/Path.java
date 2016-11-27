package com.configapi.configuration.enums;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;


public enum Path {
	
	/**
	 * GroupManager Path
	 */
	ENABLE_GM {
		@Override
		public String getPath() {
			return "groupmanager.enablegroupmanager";
		}

		@Override
		public Object defValue() {
			return true;
		}
	},
	/**
	 * BannerManager Path
	 */
	ENABLE_BM {
		@Override
		public String getPath() {
			return "bannermanager.enablebannermanager";
		}

		@Override
		public Object defValue() {
			return true;
		}
	},
	/**
	 * ChatColor Path
	 */
	ENABLE_CC {

		@Override
		public String getPath() {
			return "chat.enablechatcolor";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	/**
	 * Homes Path
	 */
	ENABLE_HOMES {

		@Override
		public String getPath() {
			return "homes.enablehomes";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	/**
	 * Warps Path
	 */
	ENABLE_WARPS {

		@Override
		public String getPath() {
			return "warps.enablewarps";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	/**
	 * Limit of warps allowed for the server.
	 */
	WARPS_WARPLIMIT {

		@Override
		public String getPath() {
			return "warps.warplimit";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	/**
	 * Limits the warps to per world.
	 */
	WARPS_PER_WORLD {

		@Override
		public String getPath() {
			return "warps.maxperworld";
		}

		@Override
		public Object defValue() {
			return "none";
		} //List of worlds then beside it have an integer value
		
	},
	/**
	 * Determine whether to limit warps at all.
	 */
	WARPS_WARPLIMITVALUE {

		@Override
		public String getPath() {
			return "warps.maxforserver";
		}

		@Override
		public Object defValue() {
			return 20;
		}
		
	},
	/**
	 * Whether to have an opped name color or not. 
	 * Default is "&4"
	 */
	OP_NAME_COLOR {

		@Override
		public String getPath() {
			return "main.operators.opnamecolor";
		}

		@Override
		public Object defValue() {
			return "&4";
		} //String that has chatcolor translation.
		
	},
	/**
	 * If not set to "null" then the prefix will be whatever the value is. 
	 */
	NICKNAME_PREFIX {

		@Override
		public String getPath() {
			return "main.nickname.nickprefix";
		}

		@Override
		public Object defValue() {
			return "~";
		} //String that has chatcolor translation.
		
	},
	/**
	 * Set a maximum character count for nicknames.
	 */
	NICK_MAX_LENGTH {

		@Override
		public String getPath() {
			return "main.nickname.maxnicknamelength";
		}

		@Override
		public Object defValue() {
			return 15;
		} //Int default is 15
		
	},
	/**
	 * List of blacklisted Commands. 
	 */
	BLACKLIST_COMMANDS {

		@Override
		public String getPath() {
			return "main.blacklist.commands";
		}

		@Override
		public Object defValue() {
			List<String> blacklistedCommands = Arrays.asList("nick", "nickname", "disguise", "newname");
			return blacklistedCommands;
		} //List of commands that are cancelled via commandevent
		
	},
	/**
	 * Message sent to command sender if the command is disabled.
	 */
	BLACKLIST_COMMANDS_MESSAGE {

		@Override
		public String getPath() {
			return "main.blacklist.commands.warn-message";
		}

		@Override
		public Object defValue() {
			return "&cThe command you tried to execute is not permitted.";
		}
		
	},
	/**
	 * List of blacklisted blocks
	 */
	BLACKLIST_BLOCKS {

		@Override
		public String getPath() {
			return "main.blacklist.blocks";
		}

		@Override
		public Object defValue() {
			List<String> blacklistedBlocks = Arrays.asList("minecraft:bedrock", "minecraft:barrier");
			return blacklistedBlocks;
		} //Must be as their actual identification so like stone:1. 1 being the metadata value
		
	},
	/**
	 * Enables or disables the console notification.
	 *
	 */
	BLACKLIST_BLOCKS_NOTIFYCSL {

		@Override
		public String getPath() {
			return "main.blacklist.blocks.enable-console-warn";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	/**
	 * The notify message for the console when a non-permitted block is placed.
	 *
	 */
	BLACKLIST_BLOCKS_NOTIFYCSL_MESSAGE {

		@Override
		public String getPath() {
			return "main.blacklist.blocks.console-warn-message";
		}

		@Override
		public Object defValue() {
			return "&cA player has tried to place a non-permitted block.";
		}
		
	},
	/**
	 * Enables or disables a warn message to operators if a non-permitted block is placed.
	 *
	 */
	BLACKLIST_BLOCKS_NOTIFYOP {

		@Override
		public String getPath() {
			return "main.blacklist.blocks.enable-operator-warn";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	/**
	 * The message being sent to an operator or admin when a non-permitted block is placed.
	 * Add support for tags like {BLOCK} {BLOCKID} {XPOS} {YPOS} {XPOS} {PLAYER} {DISPLAYNAME} {WORLD} {DATE} {TIME} and colorcodes.
	 */
	BLACKLIST_BLOCKS_NOTIFYOP_MESSAGE {

		@Override
		public String getPath() {
			return "main.blacklist.blocks.operator-warn-message";
		}

		@Override
		public Object defValue() {
			return "&cA player has tried to place a non-permitted block.";
		}
		
	},
	/**
	 * Enables or disables the player warn message.
	 */
	BLACKLIST_BLOCKS_MESSAGE_ENABLE {

		@Override
		public String getPath() {
			return "main.blacklist.blocks.enable-player-warn";
		}

		@Override
		public Object defValue() {
			return true;
		}
		
	},
	/**
	 * Message sent to the user of the block if it is blacklisted.
	 * Add support for tags like {BLOCK} {BLOCKID} {XPOS} {YPOS} {XPOS} {PLAYER} {DISPLAYNAME} {WORLD} {DATE} {TIME} and colorcodes.
	 */
	BLACKLIST_BLOCKS_MESSAGE {

		@Override
		public String getPath() {
			return "main.blacklist.blocks.player-warn-message";
		}

		@Override
		public Object defValue() {
			return "&cThe block you tried to use is not permitted.";
		}
		
	},
	/**
	 * Commands that are relayed back to players with socialspy permissions.
	 */
	SPYED_COMMANDS {

		@Override
		public String getPath() {
			return "main.spying.spycommands";
		}

		@Override
		public Object defValue() {
			List<String> spyedCommands = Arrays.asList("message", "write", "r", "reply", "pl", "plugins");
			return spyedCommands;
		} //List of commands that are spyed on.
		
	},
	/**
	 * The chat formatting for the server. 
	 */
	CHAT_FORMAT {

		@Override
		public String getPath() {
			return "chat.format.chatformat";
		}

		@Override
		public Object defValue() {
			return "&7[ThisWouldBeRank] &r{DISPLAYNAME}&7:&r ";
		} //String with chatcolor translations. 
		
	},
	/**
	 * Custom join message. 
	 */
	CHAT_JOINMESSAGE {

		@Override
		public String getPath() {
			return "chat.format.joinmessage";
		}

		@Override
		public Object defValue() {
			return "none";
		} //String with chatcolor translations. 
		
	},
	/**
	 * Custom quit message.
	 */
	CHAT_QUITMESSAGE {

		@Override
		public String getPath() {
			return "chat.format.quitmessage";
		}

		@Override
		public Object defValue() {
			return "none";
		} //String with chatcolor translations. 
		
	},
	HOMES_LIMITHOMES {

		@Override
		public String getPath() {
			return "homes.limithomes";
		}

		@Override
		public Object defValue() {
			return false;
		}
		
	},
	/**
	 * Maximum amount of homes for a user. 
	 * Set none for no limit. 
	 */
	HOMES_MAXHOMES {

		@Override
		public String getPath() {
			return "homes.maxhomes";
		}

		@Override
		public Object defValue() {
			return 5;
		} //int Default is 5
		
	},
	/**
	 * Maximum amount of homes for a user in different worlds. 
	 * Set none to use server max and not world max.
	 */
	HOMES_MAXHOMES_PERWORLD {

		@Override
		public String getPath() {
			return "homes.maxperworld";
		}

		@Override
		public Object defValue() {
			return "none";
		} //int
		
	},
	/**
	 * Allow to have players teleport to another players homes. 
	 */
	HOMES_TELEPORT_OTHER {

		@Override
		public String getPath() {
			return "homes.gotootherhome";
		}

		@Override
		public Object defValue() {
			return true;
		} //boolean default is false
		
	},
	/**
	 * Allow per world MOTD.
	 */
	PER_WORLD_MOTD {

		@Override
		public String getPath() {
			return "main.motd.perworld";
		}

		@Override
		public Object defValue() {
			return false;
		} //boolean default is false.
		
	},
	SERVER_MOTD {

		@Override
		public String getPath() {
			return "main.motd.enablemotd";
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
	/**
	 * This checks if a path exists in the server configuration. If it doesn't, it will put it in for you and set the default value. 
	 */
	public static void checkExist() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		for (Path path : Path.values()) {
			if (!c.contains(path.getPath())) {
				c.set(path.getPath(), path.defValue());
				try {
					c.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
