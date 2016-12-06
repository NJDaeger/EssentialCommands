package com.njdaeger.java.essentials.commands.punish;

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

public class KickallCommand extends BukkitCommand{
	
	public KickallCommand() {
		super("kickall");
		List<String> a = Arrays.asList("kickeveryone");
		this.description = "Kick all players from the server.";
		this.usageMessage = "/kickall [reason]";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Bukkit.getOnlinePlayers() == null) {
			sndr.sendMessage(Error.NO_PLAYERS_ONLINE.sendError());
			return true;
		}
		if (args.length == 0) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (Holder.hasPermission(player, Permission.ESS_KICKALL)) {
					for (Player players : Bukkit.getOnlinePlayers()) {
						players.kickPlayer(sndr.getName() + " kicked all players.");
						return true;
					}
				}
				else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
				return true;
			}
			else {
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.kickPlayer(sndr.getName() + " kicked all players.");
					return true;
				}
				return true;
			}
		}
		else {
			String reason = "";
			for (String msg : args) {
				reason += msg;
			}
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (Holder.hasPermission(player, Permission.ESS_KICKALL)) {
					for (Player players : Bukkit.getOnlinePlayers()) {
						players.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
						return true;
					}
				}
				else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
				return true;
			}
			else {
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
					return true;
				}
				return true;
			}
		}
	}
}
