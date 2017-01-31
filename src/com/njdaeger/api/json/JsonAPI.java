package com.njdaeger.api.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;

import com.google.gson.JsonObject;
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
		JsonObject object = new JsonObject();
		File jsonfile = new File("plugins" + File.separator + plugin.getName() + File.separator + name + ".json");
		try (FileWriter file = new FileWriter(jsonfile)) {
			file.write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		Validate.notNull(name, "Name cannot be null.");
		Validate.notNull(path, "Path cannot be null.");
		File file = new File(path + File.separator + name + ".json");
		Validate.notNull(file, "Cannot delete file " + name + ".json... It does not exist.");
		file.delete();
	}

	/**
	 * Get a JSON Object from the default directory.
	 * 
	 * @param name Name of the file you want to get. Note: Do not include
	 *            extension.
	 *            <li>Good: file
	 *            <li>Bad: file.json
	 * @return Returns the JSON file.
	 */
	public JsonHandler getJsonObj(String name) {
		return new JsonHandler(name);
	}

	/**
	 * Get a JSON Object from the specified directory.
	 * 
	 * @param name Name of the file you want to get. Note: do not include
	 *            extension.
	 *            <li>Good: file
	 *            <li>Bad: file.json
	 * @param path The path of the file you want to get. Note: Only go up to the
	 *            folder its in
	 *            <li>Good: plugins/ExamplePlugin/
	 *            <li>Bad: plugins/ExamplePlugin/file.json
	 * @return Returns the JSON file.
	 */
	public JsonHandler getJsonObj(String name, String path) {
		return new JsonHandler(name, path);
	}

	public static Plugin getPlugin() {
		return plugin;
	}
}
