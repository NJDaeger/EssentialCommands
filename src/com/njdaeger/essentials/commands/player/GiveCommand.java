package com.njdaeger.essentials.commands.player;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;
import com.njdaeger.essentials.utils.Util;

public class GiveCommand extends BukkitCommand {

	public GiveCommand() {
		super("i");
		List<String> a = Arrays.asList("item", "give", "get", "take");
		this.description = "Give yourself items.";
		this.usageMessage = "/i <item:[data]> [amount] [player]";
		this.setAliases(a);
		
	}
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_GIVE, Permission.ESS_GIVE_OTHER)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length == 1) {
					this.give(args[0], player, sndr, 64, Error.INVENTORY_IS_FULL_P);
					return true;
				}
				if (args.length == 2) {
					if (Util.isNumber(args[1]) == false) {
						sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
						return true;
					}
					int amount = Integer.parseInt(args[1]);
					this.give(args[0], player, sndr, amount, Error.INVENTORY_IS_FULL_P);
					return true;
				}
				if (args.length == 3) {
					if (TargetHasPermission.check(player, Permission.ESS_GIVE_OTHER)) {
						Player target = Bukkit.getPlayer(args[2]);
						if (target == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						
						if (Util.isNumber(args[1]) == false) {
							sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
							return true;
						}
						int amount = Integer.parseInt(args[1]);
						this.give(args[0], target, sndr, amount, Error.INVENTORY_IS_FULL_PO);
						return true;
					}
					else {
						sndr.sendMessage(Error.NO_PERMISSION.sendError());
						return true;
					}
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
			if (args.length < 3) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 3) {
				Player target = Bukkit.getPlayer(args[2]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (Util.isNumber(args[1]) == false) {
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return true;
				}
				int amount = Integer.parseInt(args[1]);
				this.give(args[0], target, sndr, amount, Error.INVENTORY_IS_FULL_PO);
				return true;
			}
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
	}
	@SuppressWarnings("deprecation")
	public void give(String item, Player player, CommandSender sndr, int amount, Error fullerror) {
		if (Util.hasFullInventory(player)) {
			player.sendMessage(fullerror.sendError());
			return;
		}
		if (item.contains(":")) {
			if (this.contains(item) == true) {
				sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
			String[] split = item.split(":");
			if (split[0] == null) {
				sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
			if (split[1] == null) {
				sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
			if (Util.isNumber(split[0])) {
				short damage = Short.parseShort(split[1]);
				int type = Integer.parseInt(split[0]);
				if (Material.getMaterial(type) == null) {
					sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
				}
				if (Util.isNumber(split[1])) {
					ItemStack stack = new ItemStack(type, amount, damage);
					player.getInventory().addItem(stack);
					sndr.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(type).name().toLowerCase());
					player.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(type).name().toLowerCase());
					return;
				}
				else {
					sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
					return;
				}
			}
			if (split[0].equalsIgnoreCase("minecraft")) {
				String mat = split[1].toUpperCase();
				if (Material.getMaterial(mat) == null) {
					sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
					return;
				}
				else {
					ItemStack stack = new ItemStack(Material.getMaterial(mat), amount);
					player.getInventory().addItem(stack);
					sndr.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
					player.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
					return;
				}
			}
			String mat = split[0].toUpperCase();
			if (Material.getMaterial(mat) != null) {
				if (Util.isNumber(split[1])) {
					short damage = Short.parseShort(split[1]);
					ItemStack stack = new ItemStack(Material.getMaterial(mat), amount, damage);
					player.getInventory().addItem(stack);
					sndr.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
					player.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
					return;
				}
				else {
					sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
					return;
				}
			}
			else {
				sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
				return;
			}
		}
		else {
			if (Util.isNumber(item)) {
				int type = Integer.parseInt(item);
				if (Material.getMaterial(type) == null) {
					sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
					return;
				}
				else {
					ItemStack stack = new ItemStack(type, amount);
					player.getInventory().addItem(stack);
					sndr.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(type).name().toLowerCase());
					player.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(type).name().toLowerCase());
					return;
				}
			}
			else {
				String mat = item.toUpperCase();
				if (Material.getMaterial(mat) == null) {
					sndr.sendMessage(Error.UNKNOWN_ITEM.sendError());
					return;
				}
				else {
					ItemStack stack = new ItemStack(Material.getMaterial(mat), amount);
					player.getInventory().addItem(stack);
					sndr.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
					player.sendMessage(ChatColor.GREEN + sndr.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN + amount + ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
					return;
				}
			}
		}
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
		else return false;
	}
}