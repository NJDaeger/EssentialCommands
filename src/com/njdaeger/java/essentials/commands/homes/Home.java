package com.njdaeger.java.essentials.commands.homes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class Home extends EssCommand {

	@Override
	@Cmd(
			name = "home",
			desc = "Go to a saved home.",
			usage = "/home <homename> <player>",
			max = 2,
			min = 1,
			executor = Executor.PLAYER,
			aliases = { "gohome", "tphome", "tohome" },
			permissions = { Permission.ESS_HOME, Permission.ESS_HOME_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		Player player = sndr.asPlayer();
		if (args.length == 1) {
			if (!Homes.getHome(args[0], player).exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return true;
			}
			Homes.getHome(args[0], player).sendHome();
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return true;
		}
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
				sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
				return true;
			}
			if (!Homes.getHome(args[0], target).exists()) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return true;
			}
			Homes.getHome(args[0], target).sendOtherHome(player);
			sndr.sendMessage(ChatColor.GRAY + "You have successfully been sent to " + ChatColor.GREEN + args[0]);
			return true;

		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_HOME_OTHER));
		return true;
	}
}
