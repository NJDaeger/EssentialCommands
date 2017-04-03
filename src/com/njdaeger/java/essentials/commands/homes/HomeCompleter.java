package com.njdaeger.java.essentials.commands.homes;

import java.util.List;

import com.google.common.collect.Lists;
import com.njdaeger.java.command.util.commands.completer.TabComplete;
import com.njdaeger.java.wrapper.Sender;

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
						sub.add(homes);
				}
				return sub;
			}
		}
		return null;
	}

}
