package com.njdaeger.java.essentials.commands.player;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class RepairCommand extends EssCommand {

	@Override
	@Cmd(
		name = "repair",
		desc = "Repair a damaged item.",
		usage = "/repair",
		max = 0,
		aliases = { "fixitem", "fix" },
		executor = Executor.PLAYER,
		permissions = Permission.ESS_REPAIR)
	public boolean run(Sender sender, String label, String[] args) {
		ItemStack base = sender.asPlayer().getInventory().getItemInMainHand();
		base.setDurability(base.getType().getMaxDurability());
		sender.sendMessage(ChatColor.GRAY + "Item has been repaired!");
		return true;
	}
}
