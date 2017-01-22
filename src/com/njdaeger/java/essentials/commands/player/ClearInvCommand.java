package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class ClearInvCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "clear",
			desc = "Clears a player's inventory.",
			usage = "/clear",
			max = 1,
			aliases = { "ci", "clearinv", "clean" },
			permissions = { Permission.ESS_CLEAR, Permission.ESS_CLEAR_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isPlayer()) {
				Player player = (Player) sndr;
				ItemStack[] stack = player.getInventory().getContents();
				int amount = 0;
				for (ItemStack item : stack) {
					if (item != null) {
						amount += item.getAmount();
					}
				}
				player.getInventory().clear();
				sndr.sendMessage(ChatColor.GRAY + "Cleared " + ChatColor.GREEN + amount + ChatColor.GRAY
						+ " items from " + ChatColor.GREEN + player.getName());
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_CLEAR_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			ItemStack[] stack = target.getInventory().getContents();
			int amount = 0;
			for (ItemStack item : stack) {
				if (item != null) {
					amount += item.getAmount();
				}
			}
			target.getInventory().clear();
			sndr.sendMessage(ChatColor.GRAY + "Cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from "
					+ ChatColor.GREEN + target.getName());
			target.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " cleared " + ChatColor.GREEN
					+ amount + ChatColor.GRAY + " items from you.");
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
				Permission.ESS_CLEAR_OTHER));
		return true;
	}
}
