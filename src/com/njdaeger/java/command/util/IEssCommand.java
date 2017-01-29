package com.njdaeger.java.command.util;

import com.njdaeger.java.wrapper.Sender;

public interface IEssCommand {

	/**
	 * The command method. This is where the command and its args are contained.
	 * 
	 * @param sender The sender of the command.
	 * @param commandLabel The alias used when executed.
	 * @param args Command arguments.
	 * @return True if command not successful, false otherwise.
	 */
	boolean run(Sender sender, String commandLabel, String[] args);

	/**
	 * Returns the name of the command. The name is gotten from the @Cmd
	 * annotation.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Returns the description of the command. The description is gotten from
	 * the @Cmd annotation.
	 * 
	 * @return
	 */
	String getDesc();

	/**
	 * Returns the usage of the command. The usage is gotten from the @Cmd
	 * annotation.
	 * 
	 * @return
	 */
	String getUsage();

	/**
	 * Returns the aliases of the command. The aliases are gotten from the @Cmd
	 * annotation.
	 * 
	 * @return
	 */
	String[] getAliases();
}
