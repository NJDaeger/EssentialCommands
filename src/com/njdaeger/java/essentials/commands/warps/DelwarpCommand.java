package com.njdaeger.java.essentials.commands.warps;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class DelwarpCommand extends EssCommand {

	@Cmd(
			name = "delwarp",
			desc = "Delete an existing warp.",
			usage = "/delwarp <warpname>",
			min = 1,
			max = 1,
			aliases = { "removewarp", "deletewarp" },
			permissions = { Permission.ESS_DELWARP })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		WarpData d = Warps.getWarp(args[0], null);
		if (d.exists() == false) {
			sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
			return true;
		}
		d.remove();
		sndr.sendMessage(ChatColor.GRAY + "You deleted warp " + ChatColor.GREEN + args[0]);
		return true;
	}
}
