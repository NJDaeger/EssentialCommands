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
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class BurnCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "burn",
			desc = "Let them burn.",
			usage = "/burn [player] [seconds]",
			max = 2,
			permissions = { Permission.ESS_BURN, Permission.ESS_BURN_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 0:
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				player.setFireTicks(100);
				player.sendMessage(ChatColor.GRAY + "You are now on fire.");
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		case 1:
			if (Holder.hasPermission(sndr, Permission.ESS_BURN_OTHER)) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (Holder.hasPermission(target, Permission.ESS_ALL)) {
					sndr.sendMessage(Error.CANNOT_BURN_TARGET.sendError());
					return true;
				}
				target.setFireTicks(100);
				sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is now on fire.");
				target.sendMessage(ChatColor.GRAY + "You are now on fire.");
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
					Permission.ESS_BURN_OTHER));
			return true;
		default:
			if (Holder.hasPermission(sndr, Permission.ESS_BURN_OTHER)) {
				if (!Util.isNumber(args[1])) {
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return true;
				}
				int time = Integer.parseInt(args[1]);
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (Holder.hasPermission(target, Permission.ESS_ALL)) {
					sndr.sendMessage(Error.CANNOT_BURN_TARGET.sendError());
					return true;
				}
				target.setFireTicks(time * 5);
				sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is now on fire.");
				target.sendMessage(ChatColor.GRAY + "You are now on fire.");

			}
		}
		return true;
	}
}
