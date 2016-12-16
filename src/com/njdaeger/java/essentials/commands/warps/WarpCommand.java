package com.njdaeger.java.essentials.commands.warps;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class WarpCommand extends BukkitCommand{
	
	Warps warps = new Warps();
	
	public WarpCommand() {
		super("warp");
		this.description = "Warp to a location.";
		this.usageMessage = "/warp <warpname> [player]";
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_WARP, Permission.ESS_WARP_OTHER)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length == 1) {
					if (warps.getWarp(args[0]) == null) {
						sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
						return true;
					}
					warps.sendtoWarp(args[0], player);
					sndr.sendMessage(ChatColor.GRAY + "You were sent to \"" + ChatColor.GREEN + args[0] + "\"");
					return true;
				}
				if (args.length == 2) {
					if (Holder.hasPermission(player, Permission.ESS_WARP_OTHER)) {
						Player target = Bukkit.getPlayer(args[1]);
						if (warps.getWarp(args[0]) == null) {
							sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
							return true;
						}
						if (target == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						warps.sendtoWarp(args[0], target);
						sndr.sendMessage(ChatColor.GRAY + "You sent " + ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " to " + ChatColor.GREEN + "\"" + args[0] + "\"");
						target.sendMessage(ChatColor.GRAY + "You were sent to \"" + ChatColor.GREEN + args[0] + ChatColor.GRAY + "\"");
						return true;
					}
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		else {
			if (args.length <= 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[1]);
				if (warps.getWarp(args[0]) == null) {
					sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
					return true;
				}
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				warps.sendtoWarp(args[0], target);
				sndr.sendMessage(ChatColor.GRAY + "You sent " + ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " to " + ChatColor.GREEN + "\"" + args[0] + ChatColor.GRAY + "\"");
				target.sendMessage(ChatColor.GRAY + "You were sent to \"" + ChatColor.GREEN + args[0] + "\"");
				return true;
			}
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
	}

}
