package com.njdaeger.essentials.bannermanager;

import com.njdaeger.essentials.Plugin;
import com.njdaeger.essentials.bannermanager.commands.CreatebannerCommand;


public class BannerManager {
	
	public static void enableBannerManager() {
		//check if the configuration has this enabled. if not dont register any commands or permissions from it.
		Plugin.getCommand("createbanner", new CreatebannerCommand());
		//Plugin.getCommand("editbanner", new EditbannerCommand());
		//Plugin.getCommand("deletebanner", new DeleteBannerCommand());
		//Plugin.getCommand("loadbanner", new LoadBannerCommand());
		//Plugin.getCommand("savebanner", new SaveBannerCommand());
		//Plugin.getCommand("listbanners", new ListBannersCommand());
		
	}
	
}
