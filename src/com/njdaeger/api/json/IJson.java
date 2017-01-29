package com.njdaeger.api.json;

public interface IJson {

	IJson create(String name);

	IJson create(String path, String name);

	void delete(String name);

	void delete(String path, String name);

	IJson add(Object object);

}
