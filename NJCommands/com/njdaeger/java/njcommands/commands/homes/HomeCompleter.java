package com.njdaeger.java.njcommands.commands.homes;

import java.util.List;

import com.google.common.collect.Lists;
import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.commands.completer.TabComplete;
import com.njdaeger.java.wrapper.Sender;
import com.njdaeger.java.wrapper.User;

public class HomeCompleter {
	
	@TabComplete("delhome")
	public List<String> delhome(Sender sender, String alias, String[] args) {
		List<String> sub = Lists.newArrayList();
		if (args.length == 1) {
			if (sender.isUser()) {
				if (sender.asUser().getHomes() == null) {
					return null;
				}
				for (String homes : sender.asUser().getHomes()) {
					if (homes.toLowerCase().startsWith(args[0]))
						sub.add(homes.replace(".yml", ""));
				}
				return sub;
			}
		}
		return null;
	}
	
	@TabComplete("homes")
	public List<String> homelist(Sender sender, String alias, String[] args) {
		List<String> sub = Lists.newArrayList();
		if (args.length == 1) {
			for (User user : Core.getOnlineUsers()) {
				if (user.getNickname().toLowerCase().startsWith(args[0])) {
					sub.add(user.getName());
				}
				if (user.getName().toLowerCase().startsWith(args[0])) {
					sub.add(user.getName());
				}
				return sub;
			}
		}
		return null;
	}
	
}
