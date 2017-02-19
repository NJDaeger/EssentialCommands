package com.njdaeger.java.essentials.commands.player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.wrapper.Sender;

public class DebugCommand extends EssCommand {

	@Override
	@Cmd(
			name = "db",
			usage = "/db",
			desc = "Debug player configurations.",
			aliases = { "debug", "bug" },
			executor = Executor.PLAYER)
	public boolean run(Sender sender, String label, String[] args) {
		sender.sendMessage("Afk: " + (boolean) Transform.getValue(sender.asPlayer(), PlayerPaths.AFK));
		sender.sendMessage("Flyspeed: " + (double) Transform.getValue(sender.asPlayer(), PlayerPaths.FLYSPEED));
		sender.sendMessage("Rank: " + (String) Transform.getValue(sender.asPlayer(), PlayerPaths.RANK));
		sender.sendMessage("Displayname: " + (String) Transform.getValue(sender.asPlayer(), PlayerPaths.DISPLAYNAME));
		sender.sendMessage("Toggled: " + (boolean) Transform.getValue(sender.asPlayer(), PlayerPaths.TPTOGGLED));
		sender.sendMessage("Walkspeed: " + (int) Transform.getValue(sender.asPlayer(), PlayerPaths.WALKSPEED));
		sender.sendMessage("God mem: " + (boolean) Transform.getValue(sender.asPlayer(), PlayerPaths.GOD));
		sender.sendMessage("God yml: " + PlayerConfig.getConfig(sender.asPlayer()).getYamlFile().get(PlayerPaths.GOD
				.getPath()));
		return true;
	}

}
