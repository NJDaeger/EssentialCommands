package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class EditsignCommand extends BukkitCommand{

	public EditsignCommand() {
		super("editsign");
		List<String> a = Arrays.asList("editsign", "edittext", "es", "edit");
		this.description = "Edit a placed sign.";
		this.usageMessage = "/editsign <line> [message]";
		this.setAliases(a);
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (sndr instanceof Player) {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_EDITSIGN)) {
				if (args.length < 1) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				else {
					HashSet<Material> tran = new HashSet<Material>(); 
					tran.add(Material.AIR);
					Block type = player.getTargetBlock(tran, 100);
					if (type.getType().equals(Material.WALL_SIGN) || type.getType().equals(Material.SIGN_POST)) {
						if (args[0].equalsIgnoreCase("line1") || args[0].equals("1")) {
							if (args.length == 1) {
								Sign sign = (Sign) type.getState();
								sign.setLine(0, "");
								sign.update();
								return true;
							}
							else {
								Sign sign = (Sign) type.getState();
								sign.setLine(0, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
								sign.update();
								return true;
							}
						}
						if (args[0].equalsIgnoreCase("line2") || args[0].equals("2")) {
							if (args.length == 1) {
								Sign sign = (Sign) type.getState();
								sign.setLine(1, "");
								sign.update();
								return true;
							}
							else {
								Sign sign = (Sign) type.getState();
								sign.setLine(1, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
								sign.update();
								return true;
							}						
						}
						if (args[0].equalsIgnoreCase("line3") || args[0].equals("3")) {
							if (args.length == 1) {
								Sign sign = (Sign) type.getState();
								sign.setLine(2, "");
								sign.update();
								return true;
							}
							else {
								Sign sign = (Sign) type.getState();
								sign.setLine(2, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
								sign.update();
								return true;
							}
						}
						if (args[0].equalsIgnoreCase("line4") || args[0].equals("4")) {
							if (args.length == 1) {
								Sign sign = (Sign) type.getState();
								sign.setLine(3, "");
								sign.update();
								return true;
							}
							else {
								Sign sign = (Sign) type.getState();
								sign.setLine(3, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
								sign.update();
								return true;
							}
						}
						else {
							sndr.sendMessage(Error.LINE_NUMBER_INVALID.sendError());
							return true;
						}
					}
					else {
						sndr.sendMessage(Error.TARGET_NOT_SIGN.sendError());
						return true;
					}
				}
			}
			else {
				sndr.sendMessage(Error.NO_PERMISSION.sendError());
				return true;
			}
		}
		else {
			sndr.sendMessage(Error.PLAYER_ONLY.sendError());
			return true;
		}
	}
	private String setSign(String[] args) {
		String msg = "";
		for (String message : args) {
			msg = (msg + message + " ");
			StringBuilder builder = new StringBuilder();
			for(int i = 1; i < args.length; i++) builder.append(args[i]).append(' ');
			String reason = builder.toString();
			return reason;
		}
		return null;
	}
}
