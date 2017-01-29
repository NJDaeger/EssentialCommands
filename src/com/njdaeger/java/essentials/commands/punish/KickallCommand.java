package com.njdaeger.java.essentials.commands.punish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class KickallCommand extends EssCommand {

	@Cmd(
			name = "kickall",
			desc = "Kick all players from the server.",
			usage = "/kickall [reason]",
			permissions = { Permission.ESS_KICKALL })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		if (Bukkit.getOnlinePlayers() == null) {
			sndr.sendMessage(Error.NO_PLAYERS_ONLINE.sendError());
			return true;
		}
		if (args.length == 0) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.kickPlayer(sndr.getName() + " kicked all players.");
			}
			return true;
		}
		String reason = "";
		for (String msg : args) {
			reason += msg;
		}
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		}
		return true;
	}
}
