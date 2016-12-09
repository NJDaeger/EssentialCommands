package com.njdaeger.java.essentials.commands.homes;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.configuration.exceptions.DatabaseNotFound;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class Home extends EssCommand{
	
	static String name = "home";
	
	public Home() {
		super(name);
		List<String> a = Arrays.asList("gohome", "tphome", "tohome");
		this.description = "Go to a saved home.";
		this.usageMessage = "/home <homename> [player]";
		this.setAliases(a);
		
	}
	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}
	Database database = new Database();
	Homes homes = new Homes();
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_HOME, Permission.ESS_HOME_OTHER)) {
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
			if (args.length == 0) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 1) {
				if (homes.getHome(args[0], player) == null) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + homes.listHomes(player));
					return true;
				}
				homes.sendHome(args[0], player);
				sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent too " + ChatColor.GREEN + args[0]);
				return true;
			}
			if (args.length == 2) {
				if (Holder.hasPermission(player, Permission.ESS_HOME_OTHER)) {
					Player target = Bukkit.getPlayer(args[1]);
					if (!target.isOnline()) {
						try {
							if (database.getDatabaseEntry("playerdata", target.getName()) == null) {
								sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
								return true;
							}
							if (homes.getOfflineHome(args[0], database.getDatabaseEntry("playerdata", args[1])) == null) {
								sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
								sndr.sendMessage(ChatColor.GRAY + args[1] + "'s current homes: " + ChatColor.GREEN + homes.listHomes(target));
								return true;
							}
							homes.sendOfflineHome(args[0], database.getDatabaseEntry("playerdata", args[1]), player);
							sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent too " + ChatColor.GREEN + args[0]);
							return true;
						} catch (DatabaseNotFound e) {
							sndr.sendMessage(Error.DATABASE_NOT_FOUND.sendError());
							return true;
						}
					}
					if (homes.getHome(args[0], target) == null) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						sndr.sendMessage(ChatColor.GRAY + target.getName() + "'s current homes: " + ChatColor.GREEN + homes.listHomes(target));
						return true;
					}
					homes.sendOtherHome(args[0], target, player);
					sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent too " + ChatColor.GREEN + args[0]);
					return true;
				}
				else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
			}
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		else {
			sndr.sendMessage(Error.PLAYER_ONLY.sendError());
			return true;
		}
	}
	

}
