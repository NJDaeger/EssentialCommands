package com.njdaeger.java.essentials.commands.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCmd;
import com.njdaeger.java.essentials.enums.Permission;

public class RepairCommand implements EssCmd {

	@Cmd(
			name = "Repair", 
			usage = "Testing cmd annotation", 
			desc = "/repair", 
			min = 0, 
			max = 0, 
			aliases = { "rp","cmd" }, 
			permissions = { Permission.ESS_BURN }
	)
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage("Hi console");
			return true;
		}
		sender.sendMessage("Hi player");
		return true;
	}
}
