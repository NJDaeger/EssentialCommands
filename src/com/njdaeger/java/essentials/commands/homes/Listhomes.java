package com.njdaeger.java.essentials.commands.homes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.configuration.data.HomeData;
import com.njdaeger.java.configuration.data.OfflineHomeData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class Listhomes extends EssCommand {
	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "homes",
			desc = "Gets a list of your homes.",
			usage = "/homes [player] [detail]",
			max = 2,
			aliases = { "listhomes", "seehomes", "homelist" },
			permissions = { Permission.ESS_LISTHOMES, Permission.ESS_LISTHOMES_OTHER, Permission.ESS_LISTHOMES_DETAIL })
	public boolean run(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 0:
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (Holder.hasPermission(player, Permission.ESS_LISTHOMES)) {

				}
				if (Homes.getHome(null, player).listHomes() == null) {
					sndr.sendMessage(Error.NO_HOMES.sendError());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + Homes.getHome(null, player)
						.listHomes());
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		case 1:
			if (Holder.hasPermission(sndr, Permission.ESS_LISTHOMES_OTHER)) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					if (Database.getDatabase("playerdata").getEntry(args[0]) == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + Homes
							.getOfflineHome(null, args[0]).listHomes());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + Homes.getHome(null,
						target).listHomes());
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
					Permission.ESS_LISTHOMES_OTHER));
			return true;
		default:
			if (Holder.hasPermission(sndr, Permission.ESS_LISTHOMES_DETAIL)) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					if (Database.getDatabase("playerdata").getEntry(args[0]) == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (!Homes.getOfflineHome(args[1], args[0]).exists()) {
						sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
						return true;
					}
					OfflineHomeData home = Homes.getOfflineHome(args[1], args[0]);
					sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
					sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.getX());
					sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.getY());
					sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.getZ());
					sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.getYaw());
					sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.getPitch());
					sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.getWorld());
					return true;
				}
				if (!Homes.getHome(args[1], target).exists()) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					return true;
				}
				HomeData home = Homes.getHome(args[1], target);
				sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
				sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.getX());
				sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.getY());
				sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.getZ());
				sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.getYaw());
				sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.getPitch());
				sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.getWorld());
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
					Permission.ESS_LISTHOMES_OTHER));
			return true;
		}
	}
}