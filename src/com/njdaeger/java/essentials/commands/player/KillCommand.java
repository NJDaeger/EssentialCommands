package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class KillCommand extends EssCommand {

	static String name = "kill";

	public KillCommand() {
		super(name);
		List<String> a = Arrays.asList("death", "perish", "suicide");
		this.description = "Kill someone.";
		this.usageMessage = "/kill";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_KILL_OTHER, Permission.ESS_SUICIDE)) {
			if (label.equalsIgnoreCase("suicide")) {
				if (sndr instanceof Player) {
					switch (args.length) {
					case 0:
						if (Holder.hasPermission(sndr, Permission.ESS_SUICIDE)) {
							((Player) sndr).setHealth(0);
							sndr.sendMessage(ChatColor.GRAY + "You committed suicide.");
							return true;
						}
						sndr.sendMessage(Error.NO_PERMISSION.sendError());
						return true;
					default:
						sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
						return true;
					}
				}
				sndr.sendMessage(Error.PLAYER_ONLY.sendError());
				return true;
			}
			switch (args.length) {
			case 0:
				if (sndr instanceof Player) {
					if (Holder.hasPermission(sndr, Permission.ESS_SUICIDE)) {
						((Player) sndr).setHealth(0);
						sndr.sendMessage(ChatColor.GRAY + "You committed suicide.");
						return true;
					}
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
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
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}
