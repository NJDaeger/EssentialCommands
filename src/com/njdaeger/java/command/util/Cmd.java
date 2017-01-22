package com.njdaeger.java.command.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.njdaeger.java.essentials.enums.Permission;

@Retention(RetentionPolicy.RUNTIME)
public @interface Cmd {

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

	/**
	 * Specifies who is able to do the command. Default is Executor.ALL so
	 * everyone can use the command.
	 * 
	 * @return
	 */
	Executor executor() default Executor.ALL;

	/**
	 * The name of the command being executed.
	 * 
	 * @return
	 */
	String name();

	/**
	 * The command description. Default is ""
	 * 
	 * @return
	 */
	String desc();

	/**
	 * The usage of the command. Default is ""
	 * 
	 * @return
	 */
	String usage();

	/**
	 * The command aliases. Default is ""
	 * 
	 * @return
	 */
	String[] aliases() default "";

}
