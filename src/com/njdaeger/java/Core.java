package com.njdaeger.java;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.njdaeger.java.chat.listener.ChatHandler;
import com.njdaeger.java.configuration.controllers.Config;
import com.njdaeger.java.descrete.editors.bannermanager.BannerManager;
import com.njdaeger.java.descrete.editors.bannermanager.listeners.Listener;
import com.njdaeger.java.essentials.commands.CommandCore;
import com.njdaeger.java.essentials.commands.player.EditsignCommand;
import com.njdaeger.java.essentials.commands.player.GetPositionCommand;
import com.njdaeger.java.essentials.commands.player.GiveCommand;
import com.njdaeger.java.essentials.commands.player.GodCommand;
import com.njdaeger.java.essentials.commands.player.HatCommand;
import com.njdaeger.java.essentials.commands.player.HealCommand;
import com.njdaeger.java.essentials.commands.player.NickCommand;
import com.njdaeger.java.essentials.commands.punish.BanCommand;
import com.njdaeger.java.essentials.commands.punish.HelpopCommand;
import com.njdaeger.java.essentials.commands.punish.KickallCommand;
import com.njdaeger.java.essentials.commands.punish.TempBanCommand;
import com.njdaeger.java.essentials.commands.punish.UnbanCommand;
import com.njdaeger.java.essentials.commands.warps.DelwarpCommand;
import com.njdaeger.java.essentials.commands.warps.SetwarpCommand;
import com.njdaeger.java.essentials.commands.warps.WarpCommand;
import com.njdaeger.java.essentials.commands.warps.WarpsCommand;
import com.njdaeger.java.essentials.commands.world.ServerInfoCommand;
import com.njdaeger.java.essentials.listeners.AfkListener;
import com.njdaeger.java.essentials.listeners.PlayerJoinListener;
import com.njdaeger.java.essentials.listeners.PlayerLeaveListener;
import com.njdaeger.java.essentials.utils.Util;

public class Core extends JavaPlugin{
	
	Config config = new Config();
	
	public void registerListeners() {
		new PlayerLeaveListener(this);
		new PlayerJoinListener(this);
		new AfkListener(this);
	}
	@SuppressWarnings("deprecation")
	public void registerCommands() {
		Plugin.getCommand("serverinfo", new ServerInfoCommand()); //Finished
		Plugin.getCommand("i", new GiveCommand()); //Finished 
		Plugin.getCommand("god", new GodCommand()); //Finished
		Plugin.getCommand("nick", new NickCommand()); //Finished //Make it so there can only be one of each nickname
		Plugin.getCommand("position", new GetPositionCommand()); //Finished
		Plugin.getCommand("heal", new HealCommand()); //Finished
		Plugin.getCommand("tempban", new TempBanCommand()); //Finished
		Plugin.getCommand("ban", new BanCommand()); //Finished
		Plugin.getCommand("unabn", new UnbanCommand());//Finished
		Plugin.getCommand("editsign", new EditsignCommand()); //Finished
		Plugin.getCommand("hat", new HatCommand()); //Finished
		Plugin.getCommand("helpop", new HelpopCommand()); //Finished
		Plugin.getCommand("kickall", new KickallCommand()); //Finished
		
		
	}
	@SuppressWarnings("deprecation")
	public void enableSubplugins() {
		/*
		if (config.isHomesEnabled() == true) {
			Plugin.getCommand("sethome", new Sethome()); //Finished
			Plugin.getCommand("delhome", new Delhome()); //Finished
			Plugin.getCommand("home", new Home()); //Finished
			Plugin.getCommand("homes", new Listhomes()); //Finished
			Bukkit.getLogger().info("Subplugin \"Homes\" is now attached and enabled.");
		}
		*/
		if (config.isWarpsEnabled() == true) {
			Plugin.getCommand("setwarp", new SetwarpCommand()); //Finished
			Plugin.getCommand("delwarp", new DelwarpCommand()); //Finished
			Plugin.getCommand("warps", new WarpsCommand());
			Plugin.getCommand("warp", new WarpCommand());
			Bukkit.getLogger().info("Subplugin \"Warps\" is now attached and enabled.");
		}
		if (config.isBannermanagerEnabled() == true) {
			BannerManager.enableBannerManager();
			new Listener(this);
			Bukkit.getLogger().info("Subplugin \"BannerManager\" is now attached and enabled.");
		}
		if (config.isGroupmanagerEnabled() == true) {
			Bukkit.getLogger().info("Subplugin \"GroupManager\" is now attached and enabled.");
		}
		if (config.isChatcolorEnabled() == true) {
			new ChatHandler(this);
			Bukkit.getLogger().info("Subplugin \"ChatColor\" is now attached and enabled.");
		}
	}
	public void registerPermissions() {
		Util.generatePermissions();
		Bukkit.getLogger().info("[EssentialCommands] Version " + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors() + " is now Enabled!");
	}
	/*
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 * check if the plugin is enabled with a checkenabled method and call it in the onenable.
	 */
	@Override
	public void onEnable() {
		CommandCore.registerCommands();
		config.newConfig();
		TPS.getTPSClass();
		enableSubplugins();
		registerListeners();
		registerCommands();
		registerPermissions();
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
