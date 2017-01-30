package com.njdaeger.api.json;

import java.io.File;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;

import com.njdaeger.api.json.data.JsonHandler;
import com.njdaeger.api.json.interfaces.IJsonCreator;

public class JsonAPI implements IJsonCreator {

	private static Plugin plugin;

	public JsonAPI(Plugin plugin) {
		JsonAPI.plugin = plugin;
	}

	@Override
	public JsonHandler create(String name) {
		Validate.notNull(name, "Name cannot be null.");
		return new JsonHandler(name);
	}

	@Override
	public JsonHandler create(String path, String name) {
		Validate.notNull(name, "Name cannot be null.");
		Validate.notNull(path, "Path cannot be null.");
		return new JsonHandler(name, path);
	}

	@Override
	public void delete(String name) {
		Validate.notNull(name, "Name cannot be null.");
		File file = new File("plugins" + File.separator + plugin.getName() + File.separator + name + ".json");
		Validate.notNull(file, "Cannot delete file " + name + ".json... It does not exist.");
		file.delete();

	}

	@Override
	public void delete(String path, String name) {

	}

	/**
	 * Get a JSON Object from the default directory.
	 * 
	 * @param name Name of the file you want to get.
	 * @return Returns the JSON file.
	 */
	public JsonHandler getJsonObj(String name) {
		return null;
	}

	/**
	 * Get a JSON Object from the specified directory.
	 * 
	 * @param name Name of the file you want to get.
	 * @param path The path of the file you want to get.
	 * @return Returns the JSON file.
	 */
	public JsonHandler getJsonObj(String name, String path) {
		return null;
	}

	public static Plugin getPlugin() {
		return plugin;
	}
}
