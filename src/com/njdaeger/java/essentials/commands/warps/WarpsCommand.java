package com.njdaeger.java.essentials.commands.warps;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class WarpsCommand extends BukkitCommand {

	Warps warps = new Warps();

	public WarpsCommand() {
		super("warps");
		List<String> a = Arrays.asList("listwarps");
		this.description = "List all the warps on the server.";
		this.usageMessage = "/warps [warpname]";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_WARPS)) {
				if (args.length == 0) {
					sndr.sendMessage(ChatColor.GRAY + "Server warps: " + ChatColor.GREEN + warps.listWarps());
					return true;
				}
				if (args.length == 1) {
					if (warps.getWarp(args[0]) == null) {
						sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
						return true;
					}
					sndr.sendMessage(ChatColor.GRAY + "Information for warp \"" + args[0] + "\"");
					sndr.sendMessage(
							ChatColor.GRAY + "world: " + ChatColor.GREEN + warps.getWarp(args[0]).get("world"));
					sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + warps.getWarp(args[0]).get("x"));
					sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + warps.getWarp(args[0]).get("y"));
					sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + warps.getWarp(args[0]).get("z"));
					sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + warps.getWarp(args[0]).get("yaw"));
					sndr.sendMessage(
							ChatColor.GRAY + "pitch: " + ChatColor.GREEN + warps.getWarp(args[0]).get("pitch"));
					return true;
				}
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		} else {
			if (args.length == 0) {
				sndr.sendMessage(ChatColor.GRAY + "Server warps: " + ChatColor.GREEN + warps.listWarps());
				return true;
			}
			if (args.length == 1) {
				if (warps.getWarp(args[0]) == null) {
					sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + "Information for warp \"" + args[0] + "\"");
				sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + warps.getWarp(args[0]).get("world"));
				sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + warps.getWarp(args[0]).get("x"));
				sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + warps.getWarp(args[0]).get("y"));
				sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + warps.getWarp(args[0]).get("z"));
				sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + warps.getWarp(args[0]).get("yaw"));
				sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + warps.getWarp(args[0]).get("pitch"));
				return true;
			}
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
	}

}
