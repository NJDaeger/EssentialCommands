package com.njdaeger.java.essentials.utils.status;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Groups;
import com.njdaeger.java.essentials.exceptions.UnknownStatusException;

public class MessageStatus {
	
	public static void setMessageable(Player p, Status status, CommandSender sender) { //For Commands
		if (status.equals(Status.AUTO)) {
			if (Groups.nomessaging.contains(p)) {
				Groups.nomessaging.remove(p);
				return;
			}
			else {
				Groups.nomessaging.add(p);
				return;
			}	
		}
		if (status.equals(Status.TRUE)) {
			if (p.equals(sender)) {
				if (!Groups.nomessaging.contains(p)) {
					Groups.nomessaging.add(p);
					p.sendMessage(ChatColor.GRAY + "Private messaging is now disabled for you.");
					return;
				}
				else return;
			}
			else {
				if (!Groups.nomessaging.contains(p)) {
					Groups.nomessaging.add(p);
					sender.sendMessage(ChatColor.GRAY + "Socialspy is now enabled for " + ChatColor.GREEN + p.getName());
					p.sendMessage(ChatColor.GRAY + "Socialspy is now enabled.");
					return;
				}
				else return;
			}
		}
		if (status.equals(Status.FALSE)) {
			if (p.equals(sender)) {
				if (Groups.nomessaging.contains(p)) {
					Groups.nomessaging.remove(p);
					p.sendMessage(ChatColor.GRAY + "Socialspy is now disabled.");
					return;
				}
				else return;
			}
			else {
				if (Groups.nomessaging.contains(p)) {
					Groups.nomessaging.remove(p);
					sender.sendMessage(ChatColor.GRAY + "Socialspy is now disabled for " + ChatColor.GREEN + p.getName());
					p.sendMessage(ChatColor.GRAY + "Socialspy is now disabled.");
					return;
				}
				else return;
			}
		}
		else throw new UnknownStatusException();
	}
	public static void setMessageable(Player p, Status status) { //For Plugin
		if (status.equals(Status.AUTO)) {
			if (Groups.nomessaging.contains(p)) {
				Groups.nomessaging.remove(p);
				return;
			}
			else {
				Groups.nomessaging.add(p);
				return;
			}
		}
		if (status.equals(Status.TRUE)) {
			if (!Groups.nomessaging.contains(p)) {
				Groups.nomessaging.add(p);
				return;
			}
			else return;
		}
		if (status.equals(Status.FALSE)) {
			if (Groups.nomessaging.contains(p)) {
				Groups.nomessaging.remove(p);
				return;
			}
			else return;
		}
		else throw new UnknownStatusException();
	}
}
