package com.njdaeger.java.essentials.commands.world;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Groups;
import com.njdaeger.java.Holder;
import com.njdaeger.java.InfoBoard;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class InfoSidebarCommand extends EssCommand {

	@Override
	public void register() {
		Plugin.getCommand(this);
	}

	@Cmd(
			name = "infobar",
			desc = "Toggles a sidebar of the basic server info.",
			usage = "/infobar",
			max = 1,
			aliases = { "sibar", "serverinfobar", "lagbar", "gcbar" },
			permissions = { Permission.ESS_INFOBAR, Permission.ESS_INFOBAR_OTHER })
	@Override
	public boolean run(Sender sndr, String label, String[] args) {
		if (args.length == 0) {
			if (!(sndr instanceof Player)) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (Groups.infobar.contains(sndr)) {
				Groups.infobar.remove(sndr);
				InfoBoard.removeFor((Player) sndr);
				sndr.sendMessage(ChatColor.GRAY + "Infobar is no longer active.");
				return true;
			}
			Groups.infobar.add((Player) sndr);
			InfoBoard.createFor((Player) sndr);
			sndr.sendMessage(ChatColor.GRAY + "Infobar is now active.");
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_INFOBAR_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (Groups.infobar.contains(target)) {
				Groups.infobar.remove(target);
				InfoBoard.removeFor(target);
				sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
						+ "'s infobar is no longer active.");
				target.sendMessage(ChatColor.GRAY + "Infobar is no longer active.");
				return true;
			}
			InfoBoard.createFor(target);
			Groups.infobar.add(target);
			sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + "'s infobar now active.");
			target.sendMessage(ChatColor.GRAY + "Infobar is now active.");
		}
		return true;
	}
}
