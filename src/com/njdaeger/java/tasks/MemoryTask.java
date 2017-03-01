package com.njdaeger.java.tasks;

import org.bukkit.Bukkit;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.Transform;
import com.njdaeger.java.wrapper.User;

public class MemoryTask {

	public MemoryTask() {
		if (Core.getConf().loadInMemory()) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() {
				@Override
				public void run() {
					int i = 0;
					if (!Bukkit.getOnlinePlayers().isEmpty()) {
						for (User users : Core.getOnlineUsers()) {
							i++;
							Transform.reload(users);
						}
						System.out.println("[MemoryTask] Saved " + i + " memory configuration(s) to file storage.");
						return;
					}
					System.out.println("[MemoryTask] No players online to save.");
				}
			}, 0, 12000);
		}
		return;

		// TODO Auto-generated constructor stub
	}

	/*@Override
	public void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for (Player players : Bukkit.getOnlinePlayers()) {
					Transform.reload(players);
				}
				
			}
		}, delay, period)
		
	}*/
}
