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

public class BanCommand extends EssCommand {

	@Cmd(
			name = "ban",
			desc = "Ban a player from the server.",
			usage = "/ban <player> [reason]",
			min = 1,
			aliases = { "perm", "banhammer" },
			permissions = { Permission.ESS_BAN })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (Holder.hasPermission(target, Permission.ESS_BAN_EXEMPT) && sndr.isPlayer()) {
			sndr.sendMessage(Error.CANNOT_BAN_TARGET.sendError());
			return true;
		}
		if (args.length == 1) {
			new BanAPI().addBan(target.getName(), sndr, null, null);
			target.kickPlayer("You have been banned.");
			return true;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < args.length; i++)
			builder.append(args[i]).append(' ');

		String reason = builder.toString();
		new BanAPI().addBan(target.getName(), sndr, null, reason);
		target.kickPlayer("You have been banned for " + reason);
		return true;
	}
}
