package com.njdaeger.java.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class HelpopCommand extends EssCommand {

	public HelpopCommand() {
		super("helpop");
		List<String> a = Arrays.asList("admins", "staff");
		this.description = "Message online admins.";
		this.usageMessage = "/helpop <message>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(min = 1, permissions = { Permission.ESS_HELPOP, Permission.ESS_HELPOP_RECEIVE })
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (canceled(sndr, args)) {
			return true;
		}
		String message = "";
		for (String msg : args) {
			message += msg;
		}
		Bukkit.broadcast(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr.getName()
				+ ": " + ChatColor.BLUE + message, Permission.ESS_HELPOP_RECEIVE.getPermission());
		sndr.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "HELPOP" + ChatColor.GRAY + "] " + sndr.getName()
				+ ": " + ChatColor.BLUE + message);
		return true;
	}
}
