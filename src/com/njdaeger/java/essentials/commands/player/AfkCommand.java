package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.EssPlayer;
import com.njdaeger.java.wrapper.Sender;

public class AfkCommand extends EssCommand {

	@Override
	@Cmd(
			name = "afk",
			desc = "Mark yourself as away from keyboard.",
			usage = "/afk [player]",
			max = 1,
			aliases = { "akf", "away", "brb" },
			permissions = { Permission.ESS_AFK, Permission.ESS_AFK_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr instanceof EssPlayer) {
				EssPlayer player = (EssPlayer) sndr;
				PlayerConfig.getConfig(player).setAfk();
				return true;
			} else {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
		}
		if (sndr instanceof EssPlayer) {
			EssPlayer player = (EssPlayer) sndr;
			if (Holder.hasPermission(player, Permission.ESS_AFK_OTHER)) {
			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		EssPlayer target = (EssPlayer) Bukkit.getPlayer(args[0]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		PlayerConfig.getConfig(target).setAfk();
		return true;
	}
}