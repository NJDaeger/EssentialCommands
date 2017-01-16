package com.njdaeger.java.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.BanAPI;

import net.md_5.bungee.api.ChatColor;

public class TempBanCommand extends EssCommand {

	static final String name = "tempban";
	//private final BanAPI api = new BanAPI();

	public TempBanCommand() {
		super(name);
		List<String> a = Arrays.asList("temp", "tb", "bantemp");
		this.description = "Temp ban a player.";
		this.usageMessage = "/tempban <player> <time:<d/h/m/s>> [reason]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_TEMPBAN)) {
			switch (args.length) {
			case 0:
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 2:
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			default:
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
		}
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_TEMPBAN)) {
				if (args.length <= 1) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
					return true;
				}
				if (target.equals(sndr) || Holder.hasPermission(player, Permission.ESS_BAN_EXEMPT)) {
					sndr.sendMessage(Error.CANNOT_BAN_TARGET.sendError());
					return true;
				}
				if (args.length == 2) {
					new BanAPI().addBan(target.getName(), sndr, args[1], null);
					target.kickPlayer("You have been temp banned.");
					return true;
				} else {
					StringBuilder builder = new StringBuilder();
					for (int i = 2; i < args.length; i++)
						builder.append(args[i]).append(' ');
					String reason = builder.toString();
					new BanAPI().addBan(target.getName(), sndr, args[1], ChatColor.translateAlternateColorCodes('&',
							reason));
					target.kickPlayer("You have been temp banned.");
					return true;
				}
			} else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		} else {
			if (args.length <= 1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (args.length == 2) {
				new BanAPI().addBan(target.getName(), sndr, args[1], null);
				if (target.isBanned()) {
					target.kickPlayer("You have been temp banned.");
				}
				return true;
			} else {
				StringBuilder builder = new StringBuilder();
				for (int i = 2; i < args.length; i++)
					builder.append(args[i]).append(' ');
				String reason = builder.toString();
				new BanAPI().addBan(target.getName(), sndr, args[1], ChatColor.translateAlternateColorCodes('&',
						reason));
				target.kickPlayer("You have been temp banned.");
				return true;
			}
		}
	}
}
