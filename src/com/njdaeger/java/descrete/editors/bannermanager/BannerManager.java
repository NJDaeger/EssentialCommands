package com.njdaeger.java.descrete.editors.bannermanager;

import com.njdaeger.java.Plugin;
import com.njdaeger.java.descrete.editors.bannermanager.commands.CreatebannerCommand;


public class BannerManager {
	
	@SuppressWarnings("deprecation")
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
