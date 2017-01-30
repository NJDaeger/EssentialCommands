package com.njdaeger.api.json.data;

import java.io.File;

import org.apache.commons.lang.Validate;

import com.google.gson.JsonObject;
import com.njdaeger.api.json.JsonAPI;
import com.njdaeger.api.json.interfaces.IJsonData;

public final class JsonHandler extends JsonAPI implements IJsonData {

	private String name;
	private String path;

	/**
	 * New instance of JsonHandler.
	 * 
	 * @param name The name of the JSON file. Note: Do not include extension.
	 *            <li>Good: file
	 *            <li>Bad: file.json
	 * 
	 */
	public JsonHandler(String name) {
		super(getPlugin());
		this.path = "plugins" + File.separator + getPlugin().getName();
		this.name = name;
	}

	/**
	 * New instance of JsonHandler.
	 * 
	 * @param name The name of the JSON file. Note: Do not include extension.
	 *            <li>Good: file
	 *            <li>Bad: file.json
	 * @param path The path of the JSON file. Note: Only go up to the folder its
	 *            in
	 *            <li>Good: plugins/ExamplePlugin/
	 *            <li>Bad: plugins/ExamplePlugin/file.json
	 */
	public JsonHandler(String name, String path) {
		super(getPlugin());
		this.name = name;
		this.path = path;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		Validate.notNull(name, "Name cannot be null.");
		File file = new File(this.path + File.separator + this.name + ".json");
		File renamed = new File(this.path + File.separator + name + ".json");
		file.renameTo(renamed);
		file.delete();
	}

	@Override
	public String getPath() {

		return path;
	}

	@Override
	public void setPath(String path) {
		Validate.notNull(path, "Path cannot be null.");
		File file = new File(this.path + File.separator + this.name + ".json");
		File moved = new File(path + File.separator + this.name + ".json");
		file.renameTo(moved);
		file.delete();

	}

	@Override
	public JsonObject getJsonObj() {
		// TODO Auto-generated method stub
		return null;
	}

}
