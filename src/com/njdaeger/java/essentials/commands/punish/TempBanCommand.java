package com.njdaeger.java.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.BanAPI;

import net.md_5.bungee.api.ChatColor;

public class TempBanCommand extends EssCommand {

	static final String name = "tempban";
	private final BanAPI api = new BanAPI();

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

	@Cmd(max = -1, min = 2, permissions = { Permission.ESS_TEMPBAN })
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (canceled(sndr, args)) {
			return true;
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (target.equals(sndr) || Holder.hasPermission(target, Permission.ESS_BAN_EXEMPT)) {
			sndr.sendMessage(Error.CANNOT_BAN_TARGET.sendError());
			return true;
		}
		switch (args.length) {
		case 2:
			api.addBan(target.getName(), sndr, args[1], null);
			target.kickPlayer("You have been temp banned.");
			return true;
		default:
			StringBuilder builder = new StringBuilder();
			for (int i = 2; i < args.length; i++)
				builder.append(args[i]).append(' ');
			String reason = builder.toString();
			api.addBan(target.getName(), sndr, args[1], ChatColor.translateAlternateColorCodes('&', reason));
			target.kickPlayer("You have been temp banned for " + reason);
			return true;
		}
	}
}
