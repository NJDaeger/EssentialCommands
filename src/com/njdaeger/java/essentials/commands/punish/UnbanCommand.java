package com.njdaeger.java.essentials.commands.punish;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.essentials.utils.BanAPI;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class UnbanCommand extends EssCommand {

	BanAPI api = new BanAPI();

	@Cmd(
			name = "unban",
			desc = "Unban a banned player.",
			usage = "/unban <player>",
			min = 1,
			max = 1,
			aliases = { "pardon", "removeban" },
			permissions = { Permission.ESS_UNBAN })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		if (!api.isBanned(args[0])) {
			sndr.sendMessage(Error.PLAYER_NOT_BANNED.sendError());
			return true;
		}
		api.unban(args[0]);
		sndr.sendMessage(ChatColor.GRAY + "Successfully unbanned " + ChatColor.GREEN + args[0]);
		return true;
	}
}
