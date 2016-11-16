package com.njdaeger.essentials;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.inventory.ItemStack;
public class Plugin {
	/*
	 * Any reflection that is needed will be done in here.
	 */
	public static void getCommand(String command, Command getCommand) {
		try {
			Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			f.setAccessible(true);
			CommandMap map = (CommandMap) f.get(Bukkit.getServer());
			map.register(command, getCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Material getItem(String itemID) {
		try {
			Class<?> minecraftKey = Plugin.getNMSClass("MinecraftKey");
			Class<?> craftItem = Plugin.getCBClass("inventory.CraftItemStack");
			Class<?> regitem = Plugin.getNMSClass("Item");
			Class<?> regmat = Plugin.getNMSClass("RegistryMaterials");
			Object key = minecraftKey.getConstructor(String.class).newInstance(itemID);
			
			Field field = regitem.getDeclaredField("REGISTRY");
			field.setAccessible(true);
			Object reg = field.get(null);
			
			Method get = regmat.getMethod("get", Object.class);
			Object item = get.invoke(reg, key);
			
			
			Method asNew = craftItem.getMethod("asNewCraftStack", regitem);
			ItemStack stack = (ItemStack) asNew.invoke(null, item);
			return stack.getType();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Class<?> getNMSClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Class<?> getCBClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
