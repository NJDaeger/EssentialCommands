package com.njdaeger.java.essentials.commands.player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

public class PweatherCommand extends EssCommand {

	@Override
	@Cmd(
			name = "pweather",
			desc = "Set your current client weather.",
			usage = "/pweather <weatherType> [player]",
			min = 1,
			max = 2,
			aliases = { "playerwather", "myweather" },
			permissions = { Permission.ESS_PWEATHER, Permission.ESS_PWEATHER_OTHER })
	public boolean run(Sender sender, String label, String[] args) {
		return super.run(sender, label, args);
	}

}
