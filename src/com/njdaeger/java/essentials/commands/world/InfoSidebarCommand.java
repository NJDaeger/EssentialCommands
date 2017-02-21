package com.njdaeger.java.essentials.commands.world;

import com.njdaeger.java.Groups;
import com.njdaeger.java.InfoBoard;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class InfoSidebarCommand extends EssCommand {

	@Cmd(
			name = "infobar",
			desc = "Toggles a sidebar of the basic server info.",
			usage = "/infobar",
			max = 0,
			executor = Executor.PLAYER,
			aliases = { "sibar", "serverinfobar", "lagbar", "gcbar" },
			permissions = { Permission.ESS_INFOBAR })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		if (Groups.infobar.contains(sndr)) {
			Groups.infobar.remove(sndr);
			InfoBoard.removeFor(sndr.asPlayer());
			sndr.sendMessage(ChatColor.GRAY + "Infobar is no longer active.");
			return true;
		} else {
			Groups.infobar.add(sndr.asPlayer());
			InfoBoard.createFor(sndr.asPlayer());
			sndr.sendMessage(ChatColor.GRAY + "Infobar is now active.");
			return true;
		}
	}
}
