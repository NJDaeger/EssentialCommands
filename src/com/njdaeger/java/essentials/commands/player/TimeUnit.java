package com.njdaeger.java.essentials.commands.player;

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

	public static TimeUnit getClosestTime(long input) {
		for (TimeUnit unit : TimeUnit.values()) {
			long time = unit.getTime() - input;
			long abstime = Math.abs(time);
			if (abstime <= 6000) {
				return unit;
			}
		}
		return null;
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
