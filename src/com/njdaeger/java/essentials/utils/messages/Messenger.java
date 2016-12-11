package com.njdaeger.java.essentials.utils.messages;

import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
	
	public static HashMap<String, String> conversation = new HashMap<String, String>();

	public static boolean canMessage(String player, boolean isSender) {
		Player sender = Bukkit.getPlayer(player);
		if (!(sender instanceof Player)) {
			if (player == "CONSOLE") {
				return true;
			}
			else return false;
		}
		if (sender.isOp()) {
			return true;
		}
		if (isSender == true) {
			if (Util.allowsMessaging(sender) == false) {
				return false;
			}
			if (Util.isMuted(sender)) {
				return false;
			}
		}
		if (Util.allowsMessaging(sender) == false) {
			return false;
		}
		if (Util.isMuted(sender)) {
			return false;
		}
		if (Util.isHidden(sender)) {
			return false;
		}
		return true;
	}
	public static void sendReply(CommandSender sender, String message) {
		if (!(sender instanceof Player)) {
			if (sender.getName().equalsIgnoreCase("CONSOLE")) {
				if(conversation.get(sender.getName()) == null) {
					sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
					return;
				}
				if(Bukkit.getPlayer(conversation.get(sender.getName())) == null) { //<- this son of a bitch
					if (conversation.get(sender.getName()) == "CONSOLE") {
						System.out.println(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						sender.sendMessage(gy + "[" + rd + conversation.get(sender.getName()) + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						conversation.put(sender.getName(), conversation.get(sender.getName()));
						return;
					}
					else {
						sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return;
					}
				}
				else {
					Player target = Bukkit.getPlayerExact(conversation.get(sender.getName()));
					target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					conversation.put(sender.getName(), target.getName());
					return;
				}
			}
			else {
				sender.sendMessage(Error.INVALID_COMMAND_SENDER.sendError());
				return;
			}
		}
		else {
			if(conversation.get(sender.getName()) == null) {
				sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
				return;
			}
			if (Bukkit.getPlayer(conversation.get(sender.getName())) == null) {
				if (conversation.get(sender.getName()).equalsIgnoreCase("CONSOLE")) {
					if (sender.hasPermission(Permission.ESS_MESSAGE_CHATCOLOR.getPermission())) {
						System.out.println(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						sender.sendMessage(gy + "[" + rd + conversation.get(sender.getName()) + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						conversation.put(sender.getName(), conversation.get(sender.getName()));
						return;
					}
					else {
						System.out.println(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + message);
						sender.sendMessage(gy + "[" + rd + conversation.get(sender.getName()) + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + message);
						conversation.put(sender.getName(), conversation.get(sender.getName()));
						return;
					}
				}
				else {
					sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return;
				}
			}
			else {
				Player target = Bukkit.getPlayerExact(conversation.get(sender.getName()));
				if (canMessage(sender.getName(), true) == false) {
					sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
					return;
				}
				if (canMessage(conversation.get(sender.getName()), false) == false) {
					sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
					return;
				}
				if (sender.hasPermission(Permission.ESS_MESSAGE_CHATCOLOR.getPermission())) {
					target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					conversation.put(sender.getName(), conversation.get(sender.getName()));
					return;
				}
				else {
					target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + message);
					sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + message);
					conversation.put(sender.getName(), conversation.get(sender.getName()));
					return;
				}
			}
		}
	}
	public static void newPM(CommandSender sender, String player, String message) {
		Player target = Bukkit.getPlayer(player);
		if (!(sender instanceof Player)) {
			if (sender.getName().equalsIgnoreCase("CONSOLE")) {
				if (!(target instanceof Player)) {
					if (player == "CONSOLE") {
						System.out.println(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						conversation.put(sender.getName(), player);
						return;
					}
					else {
						sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return;
					}
				}
				else {
					target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					conversation.put(sender.getName(), target.getName());
					return;
				}
			}
			else {
				sender.sendMessage(Error.INVALID_COMMAND_SENDER.sendError());
				return;
			}
		}
		else {
			if (!(target instanceof Player)) {
				if (player.equalsIgnoreCase("CONSOLE")) {
					if (sender.hasPermission(Permission.ESS_MESSAGE_CHATCOLOR.getPermission())) {
						System.out.println(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						sender.sendMessage(gy + "[" + rd + player + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
						conversation.put(sender.getName(), player);
						conversation.put(player, sender.getName());
						return;
					}
					else {
						System.out.println(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + message);
						sender.sendMessage(gy + "[" + rd + player + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + message);
						conversation.put(sender.getName(), player);
						conversation.put(player, sender.getName());
						return;
					}
				}
				else {
					sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return;
				}
			}
			else {
				if (canMessage(sender.getName(), true) == false) {
					sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
					return;
				}
				if (canMessage(player, false) == false) {
					sender.sendMessage(Error.CANNOT_SEND_PM.sendError());
					return;
				}
				if (sender.hasPermission(Permission.ESS_MESSAGE_CHATCOLOR.getPermission())) {
					target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message));
					conversation.put(sender.getName(), player);
					conversation.put(player, sender.getName());
					return;
				}
				else {
					target.sendMessage(gy + "[" + rd + sender.getName() + gy + " -> " + rd + "you" + gy + "] " + ChatColor.RESET + message);
					sender.sendMessage(gy + "[" + rd + target.getDisplayName() + gy + " <- " + rd + "you" + gy + "] " + ChatColor.RESET + message);
					conversation.put(sender.getName(), player);
					conversation.put(player, sender.getName());
					return;
				}
			}
		}
	}
	/*
	 * Create a hashmap of the sender (string) and the receiver (string). 
	 * 
	 * 
	 * ##MESSAGE
	 * 
	 * if sender = CONSOLE
	 * 		if target = CONSOLE 
	 * 			send message to console
	 * 		if target = player
	 * 			send message to player
	 * 		else player not exist
	 * 
	 * if sender is player 
	 * 		if targets name = CONSOLE
	 * 			send message to console
	 * 		if target = player
	 * 			use canMessage method
	 * 		else player not exist
	 * 
	 * else invalid command sender.
	 * 
	 * 
	 * 
	 * ##REPLY
	 * 
	 * if sender = CONSOLE
	 * 		if (bukkit.getPlayer(target) == null) {
	 * 			if target = CONSOLE
	 * 				send message to console
	 * 			else player not exist
	 * 		if target = player
	 * 			if canmessage = true
	 * 				send message to player
	 * 			unable to send message
	 * 		else player not exist
	 * 
	 * if sender is player
	 * 		if (target == null) {
	 * 			if (target == CONSOLE)
	 * 				send to console
	 * 			else player not exist.
	 * 		if target = sender
	 * 			send message to player
	 * 		if target = player
	 * 			if canmessage = true
	 * 				send message to player
	 * 			unable to send message		
	 * 		else unknown target
	 * else invalid sender
	 * 
	 */
}
