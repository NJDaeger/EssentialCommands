package com.njdaeger.java.essentials.commands.homes;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class Delhome extends EssCommand {

	static String name = "delhome";

	public Delhome() {
		super(name);
		List<String> a = Arrays.asList("deletehome", "removehome", "clearhome");
		this.description = "Delete an existing home.";
		this.usageMessage = "/delhome <homename> [player]";
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
			if (Holder.hasPermission(sndr, Permission.ESS_DELHOME, Permission.ESS_DELHOME_OTHER)) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
		case 1:
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (Holder.hasPermission(player, Permission.ESS_DELHOME)) {
					if (!Homes.getHome(args[0], player).exists()) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						sndr.sendMessage(
								ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + Homes.getHome(null, player));
						return true;
					}
					Homes.getHome(args[0], player).remove();
					sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
							+ " from homes.");
					return true;
				}
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		case 2:
			if (Holder.hasPermission(sndr, Permission.ESS_DELHOME_OTHER)) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					if (Database.getDatabase("playerdata").getEntry(args[1]) == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (!Homes.getOfflineHome(args[0], args[1]).exists()) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN
								+ Homes.getOfflineHome(null, args[1]));
						return true;
					}
					Homes.getOfflineHome(args[0], args[1]).remove();
					sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
							+ " from " + args[1] + "'s homes.");
					return true;
				}
				if (!Homes.getHome(args[0], target).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(
							ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + Homes.getHome(null, target));
					return true;
				}
				Homes.getHome(args[0], target).remove();
				sndr.sendMessage(
						ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY + " from homes.");
				return true;

			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		default:
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
	}
}
