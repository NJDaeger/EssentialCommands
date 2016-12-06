package com.njdaeger.java.essentials.commands.warps;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.essentials.enums.Permission;

public class DelwarpCommand extends BukkitCommand{
	
	Warps warps = new Warps();
	
	public DelwarpCommand() {
		super("delwarp");
		List<String> a = Arrays.asList("removewarp", "deletewarp");
		this.description = "Delete an existing warp.";
		this.usageMessage = "/delwarp <warpname>";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_DELWARP)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length > 1) {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
				if (warps.getWarp(args[0]) == null) {
					sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
					return true;
				}
				warps.deleteWarp(args[0]);
				sndr.sendMessage(ChatColor.GRAY + "You deleted warp " + ChatColor.GREEN + args[0]);
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		else {
			if (args.length == 0) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length > 1) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			if (warps.getWarp(args[0]) == null) {
				sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
				return true;
			}
			warps.deleteWarp(args[0]);
			sndr.sendMessage(ChatColor.GRAY + "You deleted warp " + ChatColor.GREEN + args[0]);
			return true;
		}
	}
}
