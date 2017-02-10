package com.njdaeger.java.essentials.commands.player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class GiveCommand extends EssCommand {

	@Override
	@Cmd(
			name = "item",
			desc = "Give yourself items.",
			usage = "/i <item:[data]> [amount] [player]",
			min = 1,
			max = 3,
			aliases = { "i", "give", "get" },
			permissions = { Permission.ESS_GIVE, Permission.ESS_GIVE_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 1:
			give(sndr.asPlayer(), sndr, args[0], 64);
			return true;
		case 2:
			if (!Util.isNumber(args[1])) {
				sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return true;
			}
			give(sndr.asPlayer(), sndr, args[0], Integer.parseInt(args[1]));
			return true;
		default:
			if (Holder.hasPermission(sndr, Permission.ESS_GIVE_OTHER)) {
				Player target = Bukkit.getPlayer(args[2]);
				if (!Util.isNumber(args[1])) {
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return true;
				}
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				give(target, sndr, args[0], Integer.parseInt(args[1]));
				return true;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_GIVE_OTHER));
			return true;
		}

	}

	@SuppressWarnings("deprecation")
	public void give(Player player, Sender sender, String item, int amount) {
		if (Util.hasFullInventory(player)) {
			sender.sendMessage(Error.INVENTORY_IS_FULL.sendError());
			return;
		}
		if (item.contains(":")) {
			if (contains(item)) {
				sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
			String[] split = item.split(":");
			if ((split[0] == null) || (split[1] == null)) {
				sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
			if (Util.isNumber(split[0])) {
				int type = Integer.parseInt(split[0]);
				if (Material.getMaterial(type) == null) {
					sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
					return;
				}
				if (Util.isNumber(split[1])) {
					short damage = Short.parseShort(split[1]);
					ItemStack stack = new ItemStack(type, amount, damage);
					player.getInventory().addItem(stack);
					sender.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has "
							+ ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material
									.getMaterial(type).name().toLowerCase());
					player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN
							+ amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(type)
									.name().toLowerCase());
					return;
				}
				sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
			if (split[0].equalsIgnoreCase("minecraft")) {
				String mat = split[1].toUpperCase();
				if (Material.getMaterial(mat) == null) {
					sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
					return;
				}
				ItemStack stack = new ItemStack(Material.getMaterial(mat), amount);
				player.getInventory().addItem(stack);
				sender.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN
						+ amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name()
								.toLowerCase());
				player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN
						+ amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name()
								.toLowerCase());
				return;
			}
			String mat = split[0].toUpperCase();
			if (Material.getMaterial(mat) != null) {
				if (Util.isNumber(split[1])) {
					short damage = Short.parseShort(split[1]);
					ItemStack stack = new ItemStack(Material.getMaterial(mat), amount, damage);
					player.getInventory().addItem(stack);
					sender.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has "
							+ ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material
									.getMaterial(mat).name().toLowerCase());
					player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN
							+ amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat)
									.name().toLowerCase());
					return;
				}
			}
			sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
			return;
		}
		if (Util.isNumber(item)) {
			int type = Integer.parseInt(item);
			if (Material.getMaterial(type) == null) {
				sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
			ItemStack stack = new ItemStack(type, amount);
			player.getInventory().addItem(stack);
			sender.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN
					+ amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(type).name()
							.toLowerCase());
			player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN
					+ amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(type).name()
							.toLowerCase());
			return;
		}
		String mat = item.toUpperCase();
		if (Material.getMaterial(mat) == null) {
			sender.sendMessage(Error.UNKNOWN_ITEM.sendError());
		}
		ItemStack stack = new ItemStack(Material.getMaterial(mat), amount);
		player.getInventory().addItem(stack);
		sender.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN + amount
				+ ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
		player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN + amount
				+ ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
		return;
	}

	public boolean contains(String input) {
		Pattern pattern = Pattern.compile("[:]");
		Matcher matcher = pattern.matcher(input);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		if (count > 1) {
			return true;
		}
		return false;
	}
}