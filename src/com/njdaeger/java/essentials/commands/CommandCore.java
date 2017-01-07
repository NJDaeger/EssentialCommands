package com.njdaeger.java.essentials.commands;

import com.njdaeger.java.essentials.commands.homes.Delhome;
import com.njdaeger.java.essentials.commands.homes.Home;
import com.njdaeger.java.essentials.commands.homes.Listhomes;
import com.njdaeger.java.essentials.commands.homes.Sethome;
import com.njdaeger.java.essentials.commands.messaging.BroadcastCommand;
import com.njdaeger.java.essentials.commands.messaging.MeCommand;
import com.njdaeger.java.essentials.commands.messaging.MessageCommand;
import com.njdaeger.java.essentials.commands.messaging.ReplyCommand;
import com.njdaeger.java.essentials.commands.player.AfkCommand;
import com.njdaeger.java.essentials.commands.player.BreakCommand;
import com.njdaeger.java.essentials.commands.player.BurnCommand;
import com.njdaeger.java.essentials.commands.player.ClearInvCommand;
import com.njdaeger.java.essentials.commands.player.FlyCommand;
import com.njdaeger.java.essentials.commands.player.GamemodeCommand;
import com.njdaeger.java.essentials.commands.player.GetPositionCommand;
import com.njdaeger.java.essentials.commands.player.GiveCommand;
import com.njdaeger.java.essentials.commands.player.GodCommand;
import com.njdaeger.java.essentials.commands.player.HealCommand;
import com.njdaeger.java.essentials.commands.player.KillCommand;
import com.njdaeger.java.essentials.commands.player.NickCommand;
import com.njdaeger.java.essentials.commands.player.SpeedCommand;
import com.njdaeger.java.essentials.commands.warps.DelwarpCommand;
import com.njdaeger.java.essentials.commands.warps.SetwarpCommand;
import com.njdaeger.java.essentials.commands.warps.WarpCommand;
import com.njdaeger.java.essentials.commands.warps.WarpsCommand;
import com.njdaeger.java.essentials.commands.world.ServerInfoCommand;

public class CommandCore {

	public static void registerCommands() {
		new Delhome().register();
		new Home().register();
		new Listhomes().register();
		new Sethome().register();
		new FlyCommand().register();
		new BroadcastCommand().register();
		new MeCommand().register();
		new ReplyCommand().register();
		new MessageCommand().register();
		new GamemodeCommand().register();
		new AfkCommand().register();
		new SpeedCommand().register();
		new BreakCommand().register();
		new BurnCommand().register();
		new ClearInvCommand().register();
		new NickCommand().register();
		new GiveCommand().register();
		new DelwarpCommand().register();
		new SetwarpCommand().register();
		new WarpCommand().register();
		new WarpsCommand().register();
		new ServerInfoCommand().register();
		new GodCommand().register();
		new GetPositionCommand().register();
		new HealCommand().register();
		new KillCommand().register();

	}
}
