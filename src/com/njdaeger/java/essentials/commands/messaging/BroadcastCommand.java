package com.njdaeger.java.essentials.commands.messaging;

import org.bukkit.Bukkit;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class BroadcastCommand extends EssCommand {

	public BroadcastCommand() {
	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "broadcast",
			desc = "Send a message to the entire server.",
			usage = "/broadcast <message>",
			min = 1,
			aliases = { "bc", "servermessage", "announce" },
			permissions = { Permission.ESS_BROADCAST })
	public boolean run(Sender sndr, String label, String[] args) {
		String message = "";
		for (String bc : args) {
			message = (message + bc + " ");
		}
		Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Broadcast" + ChatColor.GOLD + "] "
				+ ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', message));
		return true;
	}
}
