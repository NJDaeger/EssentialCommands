package com.njdaeger.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Error;
import com.njdaeger.essentials.enums.Permission;
import com.njdaeger.essentials.utils.TargetHasPermission;

public class GetPositionCommand extends BukkitCommand{
	
	public GetPositionCommand() {
		super("position");
		List<String> a = Arrays.asList("getpos", "currentpos", "getposition", "getloc", "getlocation");
		this.description = "Get your current position.";
		this.usageMessage = "/position [player]";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (TargetHasPermission.check(player, Permission.ESS_POSITION, Permission.ESS_POSITION_OTHER)) {
				if (args.length == 0) {
					this.sendLocation(player, sndr);
					return true;
				}
				if (args.length == 1) {
					if (TargetHasPermission.check(player, Permission.ESS_POSITION_OTHER)) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
							return true;
						}
						else {
							this.sendLocation(target, sndr);
							return true;
						}
					}
					else {
						sndr.sendMessage(Error.NO_PERMISSION.sendError());
						return true;
					}
				}
				else {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			if (args.length == 0) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				else {
					this.sendLocation(target, sndr);
					return true;
				}
			}
			else {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
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
