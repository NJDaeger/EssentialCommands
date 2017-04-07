package com.njdaeger.java.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.njdaeger.java.Core;
import com.njdaeger.java.Groups;
import com.njdaeger.java.InfoBoard;

public class InfoTask {
	
	public InfoTask() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() {
			
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
