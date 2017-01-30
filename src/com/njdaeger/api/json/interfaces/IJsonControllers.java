package com.njdaeger.api.json.interfaces;

import com.njdaeger.api.json.data.JsonHandler;

public interface IJsonControllers {

	/**
	 * Set the value of a key in a JSON file.
	 * 
	 * @param key The key to create.
	 * @param value The value to set the key to.
	 * @return
	 */
	JsonHandler set(String key, Object value);

	/**
	 * Gets a boolean from a key in a JSON file.
	 * 
	 * @param key Key to get the boolean from.
	 * @return Returns the boolean value.
	 */
	boolean getBoolean(String key);

	/**
	 * Get a String from a key in a JSON file.
	 * 
	 * @param key Key to get the String from.
	 * @return Returns the string value.
	 */
	String getString(String key);

	/**
	 * Get an Object from a key in a JSON file.
	 * 
	 * @param key Key to get the Object from.
	 * @return Returns the Object value.
	 */
	Object getObject(String key);

}
