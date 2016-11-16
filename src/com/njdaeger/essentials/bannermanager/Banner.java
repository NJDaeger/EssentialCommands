package com.njdaeger.essentials.bannermanager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.essentials.bannermanager.utils.BannerExists;
import com.njdaeger.essentials.bannermanager.utils.BannerMissing;
import com.njdaeger.essentials.bannermanager.utils.Editing;
import com.njdaeger.essentials.bannermanager.utils.Errors;
import com.njdaeger.essentials.bannermanager.utils.IBannerHandler;
import com.njdaeger.essentials.bannermanager.utils.NotEditing;

public class Banner implements IBannerHandler{
	
	public static HashMap<String, GuiType> editmode = new HashMap<String, GuiType>();
	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#saveBanner(java.lang.String)
	 */
	public void saveBanner(Player sender, String bannername){
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"bannermanager"+File.separator+"banners"+File.separator+bannername+".yml");
		if (!file.exists()) {
			if (sender.getInventory().getItemInMainHand().equals(Material.BANNER)) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				YamlConfiguration banner = YamlConfiguration.loadConfiguration(file);
				org.bukkit.block.Banner b = (org.bukkit.block.Banner) sender.getInventory().getItemInMainHand();
				banner.set("creator", sender.getName());
				banner.set("pattern", b.getPatterns());
				try {
					banner.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			sender.sendMessage(Errors.NO_BANNER_IN_HAND.sendError());
			return;
		}
		try {
			throw new BannerExists();
		} catch (BannerExists e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#deleteBanner(java.lang.String)
	 */
	public void deleteBanner(Player sender, String bannername) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"bannermanager"+File.separator+"banners"+File.separator+bannername+".yml");
		if (file.exists()) {
			YamlConfiguration b = YamlConfiguration.loadConfiguration(file);
			if (b.get("creator") == sender.getName() || sender.isOp()) {
				file.delete();
				return;
			}
			sender.sendMessage(Errors.NOT_BANNER_MAKER.sendError());
			return;
		}
		try {
			throw new BannerMissing();
		} catch (BannerMissing e) {
			e.printStackTrace();
		}
		
	}
	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#getBanner(java.lang.String)
	 */
	public YamlConfiguration getSavedBanner(String bannername) {
		File file = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"bannermanager"+File.separator+"banners"+File.separator+bannername+".yml");
		if (file.exists()) {
			return YamlConfiguration.loadConfiguration(file);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#listBanners()
	 */
	public String listBanners() {
		File dir = new File("plugins"+File.separator+"EssentialCommands"+File.separator+"bannermanager"+File.separator+"banners");
		if (dir.exists()) {
			String[] banners = dir.list();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < banners.length; i++) {
				sb.append(banners[i]).append(" ");
			}
			String message = sb.toString().trim();
			String finalmsg = message.replace(".yml", "");
			String wcommas = finalmsg.replaceAll(" ", ", ");
			return wcommas;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#removeEditMode(org.bukkit.entity.Player)
	 */
	public void removeEditMode(String player) {
		if (editmode.containsValue(player)) {
			editmode.remove(player);
			return;
		}
		try {
			throw new NotEditing();
		} catch (NotEditing e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#addEditMode(org.bukkit.entity.Player)
	 */
	public void addEditMode(String player, GuiType type) {
		if (!editmode.containsValue(player)) {
			editmode.put(player, type);
			return;
		}
		try {
			throw new Editing();
		} catch (Editing e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#isEditMode(org.bukkit.entity.Player)
	 */
	public boolean isEditMode(String player) {
		if (editmode.containsValue(player)) {
			return true;
		}
		return false;
	}

}
