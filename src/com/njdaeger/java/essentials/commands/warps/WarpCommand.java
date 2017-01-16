package com.njdaeger.java.essentials.commands.warps;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class WarpCommand extends EssCommand {

	static String name = "warp";

	public WarpCommand() {
		super("warp");
		this.description = "Warp to a location.";
		this.usageMessage = "/warp <warpname> [player]";
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_WARP, Permission.ESS_WARP_OTHER)) {
			switch (args.length) {
			case 0:

				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
				if (sndr instanceof Player) {
					if (Warps.getWarp(args[0], null).exists() == false) {
						sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
						return true;
					}
					Warps.getWarp(args[0], (Player) sndr).sendWarp();
					sndr.sendMessage(ChatColor.GRAY + "You were sent to \"" + ChatColor.GREEN + args[0] + "\"");
					return true;
				}
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;

			case 2:
				if (Holder.hasPermission(sndr, Permission.ESS_WARP_OTHER)) {
					Player target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (Warps.getWarp(args[0], null).exists() == false) {
						sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
						return true;
					}
					Warps.getWarp(args[0], target).sendWarp();
					sndr.sendMessage(ChatColor.GRAY + "You sent " + ChatColor.GREEN + target.getDisplayName()
							+ ChatColor.GRAY + " to warp " + ChatColor.GREEN + "\"" + args[0] + "\"");
					target.sendMessage(ChatColor.GRAY + "You were sent to warp \"" + ChatColor.GREEN + args[0]
							+ ChatColor.GRAY + "\"");
				}
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}
