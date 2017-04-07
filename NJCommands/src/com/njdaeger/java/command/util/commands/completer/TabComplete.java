package com.njdaeger.java.command.util.commands.completer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME)
public @interface TabComplete {
	
	/**
	 * The name of the command that this tab completer belongs to.
	 * @return
	 */
	String value();
	
}

