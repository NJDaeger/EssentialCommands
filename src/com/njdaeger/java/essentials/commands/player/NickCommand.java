package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class NickCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "nick",
			desc = "Give yourself a nickname.",
			usage = "/nick <nickname> [player]",
			min = 1,
			max = 2,
			aliases = { "nickname", "newname", "disguise" },
			permissions = { Permission.ESS_NICK, Permission.ESS_NICK_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args[0].length() > 30) {
			sndr.sendMessage(Error.NICKNAME_TOO_LONG.sendError());
			return true;
		}
		if (args.length == 1) {
			if (sndr instanceof Player) {
				if (((Player) sndr).getDisplayName() == sndr.getName()) {
				}
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
					PlayerConfig.getConfig((Player) sndr).setNick(sndr.getName());
					sndr.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
					return true;
				}
				PlayerConfig.getConfig((Player) sndr).setNick(args[0]);
				sndr.sendMessage(ChatColor.GRAY + "Your nickname is now \"" + this.getNick(args[0]) + ChatColor.GRAY
						+ "\".");
				return true;
			} else {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
		}
		if (sndr instanceof Player) {
			if (Holder.hasPermission((Player) sndr, Permission.ESS_NICK_OTHER)) {
			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
			PlayerConfig.getConfig(target).setNick(target.getName());
			target.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
			sndr.sendMessage(ChatColor.GRAY + "You removed " + target.getDisplayName() + ChatColor.GRAY
					+ "'s nickname.");
			return true;
		}
		PlayerConfig.getConfig(target).setNick(args[0]);
		target.sendMessage(ChatColor.GRAY + "Your nickname is now \"" + this.getNick(args[0]) + ChatColor.GRAY + "\".");
		sndr.sendMessage(ChatColor.GRAY + "You changed " + target.getName() + ChatColor.GRAY + "'s nickname to \""
				+ this.getNick(args[0]) + ChatColor.GRAY + "\".");
		return true;
	}

	public String getNick(String nick) {
		if (nick.contains("&")) {
			return ChatColor.translateAlternateColorCodes('&', nick);
		} else
			return ChatColor.GREEN + nick;
	}
}
