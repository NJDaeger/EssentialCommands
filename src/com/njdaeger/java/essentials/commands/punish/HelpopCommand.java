package com.njdaeger.java.essentials.commands.punish;

import org.bukkit.Bukkit;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class HelpopCommand extends EssCommand {

	@Cmd(
			name = "helpop",
			desc = "Message online admins.",
			usage = "/helpop <message>",
			min = 1,
			aliases = { "admins", "staff" },
			permissions = { Permission.ESS_HELPOP, Permission.ESS_HELPOP_RECEIVE })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		String message = "";
		for (String msg : args) {
			message += msg;
		}
		Bukkit.broadcast(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr.getName()
				+ ": " + ChatColor.BLUE + message, Permission.ESS_HELPOP_RECEIVE.getPermission());
		if (!sndr.hasPermission(Permission.ESS_HELPOP_RECEIVE.getPermission())) {
			sndr.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr
					.getName() + ": " + ChatColor.BLUE + message);
		}
		return true;
	}
}
