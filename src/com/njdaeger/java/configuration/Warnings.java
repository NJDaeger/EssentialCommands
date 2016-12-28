package com.njdaeger.java.configuration;

import org.bukkit.Bukkit;

public class Warnings {

	/**
	 * Default console error message.
	 * 
	 * @param warningMessage
	 *            Message to be sent to the console.
	 * @param thrown
	 *            This is what's thrown when the error occurs.
	 * @param showCanceled
	 *            Whether to display the canceled operation message.
	 */
	public static void warn(String warningMessage, Throwable thrown, boolean showCanceled) {
		Bukkit.getLogger().warning("");
		Bukkit.getLogger().warning("");
		Bukkit.getLogger().warning("==========================EssentialCommands==========================");
		if (showCanceled == true) {
			Bukkit.getLogger().warning("");
			Bukkit.getLogger().warning("Operation canceled. Please read below.");
		}
		Bukkit.getLogger().warning("");
		Bukkit.getLogger().warning("Report error at: https://github.com/NJDaeger/EssentialCommands/issues");
		if (thrown != null) {
			Bukkit.getLogger().warning("");
			Bukkit.getLogger().warning("Threw" + thrown.toString());
		}
		if (warningMessage != null) {
			if (thrown == null) {
				Bukkit.getLogger().warning("");
			}
			Bukkit.getLogger().warning("Error: " + warningMessage);
		}
		Bukkit.getLogger().warning("");
		Bukkit.getLogger().warning("If this error persists, please paste the entire error into your issue");
		Bukkit.getLogger().warning("ticket. You should recieve a response shortly after submitting.");
		Bukkit.getLogger().warning("");
		Bukkit.getLogger().warning("==============================EndOfError=============================");
		Bukkit.getLogger().warning("");
		Bukkit.getLogger().warning("");

	}

}
