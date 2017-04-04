package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

public class InfoBoard {
	
	private final ChatColor color;
	private final int line;
	private final Team team;

	public InfoBoard(ChatColor color, int line, Team team) {
		this.color = color;
		this.line = line;
		this.team = team;
	}

	public ChatColor getColor() {
		return color;
	}

	public int getLine() {
		return line;
	}

	public Team getTeam() {

		return team;
	}

	public static void createFor(Player player) {
		Board board = new Board("Info Board", Double.parseDouble(Server.getCPULoad().toString()) + "%", ChatColor.GREEN + "" + ChatColor.BOLD + "CPU Load", ChatColor.STRIKETHROUGH + "- - --- -",
				InfoBoard.formatTps(Server.getTPS()) + "", ChatColor.GREEN + "" + ChatColor.BOLD + "TPS", ChatColor.STRIKETHROUGH + "- --- - -",
				Server.getFreeMemory() + "Mb", ChatColor.GREEN + "" + ChatColor.BOLD + "Free RAM", ChatColor.STRIKETHROUGH + "--- - - -",
				Server.getAllocatedMemory() + "Mb", ChatColor.GREEN + "" + ChatColor.BOLD + "Alcd RAM");
		player.setScoreboard(board.getScoreboard());
	}

	public static void removeFor(Player player) {
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
	public static String formatTps(String tps) {
		double ticks = Double.parseDouble(tps);
		if (ticks > 20) {
			return ChatColor.DARK_GREEN + "20";
		} else if (ticks >= 18) {
			return ChatColor.GREEN + tps;
		} else if (ticks >= 15) {
			return ChatColor.YELLOW + tps;
		} else
			return ChatColor.RED + tps;
	}
}
