package com.njdaeger.java.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.njdaeger.java.Groups;

public class AfkTask extends BukkitRunnable {

	public final JavaPlugin plugin;

	public AfkTask(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (!Groups.afk.contains(players)) {

					}
				}

			}
		}, 0L, 20L);

	}

}
