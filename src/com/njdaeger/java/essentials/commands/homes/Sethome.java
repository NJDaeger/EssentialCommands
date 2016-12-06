package com.njdaeger.java.essentials.commands.homes;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.essentials.enums.Permission;

public class Sethome extends BukkitCommand{
	
	public Sethome() {
		super("sethome");
		List<String> a = Arrays.asList("newhome", "addhome");
		this.description = "Create a new home.";
		this.usageMessage = "/sethome <name>";
		this.setAliases(a);
	}
	
	Homes homes = new Homes();
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_SETHOME)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.ADD_HOME_NAME.sendError());
					return true;
				}
				if (args.length > 1) {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
				if (homes.getHome(args[0], player) != null) {
					sndr.sendMessage(Error.HOME_EXISTS.sendError());
					sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN + homes.listHomes(player));
					return true;
				}
				else {
					homes.createHome(args[0], player);
					Location a = player.getLocation();
					sndr.sendMessage(ChatColor.GRAY + "Created a new home at " + ChatColor.GREEN + "x:" + a.getBlockX() + " y:" + a.getBlockY() + " z:" + a.getBlockZ());
					return true;
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			sndr.sendMessage(Error.PLAYER_ONLY.sendError());
			return true;
		}
	}
}
