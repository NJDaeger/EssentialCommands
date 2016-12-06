package com.njdaeger.java.essentials.commands.punish;

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
import com.njdaeger.java.essentials.utils.ServerBan;

public class TempBanCommand extends BukkitCommand{
	
	public TempBanCommand() {
		super("tempban");
		List<String> a = Arrays.asList("temp", "tb", "bantemp");
		this.description = "Temp ban a player.";
		this.usageMessage = "/tempban <player> <time:<d/h/m/s>> [reason]";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_TEMPBAN)) {
				if (args.length <= 1) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (target.equals(sndr) || Holder.hasPermission(player, Permission.ESS_BAN_EXEMPT)) {
					sndr.sendMessage(Error.CANNOT_BAN_TARGET.sendError());
					return true;
				}
				if (args.length == 2) {
					ServerBan.newTempBan(target, null, sndr, args[1]);
					return true;
				}
				else {
					StringBuilder builder = new StringBuilder();
					for(int i = 2; i < args.length; i++) builder.append(args[i]).append(' ');
					String reason = builder.toString();
					ServerBan.newTempBan(target, ChatColor.translateAlternateColorCodes('&', reason), sndr, args[1]);
					return true;	
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			if (args.length <= 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (args.length == 2) {
				ServerBan.newTempBan(target, null, sndr, args[1]);
				return true;
			}
			else {
				StringBuilder builder = new StringBuilder();
				for(int i = 2; i < args.length; i++) builder.append(args[i]).append(' ');
				String reason = builder.toString();
				ServerBan.newTempBan(target, ChatColor.translateAlternateColorCodes('&', reason), sndr, args[1]);
				return true;
			}
		}
	}
}
