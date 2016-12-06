package com.njdaeger.java.essentials.commands.player;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Error;
import com.njdaeger.java.essentials.enums.Permission;

public class GamemodeCommand extends BukkitCommand{
	
	public GamemodeCommand() {
		super("gamemode");
		List<String> a = Arrays.asList("gm", "mode");
		this.description = "Switch between gamemodes.";
		this.usageMessage = "/gamemode <creative/survivial/adventure/spectator> [player]";
		this.setAliases(a);
		
		
	}

	@Override
	public boolean execute(CommandSender sndr, String label, String[] args) {
		if (!(sndr instanceof Player)) {
			
			if (args.length == 0) {
				sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
				return true;
			}
			if (args.length > 2) {
				sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
				return true;
			}
			Player other = Bukkit.getPlayer(args[1]);
			if (other == null) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			if (!args[1].equalsIgnoreCase(Bukkit.getPlayer(args[1]).getName())) {
				sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
				return true;
			}
			
			
			//CRATIVE
			if (args[0].equalsIgnoreCase("creative") || args[0].equals("1")) {
				if (other.getGameMode().equals(GameMode.CREATIVE)) {
					sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
					return true;
				}
				else {
					other.setGameMode(GameMode.CREATIVE);
					sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
					other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
					return true;
				}
			}
			
			
			//SURVIVAL
			if (args[0].equalsIgnoreCase("survival") || args[0].equals("0")) {
				if (other.getGameMode().equals(GameMode.SURVIVAL)) {
					sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
					return true;
				}
				else {
					other.setGameMode(GameMode.SURVIVAL);
					sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
					other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
					return true;
				}
			}
			
			
			//ADVENTURE
			if (args[0].equalsIgnoreCase("adventure") || args[0].equals("2")) {
				if (other.getGameMode().equals(GameMode.ADVENTURE)) {
					sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
					return true;
				}
				else {
					other.setGameMode(GameMode.ADVENTURE);
					sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
					other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
					return true;
				}
			}
			
			
			//SPECTATOR
			if (args[0].equalsIgnoreCase("spectator") || args[0].equals("3")) {
				if (other.getGameMode().equals(GameMode.SPECTATOR)) {
					sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
					return true;
				}
				else {
					other.setGameMode(GameMode.SPECTATOR);
					sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
					other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
					return true;
				}
			}
			else sndr.sendMessage(Error.UNKNOWN_GAMEMODE.sendError());
			return true;
		}
		else {
			Player player = (Player) sndr;
			if (Holder.hasPermission(player, Permission.ESS_GAMEMODE, Permission.ESS_GAMEMODE_OTHER)) {
				if (args.length == 0) {
					sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
					return true;
				}
				if (args.length > 2) {
					sndr.sendMessage(Error.TOO_MANY_ARGS.sendError());
					return true;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("creative") || args[0].equals("1")) {
						if (player.getGameMode().equals(GameMode.CREATIVE)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							player.setGameMode(GameMode.CREATIVE);
							sndr.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("survival") || args[0].equals("0")) {
						if (player.getGameMode().equals(GameMode.SURVIVAL)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							player.setGameMode(GameMode.SURVIVAL);
							sndr.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("adventure") || args[0].equals("2")) {
						if (player.getGameMode().equals(GameMode.ADVENTURE)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							player.setGameMode(GameMode.ADVENTURE);
							sndr.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("spectator") || args[0].equals("3")) {
						if (player.getGameMode().equals(GameMode.SPECTATOR)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							player.setGameMode(GameMode.SPECTATOR);
							sndr.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					else sndr.sendMessage(Error.UNKNOWN_GAMEMODE.sendError());
					return true;
				}
				if (args.length == 2) {
					Player other = Bukkit.getPlayer(args[1]);
					if (!sndr.hasPermission(Permission.ESS_GAMEMODE_OTHER.getPermission())) {
						sndr.sendMessage(Error.NO_PERMISSION.sendError());
						return true;
					}
					if (other == null) {
						sndr.sendMessage(Error.UNKNOWN_PLAYER.sendError());
						return true;
					}
					if (args[0].equalsIgnoreCase("creative") || args[0].equals("1")) {
						if (other.getGameMode().equals(GameMode.CREATIVE)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							other.setGameMode(GameMode.CREATIVE);
							sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
							other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("survival") || args[0].equals("0")) {
						if (other.getGameMode().equals(GameMode.SURVIVAL)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							other.setGameMode(GameMode.SURVIVAL);
							sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
							other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("adventure") || args[0].equals("2")) {
						if (other.getGameMode().equals(GameMode.ADVENTURE)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							other.setGameMode(GameMode.ADVENTURE);
							sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
							other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("spectator") || args[0].equals("3")) {
						if (other.getGameMode().equals(GameMode.SPECTATOR)) {
							sndr.sendMessage(Error.ALREADY_GAMEMODE_P.sendError());
							return true;
						}
						else {
							other.setGameMode(GameMode.SPECTATOR);
							sndr.sendMessage(ChatColor.GRAY + "You changed " + args[1] + "'s gamemode to " + ChatColor.GREEN + getGamemodeString(args[0]));
							other.sendMessage(ChatColor.GRAY + "Your gamemode has been changed too " + ChatColor.GREEN + getGamemodeString(args[0]));
							return true;
						}
					}
					else sndr.sendMessage(Error.UNKNOWN_GAMEMODE.sendError());
					return true;
				}
				else sndr.sendMessage(Error.NO_PERMISSION.sendError()); 
				return true;
			}
			else sndr.sendMessage(Error.NOT_ENOUGH_ARGS.sendError());
			return true;
		}
	}
	public GameMode getGamemode(String gamemode) {
		if (gamemode.equalsIgnoreCase("creative") || gamemode.equals("1")) {
			return GameMode.CREATIVE;
		}
		else if (gamemode.equalsIgnoreCase("survival") || gamemode.equals("0")) {
			return GameMode.SURVIVAL;
		}
		else if (gamemode.equalsIgnoreCase("adventure") || gamemode.equals("2")) {
			return GameMode.ADVENTURE;
		}
		else if (gamemode.equalsIgnoreCase("spectator") || gamemode.equals("3")) {
			return GameMode.SPECTATOR;
		}
		return null;
	}
	public String getGamemodeString(String gamemode) {
		if (gamemode.equalsIgnoreCase("creative") || gamemode.equals("1")) {
			return "Creative";
		}
		else if (gamemode.equalsIgnoreCase("survival") || gamemode.equals("0")) {
			return "Survival";
		}
		else if (gamemode.equalsIgnoreCase("adventure") || gamemode.equals("2")) {
			return "Adventure";
		}
		else if (gamemode.equalsIgnoreCase("spectator") || gamemode.equals("3")) {
			return "Spectator";
		}
		return null;
	}
}
