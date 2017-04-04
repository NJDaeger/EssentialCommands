package com.njdaeger.java.register;

import com.njdaeger.java.command.util.Lib;
import com.njdaeger.java.essentials.commands.homes.HomeCommands;
import com.njdaeger.java.essentials.commands.homes.HomeCompleter;
import com.njdaeger.java.essentials.commands.messaging.MessageCommands;
import com.njdaeger.java.essentials.commands.player.PlayerCommands;
import com.njdaeger.java.essentials.commands.player.PlayerEnvironmentCommands;
import com.njdaeger.java.essentials.commands.player.UsefulPlayerCommands;
import com.njdaeger.java.essentials.commands.punish.PunishCommands;
import com.njdaeger.java.essentials.commands.warps.WarpCommands;
import com.njdaeger.java.essentials.commands.world.WorldCommands;

public class CommandCore extends Lib {

	public static void registerCommands() {
		addCommand(HomeCommands.class);
		addCompletion(HomeCompleter.class);
		addCommand(MessageCommands.class);
		addCommand(PlayerEnvironmentCommands.class);
		addCommand(PlayerCommands.class);
		addCommand(UsefulPlayerCommands.class);
		addCommand(WarpCommands.class);
		addCommand(PunishCommands.class);
		addCommand(WorldCommands.class);
		/*addCommand(new AfkCommand());
		addCommand(new BreakCommand());
		addCommand(new BurnCommand());
		addCommand(new ClearInvCommand());
		addCommand(new EditsignCommand());
		addCommand(new FlyCommand());
		addCommand(new GamemodeCommand());
		addCommand(new GetPositionCommand());
		addCommand(new GiveCommand());
		addCommand(new GodCommand());
		addCommand(new HatCommand());
		addCommand(new HealCommand());
		addCommand(new KillCommand());
		addCommand(new MoreCommand());
		addCommand(new NickCommand());
		addCommand(new DebugCommand());
		addCommand(new PingCommand());
		addCommand(new PtimeCommand());
		//addCommand(new PweatherCommand());
		//addCommand(new RealnameCommand());
		//addCommand(new RepairCommand());
		addCommand(new SpeedCommand());
		addCommand(new VanishCommand());
		addCommand(new WhoisCommand());
		addCommand(new WorkbenchCommand());
		addCommand(new FoodCommand());
		addCommand(new BanCommand());
		addCommand(new HelpopCommand());
		addCommand(new KickallCommand());
		//addCommand(new KickCommand());
		addCommand(new TempBanCommand());
		addCommand(new TempBanCommand());
		addCommand(new UnbanCommand());
		//addCommand(new TpallCommand());
		//addCommand(new TpCommand());
		addCommand(new DelwarpCommand());
		addCommand(new SetwarpCommand());
		addCommand(new WarpCommand());
		addCommand(new WarpsCommand());
		addCommand(new InfoSidebarCommand());
		addCommand(new ServerInfoCommand());
		//addCommand(new TimeCommand());
		//addCommand(new WeatherCommand());
		//addCommand(new SocialspyCommand());
		addCommand(new ReplyCommand());
		addCommand(new MessageCommand());
		addCommand(new MeCommand());
		//addCommand(new DisableMessageCommand());
		addCommand(new BroadcastCommand());
		//addCommand(new Sethome());
		//addCommand(new Listhomes());
		//addCommand(new Home());
		//addCommand(new Delhome());
		addCommand(new PtimeCommand());*/
		//addCommand(new PweatherCommand());

	}
}
