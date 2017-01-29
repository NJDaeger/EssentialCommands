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

public class HatCommand extends EssCommand {

	@Override
	@Cmd(
			name = "hat",
			desc = "Give yourself a hat.",
			usage = "/hat [player]",
			max = 1,
			aliases = { "hood", "helmet" },
			permissions = { Permission.ESS_HAT, Permission.ESS_HAT_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!(sndr instanceof Player)) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player player = (Player) sndr;
			if (player.getInventory().getItemInMainHand() == null) {
				sndr.sendMessage(Error.CANNOT_BE_HAT.sendError());
				return true;
			}
			player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
			sndr.sendMessage(ChatColor.GRAY + "Enjoy your new hat!");
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_HAT_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (target.getInventory().getItemInMainHand() == null) {
				sndr.sendMessage(Error.CANNOT_BE_HAT.sendError());
				return true;
			}
			target.getInventory().setHelmet(target.getInventory().getItemInMainHand());
			target.sendMessage(ChatColor.GRAY + "Enjoy your new hat!");
			sndr.sendMessage(ChatColor.GRAY + "You gave " + ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
					+ " a new hat!");
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
				Permission.ESS_HAT_OTHER));
		return true;
	}

}
