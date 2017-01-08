package com.njdaeger.java;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Score;

import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

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
						Score alcdram = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Allocated RAM");
						Score freeram = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Free RAM:");
						Score tps = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "TPS");
						Score usage = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "CPU Load");
						List<String> a1 = new ArrayList<String>();
						List<String> a2 = new ArrayList<String>();
						List<String> a3 = new ArrayList<String>();
						List<String> a4 = new ArrayList<String>();
						InfoBoard.b.getTeam("Infobar").setPrefix("");
						InfoBoard.createBar();
						if (Holder.hasPermission(players, Permission.ESS_INFOBAR_ALCDRAM, Permission.ESS_INFOBAR_ALL)) {
							int a = (int) Server.getAllocatedMemory();
							alcdram.setScore(12);
							if (a1.isEmpty()) {
								a1.add(0, a + "Mb");
							}
							if (a1.get(0) != a + "Mb") {
								//InfoBoard.update(a1.get(0));
								InfoBoard.reset(a1.get(0));
								InfoBoard.o.getScore(a + "Mb").setScore(11);
								a1.clear();
							}
							a1.add(0, a + "Mb");
						}
						if (Holder.hasPermission(players, Permission.ESS_INFOBAR_FREERAM, Permission.ESS_INFOBAR_ALL)) {
							int a = (int) Server.getFreeMemory();
							freeram.setScore(9);
							if (a2.isEmpty()) {
								a2.add(0, a + "Mb");
							}
							if (a2.get(0) != a + "Mb") {
								//InfoBoard.update(a2.get(0));
								InfoBoard.reset(a2.get(0));
								InfoBoard.o.getScore(a + "Mb").setScore(8);
								a2.clear();
							}
							a2.add(0, a + "Mb");
						}
						if (Holder.hasPermission(players, Permission.ESS_INFOBAR_TPS, Permission.ESS_INFOBAR_ALL)) {
							double a = Double.parseDouble(Server.getTPS());
							tps.setScore(6);
							if (a3.isEmpty()) {
								a3.add(0, a + "");
							}
							if (a3.get(0) != a + "") {
								//InfoBoard.update(a3.get(0));
								InfoBoard.reset(a3.get(0));
								InfoBoard.o.getScore(a + "").setScore(5);
								a3.clear();
							}
							a3.add(0, a + "");
						}
						if (Holder.hasPermission(players, Permission.ESS_INFOBAR_USAGE, Permission.ESS_INFOBAR_ALL)) {
							double a = Double.parseDouble(Server.getCPULoad().toString());
							usage.setScore(3);
							if (a4.isEmpty()) {
								a4.add(0, a + "%");
							}
							if (a4.get(0) != a + "%") {
								//InfoBoard.update(a4.get(0));
								InfoBoard.reset(a4.get(0));
								InfoBoard.o.getScore(a + "%").setScore(2);
								a4.clear();
							}
							a4.add(0, a + "%");
						}
					}
					return;
				}
			}
		}, 0L, 10L);

	}

}
