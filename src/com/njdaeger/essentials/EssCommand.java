package com.njdaeger.essentials;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class EssCommand extends Command{

	protected EssCommand(String name) {
		super(name);
	}
	protected abstract void register();
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		return false;
	}
	@Override
	public List<String> tabComplete(CommandSender sender, String alias,
			String[] args) throws IllegalArgumentException {
		return super.tabComplete(sender, alias, args);
	}
	
}