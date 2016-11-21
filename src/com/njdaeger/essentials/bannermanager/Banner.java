package com.njdaeger.essentials.bannermanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.njdaeger.essentials.bannermanager.exceptions.BannerExists;
import com.njdaeger.essentials.bannermanager.exceptions.MetadataException;
import com.njdaeger.essentials.bannermanager.utils.BannerMissing;
import com.njdaeger.essentials.bannermanager.utils.IBannerHandler;
import com.njdaeger.essentials.enums.Error;

public class Banner implements IBannerHandler{
	
	/**
	 * Arraylist for the main GUI.
	 */
	public static ArrayList<String> main = new ArrayList<String>();
	/**
	 * Arraylist for page one of effects.
	 */
	public static ArrayList<String> effects1 = new ArrayList<String>();
	/**
	 * Arraylist for page two of effects.
	 */
	public static ArrayList<String> effects2 = new ArrayList<String>();
	/**
	 * Arraylist to choose the color of a selected effect.
	 */
	public static ArrayList<String> effectcolor = new ArrayList<String>();
	/**
	 * Arraylist for the saves GUI.
	 */
	public static ArrayList<String> saves = new ArrayList<String>();
	/**
	 * Arraylist for the basecolor GUI. (this is for the banner if it already has a pattern on it)
	 */
	public static ArrayList<String> basecolor = new ArrayList<String>();
	
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
			sender.sendMessage(Error.NO_BANNER_IN_HAND.sendError());
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
			sender.sendMessage(Error.NOT_BANNER_MAKER.sendError());
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
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#getPreview(org.bukkit.inventory.meta.ItemMeta, org.bukkit.inventory.ItemStack)
	 */
	public ItemStack getPreview(ItemMeta m, ItemStack s) {
		ItemStack stack = new ItemStack(Material.BANNER, 1, s.getDurability());
		stack.setItemMeta(m);
		return stack;
	}
	
	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#clearLayer(org.bukkit.inventory.ItemStack, int)
	 */
	public ItemStack clearLayer(ItemStack banner, int layer) {
		short d = banner.getDurability();
		ItemStack stack = new ItemStack(Material.BANNER, 1);
		BannerMeta m = (BannerMeta)banner.getItemMeta();
		m.removePattern(layer);
		stack.setItemMeta(m);
		stack.setDurability(d);
		return stack;
	}
	
	/* (non-Javadoc)
	 * @see com.njdaeger.essentials.bannermanager.utils.IBannerHandler#getDyeColor(short)
	 */
	public DyeColor getDyeColor(short d) {
		if (d == 15) {
			return DyeColor.WHITE;
		}
		if (d == 14) {
			return DyeColor.ORANGE;
		}
		if (d == 13) {
			return DyeColor.MAGENTA;
		}
		if (d == 12) {
			return DyeColor.LIGHT_BLUE;
		}
		if (d == 11) {
			return DyeColor.YELLOW;
		}
		if (d == 10) {
			return DyeColor.LIME;
		}
		if (d == 9) {
			return DyeColor.PINK;
		}
		if (d == 8) {
			return DyeColor.GRAY;
		}
		if (d == 7) {
			return DyeColor.SILVER;
		}
		if (d == 6) {
			return DyeColor.CYAN;
		}
		if (d == 5) {
			return DyeColor.PURPLE;
		}
		if (d == 4) {
			return DyeColor.BLUE;
		}
		if (d == 3) {
			return DyeColor.BROWN;
		}
		if (d == 2) {
			return DyeColor.GREEN;
		}
		if (d == 1) {
			return DyeColor.RED;
		}
		if (d == 0) {
			return DyeColor.BLACK;
		} 
		else try {
			throw new MetadataException();
		} 
		catch (MetadataException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ItemStack item(String customName, Material material, short data) {
		ItemStack stack = new ItemStack(material, 1, data);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(customName);
		stack.setItemMeta(meta);
		return stack;
	}
	public ItemStack setBanner(String customname, ItemStack stack, PatternType type) {
		DyeColor pColor;
		short d = stack.getDurability();
		pColor = DyeColor.WHITE;
		if (d == 15 || d == 14 || d == 13 || d == 12 || d == 11 || d == 10 || d == 9 || d == 7 || d == 6 || d == 5 || d == 4 || d ==3 || d == 2 | d == 1) {
			pColor = DyeColor.BLACK;
		}
		ItemStack s = new ItemStack(Material.BANNER, 1);
		BannerMeta m = (BannerMeta)s.getItemMeta();
		m.setDisplayName(customname);
		m.addPattern(new Pattern(pColor, type));
		s.setItemMeta(m);
		s.setDurability(stack.getDurability());
		return s;
		
	}
}
