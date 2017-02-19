package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class FoodCommand extends EssCommand {

	@Override
	@Cmd(
			name = "food",
			desc = "Fill your food bar.",
			usage = "/food [player]",
			max = 1,
			aliases = { "feed" },
			permissions = { Permission.ESS_FOOD, Permission.ESS_FOOD_OTHER })
	public boolean run(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			if (Holder.hasPermission(sender, Permission.ESS_FOOD_OTHER)) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				target.setFoodLevel(20);
				sender.sendMessage(ChatColor.GRAY + "You restored " + ChatColor.GREEN + target.getDisplayName()
						+ ChatColor.GRAY + "'s food level.");
				target.sendMessage(ChatColor.GRAY + "Your food level has been restored.");
				return true;
			}
			sender.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sender.asPlayer(), "Unknown",
					Permission.ESS_FOOD_OTHER));
			return true;
		}
		if (!sender.isPlayer()) {
			sender.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		sender.asPlayer().setFoodLevel(20);
		sender.sendMessage(ChatColor.GRAY + "Your food level has been restored.");
		return true;
	}
}
