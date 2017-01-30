package com.njdaeger.api.json.interfaces;

import com.google.gson.JsonObject;

public interface IJsonData {

	String getName();

	void setName(String name);

	String getPath();

	void setPath(String path);

	JsonObject getJsonObj();

}
