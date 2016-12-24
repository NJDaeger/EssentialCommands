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

	Homes homes = new Homes();

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_DELHOME, Permission.ESS_DELHOME_OTHER)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length == 1) {
					if (homes.getHome(args[0], player) == null) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						sndr.sendMessage(
								ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + homes.listHomes(player));
						return true;
					}
					homes.removeHome(args[0], player);
					sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
							+ " from homes.");
					return true;
				}
				if (args.length == 2) {
					if (Holder.hasPermission(player, Permission.ESS_DELHOME_OTHER)) {
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							if (Database.getDatabase("playerdata").getEntry(args[1]) == null) {
								sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
								return true;
							}
							if (homes.getOfflineHome(args[0], args[1]) == null) {
								sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
								return true;
							}
							homes.removeOfflineHome(args[0], args[1]);
							sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0]
									+ ChatColor.GRAY + " from " + args[1] + "'s homes.");
							return true;
						}
						if (homes.getHome(args[0], target) == null) {
							sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
							sndr.sendMessage(ChatColor.GRAY + target.getName() + "'s current homes: " + ChatColor.GREEN
									+ homes.listHomes(target));
							return true;
						}
						homes.removeHome(args[0], target);
						sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
								+ " from " + target.getName() + "'s homes.");
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
		} else {
			if (args.length <= 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					if (Database.getDatabase("playerdata").getEntry(args[1]) == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (homes.getOfflineHome(args[0], args[1]) == null) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						return true;
					}
					homes.removeOfflineHome(args[0], args[1]);
					sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
							+ " from " + args[1] + "'s homes.");
					return true;
				}
				if (homes.getHome(args[0], target) == null) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(ChatColor.GRAY + target.getName() + "'s current homes: " + ChatColor.GREEN
							+ homes.listHomes(target));
					return true;
				}
				homes.removeHome(args[0], target);
				sndr.sendMessage(ChatColor.GRAY + "Removed home " + ChatColor.GREEN + args[0] + ChatColor.GRAY
						+ " from " + target.getName() + "'s homes.");
				return true;
			}
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
	}

}
