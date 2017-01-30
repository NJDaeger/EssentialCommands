package com.njdaeger.api.json.interfaces;

import com.google.gson.JsonObject;

public interface IJsonData {

	/**
	 * Get the name of a JSON file.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Change the name of a JSON file. Note: Do not include file extension
	 * 
	 * @param name New name of the file.
	 */
	void setName(String name);

	/**
	 * Get the path of the JSON file.
	 * 
	 * @return Returns the path.
	 */
	String getPath();

	/**
	 * Change the path of a JSON file. Note: Do not include file extension.
	 * 
	 * @param path New path for the file.
	 */
	void setPath(String path);

	/**
	 * Get the JSON file as a JSON object.
	 * 
	 * @return JsonObject of the file.
	 */
	JsonObject getJsonObj();

}
