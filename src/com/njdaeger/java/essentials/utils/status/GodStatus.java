package com.njdaeger.java.essentials.utils.status;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.Groups;
import com.njdaeger.java.essentials.exceptions.UnknownStatusException;

public class GodStatus {
	/**
	 * <p>For command usage, this will announce a message to the commandsender and the target</p>
	 * @param p - Target to set in god mode.
	 * @param sndr - Command sender
	 * @param status - Auto, True, or false.
	 * @see Status
	 */
	public static void setGod(Player p, CommandSender sndr, Status status) {
		UUID userID = p.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
		YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dir1);
		if (!dir.exists()) {
			return;
		}
		if (!dir1.exists()) {
			return;
		}
		if (status.equals(Status.AUTO)) {
			if (Groups.god.contains(p)) {
				Groups.god.remove(p);
				p.setInvulnerable(false);
				configuration.set("god", false);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (p.equals(sndr)) {
					p.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
					return;
				}
				else {
					sndr.sendMessage(ChatColor.GRAY + "You turned off " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + "'s God mode.");
					p.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
					return;
				}
				
			}
			else {
				Groups.god.add(p);
				p.setInvulnerable(true);
				configuration.set("god", true);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (p.equals(sndr)) {
					p.sendMessage(ChatColor.GRAY + "You are now in God mode.");
					return;
				}
				else {
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " to God mode.");
					p.sendMessage(ChatColor.GRAY + "You are now in God mode.");
					return;
				}
			}
		}
		if (status.equals(Status.TRUE)) {
			if (!Groups.god.contains(p)) {
				Groups.god.add(p);
				configuration.set("god", true);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (p.equals(sndr)) {
					p.sendMessage(ChatColor.GRAY + "You are now in God mode.");
					return;
				}
				else {
					sndr.sendMessage(ChatColor.GRAY + "You set " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " to God mode.");
					p.sendMessage(ChatColor.GRAY + "You are now in God mode.");
					return;
				}
			}
			else return;
		}
		if (status.equals(Status.FALSE)) {
			if (Groups.god.contains(p)) {
				Groups.god.remove(p);
				configuration.set("god", false);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (p.equals(sndr)) {
					p.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
					return;
				}
				else {
					sndr.sendMessage(ChatColor.GRAY + "You turned off " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + "'s God mode.");
					p.sendMessage(ChatColor.GRAY + "You are no longer in God mode.");
					return;
				}
			}
			else return;
		}
		else throw new UnknownStatusException();
	}
	
	/**
	 * <p>For plugin usage only, this is silent mode changing.</p>
	 * @param p - Target player to set into god mode.
	 * @param status - Auto, True, or false.
	 * @see Status
	 */
	public static void setGod(Player p, Status status) { //For plugin.
		UUID userID = p.getUniqueId();
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"users"+File.separator+userID);
		File dir1 = new File(dir+File.separator+"user.yml");
		YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dir1);
		if (!dir.exists()) {
			return;
		}
		if (!dir1.exists()) {
			return;
		}
		if (status.equals(Status.AUTO)) {
			if (Groups.god.contains(p)) {
				Groups.god.remove(p);
				p.setInvulnerable(false);
				configuration.set("god", false);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
				
			}
			else {
				Groups.god.add(p);
				p.setInvulnerable(true);
				configuration.set("god", true);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		if (status.equals(Status.TRUE)) {
			if (!Groups.god.contains(p)) {
				Groups.god.add(p);
				configuration.set("god", true);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			else return;
		}
		if (status.equals(Status.FALSE)) {
			if (Groups.god.contains(p)) {
				Groups.god.remove(p);
				configuration.set("god", false);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			else return;
		}
		else throw new UnknownStatusException();
	}
}
