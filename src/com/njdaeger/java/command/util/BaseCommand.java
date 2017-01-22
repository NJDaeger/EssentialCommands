package com.njdaeger.java.command.util;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.PluginsCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.wrapper.Sender;

public class BaseCommand extends PluginsCommand {

	EssCommand cmd;
	Method method;
	Cmd command;

	public BaseCommand(EssCommand cmmd) {
		super(cmmd.getName());
		try {
			method = cmmd.getClass().getMethod("run", Sender.class, String.class, String[].class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		this.cmd = cmmd;
		command = this.method.getAnnotation(Cmd.class);
		this.description = command.desc();
		this.usageMessage = command.usage();
		this.setAliases(Arrays.asList(command.aliases()));
	}

	public boolean canceled(CommandSender sndr, String[] args) {
		if (command.executor() == Executor.PLAYER) {
			if (!(sndr instanceof Player)) {
				sndr.sendMessage(Error.PLAYER_ONLY.sendError());
				return true;
			}
		}
		if (command.executor() == Executor.CONSOLE) {
			if (sndr instanceof Player) {
				sndr.sendMessage(Error.CONSOLE_ONLY.sendError());
				return true;
			}
		}
		if (Holder.hasPermission(sndr, command.permissions())) {
			if (args.length > command.max() && command.max() > -1) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			if (args.length < command.min() && command.min() > -1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			return false;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown", command.permissions()));
		return true;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (canceled(sender, args)) {
			return true;
		}
		Sender sndr = new Sender(sender);
		return cmd.run(sndr, label, args);
	}
}
