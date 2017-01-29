package com.njdaeger.java.configuration;

public enum ConfigType {

	YAML("YML"), JSON("JSON"), SQL("SQL"), SQL_LITE("SQL-LITE");

	String name;

	private ConfigType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
