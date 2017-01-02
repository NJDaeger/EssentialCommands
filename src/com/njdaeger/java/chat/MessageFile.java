package com.njdaeger.java.chat;

import com.njdaeger.java.configuration.controllers.Database;
import com.njdaeger.java.configuration.data.DatabaseData;
import com.njdaeger.java.configuration.enums.Messages;

public class MessageFile {

	private static DatabaseData file = Database.getDatabase("messages");

	public static void create() {
		if (file.getBase() == null) {
			file.create();
			System.out.println("Messages.yml not found... Creating!");
			for (Messages path : Messages.values()) {
				file.addEntry(path.getPath(), path.defMessage());
			}
		} else {
			for (Messages path : Messages.values()) {
				if (file.getEntry(path.getPath()) == null) {
					System.out.println("Added " + path.getPath() + " to messages.yml");
					file.addEntry(path.getPath(), path.defMessage());
				}
				return;
			}
		}
	}

}
