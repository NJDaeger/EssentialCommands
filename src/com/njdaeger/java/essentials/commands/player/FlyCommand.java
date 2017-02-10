package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class FlyCommand extends EssCommand {

	@Override
	@Cmd(
			name = "fly",
			desc = "Make yourself fly!",
			usage = "/fly [player]",
			max = 1,
			permissions = { Permission.ESS_FLY, Permission.ESS_FLY_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isPlayer()) {
				if (sndr.asPlayer().isFlying()) {
					sndr.asPlayer().setFlying(false);
					sndr.asPlayer().setAllowFlight(false);
					sndr.sendMessage(ChatColor.GRAY + "You are no longer flying.");
					return true;
				}
				sndr.asPlayer().setAllowFlight(true);
				sndr.asPlayer().setFlying(true);
				sndr.sendMessage(ChatColor.GRAY + "You are now flying.");
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
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
				sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is no longer flying.");
				return true;
			}
			target.setAllowFlight(true);
			target.setFlying(true);
			target.sendMessage(ChatColor.GRAY + "You are now flying.");
			sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is now flying.");
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_FLY_OTHER));
		return true;
	}
}
