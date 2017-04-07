package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.io.Files;
import com.njdaeger.java.Core;
import com.njdaeger.java.command.util.BukkitCommonLib;
import com.njdaeger.java.configuration.enums.InternalDatabase;
import com.njdaeger.java.configuration.interfaces.IDatabase;
import com.njdaeger.java.configuration.interfaces.IEntry;

public class Database implements IDatabase {
	
	private File dir, file;
	private String name;
	private YamlConfiguration config;
	private Collection<IEntry> collection = new ArrayList<>();
	
	public Database(String name) {
		this.name = name;
		this.dir = new File(
				"plugins" + File.separator + BukkitCommonLib.getPlugin().getName() + File.separator + "databases");
		this.file = new File(dir + name + ".yml");
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(file);
		if (!Core.getRegisterdDatabases().contains(this)) {
			Core.getRegisterdDatabases().add(this);
		}
		
	}
	
	public Database(InternalDatabase database) {
		this.name = database.getName();
		this.dir = new File(
				"plugins" + File.separator + BukkitCommonLib.getPlugin().getName() + File.separator + "databases");
		this.file = new File(dir + File.separator + database.getName() + ".yml");
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(file);
		if (!Core.getRegisterdDatabases().contains(this)) {
			Core.getRegisterdDatabases().add(this);
		}
	}
	
	@Override
	public IEntry getEntry(String entry) {
		for (IEntry entries : getEntries()) {
			if (entries.getPath().matches(entry)) {
				return entries;
			}
		}
		return null;
	}
	
	@Override
	public Collection<IEntry> getEntries() {
		for (String entry : getBase().getKeys(true)) {
			collection.add(new Entry(this, entry, getBase().get(entry)));
		}
		return collection;
	}
	
	@Override
	public void addEntry(IEntry entry) {
		setValue(entry.getPath(), entry.getValue());
	}
	
	@Override
	public void removeEntry(IEntry entry) {
		entry.remove();
		
	}
	
	@Override
	public IEntry getEntryFromValue(Object value) {
		for (IEntry entry : getEntries()) {
			if (entry.getValue() == value) {
				return entry;
			}
		}
		return null;
	}
	
	@Override
	public IDatabase getDatabase() {
		return this;
	}
	
	@Override
	public void clear() {
		for (IEntry entry : getEntries()) {
			entry.remove();
		}
	}
	
	@Override
	public void backup() {
		DateFormat format = new SimpleDateFormat("yyyy/dd/MM-hh.mm.ss");
		File file = new File(dir + File.separator + "backups");
		File bckp = new File(file + File.separator + name + format.format(new Date()) + ".yml");
		if (!dir.exists() || bckp.exists() || !this.file.exists()) {
			return;
		}
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			Files.copy(this.file, bckp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete() {
		file.delete();
	}
	
	@Override
	public YamlConfiguration getBase() {
		return config;
	}
	
	@Override
	public boolean exists() {
		return file.exists();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public File getFile() {
		return file;
	}
	
	private void setValue(String path, Object value) {
		getBase().set(path, value);
		try {
			getBase().save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
