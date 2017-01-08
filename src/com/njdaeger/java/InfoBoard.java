package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

public class InfoBoard {

	private static final ScoreboardManager m = Bukkit.getScoreboardManager();
	public static final Scoreboard b = m.getNewScoreboard();
	private static final Team t = b.registerNewTeam("Infobar");
	public static final Objective o = b.registerNewObjective("Infoboard", "dummy");

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
	}

	public static void reset(String entry) {
		b.resetScores(entry);
	}

	public static void update(String entry) {
		o.getScoreboard().getEntryTeam(entry).setPrefix("");
	}
}
