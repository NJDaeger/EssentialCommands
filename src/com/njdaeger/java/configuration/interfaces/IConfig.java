package com.njdaeger.java.configuration.interfaces;

import com.njdaeger.java.configuration.ConfigType;

public interface IConfig {

	ConfigType getConfigType();

	void setConfigType(ConfigType type);

	boolean isNJPermsEnabled();

	void setNJPermsEnabled(boolean value);

	boolean isCodesEnabled();

	void setCodesEnabled(boolean value);

	boolean isAnnotationsEnabled();

	void setAnnotationsEnabled(boolean value);

	boolean isServerProtectEnabled();

	void setServerProtectEnabled(boolean value);

	boolean isLoginClearanceEnabled();

	void setLoginClearanceEnabled(boolean value);

}
