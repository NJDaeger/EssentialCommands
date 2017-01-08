package com.njdaeger.java.essentials.commands.world;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.EssCommand;
import com.njdaeger.java.Groups;
import com.njdaeger.java.Holder;
import com.njdaeger.java.InfoBoard;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class InfoSidebarCommand extends EssCommand {

	static String name = "infobar";

	public InfoSidebarCommand() {
		super(name);
		List<String> a = Arrays.asList("sibar", "serverinfobar", "lagbar", "gcbar");
		this.description = "Toggles a sidebar of the basic server info.";
		this.usageMessage = "/infobar [player]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_INFOBAR, Permission.ESS_INFOBAR_OTHER)) {
			switch (args.length) {
			case 0:
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
			case 1:
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
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}

		}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}
}
