package com.njdaeger.java.command.util;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class CommandLauncher extends Command {
	private Permission[] permissions;
	private int min;
	private int max;
	private CommandExecutor executor;

	public CommandLauncher(EssCmd cmd) {
		super(cmd.getClass().getAnnotation(Cmd.class).name());
		this.description = cmd.getClass().getAnnotation(Cmd.class).desc();
		this.usageMessage = cmd.getClass().getAnnotation(Cmd.class).usage();
		this.setAliases(Arrays.asList(cmd.getClass().getAnnotation(Cmd.class).aliases()));
		this.min = cmd.getClass().getAnnotation(Cmd.class).min();
		this.max = cmd.getClass().getAnnotation(Cmd.class).max();
		this.permissions = cmd.getClass().getAnnotation(Cmd.class).permissions();
	}

	public boolean execute(CommandLauncher command, CommandSender sender, String commandLabel, String[] args) {
		int l = args.length;
		if (Holder.hasPermission(sender, permissions)) {
			if (l > max && max > -1) {
				sender.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return false;
			}
			if (l < min && min > -1) {
				sender.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return false;
			}
			return executor.onCommand(sender, this, commandLabel, args);
		}
		sender.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sender, "Unknown", permissions));
		return false;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		return execute(this, sender, commandLabel, args);
	}
}
