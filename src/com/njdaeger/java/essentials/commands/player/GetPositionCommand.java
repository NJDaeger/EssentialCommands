package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class GetPositionCommand extends EssCommand {

	@Override
	@Cmd(
			name = "position",
			desc = "Get your current position.",
			usage = "/position [player]",
			max = 1,
			aliases = { "getpos", "currentpos", "getposition", "getloc", "getlocation" },
			permissions = { Permission.ESS_POSITION, Permission.ESS_POSITION_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (sndr.isPlayer()) {
				this.sendLocation(sndr.asPlayer(), sndr);
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_POSITION_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			this.sendLocation(target, sndr);
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), sndr.asPlayer(), "Unknown",
				Permission.ESS_POSITION_OTHER));
		return true;
	}

	public void sendLocation(Player target, Sender sndr) {
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
