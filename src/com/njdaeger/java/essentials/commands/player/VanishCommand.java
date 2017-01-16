package com.njdaeger.java.essentials.commands.player;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;

public class VanishCommand extends EssCommand{
	
	static String name = "vanish";
	
	public VanishCommand() {
		super(name);
	}
	
	@Override
	public void register() {
		Plugin.getCommand(name, this);
		
	}
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		return false;
	}

}
