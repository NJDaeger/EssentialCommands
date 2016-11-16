package com.njdaeger.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.ServerBan;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class BanCommand extends BukkitCommand{
	
	public BanCommand() {
		super("ban");
		List<String> a = Arrays.asList("perm", "banhammer");
		this.description = "Ban a player from the server.";
		this.usageMessage = "/ban <player> [reason]";
		this.setAliases(a);
		
		
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_BAN)) {
				if (args.length < 1) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				Player target = (Player) Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (TargetHasPermission.check(target, Permission.ESS_BAN_EXEMPT)) {
					sndr.sendMessage(Error.CANNOT_BAN_TARGET.sendError());
					return true;
				}
				if (args.length == 1) {
					ServerBan.newPermBan(target, null, sndr);
					return true;
				}
				else {
					StringBuilder builder = new StringBuilder();
					for(int i = 2; i < args.length; i++) builder.append(args[i]).append(' ');
					String reason = builder.toString();
					ServerBan.newPermBan(target, ChatColor.translateAlternateColorCodes('&', reason), sndr);
					return true;	
				}
			}
			else {
				player.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			if (args.length < 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player target = (Player) Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (args.length == 1) {
				ServerBan.newPermBan(target, null, sndr);
				return true;
			}
			else {
				StringBuilder builder = new StringBuilder();
				for(int i = 2; i < args.length; i++) builder.append(args[i]).append(' ');
				String reason = builder.toString();	
				ServerBan.newPermBan(target, ChatColor.translateAlternateColorCodes('&', reason), sndr);
				return true;
			}
		}
	}
}
