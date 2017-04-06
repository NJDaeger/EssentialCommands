package com.njdaeger.java.configuration.data;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;

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
		
	}
	
	public Database(InternalDatabase database) {
		this.name = database.getName();
		this.dir = new File(
				"plugins" + File.separator + BukkitCommonLib.getPlugin().getName() + File.separator + "databases");
		this.file = new File(dir + database.getName() + ".yml");
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
	}
	
	@Override
	public IEntry getEntry(String entry) {
		for (IEntry entries : getEntries()) {
			if (entries.getPath().matches(entry))
				return entries;
		}
		return null;
	}
	
	@Override
	// Check the value of getKeys
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
		DateFormat format = new SimpleDateFormat("yyyy/dd/MM-hh:mm:ss");
		File file = new File(dir + File.separator + name + ".yml");
		File bckp = new File(
				dir + File.separator + "backups" + File.separator + name + format.format(new Date()) + ".yml");
		if (!dir.exists()) {
			return;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
