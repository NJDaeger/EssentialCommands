package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class PweatherCommand extends EssCommand {

	@Override
	@Cmd(
		name = "pweather",
		desc = "Set your current client weather.",
		usage = "/pweather <weatherType> [player]",
		min = 1,
		max = 2,
		aliases = { "playerwather", "myweather" },
		permissions = { Permission.ESS_PWEATHER, Permission.ESS_PWEATHER_OTHER })
	public boolean run(Sender sender, String label, String[] args) {
		WeatherUnit unit = WeatherUnit.getAliasUsed(args[0]);
		if (unit == null) {
			sender.sendError(Error.CANNOT_CHANGE_WEATHER);
			return true;
		}
		if (args.length == 1) {
			if (!sender.isPlayer()) {
				sender.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			sender.asPlayer().setPlayerWeather(unit.getType());
			sender.sendMessage(ChatColor.GRAY + "Client weather changed to " + ChatColor.GREEN + args[0]);
			return true;
		}
		Player player = Bukkit.getPlayer(args[1]);
		if (player == null) {
			sender.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		player.setPlayerWeather(unit.getType());
		player.sendMessage(ChatColor.GRAY + "Client weather changed to " + ChatColor.GREEN + args[0]);
		return true;
	}

	public enum WeatherUnit {
		CLEAR(WeatherType.CLEAR, "clear", "sunny", "sun"),
		RAIN(WeatherType.DOWNFALL, "rain", "rainy", "gloomy", "lightning", "thunder", "storm");

		String[] aliases;
		WeatherType type;

		WeatherUnit(WeatherType type, String... aliases) {
			this.aliases = aliases;
			this.type = type;
		}

		public WeatherType getType() {
			return type;
		}

		public String[] getAliases() {
			return aliases;
		}

		public static WeatherUnit getAliasUsed(String input) {
			for (WeatherUnit unit : WeatherUnit.values()) {
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
