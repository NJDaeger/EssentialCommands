package com.njdaeger.essentials.commands.player;


import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.AfkStatus;
import com.njdaeger.essentials.utils.Status;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class AfkCommand extends BukkitCommand{

	public AfkCommand() {
		super("afk");
		List<String> a = Arrays.asList("akf", "away", "brb");
		this.description = "Mark yourself as away from keyboard.";
		this.usageMessage = "/afk [player]";
		this.setAliases(a);
	}
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (!(sndr instanceof Player)) {
			if (args.length == 1) {
				if (Bukkit.getPlayer(args[0]) == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				else {
					Player player = Bukkit.getPlayer(args[0]);
					AfkStatus.setAfk(player, Status.AUTO);
					return true;
				}
			} 
			if (args.length == 0) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			} 
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		Player player = (Player) sndr;
		if (args.length == 1) {
			
			if(TargetHasPermission.check(player, Permission.ESS_AFK_OTHER)) {
				if (Bukkit.getPlayer(args[0]) == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				else {
					Player target = Bukkit.getPlayer(args[0]);
					AfkStatus.setAfk(target, Status.AUTO);
					return true;
				}
			} 
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (args.length == 0) {
			if (TargetHasPermission.check(player, Permission.ESS_AFK, Permission.ESS_AFK_OTHER)) {
				AfkStatus.setAfk(player, Status.AUTO);
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
}