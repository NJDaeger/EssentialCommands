package com.njdaeger.java.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
	 *            <th>Unit</th>
	 *            <th>Aliases</th>
	 *            </tr>
	 *            <tr>
	 *            <th>Seconds</th>
	 *            <th>seconds, sec, s, second</th>
	 *            </tr>
	 *            <tr>
	 *            <th>Minutes</th>
	 *            <th>minutes, min, m, minute</th>
	 *            </tr>
	 *            <tr>
	 *            <th>Hours</th>
	 *            <th>hours, hr, h, hrs, hour</th>
	 *            </tr>
	 *            <tr>
	 *            <th>Days</th>
	 *            <th>days, day, d</th>
	 *            </tr>
	 *            <tr>
	 *            <th>Weeks</th>
	 *            <th>weeks, week, w, wk, wks</th>
	 *            </tr>
	 *            <tr>
	 *            <th>Months</th>
	 *            <th>months, month, mth, mths</th>
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
		TimeType type = TimeType.getAliasUsed(split[1]);
		if (type == null) {
			banCreator.sendMessage("§cCannot issue tempban. TimeUnit \"" + split[1] + "\" does not exist.");
			return;
		}
		switch (type) {
		case SECOND:
			total += TimeUnit.SECONDS.toMillis(Long.parseLong(split[0])) + current;
			break;
		case MINUTE:
			total += TimeUnit.MINUTES.toMillis(Long.parseLong(split[0])) + current;
			break;
		case HOUR:
			total += TimeUnit.HOURS.toMillis(Long.parseLong(split[0])) + current;
			break;
		case DAY:
			total += TimeUnit.DAYS.toMillis(Long.parseLong(split[0])) + current;
			break;
		case WEEK:
			total += (TimeUnit.DAYS.toMillis(Long.parseLong(split[0])) * 7) + current;
			break;
		case MONTH:
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
		if (getExpirationLong(target) > currentTime) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the expiration date from a ban entry.
	 * 
	 * @param target Target to get the expiration date from.
	 * @return Returns the expiration date as a long in milliseconds.
	 */
	public long getExpirationLong(String target) {
		Validate.notNull(target, "Target cannot be null.");
		final BanEntry entry = Bukkit.getBanList(Type.NAME).getBanEntry(target);
		Validate.notNull(entry, "Cannot find BanList entry \"" + target + "\"");
		return entry.getExpiration().getTime();
	}

	/**
	 * Gets the expiration date from a ban entry.
	 * 
	 * @param target Target top get the expiration date from.
	 * @return Returns the expiration date as a long in proper format.
	 */
	public Date getExpirationDate(String target) {
		Validate.notNull(target, "Target cannot be null.");
		final BanEntry entry = Bukkit.getBanList(Type.NAME).getBanEntry(target);
		Validate.notNull(entry, "Cannot find BanList entry \"" + target + "\"");
		return entry.getExpiration();
	}

	/**
	 * Gets the reason for the ban.
	 * 
	 * @param target The target to get the ban reason from.
	 * @return Returns the reason for the ban.
	 */
	public String getReason(String target) {
		Validate.notNull(target, "Target cannot be null.");
		final BanEntry entry = Bukkit.getBanList(Type.NAME).getBanEntry(target);
		Validate.notNull(entry, "Cannot find BanList entry \"" + target + "\"");
		return entry.getReason();
	}

	/**
	 * Checks if the BanList contains an entry.
	 * 
	 * @param target The target to find in the list.
	 * @return True if list contains target, false otherwise.
	 */
	public boolean isBanned(String target) {
		Validate.notNull(target, "Target cannot be null.");
		final BanEntry entry = Bukkit.getBanList(Type.NAME).getBanEntry(target);
		if (entry == null) {
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
	 * Time type is an enum of all the possible values for the ban length. You
	 * can add more aliases to the list as well.
	 */
	public enum TimeType {
		SECOND(Arrays.asList("s", "seconds", "second", "sec")),
		MINUTE(Arrays.asList("m", "minutes", "minute", "min")),
		HOUR(Arrays.asList("h", "hours", "hour", "hr", "hrs")),
		DAY(Arrays.asList("d", "days", "day")),
		WEEK(Arrays.asList("w", "weeks", "week", "wk", "wks")),
		MONTH(Arrays.asList("months", "month", "mth", "mths"));

		public List<String> aliases;

		TimeType(List<String> aliases) {
			this.aliases = aliases;
		}

		/**
		 * Gets the aliases from a given TimeType
		 * 
		 * @return A list of the aliases
		 */
		public List<String> getAlias() {
			return aliases;
		}

		/**
		 * Gets the alias used in the temp-ban
		 * 
		 * @param input Input to get alias from.
		 * @return Returns the TimeType the alias came from.
		 */
		public static TimeType getAliasUsed(String input) {
			for (TimeType alias : TimeType.values()) {
				for (String value : alias.getAlias()) {
					if (value.equals(input)) {
						return alias;
					}
				}
			}
			return null;
		}
	}
}
