package com.njdaeger.java.essentials.utils.status;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.Groups;
import com.njdaeger.java.essentials.exceptions.UnknownStatusException;

public class AfkStatus {
	
	
	
	/**<p>Sets the pl,ayer to afk or not.</p>
	 * @param p - Target player to change afk status.
	 * @param status - Auto, True, or False.
	 * @see Status
	 */
	public static void setAfk(Player p, Status status) {
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
		else {
			if (status.equals(Status.AUTO)) {
				if (Groups.afk.contains(p)) {
					Groups.afk.remove(p);
					configuration.set("afk", false);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					Groups.afkloc.remove(p.getName(), p.getLocation());
					p.setCollidable(true);
					Bukkit.broadcastMessage(ChatColor.GRAY + "* " + p.getDisplayName() + ChatColor.GRAY + " is no longer AFK.");
					return;
				}
				else {
					Groups.afk.add(p);
					Groups.afkloc.put(p.getName(), p.getLocation());
					p.setCollidable(false);
					configuration.set("afk", true);
					try {
						configuration.save(dir1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					Bukkit.broadcastMessage(ChatColor.GRAY + "* " + p.getDisplayName() + ChatColor.GRAY + " is now AFK.");
					return;
				}
			}
			if (status.equals(Status.TRUE)) {
				if (!Groups.afk.contains(p)) {
					Groups.afk.add(p);
					configuration.set("afk", true);
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
				if (Groups.afk.contains(p)) {
					Groups.afk.remove(p);
					configuration.set("afk", false);
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
}
