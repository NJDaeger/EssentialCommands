package com.njdaeger.api.json.interfaces;

import com.njdaeger.api.json.JsonAPI;

public interface IJsonCreator {

	/**
	 * Creates a JSON file in the default directory.
	 * <p>
	 * Directory: /plugins/PLUGIN_NAME/###.json
	 * 
	 * @param name The name of the file you want to create.
	 * @return
	 */
	JsonAPI create(String name);

	/**
	 * Creates a JSON file in the specified directory.
	 * 
	 * @param path Path you want the file to be saved at.
	 * @param name Name of the file you are creating.
	 * @return
	 */
	JsonAPI create(String path, String name);

	/**
	 * Deletes a JSON file in the default directory.
	 * <p>
	 * Directory: /plugins/PLUGIN_NAME/###.json
	 * 
	 * @param name The name of the file getting deleted.
	 */
	void delete(String name);

	/**
	 * Deletes a JSON file in the specified directory.
	 * 
	 * @param path Path you want the file to be deleted from.
	 * @param name Name of the file you want deleted.
	 */
	void delete(String path, String name);

}
