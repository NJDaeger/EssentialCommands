package com.njdaeger.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;
import com.njdaeger.essentials.utils.Util;

public class HelpopCommand extends BukkitCommand{
	
	public HelpopCommand() {
		super("helpop");
		List<String> a = Arrays.asList("admins", "staff");
		this.description = "Message online admins.";
		this.usageMessage = "/helpop <message>";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_HELPOP, Permission.ESS_HELPOP_RECEIVE)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				String message = "";
				for (String msg :  args) {
					message += msg;
				}
				Util.broadcast(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + player.getDisplayName() + ": " + ChatColor.BLUE + message, Permission.ESS_HELPOP_RECEIVE);
				sndr.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + player.getDisplayName() + ": " + ChatColor.BLUE + message);
				return true;
				
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
			String message = "";
			for (String msg :  args) {
				message += msg;
			}
			Util.broadcast(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr.getName() + ": " + ChatColor.BLUE + message, Permission.ESS_HELPOP_RECEIVE);
			sndr.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr.getName() + ": " + ChatColor.BLUE + message);
			return true;
		}
	}
}
