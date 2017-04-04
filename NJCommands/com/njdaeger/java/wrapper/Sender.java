package com.njdaeger.java.wrapper;

import org.bukkit.Server;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import com.njdaeger.java.Core;
import com.njdaeger.java.Holder;
import com.njdaeger.java.enums.Error;

public class Sender {

	private CommandSender sender;

	public Sender(CommandSender sender) {
		this.sender = sender;
	}

	public boolean isPlayer() {
		return (sender instanceof Player);
	}

	public Player asPlayer() {
		if (isPlayer()) {
			return (Player) sender;
		}
		return null;
	}

	public boolean isBlock() {
		return (sender instanceof BlockCommandSender);
	}

	public BlockCommandSender asBlock() {
		if (isBlock()) {
			return (BlockCommandSender) sender;
		}
		return null;
	}

	public boolean isConsole() {
		return (sender instanceof ConsoleCommandSender);
	}

	public ConsoleCommandSender asConsole() {
		if (isConsole()) {
			return (ConsoleCommandSender) sender;
		}
		return null;
	}

	public CommandSender asCommandSender() {
		return sender;
	}

	public boolean isUser() {
		return isPlayer();
	}

	public User asUser() {
		if (isUser()) {
			return Core.getUser(asPlayer());
		}
		return null;
	}

	public void sendError(Error error) {
		sender.sendMessage(error.sendError());
	}

	public boolean isOp() {
		return sender.isOp();
	}

	public void setOp(boolean value) {
		sender.setOp(value);
	}

	public String getName() {
		return sender.getName();
	}

	public Server getServer() {
		return sender.getServer();
	}

	public void sendMessage(String message) {
		sender.sendMessage(message);
	}

	public void sendMessage(String[] messages) {
		sender.sendMessage(messages);
	}

	public boolean hasPermission(String permission) {
		return sender.hasPermission(permission);
	}

	public boolean hasPermission(Permission permission) {
		return sender.hasPermission(permission);
	}

	public boolean hasPermission(com.njdaeger.java.enums.Permission... permission) {
		return Holder.hasPermission(sender, permission);
	}
}