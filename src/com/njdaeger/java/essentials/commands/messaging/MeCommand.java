package com.njdaeger.java.essentials.commands.messaging;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class MeCommand extends EssCommand {

	static String name = "me";

	public MeCommand() {
		super(name);
		this.description = "Describe what you're doing.";
		this.usageMessage = "/me <message>";
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_ME, Permission.ESS_ME_CHATCOLOR)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				String me = "";
				for (String message : args) {
					me = (me + message + " ");
					if (Holder.hasPermission(player, Permission.ESS_ME_CHATCOLOR)) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + player.getDisplayName()
								+ " " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', me));
						return true;
					}
					Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + player.getDisplayName()
							+ " " + ChatColor.GRAY + me);
					return true;
				}
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), player, "Unknown", Permission.ESS_ME,
					Permission.ESS_ME_CHATCOLOR));
			return true;
		}
		sndr.sendMessage(Error.PLAYER_ONLY.sendError());
		return true;
	}
}
