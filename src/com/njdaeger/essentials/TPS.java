package com.njdaeger.essentials;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

public class TPS {
	
	private static final DecimalFormat format = new DecimalFormat("##.##");
	private static Object server;
	private static Field tpsField;
	
	public static void getTPSClass() {
			try {
				server = Plugin.getNMSClass("MinecraftServer").getMethod("getServer").invoke(null);
				tpsField = server.getClass().getField("recentTps");
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
	}

	
	public static String getTPS() { 
		int time = 1;
		double[] tps;
		try {
			tps = ((double[]) tpsField.get(server));
			return format.format(tps[time]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
		
	}
} 
