package com.njdaeger.java.essentials.commands.homes;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class Listhomes extends EssCommand {

	static String name = "homes";

	public Listhomes() {
		super(name);
		List<String> a = Arrays.asList("listhomes", "seehomes", "homelist");
		this.description = "Gets a list of your homes.";
		this.usageMessage = "/homes [player] [home]";
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
			if (Holder.hasPermission(player, Permission.ESS_LISTHOMES, Permission.ESS_LISTHOMES_OTHER)) {
			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (args.length == 0) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (homes.listHomes(player).length() == 0) {
					sndr.sendMessage(Error.NO_HOMES.sendError());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + homes.listHomes(player));
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_LISTHOMES_OTHER)) {

			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				if (Database.getDatabase("playerdata").getEntry(args[0]) == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN
						+ homes.listOfflineHomes(args[0]));
				return true;
			}
			if (homes.listHomes(target).length() == 0) {
				sndr.sendMessage(Error.NO_HOMES.sendError());
				return true;
			}
			sndr.sendMessage(ChatColor.GRAY + target.getName() + "'s current homes: " + ChatColor.GREEN
					+ homes.listHomes(target));
			return true;

		}
		if (args.length == 2) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				if (Database.getDatabase("playerdata").getEntry(args[0]) == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (homes.getOfflineHome(args[1], args[0]) == null) {
					sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
					return true;
				}
				YamlConfiguration home = homes.getOfflineHome(args[1], args[0]);
				sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
				sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.get("x"));
				sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.get("y"));
				sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.get("z"));
				sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.get("yaw"));
				sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.get("pitch"));
				sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.get("world"));
				return true;
			}
			if (homes.listHomes(target).length() == 0) {
				sndr.sendMessage(Error.NO_HOMES.sendError());
				return true;
			}
			if (homes.getHome(args[1], target) == null) {
				sndr.sendMessage(Error.HOME_NOTEXIST.sendError());
				return true;
			}
			YamlConfiguration home = homes.getHome(args[1], target);
			sndr.sendMessage(ChatColor.GRAY + "Home \"" + args[1] + "\" information.");
			sndr.sendMessage(ChatColor.GRAY + "x: " + ChatColor.GREEN + home.get("x"));
			sndr.sendMessage(ChatColor.GRAY + "y: " + ChatColor.GREEN + home.get("y"));
			sndr.sendMessage(ChatColor.GRAY + "z: " + ChatColor.GREEN + home.get("z"));
			sndr.sendMessage(ChatColor.GRAY + "yaw: " + ChatColor.GREEN + home.get("yaw"));
			sndr.sendMessage(ChatColor.GRAY + "pitch: " + ChatColor.GREEN + home.get("pitch"));
			sndr.sendMessage(ChatColor.GRAY + "world: " + ChatColor.GREEN + home.get("world"));
			return true;
		}
		sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
		return true;
	}
}