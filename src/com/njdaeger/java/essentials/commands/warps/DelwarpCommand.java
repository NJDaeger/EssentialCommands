package com.njdaeger.java.essentials.commands.warps;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class DelwarpCommand extends EssCommand {

	public DelwarpCommand() {
		super("delwarp");
		List<String> a = Arrays.asList("removewarp", "deletewarp");
		this.description = "Delete an existing warp.";
		this.usageMessage = "/delwarp <warpname>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(min = 1, max = 1, permissions = { Permission.ESS_DELWARP })
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (canceled(sndr, args)) {
			return true;
		}
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
