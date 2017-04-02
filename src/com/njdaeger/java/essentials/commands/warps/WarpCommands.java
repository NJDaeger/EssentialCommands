package com.njdaeger.java.essentials.commands.warps;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.commands.Cmd;
import com.njdaeger.java.command.util.commands.Executor;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class WarpCommands {

	/**
	 * 
	 * 
	 * DELWARP COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "delwarp",
		desc = "Delete an existing warp.",
		usage = "/delwarp <warpname>",
		min = 1,
		max = 1,
		aliases = { "removewarp", "deletewarp" },
		permissions = { Permission.ESS_DELWARP })
	public void delwarp(Sender sndr, String label, String[] args) {
		WarpData d = Warps.getWarp(args[0], null);
		if (d.exists() == false) {
			sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
			return;
		}
		d.remove();
		sndr.sendMessage(ChatColor.GRAY + "You deleted warp " + ChatColor.GREEN + args[0]);
		return;
	}

	/**
	 * 
	 * 
	 * SETWARP COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "setwarp",
		desc = "Add a new warp.",
		usage = "/setwarp <warpname>",
		min = 1,
		max = 1,
		executor = Executor.PLAYER,
		aliases = { "newwarp", "addwarp" },
		permissions = { Permission.ESS_SETWARP })
	public void setwarp(Sender sndr, String label, String[] args) {
		WarpData d = Warps.getWarp(args[0], sndr.asPlayer());
		if (d.exists() == true) {
			sndr.sendMessage(Error.WARP_EXISTS.sendError());
			return;
		}
		d.create();
		sndr.sendMessage(ChatColor.GRAY + "Created warp \"" + args[0] + "\"");
		return;
	}

	/**
	 * 
	 * 
	 * WARP COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "warp",
		desc = "Warp to a location.",
		usage = "/warp <warpname> [player]",
		min = 1,
		max = 2,
		permissions = { Permission.ESS_WARP, Permission.ESS_WARP_OTHER })
	public void warp(Sender sndr, String label, String[] args) {
		if (args.length == 1) {
			if (!sndr.isUser()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
			if (Warps.getWarp(args[0], null).exists() == false) {
				sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
				return;
			}
			Warps.getWarp(args[0], sndr.asPlayer()).sendWarp();
			sndr.sendMessage(ChatColor.GRAY + "You were sent to \"" + ChatColor.GREEN + args[0] + "\"");
			return;
		}
		if (!sndr.hasPermission(Permission.ESS_WARP_OTHER)) {
			sndr.sendError(Error.NO_PERMISSION);
			return;
		}
		User user = Core.getUser(args[1]);
		if (user == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (Warps.getWarp(args[0], null).exists() == false) {
			sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
			return;
		}
		Warps.getWarp(args[0], user.getBase()).sendWarp();
		sndr.sendMessage(ChatColor.GRAY + "You sent " + ChatColor.GREEN + user.getNickname() + ChatColor.GRAY
				+ " to warp " + ChatColor.GREEN + "\"" + args[0] + "\"");
		user.sendMessage(ChatColor.GRAY + "You were sent to warp \"" + ChatColor.GREEN + args[0] + ChatColor.GRAY
				+ "\"");
	}

	@Cmd(
		name = "warps",
		desc = "List all the warps on the server.",
		usage = "/warps [warpname]",
		aliases = { "listwarps" },
		max = 1,
		permissions = { Permission.ESS_WARPS, Permission.ESS_WARPS_DETAIL })
	public void listwarps(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (Holder.hasPermission(sndr, Permission.ESS_WARPS)) {
				if (Warps.getWarp(null, null).listWarps() == null) {
					sndr.sendMessage(Error.throwError("No warps exist."));
					return;
				}
				sndr.sendMessage(ChatColor.GRAY + "Server warps: " + ChatColor.GREEN + Warps.getWarp(null, null)
						.listWarps());
				return;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_WARPS_DETAIL)) {
			if (Warps.getWarp(args[0], null).exists() == false) {
				sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
				return;
			}
			WarpData d = Warps.getWarp(args[0], null);
			ChatColor gr = ChatColor.GRAY;
			ChatColor gn = ChatColor.GREEN;
			sndr.sendMessage(gr + "Information for warp \"" + gn + args[0] + gr + "\".");
			sndr.sendMessage(gr + "World: " + gn + d.getWorld());
			sndr.sendMessage(gr + "X: " + gn + d.getX());
			sndr.sendMessage(gr + "Y: " + gn + d.getY());
			sndr.sendMessage(gr + "X: " + gn + d.getZ());
			sndr.sendMessage(gr + "Yaw: " + gn + d.getYaw());
			sndr.sendMessage(gr + "Pitch: " + gn + d.getPitch());
			return;
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return;
	}

}
