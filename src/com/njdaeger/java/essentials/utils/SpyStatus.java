package com.njdaeger.java.essentials.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.Groups;
import com.njdaeger.java.essentials.exceptions.UnknownStatusException;

/**
 * Socialspy enable or disable for player.
 *
 */
public class SpyStatus {
	
	/**
	 * For command usage, sends a message to the player and the sender. 
	 * @param p - Target to effect.	
	 * @param status - Auto, True, or False.
	 * @param sender - The command sender.
	 * @see Status
	 */
	public static void setSpying(Player p, Status status, CommandSender sender) { //For Commands
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
			if (p.equals(sender)) {
				if (Groups.socialspy.contains(p)) {
					Groups.socialspy.remove(p);
					configuration.set("socialspy", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage("Socialspy is now disabled.");
					return;
				}
				else {
					Groups.socialspy.add(p);
					configuration.set("socialspy", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage(ChatColor.GRAY + "Socialspy is now enabled.");
					return;
				}
			}
			else {
				if (Groups.socialspy.contains(p)) {
					Groups.socialspy.remove(p);
					configuration.set("socialspy", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sender.sendMessage(ChatColor.GRAY + "Socialspy is now disabled for " + ChatColor.GREEN + p.getName());
					p.sendMessage(ChatColor.GRAY + "Socialspy is now disabled.");
					return;
				}
				else {
					Groups.socialspy.add(p);
					configuration.set("socialspy", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sender.sendMessage(ChatColor.GRAY + "Socialspy is now enabled for " + ChatColor.GREEN + p.getName());
					p.sendMessage(ChatColor.GRAY + "Socialspy is now enabled.");
					return;
				}
			}	
		}
		if (status.equals(Status.TRUE)) {
			if (p.equals(sender)) {
				if (!Groups.socialspy.contains(p)) {
					Groups.socialspy.add(p);
					configuration.set("socialspy", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage(ChatColor.GRAY + "Socialspy is now enabled.");
					return;
				}
				else return;
			}
			else {
				if (!Groups.socialspy.contains(p)) {
					Groups.socialspy.add(p);
					configuration.set("socialspy", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sender.sendMessage(ChatColor.GRAY + "Socialspy is now enabled for " + ChatColor.GREEN + p.getName());
					p.sendMessage(ChatColor.GRAY + "Socialspy is now enabled.");
					return;
				}
				else return;
			}
		}
		if (status.equals(Status.FALSE)) {
			if (p.equals(sender)) {
				if (Groups.socialspy.contains(p)) {
					Groups.socialspy.remove(p);
					configuration.set("socialspy", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage(ChatColor.GRAY + "Socialspy is now disabled.");
					return;
				}
				else return;
			}
			else {
				if (Groups.socialspy.contains(p)) {
					Groups.socialspy.remove(p);
					configuration.set("socialspy", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sender.sendMessage(ChatColor.GRAY + "Socialspy is now disabled for " + ChatColor.GREEN + p.getName());
					p.sendMessage(ChatColor.GRAY + "Socialspy is now disabled.");
					return;
				}
				else return;
			}
		}
		else throw new UnknownStatusException();
	}
	/**
	 * For plugin use. Sets them silently.
	 * @param p - Target to effect.	
	 * @param status - Auto, True, or False.
	 * @see Status
	 */
	public static void setSpying(Player p, Status status) { //For Plugin
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
			if (Groups.socialspy.contains(p)) {
				Groups.socialspy.remove(p);
				configuration.set("socialspy", false);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			else {
				Groups.socialspy.add(p);
				configuration.set("socialspy", true);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		if (status.equals(Status.TRUE)) {
			if (!Groups.socialspy.contains(p)) {
				Groups.socialspy.add(p);
				configuration.set("socialspy", true);
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
			if (Groups.socialspy.contains(p)) {
				Groups.socialspy.remove(p);
				configuration.set("socialspy", false);
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
