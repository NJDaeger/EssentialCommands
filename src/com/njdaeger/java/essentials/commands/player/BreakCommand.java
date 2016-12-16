package com.njdaeger.java.essentials.commands.player;

import java.util.HashSet;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class BreakCommand extends BukkitCommand {
	
	private ChatColor green = ChatColor.GREEN;
	private ChatColor gray = ChatColor.GRAY;
	
	public BreakCommand() {
		super("break");
		this.description = "Break the block you are looking at.";
		this.usageMessage = "/break";
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (!(sndr instanceof Player)) {
			sndr.sendMessage(Error.PLAYER_ONLY.sendError());
			return true;
		}
		Player player = (Player) sndr;
		if (Holder.hasPermission(player, Permission.ESS_BREAK)) {
			if (args.length > 0) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			HashSet<Material> tran = new HashSet<Material>(); 
			tran.add(Material.AIR);
			Block block = player.getTargetBlock(tran, 100).getLocation().getBlock();
			sndr.sendMessage(gray + "You broke block " + green + player.getTargetBlock(tran, 100).getType().toString().toLowerCase() +
					gray + " at x:" + green + block.getX() + 
					gray + " y:" + green + block.getY() + 
					gray + " z:" + green + block.getZ() + 
					gray + ".");;
			
			block.setType(Material.AIR);
			return true;
		}
		else {
			sndr.sendMessage(Error.NO_PERMISSION.sendError());
			return true;
		}
	}
}
