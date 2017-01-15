package com.njdaeger.java.essentials.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.Validate;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class BanAPI {

	/**
	 * Creates a new BanEntry. Player can be online or offline.
	 * 
	 * <br>
	 * Note: This can also create TempBans. </br>
	 * 
	 * <br>
	 * Example: new BanAPI().addBan(player, sender, 10:d, null); This creates a
	 * temp ban for 10 days with the default ban reason.</br>
	 * 
	 * @param player Player to ban.
	 * @param banCreator Creator of the ban.
	 * @param expirationDate Expiration date of the ban.
	 * @param reason Reason for the ban. Default is "The ban hammer has spoken!"
	 * 
	 *            <p>
	 *            - Note: if expirationDate is null, the ban will be permanent.
	 *            </p>
	 *            <p>
	 *            - Note: If reason is null, the reason will be
	 *            "The ban hammer has spoken!"
	 *            </p>
	 *            <p>
	 *            <table border=1>
	 *            <caption>Expiration Date Information</caption>
	 *            <tr>
	 *            <th>Symbol</th>
	 *            <th>Description</th>
	 *            </tr>
	 *            <tr>
	 *            <th>s</th>
	 *            <th>Seconds</th>
	 *            </tr>
	 *            <tr>
	 *            <th>m</th>
	 *            <th>Minutes</th>
	 *            </tr>
	 *            <tr>
	 *            <th>h</th>
	 *            <th>Hours</th>
	 *            </tr>
	 *            <tr>
	 *            <th>d</th>
	 *            <th>Days</th>
	 *            </tr>
	 *            <tr>
	 *            <th>w</th>
	 *            <th>Weeks</th>
	 *            </tr>
	 *            <tr>
	 *            <th>M</th>
	 *            <th>Months</th>
	 *            </tr>
	 *            </p>
	 * 
	 */
	public void addBan(String player, CommandSender banCreator, String expirationDate, String reason) {
		Validate.notNull(player, "Cannot issue tempban. Player cannot be null.");
		BanList list = Bukkit.getBanList(Type.NAME);
		if (reason == null) {
			reason += "§cThe ban hammer has spoken!";
		}
		if (expirationDate == null) {
			list.addBan(player, reason, null, banCreator.getName());
			return;
		}
		if (!expirationDate.contains(":")) {
			banCreator.sendMessage("§cCannot issue tempban. Improper formatting.");
			return;
		}
		String[] split = expirationDate.split(":");
		if (!isNumber(split[0])) {
			banCreator.sendMessage("§cCannot issue tempban. Improper formatting.");
			return;
		}
		long current = System.currentTimeMillis();
		long total = 0;
		TimeType type = null;
		try {
			type = TimeType.valueOf(split[1]);
		} catch (IllegalArgumentException e) {
			banCreator.sendMessage("§cCannot issue tempban. Unknown timeunit.");
			return;
		}
		switch (type) {
		case s:
			total += TimeUnit.SECONDS.toMillis(Long.parseLong(split[0])) + current;
			break;
		case m:
			total += TimeUnit.MINUTES.toMillis(Long.parseLong(split[0])) + current;
			break;
		case h:
			total += TimeUnit.HOURS.toMillis(Long.parseLong(split[0])) + current;
			break;
		case d:
			total += TimeUnit.DAYS.toMillis(Long.parseLong(split[0])) + current;
			break;
		case w:
			total += (TimeUnit.DAYS.toMillis(Long.parseLong(split[0])) * 7) + current;
			break;
		case M:
			total += (TimeUnit.DAYS.toMillis(Long.parseLong(split[0])) * 31) + current;
			break;
		default:
			banCreator.sendMessage("§cCannot issue tempban. Improper formatting.");
			break;
		}
		list.addBan(player, reason, new Date(total), banCreator.getName());
		return;
	}

	/**
	 * Unbans a player.
	 * 
	 * @param target This is the player to unban.
	 * 
	 *            <p>
	 *            - Note: Add a check in your command method to see if the
	 *            banlist contains the target.
	 *            </p>
	 */
	public void unban(String target) {
		Validate.notNull(target, "Target cannot be null.");
		final BanList list = Bukkit.getBanList(Type.NAME);
		Validate.notNull(list.getBanEntry(target), "Cannot find BanList entry \"" + target + "\"");
		list.pardon(target);
	}

	/**
	 * Checks if a target is still banned or not.
	 * 
	 * @param target This is the player that is being checked for the ban
	 *            expire.
	 * @return Returns false if still banned, true otherwise.
	 * 
	 *         <p>
	 *         - Note: Use this method in a login event. To check if a player is
	 *         still banned.
	 *         </p>
	 */
	public boolean isBanExpired(String target) {
		Validate.notNull(target, "Target cannot be null.");
		final BanEntry entry = Bukkit.getBanList(Type.NAME).getBanEntry(target);
		Validate.notNull(entry, "Cannot find BanList entry \"" + target + "\"");
		final long currentTime = System.currentTimeMillis(); //Gets the current time
		final long expiration = entry.getExpiration().getTime(); //Gets the time the ban expires.
		if (expiration > currentTime) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if a value is a number or not.
	 * 
	 * @param string The string to check.
	 * @return True if its a number, false otherwise.
	 */
	private boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Time type is an enum of all the possible values for the ban length.
	 * <p>
	 * <table border=1>
	 * <caption>Time Type</caption>
	 * <tr>
	 * <th>Symbol</th>
	 * <th>Description</th>
	 * </tr>
	 * <tr>
	 * <th>s</th>
	 * <th>Seconds</th>
	 * </tr>
	 * <tr>
	 * <th>m</th>
	 * <th>Minutes</th>
	 * </tr>
	 * <tr>
	 * <th>h</th>
	 * <th>Hours</th>
	 * </tr>
	 * <tr>
	 * <th>d</th>
	 * <th>Days</th>
	 * </tr>
	 * <tr>
	 * <th>w</th>
	 * <th>Weeks</th>
	 * </tr>
	 * <tr>
	 * <th>M</th>
	 * <th>Months</th>
	 * </tr>
	 * </p>
	 * 
	 * 
	 */
	public enum TimeType {
		s, m, h, d, w, M;
	}
}
