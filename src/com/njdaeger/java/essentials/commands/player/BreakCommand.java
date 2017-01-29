package com.njdaeger.java.essentials.commands.player;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class BreakCommand extends EssCommand {

	private ChatColor green = ChatColor.GREEN;
	private ChatColor gray = ChatColor.GRAY;

	@Override
	@Cmd(
			name = "break",
			desc = "Break the block you are looking at.",
			usage = "/break",
			min = 0,
			max = 0,
			executor = Executor.PLAYER,
			permissions = { Permission.ESS_BREAK })
	public boolean run(Sender sndr, String label, String[] args) {
		Player player = (Player) sndr;
		HashSet<Material> tran = new HashSet<Material>();
		tran.add(Material.AIR);
		Block block = player.getTargetBlock(tran, 100).getLocation().getBlock();
		sndr.sendMessage(gray + "You broke block " + green + player.getTargetBlock(tran, 100).getType().toString()
				.toLowerCase() + gray + " at x:" + green + block.getX() + gray + " y:" + green + block.getY() + gray
				+ " z:" + green + block.getZ() + gray + ".");
		;

		block.setType(Material.AIR);
		return true;
	}
}
