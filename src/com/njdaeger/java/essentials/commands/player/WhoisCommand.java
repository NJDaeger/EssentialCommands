package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class WhoisCommand extends EssCommand {

	private ChatColor g = ChatColor.GRAY;
	private ChatColor gr = ChatColor.GREEN;

	@Override
	@Cmd(
			name = "whois",
			desc = "Information about the player.",
			usage = "/whois <player>",
			min = 1,
			max = 1,
			permissions = { Permission.ESS_WHOIS })
	public boolean run(Sender sender, String label, String[] args) {
		User user = Core.getUser(args[0]);
		if (user == null) {
			sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		sender.sendMessage(g + "Realname: " + gr + user.getName());
		sender.sendMessage(g + "Exp: " + gr + user.getBase().getExp());
		sender.sendMessage(g + "Exp. Level: " + gr + user.getBase().getLevel());
		sender.sendMessage(g + "Flyspeed: " + gr + user.getFlyingSpeed());
		sender.sendMessage(g + "Walkspeed: " + gr + user.getWalkingSpeed());
		sender.sendMessage(g + "Gamemode: " + gr + user.getGamemode().name().toLowerCase());
		sender.sendMessage(g + "Health Level: " + gr + user.getBase().getHealth());
		sender.sendMessage(g + "Food Level: " + gr + user.getBase().getFoodLevel());
		sender.sendMessage(g + "UUID: " + gr + user.getId());
		return true;
	}
}
