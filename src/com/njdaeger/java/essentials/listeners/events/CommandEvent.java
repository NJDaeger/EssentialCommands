package com.njdaeger.java.essentials.listeners.events;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.commands.messaging.BroadcastCommand;
import com.njdaeger.java.essentials.commands.messaging.MessageCommand;
import com.njdaeger.java.essentials.commands.messaging.ReplyCommand;
import com.njdaeger.java.essentials.commands.player.AfkCommand;
import com.njdaeger.java.essentials.commands.player.VanishCommand;

public class CommandEvent {

	private AfkCommand afkCommand = new AfkCommand();
	private MessageCommand messageCommand = new MessageCommand();
	private ReplyCommand replyCommand = new ReplyCommand();
	private BroadcastCommand broadcastCommand = new BroadcastCommand();
	private VanishCommand vanishCommand = new VanishCommand();

	public void whenAfk(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage().toLowerCase();
		if (afkCommand.getAliases().contains(message) || afkCommand.getName().equalsIgnoreCase(message)
				|| messageCommand.getAliases().contains(message) || messageCommand.getName().equalsIgnoreCase(message)
				|| replyCommand.getAliases().contains(message) || replyCommand.getName().equalsIgnoreCase(message)
				|| broadcastCommand.getAliases().contains(message)
				|| broadcastCommand.getName().equalsIgnoreCase(message) || vanishCommand.getAliases().contains(message)
				|| vanishCommand.getName().equalsIgnoreCase(message)) {
			if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true) {
				return;
			} else {
				PlayerConfig.getConfig(e.getPlayer()).setAfk();
			}
			return;
		}
	}
}
