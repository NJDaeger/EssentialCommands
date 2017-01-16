package com.njdaeger.java.command.util;

import org.bukkit.command.CommandSender;

public interface EssCmd {

	boolean execute(CommandSender sender, String commandLabel, String[] args);

}
