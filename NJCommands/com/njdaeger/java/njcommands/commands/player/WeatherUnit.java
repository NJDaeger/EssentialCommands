package com.njdaeger.java.njcommands.commands.player;

import org.bukkit.WeatherType;

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

	public static WeatherUnit getFromWeatherType(WeatherType type) {
		for (WeatherUnit types : WeatherUnit.values()) {
			if (types.getType() == type) {
				return types;
			}
		}
		return null;
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
