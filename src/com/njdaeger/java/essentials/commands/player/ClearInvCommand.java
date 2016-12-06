package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Permission;

public class ClearInvCommand extends BukkitCommand {
	
	public ClearInvCommand() {
		super("clear");
		List<String> a = Arrays.asList("ci", "clearinv", "clean");
		this.description = "Clears a player's inventory.";
		this.usageMessage = "/clear [player]";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_CLEAR, Permission.ESS_CLEAR_OTHER)) {
				if (args.length == 0) {
					ItemStack[] stack = player.getInventory().getContents();
					int amount = 0;
					for (ItemStack item : stack) {
						if (item != null) {
							amount += item.getAmount();
						}
					}
					player.getInventory().clear();
					sndr.sendMessage(ChatColor.GRAY + "Cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from " + ChatColor.GREEN + player.getName());
					return true;
				}
				if (args.length == 1) {
					if (Holder.hasPermission(player, Permission.ESS_CLEAR_OTHER)) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						ItemStack[] stack = target.getInventory().getContents();
						int amount = 0;
						for (ItemStack item : stack) {
							if (item != null) {
								amount += item.getAmount();
							}
						}
						target.getInventory().clear();
						sndr.sendMessage(ChatColor.GRAY + "Cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from " + ChatColor.GREEN + target.getName());
						target.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from you.");
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
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			if (args.length == 0) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				ItemStack[] stack = target.getInventory().getContents();
				int amount = 0;
				for (ItemStack item : stack) {
					if (item != null) {
						amount += item.getAmount();
					}
				}
				target.getInventory().clear();
				sndr.sendMessage(ChatColor.GRAY + "Cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from " + ChatColor.GREEN + target.getName());
				target.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from you.");
				return true;
			}
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			
		}
	}
}
