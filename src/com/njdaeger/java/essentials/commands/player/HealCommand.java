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

public class HealCommand extends EssCommand {

	@Override
	@Cmd(
			name = "heal",
			desc = "Replenish your health.",
			usage = "/heal [player]",
			max = 1,
			aliases = { "hearts", "health" },
			permissions = { Permission.ESS_HEAL, Permission.ESS_HEAL_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isPlayer()) {
				sndr.asPlayer().setHealth(20);
				sndr.sendMessage(ChatColor.GRAY + "You have been healed.");
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_HEAL_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			target.setHealth(20);
			target.sendMessage(ChatColor.GRAY + "You have been healed.");
			sndr.sendMessage(ChatColor.GRAY + "You healed " + ChatColor.GREEN + target.getName());
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_HEAL_OTHER));
		return true;
	}
}