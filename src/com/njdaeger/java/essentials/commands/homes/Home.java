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

public class Home extends EssCommand {

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

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_HOME, Permission.ESS_HOME_OTHER)) {
				switch (args.length) {
				case 0:
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				case 1:
					if (!Homes.getHome(args[0], player).exists()) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						return true;
					}
					Homes.getHome(args[0], player).sendHome();
					sndr.sendMessage(
							ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
					return true;
				case 2:
					if (Holder.hasPermission(player, Permission.ESS_HOME_OTHER)) {
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							if (Database.getDatabase("playerdata").getEntry(args[1]) == null) {
								sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
								return true;
							}
							if (!Homes.getOfflineHome(args[0], args[1]).exists()) {
								sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
								return true;
							}
							Homes.getOfflineHome(args[0], args[1]).sendOtherHome(player);
							sndr.sendMessage(
									ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
							return true;
						}
						if (!Homes.getHome(args[0], target).exists()) {
							sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
							return true;
						}
						Homes.getHome(args[0], target).sendOtherHome(player);
						sndr.sendMessage(
								ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
						return true;

					}
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				default:
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				}
				return true;
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		sndr.sendMessage(Error.PLAYER_ONLY.sendError());
		return true;
	}
}
