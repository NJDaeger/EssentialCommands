package com.njdaeger.java.essentials.utils.messages;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Message {

	private ConsoleCommandSender console = Bukkit.getConsoleSender();

	/**
	 * Creates a new message for console
	 * 
	 * @param sndr The sender of the message.
	 * @param chatColor Whether to allow ChatColor or not.
	 * @param message The message being sent.
	 */
	public Message(CommandSender sndr, boolean chatColor, String message) {
		if (chatColor) {
			sndr.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "You >> " + ChatColor.GREEN + ChatColor.BOLD
					+ "CONSOLE" + ChatColor.GRAY + ChatColor.BOLD + ": " + ChatColor.RESET + ChatColor
							.translateAlternateColorCodes('&', message));
			console.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + sndr.getName() + " >> " + ChatColor.GRAY
					+ ChatColor.BOLD + "You" + ChatColor.GRAY + ChatColor.BOLD + ": " + ChatColor.RESET + ChatColor
							.translateAlternateColorCodes('&', message));
			return;
		}
		sndr.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "You >> " + ChatColor.GREEN + ChatColor.BOLD + "CONSOLE"
				+ ChatColor.GRAY + ChatColor.BOLD + ":" + ChatColor.RESET + message);
		console.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + sndr.getName() + " >> " + ChatColor.GRAY
				+ ChatColor.BOLD + "You" + ChatColor.GRAY + ChatColor.BOLD + ":" + ChatColor.RESET + message);
		return;
	}

	/**
	 * Creates a new message for a player.
	 * 
	 * @param sndr The sender of the message.
	 * @param player The player to whom the message is sent to.
	 * @param chatColor Whether to allow ChatColor or not.
	 * @param message The message being sent.
	 */
	public Message(CommandSender sndr, Player player, boolean chatColor, String message) {
		if (chatColor) {
			sndr.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "You >> " + ChatColor.GREEN + ChatColor.BOLD
					+ player.getDisplayName() + ChatColor.GRAY + ChatColor.BOLD + ":" + ChatColor
							.translateAlternateColorCodes('&', message));
			player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + sndr.getName() + " >> " + ChatColor.GRAY
					+ ChatColor.BOLD + "You" + ChatColor.GRAY + ChatColor.BOLD + ": " + ChatColor.RESET + ChatColor
							.translateAlternateColorCodes('&', message));
			return;
		}
		sndr.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "You >> " + ChatColor.GREEN + ChatColor.BOLD + player
				.getDisplayName() + ChatColor.GRAY + ChatColor.BOLD + ": " + ChatColor.RESET + message);
		player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + sndr.getName() + " >> " + ChatColor.GRAY
				+ ChatColor.BOLD + "You" + ChatColor.GRAY + ChatColor.BOLD + ": " + ChatColor.RESET + message);
		return;
	}
}