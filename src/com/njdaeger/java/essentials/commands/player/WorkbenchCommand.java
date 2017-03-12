package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class WorkbenchCommand extends EssCommand {

	@Override
	@Cmd(
		name = "workbench",
		desc = "Get a crafting table without a crafting table.",
		usage = "/workbench",
		executor = Executor.PLAYER,
		max = 0,
		aliases = { "wb", "craftingtable", "ctable" },
		permissions = Permission.ESS_WORKBENCH)
	public boolean run(Sender sender, String label, String[] args) {
		sender.asPlayer().openWorkbench(sender.asPlayer().getLocation(), true);
		sender.sendMessage(ChatColor.GRAY + "A new workbench has been opened.");
		return true;
	}

}
