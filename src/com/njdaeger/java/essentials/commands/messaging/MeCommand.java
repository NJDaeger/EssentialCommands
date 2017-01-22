package com.njdaeger.java.essentials.commands.messaging;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class MeCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Override
	@Cmd(
			name = "me",
			desc = "Describe what you're doing.",
			usage = "/me <message>",
			min = 1,
			executor = Executor.PLAYER,
			permissions = { Permission.ESS_ME })
	public boolean run(Sender sndr, String label, String[] args) {
		boolean color = false;
		if (Holder.hasPermission(sndr, Permission.ESS_ME_CHATCOLOR)) {
			color = true;
		}
		String me = "";
		for (String message : args) {
			me = (me + message + " ");
			if (color) {
				Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + ((Player) sndr)
						.getDisplayName() + " " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', me));
				return true;
			}
			Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + ChatColor.RESET + ((Player) sndr).getDisplayName()
					+ " " + ChatColor.GRAY + me);

		}
		return true;
	}
}
