package com.njdaeger.java.wrapper;

public enum Gamemode {

	SURVIVAL("0", "survival"), CREATIVE("1", "creative"), ADVENTURE("2", "adventure"), SPECTATOR("3", "spectator");

	String[] aliases;

	Gamemode(String... aliases) {
		this.aliases = aliases;
	}

	public String[] getAliases() {
		return aliases;
	}

	public static Gamemode getAliasUsed(String input) {
		for (Gamemode alias : Gamemode.values()) {
			for (String value : alias.getAliases()) {
				if (value.equals(input)) {
					return alias;
				}
			}
		}
		return null;
	}

}
