package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class KillCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "kill",
			desc = "Kill someone.",
			usage = "/kill [player]",
			max = 1,
			aliases = { "death", "perish", "suicide" },
			permissions = { Permission.ESS_SUICIDE, Permission.ESS_KILL_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (label.equalsIgnoreCase("suicide")) {
			if (sndr instanceof Player) {
				switch (args.length) {
				case 0:
					if (Holder.hasPermission(sndr, Permission.ESS_SUICIDE)) {
						((Player) sndr).setHealth(0);
						sndr.sendMessage(ChatColor.GRAY + "You committed suicide.");
						return true;
					}
					sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
							Permission.ESS_SUICIDE));
					return true;
				default:
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
			}
			sndr.sendMessage(Error.PLAYER_ONLY.sendError());
			return true;
		}
		if (args.length == 0) {
			if (sndr instanceof Player) {
				if (Holder.hasPermission(sndr, Permission.ESS_SUICIDE)) {
					((Player) sndr).setHealth(0);
					sndr.sendMessage(ChatColor.GRAY + "You committed suicide.");
					return true;
				}
				sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
						Permission.ESS_SUICIDE));
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_KILL_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			sndr.sendMessage(ChatColor.GRAY + "You killed " + target.getName());
			target.setHealth(0);
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
				Permission.ESS_SUICIDE));
		return true;
	}
}
