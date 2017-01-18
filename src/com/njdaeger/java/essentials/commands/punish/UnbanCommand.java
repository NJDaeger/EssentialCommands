package com.njdaeger.java.essentials.commands.punish;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.BanAPI;

import net.md_5.bungee.api.ChatColor;

public class UnbanCommand extends EssCommand {

	BanAPI api = new BanAPI();

	public UnbanCommand() {
		super("unban");
		List<String> a = Arrays.asList("pardon", "removeban");
		this.description = "Unban a banned player.";
		this.usageMessage = "/unban <player>";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(min = 1, max = 1, permissions = { Permission.ESS_UNBAN })
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (canceled(sndr, args)) {
			return true;
		}
		if (!api.isBanned(args[0])) {
			sndr.sendMessage(Error.PLAYER_NOT_BANNED.sendError());
			return true;
		}
		api.unban(args[0]);
		sndr.sendMessage(ChatColor.GRAY + "Successfully unbanned " + ChatColor.GREEN + args[0]);
		return true;
	}
}
