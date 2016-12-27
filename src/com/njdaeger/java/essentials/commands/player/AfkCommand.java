package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class AfkCommand extends EssCommand {

	public static String name = "afk";

	public AfkCommand() {
		super("afk");
		List<String> a = Arrays.asList("akf", "away", "brb");
		this.description = "Mark yourself as away from keyboard.";
		this.usageMessage = "/afk [player]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (args.length > 1) {
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_AFK, Permission.ESS_AFK_OTHER)) {

			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (args.length == 0) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				PlayerConfig.getConfig(player).setAfk();
				return true;
			} else {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
		} else {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (Holder.hasPermission(player, Permission.ESS_AFK_OTHER)) {
				} else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			PlayerConfig.getConfig(target).setAfk();
			return true;
		}
	}
}