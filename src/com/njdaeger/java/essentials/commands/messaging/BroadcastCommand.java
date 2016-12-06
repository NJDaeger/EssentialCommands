package com.njdaeger.java.essentials.commands.messaging;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Permission;

public class BroadcastCommand extends BukkitCommand {
	
	private ChatColor gold = ChatColor.GOLD;
	private ChatColor red = ChatColor.DARK_RED;
	private ChatColor green = ChatColor.GREEN;
	
	public BroadcastCommand() {
		super("broadcast");
		List<String> a = Arrays.asList("bc", "servermessage", "announce");
		this.description = "Send a message to the entire server.";
		this.usageMessage = "/broadcast <message>";
		this.setAliases(a);
	}
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_BROADCAST)) {
				if (args.length < 1) {
					sndr.sendMessage("" + Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				else if (args.length >= 1) {
					String message = "";
					for (String bc : args) {
						message = (message + bc + " ");
					}
					Bukkit.broadcastMessage(gold + "[" + red + "Broadcast"+ gold + "] " + green + ChatColor.translateAlternateColorCodes('&', message));
					return true;
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (args.length < 1) {
			sndr.sendMessage("" + Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		else if (args.length >= 1) {
			String message = "";
			for (String bc : args) {
				message = (message + bc + " ");
			}
			Bukkit.broadcastMessage(gold + "[" + red + "Broadcast"+ gold + "] " + green + ChatColor.translateAlternateColorCodes('&', message));
			return true;
		}
		return true;
	}
}
