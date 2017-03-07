package com.njdaeger.java.essentials.commands.homes;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.configuration.data.Home;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

import net.md_5.bungee.api.ChatColor;

public class Sethome extends EssCommand {

	@Override
	@Cmd(
			name = "sethome",
			desc = "Create a new home.",
			usage = "/sethome <name>",
			min = 1,
			max = 1,
			executor = Executor.PLAYER,
			aliases = { "newhome", "addhome" },
			permissions = { Permission.ESS_SETHOME })

	public boolean run(Sender sndr, String label, String[] args) {
		User user = sndr.asUser();
		Home home = (Home) user.getHome(args[0]);
		if (home.exists()) {
			sndr.sendMessage(Error.HOME_EXISTS.sendError());
			sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + home.listHomes());
			return true;
		}
		home.create();
		sndr.sendMessage(ChatColor.GRAY + "Created a new home at " + ChatColor.GREEN + "x:" + home.getX() + " y:" + home
				.getY() + " z:" + home.getZ());
		return true;
	}
}
