package com.njdaeger.essentials.commands.homes;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.configapi.configuration.Database;
import com.configapi.configuration.Homes;
import com.configapi.configuration.exceptions.DatabaseNotFound;
import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class Listhomes extends BukkitCommand{
	
	public Listhomes() {
		super("homes");
		List<String> a = Arrays.asList("listhomes", "seehomes", "homelist");
		this.description = "Gets a list of your homes.";
		this.usageMessage = "/homes [player] [home]";
		this.setAliases(a);
	}
	Database database = new Database();
	Homes homes = new Homes();
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_LISTHOMES, Permission.ESS_LISTHOMES_OTHER)) {
				if (args.length == 0) {
					if (homes.listHomes(player).length() == 0) {
						sndr.sendMessage(Error.NO_HOMES.sendError());
						return true;
					}
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + homes.listHomes(player));
					return true;
				}
				if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if (TargetHasPermission.check(player, Permission.ESS_LISTHOMES_OTHER)){
						if (target == null) {
							try {
								if (database.getDatabaseEntry("playerdata", args[0]) == null) {
									sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
									return true;
								}
								sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + homes.listOfflineHomes(args[0]));
								return true;
							} catch (DatabaseNotFound e) {
								sndr.sendMessage(Error.DATABASE_NOT_FOUND.sendError());
								return true;
							}
						}
						if (homes.listHomes(target).length() == 0) {
							sndr.sendMessage(Error.NO_HOMES.sendError());
							return true;
						}
						sndr.sendMessage(ChatColor.GRAY + target.getName() + "'s current homes: " + ChatColor.GREEN + homes.listHomes(target));
						return true;
					}
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				
				}
				if (args.length == 2) {
					Player target = Bukkit.getPlayer(args[0]);
					if (TargetHasPermission.check(player, Permission.ESS_LISTHOMES_OTHER)){
						if (target == null) {
							try {
								if (database.getDatabaseEntry("playerdata", args[0]) == null) {
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
							} catch (DatabaseNotFound e) {
								sndr.sendMessage(Error.DATABASE_NOT_FOUND.sendError());
								return true;
							}
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
			
			
			if (args.length == 0) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			
			
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					try {
						if (database.getDatabaseEntry("playerdata", args[0]) == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + homes.listOfflineHomes(args[0]));
						return true;
					} catch (DatabaseNotFound e) {
						sndr.sendMessage(Error.DATABASE_NOT_FOUND.sendError());
						return true;
					}
				}
				if (homes.listHomes(target).length() == 0) {
					sndr.sendMessage(Error.NO_HOMES.sendError());
					return true;
				}
				sndr.sendMessage(ChatColor.GRAY + args[0] + "'s current homes: " + ChatColor.GREEN + homes.listHomes(target));
				return true;
			}
			
			
			if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					try {
						if (database.getDatabaseEntry("playerdata", args[0]) == null) {
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
					} catch (DatabaseNotFound e) {
						sndr.sendMessage(Error.DATABASE_NOT_FOUND.sendError());
						return true;
					}
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
				sndr.sendMessage(ChatColor.GRAY + "Home " + args[1] + " information.");
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
}
