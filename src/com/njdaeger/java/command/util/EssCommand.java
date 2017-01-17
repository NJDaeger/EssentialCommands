package com.njdaeger.java.command.util;

import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.essentials.enums.Error;

public abstract class EssCommand extends Command implements IEssCommand {

	private Cmd cmd;
	private Method method;

	public EssCommand(String name) {
		super(name);
		try {
			method = super.getClass().getMethod("execute", CommandSender.class, String.class, String[].class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (method.isAnnotationPresent(Cmd.class)) {
			cmd = method.getAnnotation(Cmd.class);
			System.out.println("Registered command " + super.getClass().getSimpleName() + " with annotation type Cmd");
		}
		return;
	}

	public boolean canceled(CommandSender sndr, String[] args) {
		if (cmd.executor() == Executor.PLAYER) {
			if (!(sndr instanceof Player)) {
				sndr.sendMessage(Error.PLAYER_ONLY.sendError());
				return true;
			}
		}
		if (cmd.executor() == Executor.CONSOLE) {
			if (sndr instanceof Player) {
				sndr.sendMessage(Error.CONSOLE_ONLY.sendError());
				return true;
			}
		}
		if (Holder.hasPermission(sndr, cmd.permissions())) {
			if (args.length > cmd.max() && cmd.max() > -1) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			if (args.length < cmd.min() && cmd.min() > -1) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			return false;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown", cmd.permissions()));
		return true;
	}

	@Override
	public abstract void register();

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		return false;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		return super.tabComplete(sender, alias, args);
	}

}