package com.njdaeger.java.njcommands.listeners.events;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.njdaeger.java.Core;
import com.njdaeger.java.chat.AllowColor;
import com.njdaeger.java.wrapper.User;

public class ChatEvent {

	public void forAfk(AsyncPlayerChatEvent e) {
		User user = Core.getUser(e.getPlayer());
		if (user.isAfk()) {
			user.setAfk(false);
		}
		return;
	}

	public void forColor(AsyncPlayerChatEvent e) {
		if (AllowColor.canColorChat(e.getPlayer()) == true) {
			e.setMessage(AllowColor.translateCodes(e.getMessage()));
		}
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
