package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.Plugin;
import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.configuration.Parser;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.data.PlayerConfigData;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class VanishCommand extends EssCommand {

	public VanishCommand() {
		super("vanish");
		List<String> a = Arrays.asList("v", "hide");
		this.description = "Hide yourself from others";
		this.usageMessage = "/vanish [player]";
		this.setAliases(a);
	}

	@Override
	public void register() {
		Plugin.getCommand(this);

	}

	@Cmd(max = 1, permissions = { Permission.ESS_VANISH, Permission.ESS_VANISH_OTHER })
	@Override
	public boolean execute(CommandSender sndr, String commandLabel, String[] args) {
		if (canceled(sndr, args)) {
			return true;
		}
		if (args.length == 0) {
			if (!(sndr instanceof Player)) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			PlayerConfigData config = PlayerConfig.getConfig((Player) sndr);
			config.setHidden();
			if (config.isHidden()) {
				sndr.sendMessage(ChatColor.GRAY + "You are now hidden.");
				return true;
			}
			sndr.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
			return true;
		}
		if (Holder.hasPermission(sndr, Permission.ESS_VANISH_OTHER)) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			PlayerConfigData config = PlayerConfig.getConfig(target);
			config.setHidden();
			if (config.isHidden()) {
				sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is now hidden.");
				target.sendMessage(ChatColor.GRAY + "You are now hidden.");
				return true;
			}
			sndr.sendMessage(ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY + " is no longer hidden.");
			target.sendMessage(ChatColor.GRAY + "You are no longer hidden.");
			return true;
		}
		sndr.sendMessage(Parser.parse(Error.NO_PERMISSION.getError(), (Player) sndr, "Unknown",
				Permission.ESS_VANISH_OTHER));
		return true;
	}
}
