package com.njdaeger.java.command.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.njdaeger.java.essentials.enums.Permission;

@Retention(RetentionPolicy.RUNTIME)
public @interface Cmd {

	/**
	 * A list of all the aliases of a command. Do not, under any circumstances,
	 * put the real name of the command in this list. Default is "", or in other
	 * words, no aliases.
	 * 
	 * @return
	 */
	String[] aliases() default "";

	/**
	 * A list of the base permission needed to use this command. If more
	 * commands are needed to use sub commands, handle that inside the execute
	 * method. Default is Permission.ESS_ALL, one would need to have the
	 * wild-card permission in order to do the command.
	 * 
	 * @return
	 */
	Permission[] permissions() default Permission.ESS_ALL;

	/**
	 * The name of the command. Make sure not to include this in the alias list.
	 * This field is required at the very least in a command method.
	 * 
	 * @return
	 */
	String name();

	/**
	 * A brief description of the command. Default is "", or no description.
	 * 
	 * @return
	 */
	String desc() default "";

	/**
	 * The command usage. Default is "", or no usage message.
	 * 
	 * @return
	 */
	String usage() default "";

	/**
	 * The maximum amount of arguments in the command before an error is thrown.
	 * Default is -1 for no maximum amount.
	 * 
	 * @return
	 */
	int max() default -1;

	/**
	 * The minimum amount of arguments in the command before an error is thrown.
	 * Default is -1 for no minimum amount.
	 * 
	 * @return
	 */
	int min() default -1;

}
