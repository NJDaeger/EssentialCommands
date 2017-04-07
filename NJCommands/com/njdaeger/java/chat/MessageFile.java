package com.njdaeger.java.chat;

import com.njdaeger.java.Core;
import com.njdaeger.java.configuration.data.Database;
import com.njdaeger.java.configuration.data.Entry;
import com.njdaeger.java.configuration.enums.InternalDatabase;
import com.njdaeger.java.configuration.enums.Messages;

public class MessageFile {
	
	// private static DatabaseData file = Database.getDatabase("messages");
	
	public static void create() {
		Database database = (Database) Core.getDatabase(InternalDatabase.MESSAGES);
		for (Messages path : Messages.values()) {
			if (database.getEntry(path.getPath()) == null) {
				database.addEntry(new Entry(database, path.getPath(), path.defMessage()));
			}
		}
	}
	
}
