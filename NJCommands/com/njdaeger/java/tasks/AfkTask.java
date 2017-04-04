package com.njdaeger.java.tasks;

import org.bukkit.Bukkit;

import com.njdaeger.java.Core;
import com.njdaeger.java.wrapper.User;

public class AfkTask {

	public AfkTask() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() {

			@Override
			public void run() {
				for (User user : Core.getAfkUsers()) {
					if (!user.isAfk()) {

					}
				}

			}
		}, 0L, 20L);

	}

}
