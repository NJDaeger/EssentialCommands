package com.njdaeger.java.essentials.listeners.events;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.njdaeger.java.chat.AllowColor;
import com.njdaeger.java.configuration.controllers.Config;
import com.njdaeger.java.configuration.controllers.PlayerConfig;

public class ChatEvent {
	
	public void forAfk(AsyncPlayerChatEvent e) {
		if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true) {
			PlayerConfig.getConfig(e.getPlayer()).setAfk();
		}
		return;
	}
	public void forColor(AsyncPlayerChatEvent e) {
		if (new Config().isChatcolorEnabled() == true) {
			if (AllowColor.canColorChat(e.getPlayer()) == true) {
				e.setMessage(AllowColor.translateCodes(e.getMessage()));
			}
		}
		return;
	}
	public void forFormat(AsyncPlayerChatEvent e) {
		String format = "";
		e.setFormat(format);
		/*
		 * 
		 * Add the following to the parser:
		 * {PLAYERNAME}
		 * {DISPLAYNAME}
		 * {RANK}
		 * {BALANCE} (This will be implemented later)
		 * {DATE}
		 * {TIME}
		 * {RANKLADDER}
		 * 
		 */
	}
}
