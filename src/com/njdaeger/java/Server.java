package com.njdaeger.java;

import java.lang.management.ManagementFactory;

import org.bukkit.Bukkit;

import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("restriction")
public class Server{
	
	public static Object getCPULoad() {
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		double l = os.getProcessCpuLoad();
		double a = l * Math.pow(10, 2);
		double[] values = {a};
		for (double v : values) 
			return round(v);
		return null;
	}
	public static double round(double number) {
		return (long) (number * 1e2) / 1e2;
	}
	public static int getCPU() {
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		return os.getAvailableProcessors();
	}
	public static String getArchitecture() {
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		return os.getArch();
	}
	public static String getOS() {
		return System.getProperty("os.name");
	}
	public static long getFreeMemory() {
		return Runtime.getRuntime().freeMemory() / 1024 / 1024;
	}
	public static long getMaxMemory() {
		return Runtime.getRuntime().maxMemory() / 1024 / 1024;
	}
	public static long getAllocatedMemory() {
		return Runtime.getRuntime().totalMemory() / 1024 / 1024;
	}
	public static int getPort() {
		return Bukkit.getServer().getPort();
	}
	public static String getTPS() {
		return TPS.getTPS();
	}
	public static String getName() {
		return Bukkit.getServer().getServerName();
	}
}
