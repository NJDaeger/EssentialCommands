package com.njdaeger.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.GodStatus;
import com.njdaeger.essentials.utils.Status;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class GodCommand extends BukkitCommand{
	
	public GodCommand() {
		super("god");
		List<String> a = Arrays.asList("invincible");
		this.description = "Make yourself unkillable.";
		this.usageMessage = "/god [player]";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_GOD, Permission.ESS_GOD_OTHER)) {
				if (args.length == 1) {
					if (TargetHasPermission.check(player, Permission.ESS_GOD_OTHER)) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						GodStatus.setGod(target, sndr, Status.AUTO);
						return true;
						
					}
					else {
						sndr.sendMessage(Error.NO_PERMISSION.sendError());
						return true;
					}
				}
				if (args.length == 0) {
					GodStatus.setGod(player, sndr, Status.AUTO);
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
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				GodStatus.setGod(target, sndr, Status.AUTO);
				return true;
			}
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
	}	
}
