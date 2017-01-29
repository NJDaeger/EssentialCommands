package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.data.PlayerConfigData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class GodCommand extends EssCommand {

	@Override
	@Cmd(
			name = "god",
			desc = "Make yourself unkillable.",
			usage = "/god [player]",
			max = 0,
			aliases = { "invincible" },
			permissions = { Permission.ESS_GOD, Permission.ESS_GOD_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr instanceof Player) {
				PlayerConfigData c = PlayerConfig.getConfig((Player) sndr);
				if (c.isGod() == true) {
					c.setGod();
					sndr.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
					return true;
				}
				c.setGod();
				sndr.sendMessage(ChatColor.GRAY + "You are now in God mode.");
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_GOD_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			PlayerConfigData c = PlayerConfig.getConfig(target);
			if (c.isGod() == true) {
				c.setGod();
				target.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
				sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
						+ " is no longer in God mode.");
				return true;
			}
			c.setGod();
			target.sendMessage(ChatColor.GRAY + "You are now in God mode.");
			sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is now in God mode.");
			return true;
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}
