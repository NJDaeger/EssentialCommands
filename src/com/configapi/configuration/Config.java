package com.configapi.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.configapi.configuration.exceptions.ConfigExistsException;
import com.configapi.configuration.interfaces.IConfiguration;

public class Config implements IConfiguration{

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IConfiguration#newConfig()
	 */
	public void newConfig() throws IOException {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (!file.exists()) {
			file.createNewFile();
			YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
			c.set("enable.groupmanager", true);
			c.set("enable.bannermanager", true);
			c.set("enable.chatcolor", true);
			c.set("enable.homes", true);
			c.set("enable.warps", true);
			c.set("essentials.enablecmd.Afk", true);
			c.set("essentials.enablecmd.Break", true);
			c.set("essentials.enablecmd.Burn", true);
			c.set("essentials.enablecmd.ClearInv", true);
			c.set("essentials.enablecmd.Editsign", true);
			c.set("essentials.enablecmd.Fly", true);
			c.set("essentials.enablecmd.Gamemode", true);
			c.set("essentials.enablecmd.GetPosition", true);
			c.set("essentials.enablecmd.Give", true);
			c.set("essentials.enablecmd.God", true);
			c.set("essentials.enablecmd.Hat", true);
			c.set("essentials.enablecmd.Heal", true);
			c.set("essentials.enablecmd.Kill", true);
			c.set("essentials.enablecmd.More", true);
			c.set("essentials.enablecmd.Nick", true);
			c.set("essentials.enablecmd.Ptime", true);
			c.set("essentials.enablecmd.Pweather", true);
			c.set("essentials.enablecmd.Realname", true);
			c.set("essentials.enablecmd.Repair", true);//
			c.set("essentials.enablecmd.Speed", true);
			c.set("essentials.enablecmd.Suicide", true);
			c.set("essentials.enablecmd.Vanish", true);
			c.set("essentials.enablecmd.Whois", true);
			c.set("essentials.enablecmd.Workbench", true);
			c.set("essentials.enablecmd.Ban", true);
			c.set("essentials.enablecmd.Helpop", true);
			c.set("essentials.enablecmd.Kickall", true);
			c.set("essentials.enablecmd.Kick", true);
			c.set("essentials.enablecmd.TempBan", true);
			c.set("essentials.enablecmd.Unban", true);
			c.set("essentials.enablecmd.Tpall", true);
			c.set("essentials.enablecmd.Tp", true);
			c.set("essentials.enablecmd.ServerInfo", true);
			c.set("essentials.enablecmd.Time", true);
			c.set("essentials.enablecmd.Weather", true);
			c.set("warps.warplimit", false);
			c.set("warps.warplimitvalue", 10);
			c.set("warps.enablecmd.Delwarp", true);
			c.set("warps.enablecmd.Setwarp", true);
			c.set("warps.enablecmd.Renamewarp", true);
			c.set("warps.enablecmd.Listwarps", true);
			c.set("groupmanager.enablecmd.AddGroup", true);
			c.save(file);
			/*
			 * Create some type of for loop on startup. Go into the commands.player package get the class names and cut off "Command" from them
			 * It will be easier to check if enabled or not. 
			 * 
			 * public String getClassName(Class class) (if doing AfkCommand it would return Afk).
			 * 
			 * public boolean isEnabled(getClassName(AfkCommand)) (this would hvae the "essentials.enablecmd." in front of it in the original method)
			 * if ()
			 */
			
		}
		throw new ConfigExistsException();
	}

	/* (non-Javadoc)
	 * @see com.configapi.configuration.interfaces.IConfiguration#getConfig()
	 */
	public YamlConfiguration getConfig() {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"config.yml");
		if (file.exists()) {
			return YamlConfiguration.loadConfiguration(file);
		}
		return null;
	}
}
