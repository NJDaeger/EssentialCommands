package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.data.PlayerConfigData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class VanishCommand extends EssCommand {

	@Cmd(
			name = "vanish",
			desc = "Hide yourself from others.",
			usage = "/vanish [player]",
			max = 1,
			aliases = { "v", "hide" },
			permissions = { Permission.ESS_VANISH, Permission.ESS_VANISH_OTHER })
	@Override
	public boolean run(Sender sndr, String commandLabel, String[] args) {
		if (args.length == 0) {
			if (!sndr.isPlayer()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			PlayerConfigData config = PlayerConfig.getConfig(sndr.asPlayer());
			config.setHidden();
			if (config.isHidden()) {
				sndr.sendMessage(ChatColor.GRAY + "You are now hidden.");
				return true;
			}
			sndr.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_VANISH_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			PlayerConfigData config = PlayerConfig.getConfig(target);
			config.setHidden();
			if (config.isHidden()) {
				sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is now hidden.");
				target.sendMessage(ChatColor.GRAY + "You are now hidden.");
				return true;
			}
			sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is no longer hidden.");
			target.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_VANISH_OTHER));
		return true;
	}
}
