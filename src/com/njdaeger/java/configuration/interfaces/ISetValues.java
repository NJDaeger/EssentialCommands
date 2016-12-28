package com.njdaeger.java.configuration.interfaces;


public interface ISetValues {
	
	/**
	 * Sets the last or logout location X value.
	 * @param value
	 */
	void setX(double value);
	
	/**
	 * Sets the last or logout location Y value.
	 * @param value
	 */
	void setY(double value);
	
	/**
	 * Sets the last or logout location Z value.
	 * @param value
	 */
	void setZ(double value);
	
	/**
	 * Sets the last or logout location YAW value.
	 * @param value
	 */
	void setYaw(float value);
	
	/**
	 * Sets the last or logout location PITCH value.
	 * @param value
	 */
	void setPitch(float value);
	
	/**
	 * Sets the last or logout location WORLD value.
	 * @param value
	 */
	void setWorld(String value);
	
}
