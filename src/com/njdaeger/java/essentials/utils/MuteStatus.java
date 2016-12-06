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
 * MuteStatus sets the player muted or not. 
 */

public class MuteStatus { 
	 
	/**
	 * <p>For regular command usage, sends a message to sender and target.</p>
	 * @param target - Target to effect.
	 * @param status - Auto, True, or False.
	 * @param sender - The command sender.
	 * @see Status 
	 */
	public static void setMuted(Player target, Status status, CommandSender sender) { //For commands.
		UUID userID = target.getUniqueId();
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
			if (target.equals(sender)) {
				if (Groups.muted.contains(target)) {
					Groups.muted.remove(target);
					configuration.set("muted", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					target.sendMessage(ChatColor.GRAY + "You are no longer muted.");
					return;
				}
				else {
					Groups.muted.add(target);
					configuration.set("muted", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					target.sendMessage(ChatColor.GRAY + "You have been muted.");
					return;
				}
			}
			else {
				if (Groups.muted.contains(target)) {
					Groups.muted.remove(target);
					configuration.set("muted", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sender.sendMessage(ChatColor.GREEN + target.getName() + ChatColor.GRAY + " is no longer muted.");
					target.sendMessage(ChatColor.GRAY + "You are no longer muted.");
					return;
				}
				else {
					configuration.set("muted", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					Groups.muted.add(target);
					sender.sendMessage(ChatColor.GREEN + target.getName() + ChatColor.GRAY + " is now muted.");
					target.sendMessage(ChatColor.GRAY + "You have been muted.");
					return;
				}
			}
		}
		if (status.equals(Status.TRUE)) {
			if(target.equals(sender)) {
				if (!Groups.muted.contains(target)) {
					Groups.muted.add(target);
					target.sendMessage(ChatColor.GRAY + "You are now muted.");
					configuration.set("muted", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
				else return;
			}
			else {
				if (!Groups.muted.contains(target)) {
					Groups.muted.add(target);
					sender.sendMessage(ChatColor.GREEN + target.getName() + ChatColor.GRAY + " is now muted.");
					target.sendMessage(ChatColor.GRAY + "You are now muted.");
					configuration.set("muted", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
				else return;
			}
		}
		if (status.equals(Status.FALSE)) {
			if(target.equals(sender)) {
				if (Groups.muted.contains(target)) {
					Groups.muted.remove(target);
					configuration.set("muted", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					target.sendMessage(ChatColor.GRAY + "You are now unmuted.");
					return;
					
				}
				else return;
			}
			else {
				if (Groups.muted.contains(target)) {
					Groups.muted.remove(target);
					target.sendMessage(ChatColor.GRAY + "You are now unmuted.");
					sender.sendMessage(ChatColor.GREEN + target.getName() + ChatColor.GRAY + " is no longer muted.");
					configuration.set("muted", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
				else return;
			}
		}
		else throw new UnknownStatusException();
	}
	/**
	 * <p>For plugin backend use. Meant for silent switching. </p>
	 * @param target - Target to effect.
	 * @param status - Auto, True, or False. 
	 * @see Status 
	 */
	public static void setMuted(Player target, Status status) { //For plugin
		UUID userID = target.getUniqueId();
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
			if (Groups.muted.contains(target)) {
				Groups.muted.remove(target);
				configuration.set("muted", false);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			else {
				Groups.muted.add(target);
				configuration.set("muted", true);
				try {
					configuration.save(dir1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		if (status.equals(Status.TRUE)) {
			if (!Groups.muted.contains(target)) {
				Groups.muted.add(target);
				configuration.set("muted", true);
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
			if (Groups.muted.contains(target)) {
				Groups.muted.remove(target);
				configuration.set("muted", false);
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
