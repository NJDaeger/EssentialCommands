package com.njdaeger.java.essentials.commands.warps;

import org.bukkit.entity.Player;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class SetwarpCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(
			name = "setwarp",
			desc = "Add a new warp.",
			usage = "/setwarp <warpname>",
			min = 1,
			max = 1,
			executor = Executor.PLAYER,
			aliases = { "newwarp", "addwarp" },
			permissions = { Permission.ESS_SETWARP })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		WarpData d = Warps.getWarp(args[0], (Player) sndr);
		if (d.exists() == true) {
			sndr.sendMessage(Error.WARP_EXISTS.sendError());
			return true;
		}
		d.create();
		sndr.sendMessage(ChatColor.GRAY + "Created warp \"" + args[0] + "\"");
		return true;
	}

}
