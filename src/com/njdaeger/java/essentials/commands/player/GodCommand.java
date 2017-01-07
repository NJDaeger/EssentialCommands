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
import com.njdaeger.java.configuration.data.PlayerConfigData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class GodCommand extends EssCommand {

	static String name = "god";

	public GodCommand() {
		super(name);
		List<String> a = Arrays.asList("invincible");
		this.description = "Make yourself unkillable.";
		this.usageMessage = "/god [player]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_GOD, Permission.ESS_GOD_OTHER)) {
			switch (args.length) {
			case 0:
				if (sndr instanceof Player) {
					PlayerConfigData c = PlayerConfig.getConfig((Player) sndr);
					if (c.isGod() == true) {
						PlayerConfig.getConfig((Player) sndr).setGod();
						sndr.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
						return true;
					}
					PlayerConfig.getConfig((Player) sndr).setGod();
					sndr.sendMessage(ChatColor.GRAY + "You are now in God mode.");
					return true;
				}
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
				if (Holder.hasPermission(sndr, Permission.ESS_GOD_OTHER)) {
					Player target = Bukkit.getPlayer(args[0]);

					if (target == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					PlayerConfigData c = PlayerConfig.getConfig(target);
					if (c.isGod() == true) {
						PlayerConfig.getConfig(target).setGod();
						target.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
						sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
								+ " is no longer in God mode.");
						return true;
					}
					PlayerConfig.getConfig(target).setGod();
					target.sendMessage(ChatColor.GRAY + "You are now in God mode.");
					sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
							+ " is now in God mode.");
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
