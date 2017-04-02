package com.njdaeger.java.essentials.commands.player;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.commands.Cmd;
import com.njdaeger.java.command.util.commands.Executor;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class PlayerCommands {

	/**
	 * 
	 * 
	 * AFK COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "afk",
		desc = "Mark yourself as away from keyboard.",
		usage = "/afk [player]",
		max = 1,
		aliases = { "akf", "away", "brb" },
		permissions = { Permission.ESS_AFK, Permission.ESS_AFK_OTHER })
	public void afk(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
			User u = sndr.asUser();
			u.setAfk(!u.isAfk());
			return;
		}
		if (sndr.hasPermission(Permission.ESS_AFK_OTHER)) {
			User u = Core.getUser(args[0]);
			if (u == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return;
			}
			u.setAfk(!u.isAfk());
			return;
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return;
	}

	/**
	 * 
	 * 
	 * BREAK COMMNAD
	 * 
	 * 
	 */
	@Cmd(
		name = "break",
		desc = "Break the block you are looking at.",
		usage = "/break",
		min = 0,
		max = 0,
		executor = { Executor.PLAYER },
		permissions = { Permission.ESS_BREAK })
	public void breakcommand(Sender sndr, String label, String[] args) {
		ChatColor green = ChatColor.GREEN;
		ChatColor gray = ChatColor.GRAY;
		User user = sndr.asUser();
		HashSet<Material> tran = new HashSet<Material>();
		tran.add(Material.AIR);
		Block block = user.getBase().getTargetBlock(tran, 100).getLocation().getBlock();
		sndr.sendMessage(gray + "You broke block " + green + user.getBase().getTargetBlock(tran, 100).getType()
				.toString().toLowerCase() + gray + " at x:" + green + block.getX() + gray + " y:" + green + block.getY()
				+ gray + " z:" + green + block.getZ() + gray + ".");
		block.setType(Material.AIR);
		return;
	}

	/**
	 * 
	 * 
	 * BURN COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "burn",
		desc = "Let them burn.",
		usage = "/burn [player] [seconds]",
		max = 2,
		permissions = { Permission.ESS_BURN, Permission.ESS_BURN_OTHER })
	public void burn(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendError(Error.NOT_ENOUGH_ARGS);
				return;
			}
			sndr.asUser().getBase().setFireTicks(100);
			sndr.sendMessage(ChatColor.GRAY + "You are now on fire.");
			return;
		}
		User user = Core.getUser(args[0]);
		if (user == null) {
			sndr.sendError(Error.UNKNOWN_PLAYER);
			return;
		}
		if (user.hasPermission(Permission.ESS_BURN_EXEMPT)) {
			sndr.sendError(Error.CANNOT_BURN_TARGET);
			return;
		}
		int time = 100;
		if (args.length == 2) {
			if (!Util.isNumber(args[1])) {
				sndr.sendError(Error.INPUT_NOT_NUM);
				return;
			}
			time = Integer.parseInt(args[1]) * 20;
		}
		user.getBase().setFireTicks(time);
		sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is now on fire.");
		user.sendMessage(ChatColor.GRAY + "You are now on fire.");
		return;
	}

	/**
	 * 
	 * 
	 * EDITSIGN COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "editsign",
		desc = "Edit a placed sign.",
		usage = "/editsign <line> [message]",
		min = 1,
		executor = Executor.PLAYER,
		aliases = { "editsign", "edittext", "es", "edit" },
		permissions = { Permission.ESS_EDITSIGN })
	public void editsign(Sender sndr, String label, String[] args) {
		HashSet<Material> tran = new HashSet<Material>();
		tran.add(Material.AIR);
		Block type = sndr.asPlayer().getTargetBlock(tran, 100);
		if (type.getType().equals(Material.WALL_SIGN) || type.getType().equals(Material.SIGN_POST)) {
			Line line = Line.getAliasUsed(args[0]);
			Sign sign = (Sign) type.getState();
			switch (line) {
			case ONE:
				if (args.length == 1) {
					sign.setLine(0, "");
					sign.update();
					return;
				}
				sign.setLine(0, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return;
			case TWO:
				if (args.length == 1) {
					sign.setLine(1, "");
					sign.update();
					return;
				}
				sign.setLine(1, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return;
			case THREE:
				if (args.length == 1) {
					sign.setLine(2, "");
					sign.update();
					return;
				}
				sign.setLine(2, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return;
			case FOUR:
				if (args.length == 1) {
					sign.setLine(3, "");
					sign.update();
					return;
				}
				sign.setLine(3, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return;
			default:
				sndr.sendMessage(Error.LINE_NUMBER_INVALID.sendError());
				return;
			}
		}
		sndr.sendMessage(Error.TARGET_NOT_SIGN.sendError());
		return;
	}

	private String setSign(String[] args) {
		String msg = "";
		for (String message : args) {
			msg = (msg + message + " ");
			StringBuilder builder = new StringBuilder();
			for (int i = 1; i < args.length; i++)
				builder.append(args[i]).append(' ');
			String reason = builder.toString();
			return reason;
		}
		return null;
	}

	/**
	 * 
	 * 
	 * GOD COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "god",
		desc = "Make yourself unkillable.",
		usage = "/god [player]",
		max = 0,
		aliases = { "invincible" },
		permissions = { Permission.ESS_GOD, Permission.ESS_GOD_OTHER })
	public void godcommand(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isPlayer()) {
				User user = sndr.asUser();
				if (user.isGod()) {
					user.setGod(false);
					sndr.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
					return;
				}
				user.setGod(true);
				sndr.sendMessage(ChatColor.GRAY + "You are now in God mode.");
				return;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return;
		}
		if (sndr.hasPermission(Permission.ESS_GOD_OTHER)) {
			User target = Core.getUser(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return;
			}
			if (target.isGod()) {
				target.setGod(false);
				target.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
				sndr.sendMessage(ChatColor.GREEN + target.getNickname() + ChatColor.GRAY
						+ " is no longer in God mode.");
				return;
			}
			target.setGod(true);
			target.sendMessage(ChatColor.GRAY + "You are now in God mode.");
			sndr.sendMessage(ChatColor.GREEN + target.getNickname() + ChatColor.GRAY + " is now in God mode.");
			return;
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return;
	}

	/**
	 * 
	 * 
	 * HAT COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "hat",
		desc = "Give yourself a hat.",
		usage = "/hat [player]",
		max = 1,
		aliases = { "hood", "helmet" },
		permissions = { Permission.ESS_HAT, Permission.ESS_HAT_OTHER })
	public void hat(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendError(Error.NOT_ENOUGH_ARGS);
				return;
			}
			if (sndr.asUser().getBase().getInventory().getItemInMainHand() == null) {
				sndr.sendMessage(Error.CANNOT_BE_HAT.sendError());
				return;
			}
			sndr.asUser().getBase().getInventory().setHelmet(sndr.asUser().getBase().getInventory()
					.getItemInMainHand());
			sndr.sendMessage(ChatColor.GRAY + "Enjoy your new hat!");
			return;
		}
		if (!sndr.hasPermission(Permission.ESS_HAT_OTHER)) {
			sndr.sendError(Error.NO_PERMISSION);
			return;
		}
		User target = Core.getUser(args[0]);
		if (target == null) {
			sndr.sendError(Error.UNKNOWN_PLAYER);
			return;
		}
		if (target.getBase().getInventory().getItemInMainHand() == null) {
			sndr.sendMessage(Error.CANNOT_BE_HAT.sendError());
			return;
		}
		target.getBase().getInventory().setHelmet(target.getBase().getInventory().getItemInMainHand());
		target.sendMessage(ChatColor.GRAY + "Enjoy your new hat!");
		sndr.sendMessage(ChatColor.GRAY + "You gave " + ChatColor.GREEN + target.getNickname() + ChatColor.GRAY
				+ " a new hat!");
		return;
	}

	/**
	 * 
	 * 
	 * NICK COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "nick",
		desc = "Give yourself a nickname.",
		usage = "/nick <nickname> [player]",
		min = 1,
		max = 2,
		aliases = { "nickname", "newname", "disguise" },
		permissions = { Permission.ESS_NICK, Permission.ESS_NICK_OTHER })
	public void nick(Sender sndr, String label, String[] args) {
		if (args[0].length() > 30) {
			sndr.sendMessage(Error.NICKNAME_TOO_LONG.sendError());
			return;
		}
		if (args.length == 1) {
			if (sndr.isUser()) {
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
					sndr.asUser().setNickname(sndr.getName());
					sndr.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
					return;
				}
				sndr.asUser().setNickname(ChatColor.translateAlternateColorCodes('&', args[0]));
				sndr.sendMessage(ChatColor.GRAY + "Your nickname is now \"" + this.getNick(args[0]) + ChatColor.GRAY
						+ "\".");
				return;
			} else {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return;
			}
		}
		if (!sndr.hasPermission(Permission.ESS_NICK_OTHER)) {
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return;
		}
		User target = Core.getUser(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
			target.setNickname(target.getName());
			target.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
			sndr.sendMessage(ChatColor.GRAY + "You removed " + target.getName() + ChatColor.GRAY + "'s nickname.");
			return;
		}
		target.setNickname(ChatColor.translateAlternateColorCodes('&', args[0]));
		target.sendMessage(ChatColor.GRAY + "Your nickname is now \"" + this.getNick(args[0]) + ChatColor.GRAY + "\".");
		sndr.sendMessage(ChatColor.GRAY + "You changed " + target.getName() + ChatColor.GRAY + "'s nickname to \""
				+ this.getNick(args[0]) + ChatColor.GRAY + "\".");
		return;
	}

	private String getNick(String nick) {
		if (nick.contains("&")) {
			return ChatColor.translateAlternateColorCodes('&', nick);
		} else
			return ChatColor.GREEN + nick;
	}

	/**
	 * 
	 * 
	 * PING COMMAND
	 * 
	 * 
	 */
	@Cmd(name = "ping", desc = "Pong!", usage = "/ping", max = 0, permissions = Permission.ESS_PING)
	public void ping(Sender sender, String label, String[] args) {
		sender.sendMessage("§7Pong!");
		return;
	}

	@Cmd(
		name = "vanish",
		desc = "Hide yourself from others.",
		usage = "/vanish [player]",
		max = 1,
		aliases = { "v", "hide" },
		permissions = { Permission.ESS_VANISH, Permission.ESS_VANISH_OTHER })
	public void vanish(Sender sndr, String commandLabel, String[] args) {
		if (args.length == 0) {
			if (!sndr.isUser()) {
				sndr.sendError(Error.NOT_ENOUGH_ARGS);
				return;
			}
			User user = sndr.asUser();
			user.setHidden(!user.isHidden());
			if (!user.isHidden()) {
				sndr.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
				return;
			}
			sndr.sendMessage(ChatColor.GRAY + "You are now hidden.");
			return;
		}
		if (sndr.hasPermission(Permission.ESS_VANISH_OTHER)) {
			User user = Core.getUser(args[0]);
			if (user == null) {
				sndr.sendError(Error.UNKNOWN_PLAYER);
				return;
			}
			user.setHidden(!user.isHidden());
			if (!user.isHidden()) {
				sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is no longer hidden.");
				user.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
				return;
			}
			sndr.sendMessage(ChatColor.GREEN + user.getNickname() + ChatColor.GRAY + " is now hidden.");
			user.sendMessage(ChatColor.GRAY + "You are now hidden.");
			return;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_VANISH_OTHER));
		return;
	}

	/**
	 * 
	 * 
	 * WHOIS COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "whois",
		desc = "Information about the player.",
		usage = "/whois <player>",
		min = 1,
		max = 1,
		permissions = { Permission.ESS_WHOIS })
	public void whois(Sender sender, String label, String[] args) {
		ChatColor g = ChatColor.GRAY;
		ChatColor gr = ChatColor.GREEN;
		User user = Core.getUser(args[0]);
		if (user == null) {
			sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return;
		}
		sender.sendMessage(g + "Realname: " + gr + user.getName());
		sender.sendMessage(g + "Exp: " + gr + user.getBase().getExp());
		sender.sendMessage(g + "Exp Level: " + gr + user.getBase().getLevel());
		sender.sendMessage(g + "Flyspeed: " + gr + user.getFlyingSpeed());
		sender.sendMessage(g + "Walkspeed: " + gr + user.getWalkingSpeed());
		sender.sendMessage(g + "Gamemode: " + gr + user.getGamemode().toString().toLowerCase());
		sender.sendMessage(g + "Health Level: " + gr + user.getBase().getHealth());
		sender.sendMessage(g + "Food Level: " + gr + user.getBase().getFoodLevel());
		sender.sendMessage(g + "UUID: " + gr + user.getId());
		return;
	}

	/**
	 * 
	 * 
	 * WORKBENCH COMMAND
	 * 
	 * 
	 */
	@Cmd(
		name = "workbench",
		desc = "Get a crafting table without a crafting table.",
		usage = "/workbench",
		executor = Executor.PLAYER,
		max = 0,
		aliases = { "wb", "craftingtable", "ctable" },
		permissions = Permission.ESS_WORKBENCH)
	public void run(Sender sender, String label, String[] args) {
		sender.asPlayer().openWorkbench(sender.asPlayer().getLocation(), true);
		sender.sendMessage(ChatColor.GRAY + "A new workbench has been opened.");
		return;
	}

}
