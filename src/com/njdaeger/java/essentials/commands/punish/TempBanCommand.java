package com.njdaeger.java.essentials.commands.punish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.BanAPI;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class TempBanCommand extends EssCommand {

	private final BanAPI api = new BanAPI();

	@Cmd(
			name = "tempban",
			desc = "Temp ban a player.",
			usage = "/tempban <player> <time:<d/h/m/s>> [reason...]",
			min = 2,
			aliases = { "temp", "tb", "bantemp" },
			permissions = { Permission.ESS_TEMPBAN })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		Player target = Bukkit.getPlayer(args[0]);
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
