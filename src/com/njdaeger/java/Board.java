package com.njdaeger.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

public class Board {

	private static final List<ChatColor> COLORS = Arrays.asList(ChatColor.values());
	private final Scoreboard scoreboard;
	private final Objective objective;
	private final List<InfoBoard> lines = new ArrayList<>();

	public Board(String displayname, String... newlines) {
		Validate.isTrue(newlines.length < COLORS.size() - 3, "Too many lines to display.");
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective("Infoboard", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(displayname);
		for (int i = 0; i < COLORS.size() - 3; i++) {
			final ChatColor color = COLORS.get(i);
			final Team team = scoreboard.registerNewTeam("line" + i);
			team.addEntry(color.toString());
			lines.add(new InfoBoard(color, i, team));
		}
		for (int i = 0; i < newlines.length; i++)
			setValue(i, newlines[i]);
	}

	private InfoBoard getLine(int line) {
		return lines.stream().filter(line1 -> line1.getLine() == line).findFirst().orElse(null);
	}

	public void setValue(int line, String value) {
		final InfoBoard ib = getLine(line);
		Validate.notNull(ib, "Cannot find line with index of " + line);
		objective.getScore(ib.getColor().toString()).setScore(line);
		ib.getTeam().setPrefix(value);
	}

	public void removeLine(int line) {
		final InfoBoard ib = getLine(line);
		Validate.notNull(ib, "Cannot find line with index of " + line);
		scoreboard.resetScores(ib.getColor().toString());
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}
}
