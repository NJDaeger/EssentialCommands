package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class GetPositionCommand extends EssCommand {

	static String name = "position";

	public GetPositionCommand() {
		super(name);
		List<String> a = Arrays.asList("getpos", "currentpos", "getposition", "getloc", "getlocation");
		this.description = "Get your current position.";
		this.usageMessage = "/position [player]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_POSITION, Permission.ESS_POSITION_OTHER)) {
			switch (args.length) {
			case 0:
				if (sndr instanceof Player) {
					this.sendLocation((Player) sndr, sndr);
					return true;
				}
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
				if (Holder.hasPermission(sndr, Permission.ESS_POSITION_OTHER)) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					this.sendLocation(target, sndr);
					return true;
				}
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}

	public void sendLocation(Player target, CommandSender sndr) {
		sndr.sendMessage(ChatColor.GRAY + "Location for player " + ChatColor.GREEN + target.getName());
		sndr.sendMessage(ChatColor.GRAY + "Coord x:" + ChatColor.GREEN + this.getX(target));
		sndr.sendMessage(ChatColor.GRAY + "Coord y:" + ChatColor.GREEN + this.getY(target));
		sndr.sendMessage(ChatColor.GRAY + "Coord z:" + ChatColor.GREEN + this.getZ(target));
		sndr.sendMessage(ChatColor.GRAY + "Chunk x:" + ChatColor.GREEN + this.getChunkX(target));
		sndr.sendMessage(ChatColor.GRAY + "Chunk z:" + ChatColor.GREEN + this.getChunkZ(target));
		sndr.sendMessage(ChatColor.GRAY + "World:" + ChatColor.GREEN + this.getWorld(target));
		return;
	}

	public int getX(Player target) {
		return target.getLocation().getBlockX();
	}

	public int getY(Player target) {
		return target.getLocation().getBlockY();
	}

	public int getZ(Player target) {
		return target.getLocation().getBlockZ();
	}

	public int getChunkX(Player target) {
		return target.getLocation().getChunk().getX();
	}

	public int getChunkZ(Player target) {
		return target.getLocation().getChunk().getZ();
	}

	public String getWorld(Player target) {
		return target.getWorld().getName();
	}
}
