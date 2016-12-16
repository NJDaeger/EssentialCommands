package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class HatCommand extends BukkitCommand{
	
	public HatCommand() {
		super("hat");
		List<String> a = Arrays.asList("hood", "helmet");
		this.description = "Give yourself a hat.";
		this.usageMessage = "/hat [player]";
		this.setAliases(a);
	}
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_HAT, Permission.ESS_HAT_OTHER)) {
				if (args.length == 0) {
					if (player.getInventory().getItemInMainHand() == null) {
						sndr.sendMessage(Error.CANNOT_BE_HAT.sendError());
						return true;
					}
					else {
						player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
						sndr.sendMessage(ChatColor.GRAY + "Enjoy your new hat!");
						return true;
					}
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (args.length == 1) {
					if (Holder.hasPermission(player, Permission.ESS_HAT_OTHER)) {
						if (target.getInventory().getItemInMainHand() == null) {
						sndr.sendMessage(Error.CANNOT_BE_HAT.sendError());
						return true;
						}
						else {
							target.getInventory().setHelmet(target.getInventory().getItemInMainHand());
							target.sendMessage(ChatColor.GRAY + "Enjoy your new hat!");
							return true;
						}
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
			if (args.length > 1) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			if (args.length < 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (target.getInventory().getItemInMainHand() == null) {
				sndr.sendMessage(Error.CANNOT_BE_HAT.sendError());
				return true;
			}
			else {
				target.getInventory().setHelmet(target.getInventory().getItemInMainHand());
				target.sendMessage(ChatColor.GRAY + "Enjoy your new hat!");
				sndr.sendMessage(ChatColor.GRAY + "You changed " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + "'s hat!");
				return true;
			}
		}
	}

}
