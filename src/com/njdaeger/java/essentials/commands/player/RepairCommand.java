package com.njdaeger.java.essentials.commands.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;

public class RepairCommand extends EssCommand {

	public RepairCommand() {
		super("repair");
	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(min = 0, max = 0, permissions = { Permission.ESS_BURN })
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (canceled(sender, args)) {
			return true;
		}
		if (sender instanceof Player) {
			sender.sendMessage("Hi player");
			return true;
		}
		sender.sendMessage("Hi console");
		return true;
	}

}
