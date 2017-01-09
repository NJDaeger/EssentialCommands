package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InfoTask extends BukkitRunnable {

	public final JavaPlugin plugin;

	public InfoTask(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				if (Groups.infobar.isEmpty()) {
					return;
				}
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (Groups.infobar.contains(players)) {
						InfoBoard.createFor(players);
					}
					return;
				}
			}
		}, 0L, 10L);

	}

}
