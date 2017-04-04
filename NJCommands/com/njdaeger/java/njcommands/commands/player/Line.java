package com.njdaeger.java.njcommands.commands.player;

public enum Line {
	ONE("line1", "1", "one"), TWO("line2", "2", "two"), THREE("line3", "3", "three"), FOUR("line4", "4", "four");

	String[] names;

	Line(String... name) {
		this.names = name;
	}

	public String[] getAlias() {
		return names;
	}

	public static Line getAliasUsed(String input) {
		for (Line alias : Line.values()) {
			for (String value : alias.getAlias()) {
				if (value.equals(input)) {
					return alias;
				}
			}
		}
		return null;
	}
}
