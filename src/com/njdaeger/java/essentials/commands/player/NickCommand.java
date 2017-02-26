package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class NickCommand extends EssCommand {

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
			if (sndr.isUser()) {
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
					sndr.asUser().setNickname(sndr.getName());
					sndr.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
					return true;
				}
				sndr.asUser().setNickname(ChatColor.translateAlternateColorCodes('&', args[0]));
				sndr.sendMessage(ChatColor.GRAY + "Your nickname is now \"" + this.getNick(args[0]) + ChatColor.GRAY
						+ "\".");
				return true;
			} else {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
		}
		if (!sndr.hasPermission(Permission.ESS_NICK_OTHER)) {
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		User target = Core.getUser(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
			target.setNickname(target.getName());
			target.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
			sndr.sendMessage(ChatColor.GRAY + "You removed " + target.getName() + ChatColor.GRAY + "'s nickname.");
			return true;
		}
		target.setNickname(ChatColor.translateAlternateColorCodes('&', args[0]));
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
