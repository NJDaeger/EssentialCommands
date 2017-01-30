package com.njdaeger.api.json.data;

import java.io.File;

import org.bukkit.plugin.Plugin;

import com.google.gson.JsonObject;
import com.njdaeger.api.json.JsonAPI;
import com.njdaeger.api.json.interfaces.IJsonData;

public final class JsonHandler extends JsonAPI implements IJsonData {

	private String name;
	private String path;
	private Plugin plugin;

	public JsonHandler(String name) {
		super(getPlugin());
		this.plugin = getPlugin();
		this.name = name;
	}

	public JsonHandler(String name, String path) {
		super(getPlugin());
		this.plugin = getPlugin();
		this.name = name;
		this.path = path;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if (path == null) {
			File file = new File("plugins" + File.separator + plugin.getName() + File.separator + this.name + ".json");
			File renamed = new File("plugins" + File.separator + plugin.getName() + File.separator + name + ".json");
			file.renameTo(renamed);
		}
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPath(String path) {

	}

	@Override
	public JsonObject getJsonObj() {
		// TODO Auto-generated method stub
		return null;
	}

}
