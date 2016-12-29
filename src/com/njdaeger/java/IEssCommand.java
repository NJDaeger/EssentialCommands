package com.njdaeger.java;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface IEssCommand {

	void register();

	boolean execute(CommandSender sender, String commandLabel, String[] args);

	List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException;
}
