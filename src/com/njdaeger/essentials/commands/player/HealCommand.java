package com.njdaeger.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;


public class HealCommand extends BukkitCommand {
	
	public HealCommand() {
		super("heal");
		List<String> a = Arrays.asList("hearts", "health");
		this.description = "Replenish your health.";
		this.usageMessage = "/heal [player]";
		this.setPermission(Permission.ESS_HEAL.getPermission());
		this.setPermission(Permission.ESS_HEAL_OTHER.getPermission());
		this.setPermission(Permission.ESS_ALL.getPermission());
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (TargetHasPermission.check(player, Permission.ESS_HEAL, Permission.ESS_HEAL_OTHER)) {
					player.setHealth(20);
					sndr.sendMessage(ChatColor.GRAY + "You have been healed.");
					return true;
				}
				else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
			}
			else sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (args.length == 1) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				if (TargetHasPermission.check(player, Permission.ESS_HEAL_OTHER)) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					else {
						target.setHealth(20);
						target.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " healed you.");
						sndr.sendMessage(ChatColor.GRAY + "You healed " + ChatColor.GREEN + target.getName());
						return true;
					}
				}
				else {
					sndr.sendMessage(Error.NO_PERMISSION.sendError());
					return true;
				}
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			else {
				target.setHealth(20);
				target.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " healed you.");
				sndr.sendMessage(ChatColor.GRAY + "You healed " + ChatColor.GREEN + target.getName());
				return true;
			}
		}
		else {
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
	}
}