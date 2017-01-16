package com.njdaeger.java.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.BanAPI;

import net.md_5.bungee.api.ChatColor;

public class UnbanCommand extends EssCommand {

	static String name = "unban";

	public UnbanCommand() {
		super(name);
		List<String> a = Arrays.asList("pardon", "removeban");
		this.description = "Unban a banned player.";
		this.usageMessage = "/unban <player>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_UNBAN)) {
			switch (args.length) {
			case 0:
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			case 1:
				new BanAPI().unban(args[0]);
				sndr.sendMessage(ChatColor.GRAY + "Successfully unbanned " + ChatColor.GREEN + args[0]);
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown", Permission.ESS_UNBAN));
		return true;
	}
}
