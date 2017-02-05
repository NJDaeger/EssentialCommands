package com.njdaeger.api.json.interfaces;

import java.util.List;
import java.util.Set;

public interface IJsonControllers {

	boolean contains(String path);

	Object get(String path);

	Object get(String path, Object def);

	boolean getBoolean(String path);

	boolean getBoolean(String path, boolean def);

	String getCurrentPath();

	double getDouble(String path);

	double getDouble(String path, double def);

	int getInt(String path);

	int getInt(String path, int def);

	Set<String> getKeys(boolean deep);

	List<?> getList(String path);

	List<?> getList(String path, List<?> def);

	long getLong(String path);

	long getLong(String path, long def);

	String getName();

	String getString(String path);

	String getString(String path, String def);

	List<String> getStringList(String path);

	boolean isBoolean(String path);

	boolean isDouble(String path);

	boolean isInt(String path);

	boolean isList(String path);

	boolean isLong(String path);

	boolean isSet(String path);

	boolean isString(String path);

	IJsonControllers set(String path, Object value);

	/**
	 * Set the value of a key in a JSON file.
	 * 
	 * @param key The key to create.
	 * @param value The value to set the key to.
	 * @return JsonHandler set(String key, Object value);
	 * 
	 *         /** Gets a boolean from a key in a JSON file.
	 * 
	 * @param key Key to get the boolean from.
	 * @return Returns the boolean value. boolean getBoolean(String key);
	 * 
	 *         /** Get a String from a key in a JSON file.
	 * 
	 * @param key Key to get the String from.
	 * @return Returns the string value. String getString(String key);
	 * 
	 *         /** Get an Object from a key in a JSON file.
	 * 
	 * @param key Key to get the Object from.
	 * @return Returns the Object value. Object getObject(String key);
	 */

}
