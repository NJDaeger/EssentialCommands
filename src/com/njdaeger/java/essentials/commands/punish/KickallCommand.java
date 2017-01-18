package com.njdaeger.java.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class KickallCommand extends EssCommand {

	public KickallCommand() {
		super("kickall");
		List<String> a = Arrays.asList("kickeveryone");
		this.description = "Kick all players from the server.";
		this.usageMessage = "/kickall [reason]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(permissions = { Permission.ESS_KICKALL })
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (canceled(sndr, args)) {
			return true;
		}
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
