package com.njdaeger.java.essentials.commands.homes;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.configuration.data.HomeData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class Sethome extends EssCommand {

	static String name = "sethome";

	public Sethome() {
		super(name);
		List<String> a = Arrays.asList("newhome", "addhome");
		this.description = "Create a new home.";
		this.usageMessage = "/sethome <name>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_SETHOME)) {
				switch (args.length) {
				case 0:
					sndr.sendMessage(Error.ADD_HOME_NAME.sendError());
					return true;
				case 1:
					HomeData home = Homes.getHome(args[0], player);
					if (!home.exists()) {
						sndr.sendMessage(Error.HOME_EXISTS.sendError());
						sndr.sendMessage(ChatColor.GRAY + "Current homes: " + ChatColor.GREEN
								+ Homes.getHome(args[0], player).listHomes());
						return true;
					}
					home.create();
					sndr.sendMessage(ChatColor.GRAY + "Created a new home at " + ChatColor.GREEN + "x:" + home.getX()
							+ " y:" + home.getY() + " z:" + home.getZ());
					return true;
				default:
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
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
