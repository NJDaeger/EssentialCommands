package com.njdaeger.java.essentials.commands.player;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Plugin;

public class FlyCommand extends EssCommand {
	
	static String name = "fly";
	
	public FlyCommand() {
		super(name);
		this.description = "Make yourself fly!";
		this.usageMessage = "/fly [player]";
	}
	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}
	@Override
	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		sender.sendMessage("works men");
		return true;
	}
}