package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

public class InfoBoard {

	private static final ScoreboardManager m = Bukkit.getScoreboardManager();
	public static final Scoreboard b = m.getNewScoreboard();
	private static final Team t = b.registerNewTeam("Infobar");
	public static final Objective o = b.registerNewObjective("Infoboard", "dummy");
	private static final Score alcdram = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Allocated RAM");
	private static final Score freeram = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Free RAM:");
	private static final Score tps = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "TPS");
	private static final Score usage = InfoBoard.o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "CPU Load");
	private static int a1 = (int) Server.getAllocatedMemory();
	private static int a2 = (int) Server.getFreeMemory();
	private static double a3 = Double.parseDouble(Server.getTPS());
	private static double a4 = Double.parseDouble(Server.getCPULoad().toString());

	public static void createFor(Player player) {
		if (!t.hasEntry(player.getName())) {
			player.setScoreboard(b);
			t.addEntry(player.getName());

		}
		return;
	}

	public static void removeFor(Player player) {
		if (t.hasEntry(player.getName())) {
			player.setScoreboard(m.getNewScoreboard());
			t.removeEntry(player.getName());
		}
		return;
	}

	public static void createBar() {
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.GRAY + "Info Board");
		InfoBoard.alcdram.setScore(15);
		InfoBoard.o.getScore(a1 + "Mb").setScore(14);
		InfoBoard.o.getScore(ChatColor.STRIKETHROUGH + "--- - - -").setScore(13);
		InfoBoard.freeram.setScore(12);
		InfoBoard.o.getScore(a2 + "Mb").setScore(11);
		InfoBoard.o.getScore(ChatColor.STRIKETHROUGH + "- --- - -").setScore(10);
		InfoBoard.tps.setScore(9);
		InfoBoard.o.getScore(a3 + "").setScore(8);
		InfoBoard.o.getScore(ChatColor.STRIKETHROUGH + "- - --- -").setScore(7);
		InfoBoard.usage.setScore(6);
		InfoBoard.o.getScore(a4 + "%").setScore(5);
		InfoBoard.o.getScore(ChatColor.STRIKETHROUGH + "- - - ---").setScore(4);
	}
}
