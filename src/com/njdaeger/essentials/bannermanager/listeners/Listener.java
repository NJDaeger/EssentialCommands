package com.njdaeger.essentials.bannermanager.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.essentials.Core;
import com.njdaeger.essentials.bannermanager.Banner;
import com.njdaeger.essentials.bannermanager.utils.BannerGUI;

public class Listener extends Banner implements org.bukkit.event.Listener{
	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");
	public Listener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	BannerGUI bGui = new BannerGUI();
	MainGUI mainGui = new MainGUI();
	Effects1GUI e1Gui = new Effects1GUI();
	Effects2GUI e2Gui = new Effects2GUI();
	EColorGUI cGUI = new EColorGUI();
	SavesGUI sGui = new SavesGUI();
	BasecolorGUI baseGui = new BasecolorGUI();
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Bukkit.getLogger().info("a" + main.toString());
		Bukkit.getLogger().info("b" + effects1.toString());
		Bukkit.getLogger().info("c" + effects2.toString());
		Bukkit.getLogger().info("d" + effectcolor.toString());
		Bukkit.getLogger().info("e" + saves.toString());
		Player player = (Player) e.getWhoClicked();
		if (main.contains(player.getName())) {
			mainGui.listen(e);
			return;
		}
		if (effects1.contains(player.getName())) {
			e1Gui.listen(e);
			return;
		}
		if (effects2.contains(player.getName())) {
			e2Gui.listen(e);
			return;
		}
		if (effectcolor.contains(player.getName())) {
			cGUI.listen(e);
			return;
		}
		if (saves.contains(player.getName())) {
			sGui.listen(e);
			return;
		}
		if (basecolor.contains(player.getName())) {
			
		}
		return;
	}
	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if (main.contains(player.getName())) { main.remove(player.getName()); }
		if (effects1.contains(player.getName())) { effects2.remove(player.getName()); }
		if (effects2.contains(player.getName())) { effects1.remove(player.getName()); }
		if (effectcolor.contains(player.getName())) { effectcolor.remove(player.getName()); }
		if (saves.contains(player.getName())) { saves.remove(player.getName()); }
		return;
	}
}
