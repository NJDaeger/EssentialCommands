package com.njdaeger.java.essentials.commands.warps;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.Warps;
import com.njdaeger.java.configuration.data.WarpData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class SetwarpCommand extends EssCommand {

	static String name = "setwarp";

	public SetwarpCommand() {
		super(name);
		List<String> a = Arrays.asList("newwarp", "addwarp");
		this.description = "Add a new warp.";
		this.usageMessage = "/setwarp <warpname>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			if (Holder.hasPermission(sndr, Permission.ESS_SETWARP)) {
				switch (args.length) {
				case 0:
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				case 1:
					WarpData d = Warps.getWarp(args[0], (Player) sndr);
					if (d.exists() == true) {
						sndr.sendMessage(Error.WARP_EXISTS.sendError());
						return true;
					}
					d.create();
					sndr.sendMessage(ChatColor.GRAY + "Created warp \"" + args[0] + "\"");
					return true;
				default:
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
			}
		}
		sndr.sendMessage(Error.PLAYER_ONLY.sendError());
		return true;
	}

}
