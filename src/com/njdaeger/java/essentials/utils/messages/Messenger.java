package com.njdaeger.java.essentials.utils.messages;

import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;

public class Messenger {
	/*
	 * 
	 * Completely redo this. this is a disaster.
	 * 
	 */
	private static ChatColor gy = ChatColor.GRAY;
	private static ChatColor rd = ChatColor.RED;
	private static ChatColor bd = ChatColor.BOLD;
	
	public static HashMap<UUID, String> conversation = new HashMap<UUID, String>();
	public static HashMap<String, UUID> consoleConv = new HashMap<String, UUID>();
	/**
	 * Send a player a PM.
	 * @param tgt The recipient of the message.
	 * @param sender The sender of the message.
	 * @param message The message the sender wants sent.
	 */
	public static void sendPM(String tgt, CommandSender sender, String message) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			conversation.put(player.getUniqueId(), tgt);
			if (tgt == "CONSOLE") {
				System.out.println(bd + "[" + rd + sender.getName() + gy + " -> " + rd + tgt + gy + "] " + ChatColor.RESET + message);
				sender.sendMessage(bd + "[" + rd + tgt + gy + " <- " + rd + sender.getName() + gy + "] " + ChatColor.RESET + message);
				consoleConv.put(tgt, player.getUniqueId());
				return;
			}
			Player target = Bukkit.getPlayer(tgt);
			if (!(target instanceof Player)) {
				if (target.getName() == "CONSOLE") {
					target.sendMessage(bd + "[" + rd + sender.getName() + gy + " -> " + rd + target.getDisplayName() + gy + "] " + ChatColor.RESET + message);
					sender.sendMessage(bd + "[" + rd + target.getDisplayName() + gy + " <- " + rd + sender.getName() + gy + "] " + ChatColor.RESET + message);
					consoleConv.put(sender.getName(), target.getUniqueId());
					return;
				}
				sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return;
			}
			if (Holder.hasPermission(player, Permission.ESS_MESSAGE_CHATCOLOR)) {
				target.sendMessage(gy + "[" + rd + player.getDisplayName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
				target.playSound(target.getLocation(), Sound.BLOCK_NOTE_HARP, 2, 1);
				sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
				return;
			}
			else {
				target.sendMessage(gy + "[" + rd + player.getDisplayName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + message);
				target.playSound(target.getLocation(), Sound.BLOCK_NOTE_HARP, 2, 1);
				sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + message);
				return;
			}
			
		}
		System.out.println(bd + "" + gy + "[" + rd + sender.getName() + gy + " -> " + rd + target.getDisplayName() + gy + "] " + ChatColor.RESET + message);
		sender.sendMessage(bd + "" + gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + sender.getName() + gy + "] " + ChatColor.RESET + message);
		consoleConv.put(tgt, player.getUniqueId());
		return;
	}
	/**
	 * To be able to send a reply to someone you have already messaged.
	 * @param sender Sender of the reply.
	 * @param message message of the reply.
	 */
	public static void sendReply(CommandSender sender, String message) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(conversation.get(player.getUniqueId()));
			if (!(target instanceof Player)) {
				if (target.getName() == "CONSOLE") {
					target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + target.getDisplayName() + gy + "] " + ChatColor.RESET + message);
					sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + sender.getName() + gy + "] " + ChatColor.RESET + message);
					consoleConv.put(sender.getName(), target.getUniqueId());
					return;
				}
				sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return;
			}
			if (Holder.hasPermission(player, Permission.ESS_MESSAGE_CHATCOLOR)) {
				target.sendMessage(gy + "[" + rd + player.getDisplayName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
				target.playSound(target.getLocation(), Sound.BLOCK_NOTE_HARP, 2, 1);
				sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
				return;
			}
			else {
				target.sendMessage(gy + "[" + rd + player.getDisplayName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + message);
				target.playSound(target.getLocation(), Sound.BLOCK_NOTE_HARP, 2, 1);
				sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + message);
				return;
			}
		}
		else {
			Player target = Bukkit.getPlayer(conversation.get(sender.getName()));
			if (target == null) {
				sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return;
			}
			target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + target.getDisplayName() + gy + "] " + ChatColor.RESET + message);
			sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + sender.getName() + gy + "] " + ChatColor.RESET + message);
			consoleConv.put(sender.getName(), target.getUniqueId());
			conversation.put(target.getUniqueId(), sender.getName());
			return;
		}	
	}
	/**
	 * Checks if the target and the player are in a state to be able to message.
	 * @param sender The sender of the message.
	 * @param target The reciever of the message.
	 * @return
	 */
	public static boolean canMessage(CommandSender sender, Player target) {
		if (target == null) {
			return false;
		}
		if (sender.isOp()) {
			return true;
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Util.allowsMessaging(player) == false) {
				return false;
			}
			if (Util.isMuted(player)) {
				return false;
			}
			return true;
		}
		if (Util.isHidden(target)) {
			return false;
		}
		if (Util.isMuted(target)) {
			return false;
		}
		return true;
	}
}
