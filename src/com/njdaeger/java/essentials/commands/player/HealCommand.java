package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class HealCommand extends EssCommand {

	static String name = "heal";

	public HealCommand() {
		super("heal");
		List<String> a = Arrays.asList("hearts", "health");
		this.description = "Replenish your health.";
		this.usageMessage = "/heal [player]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_HEAL, Permission.ESS_HEAL_OTHER)) {
			switch (args.length) {
			case 0:
				if (sndr instanceof Player) {
					((Player) sndr).setHealth(20);
					sndr.sendMessage(ChatColor.GRAY + "You have been healed.");
					return true;
				}
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
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
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			default:
				break;
			}
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}