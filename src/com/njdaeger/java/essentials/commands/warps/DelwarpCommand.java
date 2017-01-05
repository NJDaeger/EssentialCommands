package com.njdaeger.java.essentials.commands.warps;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class DelwarpCommand extends EssCommand {

	static String name = "delwarp";

	public DelwarpCommand() {
		super(name);
		List<String> a = Arrays.asList("removewarp", "deletewarp");
		this.description = "Delete an existing warp.";
		this.usageMessage = "/delwarp <warpname>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_DELWARP)) {
			switch (args.length) {
			case 0:
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
				WarpData d = Warps.getWarp(args[0], null);
				if (d.exists() == false) {
					sndr.sendMessage(Error.WARP_NOTEXISTS.sendError());
					return true;
				}
				d.remove();
				sndr.sendMessage(ChatColor.GRAY + "You deleted warp " + ChatColor.GREEN + args[0]);
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}
