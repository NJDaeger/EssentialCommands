package com.njdaeger.java.command.util;

import java.lang.reflect.Method;

import com.njdaeger.java.wrapper.Sender;

public class EssCommand implements IEssCommand {

	private Cmd cmd;
	private Method method;
	private String name;
	private String desc;
	private String usage;
	private String[] aliases;

	public EssCommand() {
		try {
			method = super.getClass().getMethod("run", Sender.class, String.class, String[].class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (method.isAnnotationPresent(Cmd.class)) {
			cmd = method.getAnnotation(Cmd.class);
		}
		this.name = cmd.name();
		this.desc = cmd.desc();
		this.usage = cmd.usage();
		this.aliases = cmd.aliases();
	}

	@Override
	public void register() {

	}

	@Override
	public boolean run(Sender sender, String label, String[] args) {
		return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getUsage() {
		return usage;
	}

	@Override
	public String[] getAliases() {
		return aliases;
	}
}