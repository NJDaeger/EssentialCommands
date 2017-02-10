package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class MoreCommand extends EssCommand {

	@Override
	@Cmd(
			name = "more",
			desc = "Max the amount of items you have in your hand.",
			usage = "/more",
			min = 0,
			max = 0,
			aliases = { "max" },
			executor = Executor.PLAYER,
			permissions = { Permission.ESS_MORE })
	public boolean run(Sender sender, String label, String[] args) {
		Player player = sender.asPlayer();
		if ((player.getInventory().getItemInMainHand() == null)) {
			sender.sendMessage(Error.NO_ITEM_IN_HAND.sendError());
			return true;
		}
		int max = player.getInventory().getItemInMainHand().getMaxStackSize();
		int current = player.getInventory().getItemInMainHand().getAmount();
		if (max <= current) {
			sender.sendMessage(Error.ITEM_ALREADY_MAXED.sendError());
			return true;
		}
		player.getInventory().getItemInMainHand().setAmount(max);
		sender.sendMessage(ChatColor.GRAY + "Added " + ChatColor.GREEN + (max - current) + ChatColor.GRAY
				+ " items to your stack.");
		return true;
	}
}
