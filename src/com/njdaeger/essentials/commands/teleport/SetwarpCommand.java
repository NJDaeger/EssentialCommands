package com.njdaeger.essentials.commands.teleport;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.configapi.configuration.Warps;
import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class SetwarpCommand extends BukkitCommand{
	Warps warps = new Warps();
	public SetwarpCommand() {
		super("setwarp");
		List<String> a = Arrays.asList("newwarp", "addwarp");
		this.description = "Add a new warp.";
		this.usageMessage = "/setwarp <warpname>";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_SETWARP)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length > 1) {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
				else {
					if (warps.getWarp(args[0]) == null) {
						warps.createWarp(args[0], player);
						sndr.sendMessage(ChatColor.GRAY + "Created warp \"" + args[0] + "\"");
						return true;
					}
					sndr.sendMessage(Error.WARP_EXISTS.sendError());
					return true;
				}
			}
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		sndr.sendMessage(Error.PLAYER_ONLY.sendError());
		return true;
	}

}
