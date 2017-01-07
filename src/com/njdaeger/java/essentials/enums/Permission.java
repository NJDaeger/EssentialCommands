package com.njdaeger.java.essentials.enums;

public enum Permission {

	ESS_ALL("essentials.*"),
	ESS_AFK("essentials.afk"),
	ESS_AFK_OTHER("essentials.afk.other"),
	ESS_BACK("essentials.back"),
	ESS_BAN("essentials.ban"),
	ESS_BAN_NOTIFY("essentials.ban.notify"),
	ESS_BREAK("essentials.break"),
	ESS_BROADCAST("essentials.broadcast"),
	ESS_BURN("essentials.burn"),
	ESS_BURN_OTHER("essentials.burn.other"),
	ESS_CLEAR("essentials.clear"),
	ESS_CLEAR_OTHER("essentials.clear.other"),
	ESS_CHATCOLOR("essentials.chatcolor"),
	ESS_FLY("essentials.fly"),
	ESS_FLY_OTHER("essentials.fly.other"),
	ESS_GAMEMODE("essentials.gamemode"),
	ESS_GAMEMODE_OTHER("essentials.gamemode.other"),
	ESS_GIVE("essentials.give"),
	ESS_GIVE_OTHER("essentials.give.other"),
	ESS_GOD("essentials.god"),
	ESS_GOD_OTHER("essentials.god.other"),
	ESS_HEAL("essentials.heal"),
	ESS_HEAL_OTHER("essentials.heal.other"),
	ESS_ME("essentials.me"),
	ESS_ME_CHATCOLOR("essentials.me.chatcolor"),
	ESS_MESSAGE("essentials.message"),
	ESS_MESSAGE_CONSOLE("essentials.message.console"),
	ESS_MESSAGE_CHATCOLOR("essentials.message.chatcolor"),
	ESS_NICK("essentials.nick"),
	ESS_NICK_CHATCOLOR("essentials.nick.chatcolor"),
	ESS_NICK_OTHER("essentials.nick.other"),
	ESS_POSITION("essentials.position"),
	ESS_POSITION_OTHER("essentials.position.other"),
	ESS_SERVER_INFO("essentials.serverinfo"),
	ESS_SERVER_INFO_TPS("essentials.serverinfo.tps"),
	ESS_SERVER_INFO_OS("essentials.serverinfo.os"),
	ESS_SERVER_INFO_ARCH("essentials.serverinfo.arch"),
	ESS_SERVER_INFO_CORES("essentials.serverinfo.cores"),
	ESS_SERVER_INFO_USAGE("essentials.serverinfo.usage"),
	ESS_SERVER_INFO_MAXRAM("essentials.serverinfo.maxram"),
	ESS_SERVER_INFO_FREERAM("essentials.serverinfo.freeram"),
	ESS_SERVER_INFO_ALCDRAM("essentials.serverinfo.alcdram"),
	ESS_SERVER_INFO_PORT("essentials.serverinfo.port"),
	ESS_SERVER_INFO_ALL("essentials.serverinfo.*"),
	ESS_SPEED("essentials.speed"),
	ESS_SPEED_OTHER("essentials.speed.other"),
	ESS_SPEED_LIMIT("essentials.speed.limit"),
	ESS_TEMPBAN("essentials.tempban"),
	ESS_TEMPBAN_UNLIMITED("essentials.tempban.nolimit"),
	ESS_BAN_EXEMPT("essentials.ban.exempt"),
	ESS_UNBAN("essentials.unban"),
	ESS_EDITSIGN("essentials.editsign"),
	ESS_EDITSIGN_COLOR("essentials.editsign.color"),
	ESS_HAT("essentials.hat"),
	ESS_HAT_OTHER("essentials.hat.other"),
	ESS_HELPOP("essentials.helpop"),
	ESS_HELPOP_RECEIVE("essentials.helpop.receive"),
	ESS_HELPOP_COLOR("essentials.helpop.color"),
	ESS_KICKALL("essentials.kickall"),
	ESS_KICKALL_EXEMPT("essentials.kickall.exempt"),
	ESS_SETHOME("essentials.sethome"),
	ESS_DELHOME("essentials.delhome"),
	ESS_DELHOME_OTHER("essentials.delhome.other"),
	ESS_LISTHOMES("essentials.homes"),
	ESS_LISTHOMES_OTHER("essentials.homes.other"),
	ESS_LISTHOMES_DETAIL("essentials.homes.detail"),
	ESS_HOME("essentials.home"),
	ESS_HOME_OTHER("essentials.home.other"),
	ESS_SETWARP("essentials.setwarp"),
	ESS_DELWARP("essentials.delwarp"),
	ESS_WARP("essentials.warp"),
	ESS_WARP_OTHER("essentials.warp.other"),
	ESS_WARPS("essentials.warps"),
	ESS_WARPS_DETAIL("essentials.warps.detail"),
	ESS_BUBBLE("essentials.bubble"),
	ESS_BUBBLE_OTHER("essentials.bubble.other"),
	ESS_BUBBLE_EXEMPT_MAXRANGE("essentials.bubble.nolimit"),
	ESS_BYPASS_BLOCKED_COMMANDS("essentials.bypass.commands"),
	ESS_KILL_ALL("essentials.kill.all"),
	ESS_KILL_OTHER("essentials.kill.other"),
	ESS_SUICIDE("essentials.suicide");

	public final String permission;

	Permission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

	public org.bukkit.permissions.Permission registerPermission() {
		org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(this.permission);
		return permission;
	}
}
