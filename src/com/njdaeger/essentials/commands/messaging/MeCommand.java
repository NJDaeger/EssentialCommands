package com.njdaeger.essentials.commands.messaging;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class MeCommand extends BukkitCommand {
	
	public MeCommand() {
		super("me");
		this.description = "Describe what you're doing.";
		this.usageMessage = "/me <message>";
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_ME, Permission.ESS_ME_CHATCOLOR)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				else {
					String me = "";
					for (String message : args) {
						me = (me + message + " ");
						if (TargetHasPermission.check(player, Permission.ESS_ME_CHATCOLOR)) {
							Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + player.getDisplayName() + " " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', me));
							return true;
						}
						else {
							Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + player.getDisplayName() + " " + ChatColor.GRAY + me);
							return true;
						}
					}
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			sndr.sendMessage(Error.PLAYER_ONLY.sendError());
			return true;
		}
		return true;
	}
}
