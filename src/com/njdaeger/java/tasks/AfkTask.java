package com.njdaeger.java.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Core;
import com.njdaeger.java.Groups;

public class AfkTask {

	public AfkTask() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() {

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
