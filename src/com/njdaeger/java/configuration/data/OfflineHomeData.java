package com.njdaeger.java.configuration.data;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Warnings;
import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.configuration.exceptions.homes.HomeNotFound;
import com.njdaeger.java.configuration.interfaces.IOfflineHome;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;
import com.njdaeger.java.essentials.enums.Error;

public class OfflineHomeData extends Homes implements IValues, ISetValues, IOfflineHome {

	String offpl = Database.getDatabase("playerdata").getEntry(offlinetarget);
	File dir = new File("plugins" + File.separator + "EssentialCommands" + File.separator + "users" + File.separator
			+ offpl + File.separator + "homes");
	File homes = new File(dir + File.separator + home + ".yml");

	@Override
	public void remove() {
		if (homes.exists()) {
			homes.delete();
			return;
		} else
			try {
				throw new HomeNotFound();
			} catch (HomeNotFound e) {
				Warnings.warn("The home \"" + home + "\" could not be deleted.", new HomeNotFound(), true);
			}
	}

	@Override
	public String listHomes() {
		if (dir.exists()) {
			if (dir.list() == null) {
				return new HomeNotFound().getMessage();
			}
			String[] homes = dir.list();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < homes.length; i++) {
				sb.append(homes[i]).append(" ");
			}
			String message = sb.toString().trim();
			String finalmsg = message.replace(".yml", "");
			String wcommas = finalmsg.replaceAll(" ", ", ");
			return wcommas;
		}
		return new HomeNotFound().getMessage();
	}

	@Override
	public void sendOtherHome(Player target) {
		if (homes.exists()) {
			World world = Bukkit.getWorld(getWorld());
			if (world == null) {
				target.sendMessage(Error.WORLD_NOT_FOUND.sendError());
				return;
			}
			Location location = new Location(world, getX(), getY(), getZ(), getYaw(), getPitch());
			target.teleport(location);
			return;
		}
		return;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getYaw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setX(double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setZ(double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setYaw(float value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPitch(float value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWorld(String value) {
		// TODO Auto-generated method stub

	}
}
