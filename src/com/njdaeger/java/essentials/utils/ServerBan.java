package com.njdaeger.java.essentials.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.enums.BanMessage;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.enums.Error;

public class ServerBan {
	
	/**
	 * Creates a new perm ban.
	 * @param target - Target player to ban
	 * @param reason - Ban reason
	 * @param bancreator - Creator of the ban, if null then it defaults to the console.
	 * @param kickmessage - Player kick message.
	 */
	public static void newPermBan(Player target, String reason, CommandSender bancreator) {
		Bukkit.getServer().getBanList(Type.NAME).addBan(target.getName(), ServerBan.breason(target, bancreator, reason), null, bancreator.getName());
		target.kickPlayer(ServerBan.breason(target, bancreator, reason));
		Bukkit.broadcast(ServerBan.breason(target, bancreator, reason), Permission.ESS_BAN_NOTIFY.getPermission());
		return;
	}
	/**
	 * 
	 * Creates a new temp ban. 
	 * @param target - Target player to temp ban.
	 * @param reason - Reason for temp ban.
	 * @param bancreator - Commandsender
	 * @param time - The input time the sender enters
	 */
	public static void newTempBan(Player target, String reason, CommandSender bancreator, String time) {
		if (!time.contains(":")) {
			bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
			return;
		}
		String units = "";
		if (time.contains("d")) {
			String[] split = time.split(":");
			units += "day(s)";
			if (!Util.isNumber(split[0])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			if (Util.isNumber(split[1])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			long a = Long.parseLong(split[0]);
			long bantime = TimeUnit.DAYS.toMillis(a) + System.currentTimeMillis();
			Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), ServerBan.freason(target, bancreator, reason, split[0], units), ServerBan.expire(bantime), bancreator.getName());
			target.kickPlayer(ServerBan.freason(target, bancreator, reason, split[0], units));
			Bukkit.broadcast(ServerBan.freason(target, bancreator, reason, split[0], units), Permission.ESS_BAN_NOTIFY.getPermission());
			return;
		}
		
		
		if (time.contains("h")) {
			String[] split = time.split(":");
			units += "hours(s)";
			if (!Util.isNumber(split[0])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			if (Util.isNumber(split[1])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			long a = Long.parseLong(split[0]);
			long bantime = TimeUnit.HOURS.toMillis(a) + System.currentTimeMillis();
			Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), ServerBan.freason(target, bancreator, reason, split[0], units), ServerBan.expire(bantime), bancreator.getName());
			target.kickPlayer(ServerBan.freason(target, bancreator, reason, split[0], units));
			Bukkit.broadcast(ServerBan.freason(target, bancreator, reason, split[0], units), Permission.ESS_BAN_NOTIFY.getPermission());
			return;
		}
		
		
		if (time.contains("m")) {
			String[] split = time.split(":");
			units += "minute(s)";
			if (!Util.isNumber(split[0])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			if (Util.isNumber(split[1])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			long a = Long.parseLong(split[0]);
			long bantime = TimeUnit.MINUTES.toMillis(a) + System.currentTimeMillis();
			Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), ServerBan.freason(target, bancreator, reason, split[0], units), ServerBan.expire(bantime), bancreator.getName());
			target.kickPlayer(ServerBan.freason(target, bancreator, reason, split[0], units));
			Bukkit.broadcast(ServerBan.freason(target, bancreator, reason, split[0], units), Permission.ESS_BAN_NOTIFY.getPermission());
			return;
		}
		
		
		if (time.contains("s")) {
			String[] split = time.split(":");
			units += "second(s)";
			if (!Util.isNumber(split[0])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			if (Util.isNumber(split[1])) {
				bancreator.sendMessage(Error.BAN_WRONG_FORMAT.sendError());
				return;
			}
			long a = Long.parseLong(split[0]);
			long bantime = TimeUnit.SECONDS.toMillis(a) + System.currentTimeMillis();
			Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), ServerBan.freason(target, bancreator, reason, split[0], units), ServerBan.expire(bantime), bancreator.getName());
			target.kickPlayer(ServerBan.freason(target, bancreator, reason, split[0], units));
			Bukkit.broadcast(ServerBan.freason(target, bancreator, reason, split[0], units), Permission.ESS_BAN_NOTIFY.getPermission());
			return;
		}
		else {
			bancreator.sendMessage(Error.UNKNOWN_BAN_TYPE.sendError());
			return;
		}
	}
	
	/**
	 * @param player -  Target player to unban.
	 */
	public static void unban(String player, CommandSender sndr) {
		if (Bukkit.getBanList(Type.NAME).getBanEntry(player) != null) {
			Bukkit.getBanList(Type.NAME).pardon(player);
			sndr.sendMessage(ChatColor.GRAY + "You unbanned " + ChatColor.GREEN + player + ChatColor.GRAY + " from the server.");
			return;
			
		}
		else {
			sndr.sendMessage(Error.PLAYER_NOT_BANNED.sendError());
			return;
		}
	}
	/*
	 * Private Utils
	 */
	/**
	 * FOR TEMP BAN METHOD ONLY!
	 * Determined the message to send to the players and the server when the player is temp banned. 
	 * @param target - Target player.
	 * @param bancreator - Creator of ban (commandsender)
	 * @param reason - Reason for punishment
	 * @param time - How long of a temp ban
	 * @param units - Unit type of temp ban. So, "d" (day), "m"(minute), "h"(hour), "s"(second)
	 * @return
	 */
	private static String freason(Player target, CommandSender bancreator, String reason, String time, String units) {
		String freason1 = "";
		if (reason == null) {
			freason1 += ChatColor.RED + target.getName() + 
					ChatColor.GRAY + " has been temp-banned by " +
					ChatColor.GREEN + bancreator.getName() + ChatColor.GRAY + " for: " + 
					ChatColor.RED + BanMessage.TEMP_NOREASON.banNoReason() + 
					ChatColor.GRAY + ". Expires in: " + 
					ChatColor.RED + time + ChatColor.GRAY + " " + units;
			return freason1;
		}
		else {
			freason1 += ChatColor.RED + target.getName() + 
					ChatColor.GRAY + " has been temp-banned by " +
					ChatColor.GREEN + bancreator.getName() + ChatColor.GRAY + " for: " + 
					ChatColor.RED + BanMessage.TEMP_REASON.banReason(reason) + 
					ChatColor.GRAY + ". Expires in: " + 
					ChatColor.RED + time + ChatColor.GRAY + " " + units;
			return freason1;
		}
	}
	
	/**
	 * FOR BAN METHOD ONLY!
	 * Determines the message to send to the player and the server when the player is banned.
	 * @param target - Target player.
	 * @param bancreator - Creator of ban (commandsender)
	 * @param reason - Reason for punishment
	 * @return
	 */
	private static String breason(Player target, CommandSender bancreator, String reason) {
		String freason1 = "";
		if (reason == null) {
			freason1 += ChatColor.RED + target.getName() + 
					ChatColor.GRAY + " has been banned by " +
					ChatColor.GREEN + bancreator.getName() + ChatColor.GRAY + " for: " + 
					ChatColor.RED + BanMessage.TEMP_NOREASON.banNoReason() + 
					ChatColor.GRAY + ".";
			return freason1;
		}
		else {
			freason1 += ChatColor.RED + target.getName() + 
					ChatColor.GRAY + " has been temp-banned by " +
					ChatColor.GREEN + bancreator.getName() + ChatColor.GRAY + " for: " + 
					ChatColor.RED + BanMessage.TEMP_REASON.banReason(reason) + 
					ChatColor.GRAY + ".";
			return freason1;
		}
	}
	
	/**
	 * @param expirationDate simple date converter.
	 * @return
	 */
	public static Date expire(long expirationDate) {
		Date date = new Date(expirationDate);
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		try {
			return format.parse(format.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
