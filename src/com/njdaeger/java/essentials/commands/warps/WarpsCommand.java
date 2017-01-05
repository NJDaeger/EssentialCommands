package com.njdaeger.java.essentials.commands.warps;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class WarpsCommand extends EssCommand {

	static String name = "warps";

	public WarpsCommand() {
		super(name);
		List<String> a = Arrays.asList("listwarps");
		this.description = "List all the warps on the server.";
		this.usageMessage = "/warps [warpname]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		switch (args.length) {
		case 0:
			if (Holder.hasPermission(sndr, Permission.ESS_WARPS)) {
				if (Warps.getWarp(null, null).listWarps() == null) {
					sndr.sendMessage(Error.throwError("No warps exist."));
				}
				sndr.sendMessage(ChatColor.GRAY + "Server warps: " + ChatColor.GREEN + Warps.getWarp(null, null)
						.listWarps());
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		case 1:
			if (Holder.hasPermission(sndr, Permission.ESS_WARPS_DETAIL)) {
				if (!Warps.getWarp(args[0], null).exists()) {
					sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
					return true;
				}
				WarpData d = Warps.getWarp(args[0], null);
				ChatColor gr = ChatColor.GRAY;
				ChatColor gn = ChatColor.GREEN;
				sndr.sendMessage(gr + "Information for warp \"" + gn + args[0] + gr + "\".");
				sndr.sendMessage(gr + "World: " + gn + d.getWorld());
				sndr.sendMessage(gr + "World: " + gn + d.getX());
				sndr.sendMessage(gr + "World: " + gn + d.getY());
				sndr.sendMessage(gr + "World: " + gn + d.getZ());
				sndr.sendMessage(gr + "World: " + gn + d.getYaw());
				sndr.sendMessage(gr + "World: " + gn + d.getPitch());
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		default:
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
	}

}
