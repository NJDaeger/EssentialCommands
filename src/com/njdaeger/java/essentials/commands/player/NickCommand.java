package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class NickCommand extends EssCommand {
	
	static String name = "nick";
	
	public NickCommand() {
		super("nick");
		List<String> a = Arrays.asList("nickname", "newname", "disguise");
		this.description = "Mark yourself as away from keyboard.";
		this.usageMessage = "/nick <Nickname> [Player]";
		this.setAliases(a);
	}
	
	@Override
	public void register() {
		Plugin.getCommand(name, this);
	}
	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_NICK, Permission.ESS_NICK_OTHER)) {
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		if (args.length > 2) {
			sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
			return true;
		}
		if (args.length == 0) {
			sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
		if (args[0].length() > 30) {
			sndr.sendMessage(Error.NICKNAME_TOO_LONG.sendError());
			return true;
		}
		if (args.length == 1) {
			if (sndr instanceof Player) {
				if (((Player) sndr).getDisplayName() == sndr.getName()) {
					//This will go down past the next if statement to check the length of the name and set their name if the checks pass.
					//TODO add ability to adjust how long the nickname name can be in the essentials configuration.
				}
				if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
					PlayerConfig.getConfig((Player)sndr).setNick(sndr.getName());
					sndr.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
					return true;
				}
				PlayerConfig.getConfig((Player)sndr).setNick(args[0]);
				sndr.sendMessage(ChatColor.GRAY + "Your nickname is now \"" + this.getNick(args[0]) + ChatColor.GRAY + "\".");
				return true;
			}
			else {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
		}
		if (sndr instanceof Player) {
			if (Holder.hasPermission((Player) sndr, Permission.ESS_NICK_OTHER)) {
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
			return true;
		}
		if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
			PlayerConfig.getConfig(target).setNick(target.getName());
			target.sendMessage(ChatColor.GRAY + "You no longer have a nickname.");
			sndr.sendMessage(ChatColor.GRAY + "You removed " + target.getDisplayName() + ChatColor.GRAY + "'s nickname.");
			return true;
		}
		PlayerConfig.getConfig(target).setNick(args[0]);
		target.sendMessage(ChatColor.GRAY + "Your nickname is now \"" + this.getNick(args[0]) + ChatColor.GRAY + "\".");
		sndr.sendMessage(ChatColor.GRAY + "You changed " + target.getName() + ChatColor.GRAY + "'s nickname to \"" + this.getNick(args[0]) + ChatColor.GRAY + "\".");
		return true;
	}
	public String getNick(String nick) {
		if (nick.contains("&")) {
			return ChatColor.translateAlternateColorCodes('&', nick);
		}
		else return ChatColor.GREEN + nick;
	}
}
