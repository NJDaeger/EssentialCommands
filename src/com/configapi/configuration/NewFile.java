package com.configapi.configuration;

import java.io.File;
import java.io.IOException;

import com.configapi.configuration.exceptions.ConfigExistsException;


public class NewFile {
	
	public void generateNewFile(String directory, String filename) {
		File dir = new File(directory);
		File file = new File(directory+File.separator+filename);
		if (!file.exists()) {
			dir.mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else throw new ConfigExistsException();
		
	}
	public static void getDataFile(String path) {
		/*
		 * check if file exists if not throw not found exception
		 */
	}
	
}
