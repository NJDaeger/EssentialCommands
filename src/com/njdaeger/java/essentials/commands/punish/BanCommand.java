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

public class BanCommand extends EssCommand {

	public BanCommand() {
		super("ban");
		List<String> a = Arrays.asList("perm", "banhammer");
		this.description = "Ban a player from the server.";
		this.usageMessage = "/ban <player> [reason]";
		this.setAliases(a);

	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(min = 1, permissions = { Permission.ESS_BAN })
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (canceled(sndr, args)) {
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (Holder.hasPermission(target, Permission.ESS_BAN_EXEMPT) && sndr instanceof Player) {
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
