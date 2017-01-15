package com.njdaeger.java.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.BanAPI;

public class UnbanCommand extends BukkitCommand {

	public UnbanCommand() {
		super("unban");
		List<String> a = Arrays.asList("pardon", "removeban");
		this.description = "Unban a banned player.";
		this.usageMessage = "/unban <player>";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_UNBAN)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length == 1) {
					new BanAPI().unban(args[0]);
					return true;
				} else {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		} else {
			if (args.length == 0) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			if (args.length == 1) {
				new BanAPI().unban(args[0]);
				return true;
			} else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
	}
}
