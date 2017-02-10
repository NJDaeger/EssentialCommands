package com.njdaeger.java.wrapper;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class Sender implements CommandSender {

	private CommandSender sender;

	public Sender(CommandSender sender) {
		this.sender = sender;
	}

	public boolean isPlayer() {
		if (sender instanceof Player) {
			return true;
		}
		return false;
	}

	public Player asPlayer() {
		if (isPlayer()) {
			return (Player) sender;
		}
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		return sender.addAttachment(plugin);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
		return sender.addAttachment(plugin, ticks);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
		return sender.addAttachment(plugin, name, value);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
		return sender.addAttachment(plugin, name, value, ticks);
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return sender.getEffectivePermissions();
	}

	@Override
	public boolean hasPermission(String name) {
		return sender.hasPermission(name);
	}

	@Override
	public boolean hasPermission(Permission perm) {
		return sender.hasPermission(perm);
	}

	@Override
	public boolean isPermissionSet(String name) {
		return sender.isPermissionSet(name);
	}

	@Override
	public boolean isPermissionSet(Permission perm) {
		return isPermissionSet(perm);
	}

	@Override
	public void recalculatePermissions() {
		sender.recalculatePermissions();
	}

	@Override
	public void removeAttachment(PermissionAttachment attachment) {
		sender.removeAttachment(attachment);
	}

	@Override
	public boolean isOp() {
		return sender.isOp();
	}

	@Override
	public void setOp(boolean value) {
		sender.setOp(value);
	}

	@Override
	public String getName() {
		return sender.getName();
	}

	@Override
	public Server getServer() {
		return sender.getServer();
	}

	@Override
	public void sendMessage(String message) {
		sender.sendMessage(message);
	}

	@Override
	public void sendMessage(String[] messages) {
		sender.sendMessage(messages);
	}

}