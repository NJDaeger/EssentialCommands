package com.njdaeger.java.essentials.commands.player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.commands.Cmd;
import com.njdaeger.java.command.util.commands.Executor;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.enums.Error;
import com.njdaeger.java.enums.Permission;
import com.njdaeger.java.utils.Util;
import com.njdaeger.java.wrapper.Gamemode;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class UsefulPlayerCommands {

	/**
	 * 
	 * 
	 * CLEAR INVENTORY COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "clear",
		desc = "Clears a player's inventory.",
		usage = "/clear",
		max = 1,
		aliases = { "ci", "clearinv", "clean" },
		permissions = { Permission.ESS_CLEAR, Permission.ESS_CLEAR_OTHER })
	public void clearcommand(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendError(Error.NOT_ENOUGH_ARGS);
				return;
			}
			ItemStack[] stack = sndr.asUser().getBase().getInventory().getContents();
			int amount = 0;
			for (ItemStack item : stack) {
				if (item != null) {
					amount += item.getAmount();
				}
			}
			sndr.asUser().getBase().getInventory().clear();
			sndr.sendMessage(ChatColor.GRAY + "Cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from "
					+ ChatColor.GREEN + sndr.asUser().getNickname());
			return;
		}
		if (!sndr.hasPermission(Permission.ESS_CLEAR_OTHER)) {
			sndr.sendError(Error.NO_PERMISSION);
			return;
		}
		User user = Core.getUser(args[0]);
		if (user == null) {
			sndr.sendError(Error.UNKNOWN_PLAYER);
			return;
		}
		ItemStack[] stack = user.getBase().getInventory().getContents();
		int amount = 0;
		for (ItemStack item : stack) {
			if (item != null) {
				amount += item.getAmount();
			}
		}
		user.getBase().getInventory().clear();
		sndr.sendMessage(ChatColor.GRAY + "Cleared " + ChatColor.GREEN + amount + ChatColor.GRAY + " items from "
				+ ChatColor.GREEN + user.getNickname());
		user.sendMessage(ChatColor.GRAY + "Your inventory was cleared.");
		return;
	}

	/**
	 * 
	 * 
	 * FLY COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "fly",
		desc = "Make yourself fly!",
		usage = "/fly [player]",
		max = 1,
		permissions = { Permission.ESS_FLY, Permission.ESS_FLY_OTHER })
	public void flycommand(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isUser()) {
				User user = sndr.asUser();
				if (user.isFlying()) {
					user.setFlying(false);
					sndr.sendMessage(ChatColor.GRAY + "You are no longer flying.");
					return;
				}
				user.setFlying(true);
				sndr.sendMessage(ChatColor.GRAY + "You are now flying.");
				return;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return;
		}
		if (!sndr.hasPermission(Permission.ESS_FLY_OTHER)) {
			sndr.sendError(Error.NO_PERMISSION);
			return;
		}
		User user = Core.getUser(args[0]);
		if (user == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (user.isFlying()) {
			user.setFlying(false);
			user.sendMessage(ChatColor.GRAY + "You are no longer flying.");
			sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is no longer flying.");
			return;
		}
		user.setFlying(true);
		user.sendMessage(ChatColor.GRAY + "You are now flying.");
		sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is now flying.");
		return;
	}

	/**
	 * 
	 * 
	 * FOOD COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "food",
		desc = "Fill your food bar.",
		usage = "/food [player]",
		max = 1,
		aliases = { "feed" },
		permissions = { Permission.ESS_FOOD, Permission.ESS_FOOD_OTHER })
	public void foodcommand(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			if (!sender.hasPermission(Permission.ESS_FOOD_OTHER)) {
				sender.sendError(Error.NO_PERMISSION);
				return;
			}
			User user = Core.getUser(args[0]);
			if (user == null) {
				sender.sendError(Error.UNKNOWN_PLAYER);
				return;
			}
			user.getBase().setFoodLevel(20);
			sender.sendMessage(ChatColor.GRAY + "You restored " + ChatColor.GREEN + user.getNickname() + ChatColor.GRAY
					+ "'s food level.");
			user.sendMessage(ChatColor.GRAY + "Your food level has been restored.");
			return;
		}
		if (!sender.isUser()) {
			sender.sendError(Error.NOT_ENOUGH_ARGS);
			return;
		}
		sender.asUser().getBase().setFoodLevel(20);
		sender.sendMessage(ChatColor.GRAY + "Your food level has been restored.");
		return;
	}

	/**
	 * 
	 * 
	 * GAMEMODE COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "gamemode",
		desc = "Switch between gamemodes.",
		usage = "/gamemode <gamemode> [player]",
		min = 1,
		max = 2,
		aliases = { "gm", "mode" },
		permissions = { Permission.ESS_GAMEMODE, Permission.ESS_GAMEMODE_OTHER })
	public void gamemode(Sender sndr, String label, String[] args) {
		if (Gamemode.getAliasUsed(args[0]) == null) {
			sndr.sendMessage(Error.UNKNOWN_GAMEMODE.sendError());
			return;
		}
		if (args.length == 1) {
			if (sndr.isUser()) {
				User user = sndr.asUser();
				user.setGamemode(args[0]);
				user.sendMessage(ChatColor.GRAY + "Your gamemode is now set to " + ChatColor.GREEN + Gamemode
						.getAliasUsed(args[0]).toString().toLowerCase());
				return;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return;
		}
		User target = Core.getUser(args[0]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (sndr.hasPermission(Permission.ESS_GAMEMODE_OTHER)) {
		} else {
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return;
		}
		target.setGamemode(args[0]);
		sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + Gamemode
				.getAliasUsed(args[0]).toString().toLowerCase());
		target.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + Gamemode
				.getAliasUsed(args[0]).toString().toLowerCase());
		return;
	}

	/**
	 * 
	 * 
	 * GETPOSITION COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "position",
		desc = "Get your current position.",
		usage = "/position [player]",
		max = 1,
		aliases = { "getpos", "currentpos", "getposition", "getloc", "getlocation" },
		permissions = { Permission.ESS_POSITION, Permission.ESS_POSITION_OTHER })
	public void position(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
			this.sendLocation(sndr.asUser(), sndr);
			return;

		}
		if (!sndr.hasPermission(Permission.ESS_POSITION_OTHER)) {
			sndr.sendError(Error.NO_PERMISSION);
			return;
		}
		User user = Core.getUser(args[0]);
		if (user == null) {
			sndr.sendError(Error.UNKNOWN_PLAYER);
			return;
		}
		this.sendLocation(user, sndr);
		return;
	}

	public void sendLocation(User target, Sender sndr) {
		sndr.sendMessage(ChatColor.GRAY + "Location for player " + ChatColor.GREEN + target.getName());
		sndr.sendMessage(ChatColor.GRAY + "Coord x:" + ChatColor.GREEN + target.getLocation().getBlockX());
		sndr.sendMessage(ChatColor.GRAY + "Coord y:" + ChatColor.GREEN + target.getLocation().getBlockY());
		sndr.sendMessage(ChatColor.GRAY + "Coord z:" + ChatColor.GREEN + target.getLocation().getBlockZ());
		sndr.sendMessage(ChatColor.GRAY + "Chunk x:" + ChatColor.GREEN + target.getLocation().getChunk().getX());
		sndr.sendMessage(ChatColor.GRAY + "Chunk z:" + ChatColor.GREEN + target.getLocation().getChunk().getZ());
		sndr.sendMessage(ChatColor.GRAY + "World:" + ChatColor.GREEN + target.getWorld().getName());
		return;
	}

	/**
	 * 
	 * 
	 * GIVE COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "item",
		desc = "Give yourself items.",
		usage = "/i <item:[data]> [amount] [player]",
		min = 1,
		max = 3,
		aliases = { "i", "give", "get" },
		permissions = { Permission.ESS_GIVE, Permission.ESS_GIVE_OTHER })
	public void givecommand(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 1:
			if (!sndr.isUser()) {
				sndr.sendError(Error.NOT_ENOUGH_ARGS);
				return;
			}
			give(sndr.asUser(), sndr, args[0], 64);
			return;
		case 2:
			if (!sndr.isUser()) {
				sndr.sendError(Error.NOT_ENOUGH_ARGS);
				return;
			}
			if (!Util.isNumber(args[1])) {
				sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return;
			}
			give(sndr.asUser(), sndr, args[0], Integer.parseInt(args[1]));
			return;
		default:
			if (Holder.hasPermission(sndr, Permission.ESS_GIVE_OTHER)) {
				User target = Core.getUser(args[2]);
				if (!Util.isNumber(args[1])) {
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return;
				}
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return;
				}
				give(target, sndr, args[0], Integer.parseInt(args[1]));
				return;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_GIVE_OTHER));
			return;
		}

	}

	@SuppressWarnings("deprecation")
	private void give(User player, Sender sender, String item, int amount) {
		if (Util.hasFullInventory(player.getBase())) {
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
					player.getBase().getInventory().addItem(stack);
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
				player.getBase().getInventory().addItem(stack);
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
					player.getBase().getInventory().addItem(stack);
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
			player.getBase().getInventory().addItem(stack);
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
			return;
		}
		ItemStack stack = new ItemStack(Material.getMaterial(mat), amount);
		player.getBase().getInventory().addItem(stack);
		sender.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " now has " + ChatColor.GREEN + amount
				+ ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
		player.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " added " + ChatColor.GREEN + amount
				+ ChatColor.GRAY + " items of " + ChatColor.GREEN + Material.getMaterial(mat).name().toLowerCase());
		return;
	}

	private boolean contains(String input) {
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

	/**
	 * 
	 * 
	 * HEAL COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "heal",
		desc = "Replenish your health.",
		usage = "/heal [player]",
		max = 1,
		aliases = { "hearts", "health" },
		permissions = { Permission.ESS_HEAL, Permission.ESS_HEAL_OTHER })
	public void heal(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
			sndr.asPlayer().setHealth(20);
			sndr.sendMessage(ChatColor.GRAY + "You have been healed.");
			return;
		}
		if (!sndr.hasPermission(Permission.ESS_HEAL_OTHER)) {
			sndr.sendError(Error.NO_PERMISSION);
			return;
		}
		User target = Core.getUser(args[0]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		target.getBase().setHealth(20);
		target.sendMessage(ChatColor.GRAY + "You have been healed.");
		sndr.sendMessage(ChatColor.GRAY + "You healed " + ChatColor.GREEN + target.getNickname());
		return;
	}

	/**
	 * 
	 * 
	 * MORE COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "more",
		desc = "Max the amount of items you have in your hand.",
		usage = "/more",
		min = 0,
		max = 0,
		aliases = { "max" },
		executor = Executor.PLAYER,
		permissions = { Permission.ESS_MORE })
	public void more(Sender sender, String label, String[] args) {
		User player = sender.asUser();
		if (player.getBase().getInventory().getItemInMainHand() == null) {
			sender.sendMessage(Error.NO_ITEM_IN_HAND.sendError());
			return;
		}
		int max = player.getBase().getInventory().getItemInMainHand().getMaxStackSize();
		int current = player.getBase().getInventory().getItemInMainHand().getAmount();
		if (max <= current) {
			sender.sendMessage(Error.ITEM_ALREADY_MAXED.sendError());
			return;
		}
		player.getBase().getInventory().getItemInMainHand().setAmount(max);
		sender.sendMessage(ChatColor.GRAY + "Added " + ChatColor.GREEN + (max - current) + ChatColor.GRAY
				+ " items to your stack.");
		return;
	}

	/**
	 * 
	 * 
	 * REALNAME COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "realname",
		desc = "Gets the realname of a player.",
		usage = "/realname [player]",
		max = 1,
		aliases = { "getname" },
		permissions = Permission.ESS_REALNAME)
	public void realname(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			User user = Core.getUser(args[0]);
			if (user == null) {
				sender.sendError(Error.UNKNOWN_PLAYER);
				return;
			}
			sender.sendMessage(ChatColor.GRAY + args[0] + "'s realname is " + ChatColor.GREEN + user.getName());
			return;
		}
		sender.sendMessage(ChatColor.GRAY + "Your realname is " + ChatColor.GREEN + sender.getName());
		return;
	}

	/**
	 * 
	 * 
	 * REPAIR COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "repair",
		desc = "Repair a damaged item.",
		usage = "/repair",
		max = 0,
		aliases = { "fixitem", "fix" },
		executor = Executor.PLAYER,
		permissions = Permission.ESS_REPAIR)
	public void repair(Sender sender, String label, String[] args) {
		ItemStack base = sender.asPlayer().getInventory().getItemInMainHand();
		base.setDurability(base.getType().getMaxDurability());
		sender.sendMessage(ChatColor.GRAY + "Item has been repaired!");
		return;
	}

	/**
	 * 
	 * 
	 * SPEED COMMAND
	 * 
	 * 
	 */
	@Cmd(
		max = 3,
		min = 1,
		permissions = { Permission.ESS_SPEED, Permission.ESS_SPEED_OTHER },
		name = "speed",
		desc = "Change your speed limits",
		usage = "/speed <speed> [player] [type]",
		aliases = { "flyspeed", "walkspeed", "setspeed" })
	public void speed(Sender sndr, String label, String[] args) {
		switch (args.length) {
		case 1:
			if (!sndr.isUser()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
			User user = sndr.asUser();
			if (!Util.isDouble(args[0])) {
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
					user.sendMessage(ChatColor.GRAY + "Your speed has been reset.");
					if (user.getBase().isFlying()) {
						user.setFlyingSpeed(1);
						return;
					}
					user.setWalkingSpeed(1);
					return;
				}
				sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return;
			}
			if (label.equalsIgnoreCase("flypseed")) {
				user.setFlyingSpeed(Double.parseDouble(args[0]));
				user.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
				return;
			}
			if (label.equalsIgnoreCase("walkspeed")) {
				user.setWalkingSpeed(Double.parseDouble(args[0]));
				user.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
				return;
			}
			if (user.getBase().isFlying()) {
				user.setFlyingSpeed(Double.parseDouble(args[0]));
				user.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
				return;
			}
			user.setWalkingSpeed(Double.parseDouble(args[0]));
			user.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
			return;
		case 2:
			if (sndr.hasPermission(Permission.ESS_SPEED_OTHER)) {
				User target = Core.getUser(args[1]);
				if (target == null) {
					sndr.sendError(Error.UNKNOWN_PLAYER);
					return;
				}
				if (!Util.isDouble(args[0])) {
					if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
						target.sendMessage(ChatColor.GRAY + "Your speed has been reset.");
						sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName()
								+ ChatColor.GRAY + "'s speed.");

						if (target.getBase().isFlying()) {
							target.setFlyingSpeed(1);
							return;
						}
						target.setWalkingSpeed(1);
						return;
					}
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return;
				}
				if (label.equalsIgnoreCase("flypseed")) {
					target.setFlyingSpeed(Double.parseDouble(args[0]));
					target.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s fly speed to " + ChatColor.GREEN + args[0]);
					return;
				}
				if (label.equalsIgnoreCase("walkspeed")) {
					target.setWalkingSpeed(Double.parseDouble(args[0]));
					target.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s walk speed to " + ChatColor.GREEN + args[0]);
					return;
				}
				if (target.getBase().isFlying()) {
					target.setFlyingSpeed(Double.parseDouble(args[0]));
					target.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s walk speed to " + ChatColor.GREEN + args[0]);
					return;
				}
				target.setWalkingSpeed(Double.parseDouble(args[0]));
				target.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
				sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
						+ "'s walk speed to " + ChatColor.GREEN + args[0]);
				return;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_SPEED_OTHER));
			return;
		default:
			User target = Core.getUser(args[1]);
			if (target == null) {
				sndr.sendError(Error.UNKNOWN_PLAYER);
				return;
			}
			if (sndr.hasPermission(Permission.ESS_SPEED_OTHER)) {
				if (!Util.isDouble(args[0])) {
					if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("default")) {
						if (args[2].equalsIgnoreCase("fly")) {
							target.sendMessage(ChatColor.GRAY + "Your fly speed has been reset.");
							sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName()
									+ ChatColor.GRAY + "'s fly speed.");
							target.setFlyingSpeed(1);
							return;
						}
						if (args[2].equalsIgnoreCase("walk")) {
							target.sendMessage(ChatColor.GRAY + "Your walk speed has been reset.");
							sndr.sendMessage(ChatColor.GRAY + "You reset " + ChatColor.GREEN + target.getName()
									+ ChatColor.GRAY + "'s walk speed.");
							target.setWalkingSpeed(1);
							return;
						}
						sndr.sendError(Error.UNKNOWN_WALK_TYPE);
						return;
					}
					sndr.sendMessage(Error.INPUT_NOT_NUM.sendError());
					return;
				}
				if (label.equalsIgnoreCase("flyspeed")) {
					sndr.sendError(Error.TOO_MANY_ARGS);
					return;
				}
				if (label.equalsIgnoreCase("walkspeed")) {
					sndr.sendError(Error.TOO_MANY_ARGS);
					return;
				}
				if (args[2].equalsIgnoreCase("fly")) {
					target.sendMessage(ChatColor.GRAY + "Your fly speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s fly speed to " + ChatColor.GREEN + args[0]);
					target.setFlyingSpeed(Double.parseDouble(args[0]));
					return;
				}
				if (args[2].equalsIgnoreCase("walk")) {
					target.sendMessage(ChatColor.GRAY + "Your walk speed has been set to " + ChatColor.GREEN + args[0]);
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + target.getName() + ChatColor.GRAY
							+ "'s walk speed to " + ChatColor.GREEN + args[0]);
					target.setWalkingSpeed(Double.parseDouble(args[0]));
					return;
				}
				sndr.sendError(Error.UNKNOWN_WALK_TYPE);
				return;
			}
			sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
					Permission.ESS_SPEED_OTHER));
			return;
		}
	}

}
