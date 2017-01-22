package com.njdaeger.java.essentials.commands.warps;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class WarpsCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(
			name = "warps",
			desc = "List all the warps on the server.",
			usage = "/warps [warpname]",
			aliases = { "listwarps" },
			max = 1,
			permissions = { Permission.ESS_WARPS, Permission.ESS_WARPS_DETAIL })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (Holder.hasPermission(sndr, Permission.ESS_WARPS)) {
				if (Warps.getWarp(null, null).listWarps() == null) {
					sndr.sendMessage(Error.throwError("No warps exist."));
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + "Server warps: " + ChatColor.GREEN + Warps.getWarp(null, null)
						.listWarps());
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_WARPS_DETAIL)) {
			if (Warps.getWarp(args[0], null).exists() == false) {
				sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
				return true;
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
			return true;
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}
