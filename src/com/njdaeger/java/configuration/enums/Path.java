package com.njdaeger.java.configuration.enums;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.njdaeger.java.Groups;

public enum Path {
	/*
	 * Subplugins
	 */
	ENABLE_NJP("subplugin.groupmanager", true),
	ENABLE_BM("subplugin.bannermanager", true),
	ENABLE_CODES("subplugin.codes", true),
	ENABLE_ANNO("subplugin.annotations", true),
	ENABLE_SP("subplugin.serverprotect", true),
	ENABLE_LC("subplugin.loginclearance", true),

	/*
	 * Warp options
	 */
	WARPS_WARPLIMIT("warps.enable-warp-limit", false),
	WARPS_WARPLIMIT_LIM("warps.warp-limit", 50),
	WARPS_WARPLIMIT_PERWORLD("warps.limit-per-world", false),
	/*
	 * Main essentials options
	 */
	SPIED_COMMANDS("main.various.spied-commands", Groups.spiedCommands),
	OP_NAME_COLOR("main.various.op-namecolor", "&4"),
	NICKNAME_PREFIX("main.various.nickname-prefix", "~"),
	NICKNAME_MAXLENGTH("main.various.nickname-max-length", "15"),
	BLACKLIST_COMMANDS("main.blacklist.command.command-list", Groups.blacklistedCommands),
	BLACKLIST_COMMANDS_NOTIFY_CSL("main.blacklist.command.notify-console", true),
	BLACKLIST_COMMANDS_NOTIFY_OP("main.blacklist.command.notify-ops", true),
	BLACKLIST_COMMANDS_NOTIFY_PLR("main.blacklist.command.notify-player", true),
	BLACKLIST_COMMAND_LOGGING("main.blacklist.command.log-unpermitted-cmds", true),
	BLACKLIST_BLOCKS("main.blacklist.block.block-id-list", Groups.blacklistedBlocks),
	BLACKLIST_BLOCKS_NOTIFY_CSL("main.blacklist.block.notify-console", true),
	BLACKLIST_BLOCKS_NOTIFY_OP("main.blacklist.block.notify-ops", true),
	BLACKLIST_BLOCKS_NOTIFY_PLR("main.blacklist.block.notify-player", true),
	BLACKLIST_BLOCKS_LOGGING("main.blacklist.block.log-unpermitted-blocks", true),

	/*
	 * Chat stuffs
	 */
	CHAT_CHATCOLOR("main.chat.allow-chat-color", true),
	CHAT_FORMAT("main.chat.format", "&7[{RANK}] &r{DISPLAYNAME}&7:&r"),
	CHAT_JOINMESSAGE("main.chat.custom-join-message", "none"),
	CHAT_QUITMESSAGE("main.chat.custom-quit-message", "none"),
	CHAT_PER_WORLD_MOTD("main.chat.per-world-motd", false),
	CHAT_SERVER_MOTD("main.chat.per-world-motd", true),

	/*
	 * Homes stuffs
	 */
	HOMES_LIMITHOMES("homes.limithomes", false),
	HOMES_MAXHOMES("homes.limithomes-value", 5), // Limits overall homes on entire server if value = ~ then there is no server wide limit Server limit will override this. (if ServLim = 3 and WldLim = 5 the max homes the player can have at all is 3)
	HOMES_MAXHOMES_PERWORLD("homes.limithomes-perworld-value", "~"), // limits homes per world. if value = ~ then there is no world limit
	HOMES_TELEPORT_OTHER("homes.goto-other-home", true);

	public String path;
	public Object value;

	Path(String path, Object value) {
		this.path = path;
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public Object defValue() {
		return value;
	}

	/**
	 * This checks if a path exists in the server configuration. If it doesn't,
	 * it will put it in for you and set the default value.
	 */
	public static void checkExist() {
		File file = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "config.yml");
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
