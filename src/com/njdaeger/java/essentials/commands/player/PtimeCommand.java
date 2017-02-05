package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.Util;
import com.njdaeger.java.wrapper.Sender;

public class PtimeCommand extends EssCommand {

	@Override
	@Cmd(
			name = "ptime",
			desc = "Set a players client time.",
			usage = "/ptime <timeunit> [player]",
			min = 1,
			max = 2,
			aliases = { "playertime" },
			permissions = { Permission.ESS_PTIME, Permission.ESS_PTIME_OTHER })
	public boolean run(Sender sender, String label, String[] args) {
		if (args.length == 1) {
			if (!sender.isPlayer()) {
				sender.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			TimeUnit unit = TimeUnit.getAliasUsed(args[0]);
			Player player = (Player) sender;
			if (unit != null) {
				player.setPlayerTime(unit.getTime(), false);
				player.sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + args[0]);
				return true;
			}
			setTime(sender, player, args[0]);
			return true;
		}
		TimeUnit unit = TimeUnit.getAliasUsed(args[0]);
		Player player = Bukkit.getPlayer(args[1]);
		if (player == null) {
			sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (unit != null) {
			player.setPlayerTime(unit.getTime(), false);
			player.sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + args[0]);
			return true;
		}
		setTime(sender, player, args[0]);
		return true;
	}

	private void setTime(Sender sender, Player player, String input) {
		if (input.startsWith("@") || input.endsWith("ticks") || input.equalsIgnoreCase("reset")) {
			String[] a;
			if (input.startsWith("@")) {
				a = input.split("@");
				if (Util.isNumber(a[1])) {
					player.setPlayerTime(Long.parseLong(a[1]), false);
					player.sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + a[1] + " ticks");
					return;
				}
				sender.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return;
			}
			if (input.endsWith("ticks")) {
				a = input.split("test");
				if (Util.isNumber(a[0])) {
					player.setPlayerTime(Long.parseLong(a[0]), false);
					player.sendMessage(ChatColor.GRAY + "Client time changed to " + ChatColor.GREEN + a[0] + " ticks");
					return;
				}
				sender.sendMessage(Error.INPUT_NOT_NUM.sendError());
				return;

			}
			player.resetPlayerTime();
			return;
		}
		sender.sendMessage(Error.CANNOT_CHANGE_TO_TIME.sendError());
		return;
	}

	public enum TimeUnit {
		MORNING(0, "morn", "morning", "sunrise", "dawn"),
		DAY(6000, "day", "noon", "midday", "middle"),
		EVENING(12000, "even", "evening", "dusk", "sunset"),
		NIGHT(18000, "night", "midnight", "dark");

		String[] aliases;
		long time;

		TimeUnit(long time, String... aliases) {
			this.aliases = aliases;
			this.time = time;
		}

		public long getTime() {
			return time;
		}

		public String[] getAliases() {
			return aliases;
		}

		public static TimeUnit getAliasUsed(String input) {
			for (TimeUnit unit : TimeUnit.values()) {
				for (String value : unit.getAliases()) {
					if (value.equalsIgnoreCase(input)) {
						return unit;
					}
				}
			}
			return null;
		}
	}
}
