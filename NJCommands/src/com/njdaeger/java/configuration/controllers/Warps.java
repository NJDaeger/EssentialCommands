package com.njdaeger.java.configuration.controllers;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.data.WarpData;

public class Warps {

	public static String warp;
	public static Player player;

	public static WarpData getWarp(String warp, Player player) {
		Warps.warp = warp;
		Warps.player = player;
		return new WarpData();
	}
}
