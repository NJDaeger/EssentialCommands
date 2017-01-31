package com.njdaeger.api.json.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.njdaeger.api.json.JsonAPI;
import com.njdaeger.api.json.interfaces.IJsonControllers;
import com.njdaeger.api.json.interfaces.IJsonData;

public final class JsonHandler extends JsonAPI implements IJsonData, IJsonControllers {

	/**
	 * Name of the JSON file.
	 */
	private String name;
	/**
	 * Path of the JSON file.
	 */
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

	@Override
	public boolean contains(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String path, Object def) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getBoolean(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(String path, boolean def) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCurrentPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDouble(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String path, double def) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String path, int def) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<String> getKeys(boolean deep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getList(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getList(String path, List<?> def) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLong(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String path, long def) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getString(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String path, String def) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getStringList(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBoolean(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDouble(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInt(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isList(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLong(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSet(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isString(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void set(String key, Object value) {
		File file = new File(path + File.separator + name + ".json");
		JSONObject o = new JSONObject();
		JsonParser parser = new JsonParser();
		JSONParser parser2 = new JSONParser();
		o.put(key, value);
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(o.toJSONString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
