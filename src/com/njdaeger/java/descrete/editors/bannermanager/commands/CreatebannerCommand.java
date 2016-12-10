package com.njdaeger.java.descrete.editors.bannermanager.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.descrete.editors.bannermanager.GuiType;
import com.njdaeger.java.descrete.editors.bannermanager.utils.BannerGUI;

public class CreatebannerCommand extends BukkitCommand{
	
	public CreatebannerCommand() {
		super("createbanner");
		List<String> a = Arrays.asList("newbanner", "banner");
		this.description = "Create a custom banner.";
		this.usageMessage = "/createbanner";
		this.setAliases(a);
		
	}
	
	BannerGUI bGui = new BannerGUI();
	
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			BannerGUI.main.add(player.getName());
			bGui.newBannerGui(player, GuiType.COLOR, null, 0);
			return true;
		}
		else sndr.sendMessage(Error.PLAYER_ONLY.sendError());
		return true;
	}	
}