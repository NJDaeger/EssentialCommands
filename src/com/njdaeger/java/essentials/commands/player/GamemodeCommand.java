package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Gamemode;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class GamemodeCommand extends EssCommand {

	@Override
	@Cmd(
			name = "gamemode",
			desc = "Switch between gamemodes.",
			usage = "/gamemode <gamemode> [player]",
			min = 1,
			max = 2,
			aliases = { "gm", "mode" },
			permissions = { Permission.ESS_GAMEMODE, Permission.ESS_GAMEMODE_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (Gamemode.getAliasUsed(args[0]) == null) {
			sndr.sendMessage(Error.UNKNOWN_GAMEMODE.sendError());
			return true;
		}
		if (args.length == 1) {
			if (sndr.isUser()) {
				User user = sndr.asUser();
				user.setGamemode(args[0]);
				user.sendMessage(ChatColor.GRAY + "Your gamemode is now set to " + ChatColor.GREEN + Gamemode
						.getAliasUsed(args[0]).toString().toLowerCase());
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		User target = Core.getUser(args[0]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (sndr.hasPermission(Permission.ESS_GAMEMODE_OTHER)) {
		} else {
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		target.setGamemode(args[0]);
		sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + Gamemode
				.getAliasUsed(args[0]).toString().toLowerCase());
		target.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + Gamemode
				.getAliasUsed(args[0]).toString().toLowerCase());
		return true;
	}
}
