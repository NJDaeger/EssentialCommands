package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class GamemodeCommand extends EssCommand{
	
	static String name = "gamemode";
	
	public GamemodeCommand() {
		super("gamemode");
		List<String> a = Arrays.asList("gm", "mode");
		this.description = "Switch between gamemodes.";
		this.usageMessage = "/gamemode <creative/survivial/adventure/spectator> [player]";
		this.setAliases(a);
	}
	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}
	
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (args.length == 0) {
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (args.length > 2) {
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_GAMEMODE, Permission.ESS_GAMEMODE_OTHER)) {
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (this.getGamemodeString(args[0]) == false) {
			sndr.sendMessage(Error.UNKNOWN_GAMEMODE.sendError());
			return true;
		}
		if (args.length == 1) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				PlayerConfig.getConfig(player).setGamemode(args[0]);
				player.sendMessage(ChatColor.GRAY + "Your gamemode is now set to " + ChatColor.GREEN + getGamemodeName(args[0]));
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_GAMEMODE_OTHER)) {
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		PlayerConfig.getConfig(target).setGamemode(args[0]);
		sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeName(args[0]));
		target.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeName(args[0]));
		return true;
	}
	public String getGamemodeName(String gamemode) {
		if (gamemode.equalsIgnoreCase("creative") || gamemode.equals("1")) {
			return "Creative";
		}
		else if (gamemode.equalsIgnoreCase("survival") || gamemode.equals("0")) {
			return "Survival";
		}
		else if (gamemode.equalsIgnoreCase("adventure") || gamemode.equals("2")) {
			return "Adventure";
		}
		else if (gamemode.equalsIgnoreCase("spectator") || gamemode.equals("3")) {
			return "Spectator";
		}
		return null;
	}
	public boolean getGamemodeString(String gamemode) {
		if (gamemode.equalsIgnoreCase("creative") || gamemode.equals("1") || 
				gamemode.equalsIgnoreCase("survival") || gamemode.equals("0") || 
				gamemode.equalsIgnoreCase("adventure") || gamemode.equals("2") || 
				gamemode.equalsIgnoreCase("spectator") || gamemode.equals("3")) {
			return true;
		}
		else return false;
	}
}
