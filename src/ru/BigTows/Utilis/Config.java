package ru.BigTows.Utilis;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private File DataFile;
	private FileConfiguration Data = new YamlConfiguration();

	private void LoadFile() {
		if (!this.DataFile.exists()) {
			this.DataFile.getParentFile().mkdirs();
			try {
				this.DataFile.createNewFile();
			} catch (IOException e) {
				System.out.println("[AntiLeave] Error Create Config");
			}
		} else {
			try {
				this.Data.load(DataFile);
			} catch (IOException | InvalidConfigurationException e) {
				System.out.println("[AntiLeave] Error Load Config");
			}
		}
	}

	private void SaveFile() {
		try {
			this.Data.save(DataFile);
		} catch (IOException e) {
			System.out.println("[AntiLeave] Error Save Config");
		}
	}

	public void addPlayer(String Path, String Player) {
		List<String> DataList = this.Data.getStringList(Path);
		DataList.add(Player);
		this.Data.set(Path, DataList);
		this.SaveFile();
	}

	public boolean removePlayer(String Path, String Name) {
		List<String> DataList = this.Data.getStringList(Path);
		if (DataList.remove(Name)) {
			this.Data.set(Path, DataList);
			this.SaveFile();
			return true;
		} else {
			return false;
		}

	}

	public double getDouble(String Path) {
		return this.Data.getDouble(Path);
	}

	public String getString(String Path) {
		return this.Data.getString(Path);
	}

	private void DefaultConfig() {
		if (!this.Data.isSet("Settings")) {
			this.Data.set("Settings.TicksDamager", 3);
			this.Data.set("Settings.TicksEntity", 5);
			this.Data.set("Settings.NameEntity", "KOKOKO");
			this.SaveFile();
		}
	}

	public Config(String Name) {
		this.DataFile = new File("plugins/AntiLeave/" + Name);
		this.LoadFile();
	}

	public Config() {
		this.DataFile = new File("plugins/AntiLeave/Config.yml");
		this.LoadFile();
		DefaultConfig();
	}

}
