package com.njdaeger.java.essentials.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class GamemodeCommand extends EssCommand {

	@Override
	@Cmd(
			name = "gamemode",
			desc = "Switch between gamemodes.",
			usage = "/gamemode <gamemode> [player]",
			min = 1,
			max = 2,
			aliases = { "gm", "mode" },
			permissions = { Permission.ESS_GAMEMODE, Permission.ESS_GAMEMODE_OTHER })
	public boolean run(Sender sndr, String label, String[] args) {
		if (Mode.getAliasUsed(args[0]) == null) {
			sndr.sendMessage(Error.UNKNOWN_GAMEMODE.sendError());
			return true;
		}
		if (args.length == 1) {
			if (sndr instanceof Player) {
				Player player = (Player) sndr;
				PlayerConfig.getConfig(player).setGamemode(args[0]);
				player.sendMessage(ChatColor.GRAY + "Your gamemode is now set to " + ChatColor.GREEN + Mode
						.getAliasUsed(args[0]).toString().toLowerCase());
				return true;
			}
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_GAMEMODE_OTHER)) {
		} else {
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
		PlayerConfig.getConfig(target).setGamemode(args[0]);
		sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + Mode
				.getAliasUsed(args[0]).toString().toLowerCase());
		target.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + Mode.getAliasUsed(
				args[0]).toString().toLowerCase());
		return true;
	}

	public enum Mode {
		SURVIVAL("0", "survival"), CREATIVE("1", "creative"), ADVENTURE("2", "adventure"), SPECTATOR("3", "spectator");

		String[] aliases;

		Mode(String... aliases) {
			this.aliases = aliases;
		}

		public String[] getAliases() {
			return aliases;
		}

		public static Mode getAliasUsed(String input) {
			for (Mode alias : Mode.values()) {
				for (String value : alias.getAliases()) {
					if (value.equals(input)) {
						return alias;
					}
				}
			}
			return null;
		}
	}
}
