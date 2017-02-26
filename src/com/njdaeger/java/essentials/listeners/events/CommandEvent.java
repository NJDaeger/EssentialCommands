package com.njdaeger.java.essentials.listeners.events;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.commands.messaging.BroadcastCommand;
import com.njdaeger.java.essentials.commands.messaging.MessageCommand;
import com.njdaeger.java.essentials.commands.messaging.ReplyCommand;
import com.njdaeger.java.essentials.commands.player.AfkCommand;
import com.njdaeger.java.essentials.commands.player.VanishCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.User;

public class CommandEvent {

	private AfkCommand afkCommand = new AfkCommand();
	private MessageCommand messageCommand = new MessageCommand();
	private ReplyCommand replyCommand = new ReplyCommand();
	private BroadcastCommand broadcastCommand = new BroadcastCommand();
	private VanishCommand vanishCommand = new VanishCommand();

	public void whenAfk(PlayerCommandPreprocessEvent e) {
		User user = Core.getUser(e.getPlayer());
		String message = e.getMessage().toLowerCase();
		if (getAliases(afkCommand).contains(message) || afkCommand.getName().equalsIgnoreCase(message) || getAliases(
				broadcastCommand).contains(message) || messageCommand.getName().equalsIgnoreCase(message) || getAliases(
						messageCommand).contains(message) || replyCommand.getName().equalsIgnoreCase(message)
				|| getAliases(replyCommand).contains(message) || broadcastCommand.getName().equalsIgnoreCase(message)
				|| getAliases(vanishCommand).contains(message) || vanishCommand.getName().equalsIgnoreCase(message)) {
		}
		if (user.isAfk()) {
			user.setAfk(false);
			return;
		}
	}

	public void whenBlocked(PlayerCommandPreprocessEvent e) {
		String d = e.getMessage().replace("/", "");
		String c = "";
		if (d.contains(" ")) {
			String[] b = d.split(" ");
			c += b[0];
			if (Core.getConf().getBlacklistedCommands().contains(c)) {
				if (Holder.hasPermission(e.getPlayer(), Permission.ESS_BYPASS_BLOCKED_COMMANDS)) {
					return;
				} else {
					e.getPlayer().sendMessage(Parser.parse(Error.BLOCKED_COMMAND.getError(), e.getPlayer(), c));
					e.setCancelled(true);
					return;
				}
			}
			return;
		} else {
			c += d;
			if (Core.getConf().getBlacklistedCommands().contains(c)) {
				if (Holder.hasPermission(e.getPlayer(), Permission.ESS_BYPASS_BLOCKED_COMMANDS)) {
					return;
				} else {
					e.getPlayer().sendMessage(Parser.parse(Error.BLOCKED_COMMAND.getError(), e.getPlayer(), c));
					e.setCancelled(true);
					return;
				}
			}
			return;
		}
	}

	public void checkReload(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().startsWith("rl") || e.getMessage().startsWith("reload")) {
			Core.setReloading(true);
		}
		return;
	}

	public void checkServerReload(ServerCommandEvent e) {
		if (e.getCommand().startsWith("rl") || e.getCommand().startsWith("reload")) {
			Core.setReloading(true);
		}
		return;
	}

	private List<String> getAliases(EssCommand command) {
		return Arrays.asList(command.getAliases());
	}
}
