package com.njdaeger.java.essentials.commands.messaging;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.Parse;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class BroadcastCommand extends EssCommand {

	static String name = "broadcast";

	private ChatColor gold = ChatColor.GOLD;
	private ChatColor red = ChatColor.DARK_RED;
	private ChatColor green = ChatColor.GREEN;

	public BroadcastCommand() {
		super("broadcast");
		List<String> a = Arrays.asList("bc", "servermessage", "announce");
		this.description = "Send a message to the entire server.";
		this.usageMessage = "/broadcast <message>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_BROADCAST)) {
			} else {
				sndr.sendMessage(Parse.parse(Error.NO_PERMISSION.getError(), player, "Unknown",
						Permission.ESS_BROADCAST));
				return true;
			}
		}
		if (args.length < 1) {
			sndr.sendMessage("" + Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		} else {
			String message = "";
			for (String bc : args) {
				message = (message + bc + " ");
			}
			Bukkit.broadcastMessage(gold + "[" + red + "Broadcast" + gold + "] " + green + ChatColor
					.translateAlternateColorCodes('&', message));
			return true;
		}
	}
}
