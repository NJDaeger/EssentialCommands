package com.njdaeger.java.configuration.data;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.Homes;
import com.njdaeger.java.configuration.interfaces.IHomeHandler;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;

public class HomeData extends Homes implements IValues, IHomeHandler, ISetValues {

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public String listHomes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendHome() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendOtherHome(Player target) {
		// TODO Auto-generated method stub

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