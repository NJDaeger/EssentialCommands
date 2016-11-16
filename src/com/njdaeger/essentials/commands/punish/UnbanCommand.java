package com.njdaeger.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.ServerBan;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class UnbanCommand extends BukkitCommand{

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
			if (TargetHasPermission.check(player, Permission.ESS_UNBAN)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length == 1) {
					ServerBan.unban(args[0], sndr);
					return true;
				}
				else {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			if (args.length == 0) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			if (args.length == 1) {
				ServerBan.unban(args[0], sndr);
				return true;
			}
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
	} 
}
