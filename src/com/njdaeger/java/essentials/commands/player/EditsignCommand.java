package com.njdaeger.java.essentials.commands.player;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.njdaeger.java.command.util.Cmd;
import com.njdaeger.java.command.util.EssCommand;
import com.njdaeger.java.command.util.Executor;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;
import com.njdaeger.java.wrapper.Sender;

import net.md_5.bungee.api.ChatColor;

public class EditsignCommand extends EssCommand {

	@Override
	@Cmd(
			name = "editsign",
			desc = "Edit a placed sign.",
			usage = "/editsign <line> [message]",
			min = 1,
			executor = Executor.PLAYER,
			aliases = { "editsign", "edittext", "es", "edit" },
			permissions = { Permission.ESS_EDITSIGN })
	public boolean run(Sender sndr, String label, String[] args) {
		HashSet<Material> tran = new HashSet<Material>();
		tran.add(Material.AIR);
		Block type = ((Player) sndr).getTargetBlock(tran, 100);
		if (type.getType().equals(Material.WALL_SIGN) || type.getType().equals(Material.SIGN_POST)) {
			Line line = Line.getAliasUsed(args[0]);
			Sign sign = (Sign) type.getState();
			switch (line) {
			case ONE:
				if (args.length == 1) {
					sign.setLine(0, "");
					sign.update();
					return true;
				}
				sign.setLine(0, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return true;
			case TWO:
				if (args.length == 1) {
					sign.setLine(1, "");
					sign.update();
					return true;
				}
				sign.setLine(1, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return true;
			case THREE:
				if (args.length == 1) {
					sign.setLine(2, "");
					sign.update();
					return true;
				}
				sign.setLine(2, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return true;
			case FOUR:
				if (args.length == 1) {
					sign.setLine(3, "");
					sign.update();
					return true;
				}
				sign.setLine(3, ChatColor.translateAlternateColorCodes('&', this.setSign(args)));
				sign.update();
				return true;
			default:
				sndr.sendMessage(Error.LINE_NUMBER_INVALID.sendError());
				return true;
			}
		}
		sndr.sendMessage(Error.TARGET_NOT_SIGN.sendError());
		return true;
	}

	private String setSign(String[] args) {
		String msg = "";
		for (String message : args) {
			msg = (msg + message + " ");
			StringBuilder builder = new StringBuilder();
			for (int i = 1; i < args.length; i++)
				builder.append(args[i]).append(' ');
			String reason = builder.toString();
			return reason;
		}
		return null;
	}

	public enum Line {
		ONE("line1", "1", "one"), TWO("line2", "2", "two"), THREE("line3", "3", "three"), FOUR("line4", "4", "four");

		String[] names;

		Line(String... name) {
			this.names = name;
		}

		public String[] getAlias() {
			return names;
		}

		public static Line getAliasUsed(String input) {
			for (Line alias : Line.values()) {
				for (String value : alias.getAlias()) {
					if (value.equals(input)) {
						return alias;
					}
				}
			}
			return null;
		}
	}
}
