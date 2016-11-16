package com.configapi.configuration;

public class Subplugins extends Config{
	
	public String getCommandName(Class<?> clazz) {
		return clazz.getName().replace("Command", "");
	}
	
	public boolean isCommandEnabled(String name) {
		if (getConfig() != null) {
			if (getConfig().get("essentials.enablecmd."+name).equals(true)) {
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean canRun(String plugin) {
		if (getConfig() != null) {
			if (getConfig().get("enable."+plugin).equals(true)) {
				return true;
			}
			return false;
		}
		return false;
	}
}
