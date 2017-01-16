package com.njdaeger.java.essentials.commands.world;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.Server;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class ServerInfoCommand extends EssCommand {

	private ChatColor g = ChatColor.GRAY;
	private ChatColor a = ChatColor.AQUA;

	static String name = "serverinfo";

	public ServerInfoCommand() {
		super(name);
		List<String> alias = Arrays.asList("si", "server", "lag", "memory", "internal", "gc");
		this.description = "Get server information.";
		this.usageMessage = "/serverinfo";
		this.setAliases(alias);
	}

	@Override
	public void register() {
		Plugin.getCommand(name, this);

	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO, Permission.ESS_SERVER_INFO_ALCDRAM,
				Permission.ESS_SERVER_INFO_ALL, Permission.ESS_SERVER_INFO_ARCH, Permission.ESS_SERVER_INFO_CORES,
				Permission.ESS_SERVER_INFO_FREERAM, Permission.ESS_SERVER_INFO_MAXRAM, Permission.ESS_SERVER_INFO_OS,
				Permission.ESS_SERVER_INFO_PORT, Permission.ESS_SERVER_INFO_TPS, Permission.ESS_SERVER_INFO_USAGE))
			switch (args.length) {
			case 0:
				sndr.sendMessage(g + "=-=-=- " + a + "Server Information" + g + " -=-=-=");
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO)) {
					sndr.sendMessage(g + "Name: " + a + Server.getName());
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_TPS)) {
					sndr.sendMessage(g + "Server TPS: " + a + this.formatTps(Server.getTPS()));
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_ARCH)) {
					sndr.sendMessage(g + "Architecture: " + a + Server.getArchitecture());
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_OS)) {
					sndr.sendMessage(g + "Operating System: " + a + Server.getOS());
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_CORES)) {
					sndr.sendMessage(g + "CPU Cores: " + a + Server.getCPU());
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_USAGE)) {
					sndr.sendMessage(g + "CPU Usage: " + a + Server.getCPULoad());
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_MAXRAM)) {
					sndr.sendMessage(g + "Max RAM: " + a + Server.getMaxMemory() + "Mb");
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_FREERAM)) {
					sndr.sendMessage(g + "Free RAM: " + a + Server.getFreeMemory() + "Mb");
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_ALCDRAM)) {
					sndr.sendMessage(g + "Allocated RAM: " + a + Server.getAllocatedMemory() + "Mb");
				}
				if (Holder.hasPermission(sndr, Permission.ESS_SERVER_INFO_PORT)) {
					sndr.sendMessage(g + "Port Number: " + a + Server.getPort());
				}
				return true;
			default:
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
		sndr.sendMessage(Error.NO_PERMISSION.sendError());
		return true;
	}

	public String formatTps(String tps) {
		double ticks = Double.parseDouble(tps);
		if (ticks > 20) {
			return ChatColor.GREEN + "20";
		} else if (ticks >= 18) {
			return ChatColor.GREEN + tps;
		} else if (ticks >= 15) {
			return ChatColor.YELLOW + tps;
		} else
			return ChatColor.RED + tps;
	}
}
