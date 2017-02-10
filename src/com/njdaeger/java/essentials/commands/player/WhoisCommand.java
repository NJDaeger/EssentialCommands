package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

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
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		sender.sendMessage(g + "Realname:" + gr + target.getName());
		sender.sendMessage(g + "Exp:" + gr + target.getExp());
		sender.sendMessage(g + "Exp. Level:" + gr + target.getLevel());
		sender.sendMessage(g + "Flyspeed:" + gr + PlayerConfig.getConfig(target).getFlySpeed());
		sender.sendMessage(g + "Walkspeed:" + gr + PlayerConfig.getConfig(target).getWalkingSpeed());
		sender.sendMessage(g + "Flyspeed:" + gr + PlayerConfig.getConfig(target).getGamemode());
		sender.sendMessage(g + "Health Level:" + gr + target.getHealth());
		sender.sendMessage(g + "Food Level:" + gr + target.getFoodLevel());
		sender.sendMessage(g + "UUID:" + gr + target.getUniqueId());
		return true;
	}
}
