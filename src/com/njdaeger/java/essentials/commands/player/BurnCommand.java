package com.njdaeger.java.essentials.commands.player;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;

public class BurnCommand extends EssCommand {
	
	static String name = "burn";
	
	public BurnCommand() {
		super(name);
		this.description = "Let them burn.";
		this.usageMessage = "/burn [player] [seconds]";
	}
	
	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}
	
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_BURN, Permission.ESS_BURN_OTHER)) {
				
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (args.length > 2) {
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
		if (args.length == 0) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				player.setFireTicks(100);
				player.sendMessage(ChatColor.GRAY + "You are now on fire.");
				return true;
			}
			else {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
		}
		if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (target == player) {
					player.setFireTicks(100);
					player.sendMessage(ChatColor.GRAY + "You are now on fire.");
					return true;
				}
				if (Holder.hasPermission(player, Permission.ESS_BURN_OTHER)) {
					
				}
				else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
			}
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (target.isOp()) {
				sndr.sendMessage(Error.PLAYER_IS_OPPED.sendError());
				return true;
			}
			else {
				target.setFireTicks(100);
				sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " on fire for " + ChatColor.GREEN + "5 " + ChatColor.GRAY + "seconds.");
				return true;
			}
		}
		if (!Util.isNumber(args[1])) {
			sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
			return true;
		}
		int time = Integer.parseInt(args[1]);
		Player target = Bukkit.getPlayer(args[0]);
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (target == player) {
				player.setFireTicks(time*5);
				player.sendMessage(ChatColor.GRAY + "You are now on fire.");
				return true;
			}
			if (Holder.hasPermission(player, Permission.ESS_BURN_OTHER)) {
				
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (target.isOp()) {
			sndr.sendMessage(Error.PLAYER_IS_OPPED.sendError());
			return true;
		}
		else {
			target.setFireTicks(100);
			sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " on fire for " + ChatColor.GREEN + "5 " + ChatColor.GRAY + "seconds.");
			return true;
		}
	}
}
