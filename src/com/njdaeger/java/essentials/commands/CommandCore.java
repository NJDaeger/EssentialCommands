package com.njdaeger.java.essentials.commands;

import com.njdaeger.java.configuration.controllers.Config;
import com.njdaeger.java.essentials.commands.homes.Delhome;
import com.njdaeger.java.essentials.commands.homes.Home;
import com.njdaeger.java.essentials.commands.homes.Listhomes;
import com.njdaeger.java.essentials.commands.homes.Sethome;
import com.njdaeger.java.essentials.commands.messaging.BroadcastCommand;
import com.njdaeger.java.essentials.commands.messaging.MeCommand;
import com.njdaeger.java.essentials.commands.messaging.MessageCommand;
import com.njdaeger.java.essentials.commands.messaging.ReplyCommand;
import com.njdaeger.java.essentials.commands.player.AfkCommand;
import com.njdaeger.java.essentials.commands.player.FlyCommand;
import com.njdaeger.java.essentials.commands.player.GamemodeCommand;

public class CommandCore {
	
	private static Config config = new Config();
	
	public static void registerCommands() {
		if (config.isHomesEnabled() == true) {
			new Delhome().register();
			new Home().register();
			new Listhomes().register();
			new Sethome().register();
			System.out.println("Homes is now enabled.");
		}
		new FlyCommand().register();
		new BroadcastCommand().register();
		new MeCommand().register();
		new ReplyCommand().register();
		new MessageCommand().register();
		new GamemodeCommand().register();
		new AfkCommand().register();
		
		
	}
}
