package com.njdaeger.java.essentials.enums;

public enum Permission {

	ESS_ALL {

		@Override
		public String getPermission() {
			String permission = "essentials.*";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_ALL.getPermission());
			return permission;
		}

	},
	ESS_AFK {
		@Override
		public String getPermission() {
			String permission = "essentials.afk";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_AFK.getPermission());
			return permission;
		}
	},
	ESS_AFK_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.afk.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_AFK_OTHER.getPermission());
			return permission;
		}
	},
	ESS_BACK {
		@Override
		public String getPermission() {
			String permission = "essentials.back";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BACK.getPermission());
			return permission;
		}
	},
	ESS_BAN {

		@Override
		public String getPermission() {
			String permission = "essentials.ban";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BAN.getPermission());
			return permission;
		}

	},
	ESS_BAN_NOTIFY {

		@Override
		public String getPermission() {
			String permission = "essentials.ban.notify";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BAN_NOTIFY.getPermission());
			return permission;
		}

	},
	ESS_BREAK {
		@Override
		public String getPermission() {
			String permission = "essentials.break";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BREAK.getPermission());
			return permission;
		}
	},
	ESS_BROADCAST {

		@Override
		public String getPermission() {
			String permission = "essentials.broadcast";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BROADCAST.getPermission());
			return permission;
		}
	},
	ESS_BURN {

		@Override
		public String getPermission() {
			String permission = "essentials.burn";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BURN.getPermission());
			return permission;
		}
	},
	ESS_BURN_OTHER {

		@Override
		public String getPermission() {
			String permission = "essentials.burn.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BURN_OTHER.getPermission());
			return permission;
		}
	},
	ESS_CLEAR {

		@Override
		public String getPermission() {
			String permission = "essentials.clear";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_CLEAR.getPermission());
			return permission;
		}
	},
	ESS_CLEAR_OTHER {

		@Override
		public String getPermission() {
			String permission = "essentials.clear.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_CLEAR_OTHER.getPermission());
			return permission;
		}
	},
	ESS_CHATCOLOR {

		@Override
		public String getPermission() {
			String permission = "essentials.chatcolor";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_CHATCOLOR.getPermission());
			return permission;
		}

	},
	ESS_FLY {
		@Override
		public String getPermission() {
			String permission = "essentials.fly";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_FLY.getPermission());
			return permission;
		}
	},
	ESS_FLY_OTHER {

		@Override
		public String getPermission() {
			String permission = "essentials.fly.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_FLY_OTHER.getPermission());
			return permission;
		}
	},
	ESS_GAMEMODE {
		@Override
		public String getPermission() {
			String permission = "essentials.gamemode";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_GAMEMODE.getPermission());
			return permission;
		}
	},
	ESS_GAMEMODE_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.gamemode.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_GAMEMODE_OTHER.getPermission());
			return permission;
		}
	},
	ESS_GIVE {

		@Override
		public String getPermission() {
			String permission = "essentials.give";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_GIVE.getPermission());
			return permission;
		}

	},
	ESS_GIVE_OTHER {

		@Override
		public String getPermission() {
			String permission = "essentials.give.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_GIVE_OTHER.getPermission());
			return permission;
		}

	},
	ESS_GOD {

		@Override
		public String getPermission() {
			String permission = "essentials.god";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_GOD.getPermission());
			return permission;
		}
	},
	ESS_GOD_OTHER {

		@Override
		public String getPermission() {
			String permission = "essentials.god.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_GOD_OTHER.getPermission());
			return permission;
		}
	},
	ESS_HEAL {

		@Override
		public String getPermission() {
			String permission = "essentials.heal";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HEAL.getPermission());
			return permission;
		}
	},
	ESS_HEAL_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.heal.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HEAL_OTHER.getPermission());
			return permission;
		}
	},
	ESS_ME {

		@Override
		public String getPermission() {
			String permission = "essentials.me";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_ME.getPermission());
			return permission;
		}
	},
	ESS_ME_CHATCOLOR {

		@Override
		public String getPermission() {
			String permission = "essentials.me.chatcolor";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_ME_CHATCOLOR.getPermission());
			return permission;
		}
	},
	ESS_MESSAGE {
		@Override
		public String getPermission() {
			String permission = "essentials.message";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_MESSAGE.getPermission());
			return permission;
		}
	},
	ESS_MESSAGE_CHATCOLOR {
		@Override
		public String getPermission() {
			String permission = "essentials.message.chatcolor";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_MESSAGE_CHATCOLOR.getPermission());
			return permission;
		}
	},
	ESS_NICK {

		@Override
		public String getPermission() {
			String permission = "essentials.nick";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_NICK.getPermission());
			return permission;
		}
	},
	ESS_NICK_OTHER {

		@Override
		public String getPermission() {
			String permission = "essentials.nick.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_NICK_OTHER.getPermission());
			return permission;
		}
	},
	ESS_POSITION {
		@Override
		public String getPermission() {
			String permission = "essentials.position";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_POSITION.getPermission());
			return permission;
		}
	},
	ESS_POSITION_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.position.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_POSITION_OTHER.getPermission());
			return permission;
		}
	},
	ESS_SERVER_INFO {
		@Override
		public String getPermission() {
			String permission = "essentials.serverinfo";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_SERVER_INFO.getPermission());
			return permission;
		}

	},
	ESS_SPEED {
		@Override
		public String getPermission() {
			String permission = "essentials.speed";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_SPEED.getPermission());
			return permission;
		}
	},
	ESS_SPEED_OTHER {

		@Override
		public String getPermission() {
			String permission = "essentials.speed.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_SPEED_OTHER.getPermission());
			return permission;
		}
	},
	ESS_TEMPBAN {
		@Override
		public String getPermission() {
			String permission = "essentials.tempban";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_TEMPBAN.getPermission());
			return permission;
		}
	},
	ESS_BAN_EXEMPT {
		@Override
		public String getPermission() {
			String permission = "essentials.ban.protect";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_BAN_EXEMPT.getPermission());
			return permission;
		}
	},
	ESS_UNBAN {
		@Override
		public String getPermission() {
			String permission = "essentials.unban";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_UNBAN.getPermission());
			return permission;
		}
	},
	ESS_EDITSIGN {
		@Override
		public String getPermission() {
			String permission = "essentials.editsign";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_EDITSIGN.getPermission());
			return permission;
		}
	},
	ESS_HAT {
		@Override
		public String getPermission() {
			String permission = "essentials.hat";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HAT.getPermission());
			return permission;
		}
	},
	ESS_HAT_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.hat.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HAT_OTHER.getPermission());
			return permission;
		}
	},
	ESS_HELPOP {
		@Override
		public String getPermission() {
			String permission = "essentials.helpop";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HELPOP.getPermission());
			return permission;
		}
	},
	ESS_HELPOP_RECEIVE {
		@Override
		public String getPermission() {
			String permission = "essentials.helpop.receive";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HELPOP_RECEIVE.getPermission());
			return permission;
		}
	},
	ESS_KICKALL {
		@Override
		public String getPermission() {
			String permission = "essentials.kickall";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_KICKALL.getPermission());
			return permission;
		}
	},
	ESS_SETHOME {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.set";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_SETHOME.getPermission());
			return permission;
		}
	},
	ESS_DELHOME {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.delete";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_DELHOME.getPermission());
			return permission;
		}
	},
	ESS_HOME {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.home";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HOME.getPermission());
			return permission;
		}
	},
	ESS_LISTHOMES {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.list";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_LISTHOMES.getPermission());
			return permission;
		}
	},
	ESS_HOME_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.home.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_HOME_OTHER.getPermission());
			return permission;
		}
	},
	ESS_LISTHOMES_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.list.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_LISTHOMES_OTHER.getPermission());
			return permission;
		}
	},
	ESS_DELHOME_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.delete.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_DELHOME_OTHER.getPermission());
			return permission;
		}
	},
	ESS_SETHOME_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.set.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_SETHOME_OTHER.getPermission());
			return permission;
		}
	},
	ESS_SETWARP {
		@Override
		public String getPermission() {
			String permission = "essentials.warps.setwarp";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_SETWARP.getPermission());
			return permission;
		}
	},
	ESS_DELWARP {
		@Override
		public String getPermission() {
			String permission = "essentials.warps.delwarp";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_DELWARP.getPermission());
			return permission;
		}
	},
	ESS_WARP {
		@Override
		public String getPermission() {
			String permission = "essentials.warps.warp";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_WARP.getPermission());
			return permission;
		}
	},
	ESS_WARPLIST {
		@Override
		public String getPermission() {
			String permission = "essentials.warps.warplist";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_WARPLIST.getPermission());
			return permission;
		}
	},
	ESS_WARP_OTHER {
		@Override
		public String getPermission() {
			String permission = "essentials.warps.warp.other";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_WARP_OTHER.getPermission());
			return permission;
		}
	},
	ESS_LISTHOMES_DETAIL {
		@Override
		public String getPermission() {
			String permission = "essentials.homes.list.detail";
			return permission;
		}

		@Override
		public org.bukkit.permissions.Permission registerPermission() {
			org.bukkit.permissions.Permission permission = new org.bukkit.permissions.Permission(
					ESS_LISTHOMES_DETAIL.getPermission());
			return permission;
		}
	};

	public String perm = "";

	public abstract String getPermission();

	public abstract org.bukkit.permissions.Permission registerPermission();
}
