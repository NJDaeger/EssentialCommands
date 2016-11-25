package com.njdaeger.essentials;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.chat.chatcolor.ChatHandler;
import com.configapi.configuration.Config;
import com.configapi.configuration.Subplugins;
import com.njdaeger.essentials.bannermanager.BannerManager;
import com.njdaeger.essentials.bannermanager.listeners.Listener;
import com.njdaeger.essentials.commands.homes.Delhome;
import com.njdaeger.essentials.commands.homes.Home;
import com.njdaeger.essentials.commands.homes.Listhomes;
import com.njdaeger.essentials.commands.homes.Sethome;
import com.njdaeger.essentials.commands.messaging.BroadcastCommand;
import com.njdaeger.essentials.commands.messaging.MeCommand;
import com.njdaeger.essentials.commands.messaging.MessageCommand;
import com.njdaeger.essentials.commands.player.AfkCommand;
import com.njdaeger.essentials.commands.player.BreakCommand;
import com.njdaeger.essentials.commands.player.BurnCommand;
import com.njdaeger.essentials.commands.player.ClearInvCommand;
import com.njdaeger.essentials.commands.player.EditsignCommand;
import com.njdaeger.essentials.commands.player.GamemodeCommand;
import com.njdaeger.essentials.commands.player.GetPositionCommand;
import com.njdaeger.essentials.commands.player.GiveCommand;
import com.njdaeger.essentials.commands.player.GodCommand;
import com.njdaeger.essentials.commands.player.HatCommand;
import com.njdaeger.essentials.commands.player.HealCommand;
import com.njdaeger.essentials.commands.player.NickCommand;
import com.njdaeger.essentials.commands.player.SpeedCommand;
import com.njdaeger.essentials.commands.punish.BanCommand;
import com.njdaeger.essentials.commands.punish.HelpopCommand;
import com.njdaeger.essentials.commands.punish.KickallCommand;
import com.njdaeger.essentials.commands.punish.TempBanCommand;
import com.njdaeger.essentials.commands.punish.UnbanCommand;
import com.njdaeger.essentials.commands.teleport.DelwarpCommand;
import com.njdaeger.essentials.commands.teleport.SetwarpCommand;
import com.njdaeger.essentials.commands.teleport.WarpCommand;
import com.njdaeger.essentials.commands.teleport.WarpsCommand;
import com.njdaeger.essentials.commands.world.ServerInfoCommand;
import com.njdaeger.essentials.listeners.AfkListener;
import com.njdaeger.essentials.listeners.PlayerJoinListener;
import com.njdaeger.essentials.listeners.PlayerLeaveListener;
import com.njdaeger.essentials.utils.Util;

public class Core extends JavaPlugin{
	
	Subplugins plugins = new Subplugins();
	Config config = new Config();
	
	public void registerListeners() {
		new PlayerLeaveListener(this);
		new PlayerJoinListener(this);
		new AfkListener(this);
	}
	public void registerCommands() {
		Plugin.getCommand("afk", new AfkCommand()); //Finished
		Plugin.getCommand("broadcast", new BroadcastCommand()); //Finished
		Plugin.getCommand("serverinfo", new ServerInfoCommand()); //Finished
		Plugin.getCommand("gamemode", new GamemodeCommand()); //Finished
		Plugin.getCommand("i", new GiveCommand()); //Finished 
		Plugin.getCommand("god", new GodCommand()); //Finished
		Plugin.getCommand("nick", new NickCommand()); //Finished //Make it so there can only be one of each nickname
		Plugin.getCommand("break", new BreakCommand()); //Finished 
		Plugin.getCommand("burn", new BurnCommand()); //Finished
		Plugin.getCommand("speed", new SpeedCommand()); //Finished ADD ABILITY TO DO DECIMAL NUMBERS
		Plugin.getCommand("clear", new ClearInvCommand()); //Finished
		Plugin.getCommand("position", new GetPositionCommand()); //Finished
		Plugin.getCommand("heal", new HealCommand()); //Finished
		Plugin.getCommand("me", new MeCommand()); //Finished
		Plugin.getCommand("message", new MessageCommand()); //Finished
		Plugin.getCommand("tempban", new TempBanCommand()); //Finished
		Plugin.getCommand("ban", new BanCommand()); //Finished
		Plugin.getCommand("unabn", new UnbanCommand());//Finished
		Plugin.getCommand("editsign", new EditsignCommand()); //Finished
		Plugin.getCommand("hat", new HatCommand()); //Finished
		Plugin.getCommand("helpop", new HelpopCommand()); //Finished
		Plugin.getCommand("kickall", new KickallCommand()); //Finished
		
		
	}
	public void enableSubplugins() {
		if (plugins.canRun("homes") == true) {
			Plugin.getCommand("sethome", new Sethome()); //Finished
			Plugin.getCommand("delhome", new Delhome()); //Finished
			Plugin.getCommand("home", new Home()); //Finished
			Plugin.getCommand("homes", new Listhomes()); //Finished
			Bukkit.getLogger().info("Subplugin \"Homes\" is now attached and enabled.");
		}
		if (plugins.canRun("warps") == true) {
			Plugin.getCommand("setwarp", new SetwarpCommand()); //Finished
			Plugin.getCommand("delwarp", new DelwarpCommand()); //Finished
			Plugin.getCommand("warps", new WarpsCommand());
			Plugin.getCommand("warp", new WarpCommand());
			Bukkit.getLogger().info("Subplugin \"Warps\" is now attached and enabled.");
		}
		if (plugins.canRun("bannermanager")) {
			BannerManager.enableBannerManager();
			new Listener(this);
			Bukkit.getLogger().info("Subplugin \"BannerManager\" is now attached and enabled.");
		}
		if (plugins.canRun("groupmanager") == true) {
			Bukkit.getLogger().info("Subplugin \"GroupManager\" is now attached and enabled.");
		}
		if (plugins.canRun("chatcolor") == true) {
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
		try {
			config.newConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		enableSubplugins();
		TPS.getTPSClass();
		registerListeners();
		registerCommands();
		registerPermissions();
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
