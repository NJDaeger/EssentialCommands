package com.njdaeger.java.essentials.commands.player;

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

public class FlyCommand extends EssCommand {

	static String name = "fly";

	public FlyCommand() {
		super(name);
		this.description = "Make yourself fly!";
		this.usageMessage = "/fly [player]";
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_FLY, Permission.ESS_FLY_OTHER)) {
			switch (args.length) {
			case 0:
				if (sndr instanceof Player) {
					if (((Player) sndr).isFlying()) {
						((Player) sndr).setFlying(false);
						((Player) sndr).setAllowFlight(false);
						sndr.sendMessage(ChatColor.GRAY + "You are no longer flying.");
						return true;
					}
					((Player) sndr).setAllowFlight(true);
					((Player) sndr).setFlying(true);
					sndr.sendMessage(ChatColor.GRAY + "You are now flying.");
					return true;
				}
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
				if (Holder.hasPermission(sndr, Permission.ESS_FLY_OTHER)) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (target.isFlying()) {
						target.setFlying(false);
						target.setAllowFlight(false);
						target.sendMessage(ChatColor.GRAY + "You are no longer flying.");
						sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
								+ " is no longer flying.");
						return true;
					}
					target.setAllowFlight(true);
					target.setFlying(true);
					target.sendMessage(ChatColor.GRAY + "You are now flying.");
					sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is now flying.");
					return true;
				}
				sndr.sendMessage(Parse.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
						Permission.ESS_FLY_OTHER));
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		sndr.sendMessage(Parse.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown", Permission.ESS_FLY_OTHER,
				Permission.ESS_FLY));
		return true;
	}
}
