package ru.BigTows;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ru.BigTows.Listeners.Events;
import ru.BigTows.Utilis.Config;
import ru.BigTows.Utilis.Runnable;
public class AntiLeave extends JavaPlugin implements Listener{

	private PluginManager PLuginManager = this.getServer().getPluginManager();
	
	public static Map<Player,Integer> DamageMap = new HashMap<Player,Integer>();
	public static Config Data = new Config("Data.yml");
	public static Config Config = new Config();
	private Runnable Damage = new Runnable();
	
	
	public void onEnable(){
		this.PLuginManager.registerEvents(new Events(this), this);
		this.Damage.runTaskTimer(this, 0L, (long) Config.getDouble("Settings.TicksDamager")*20);
	}
	
	public void onDisable(){
		Damage.cancel();
	}
	
}
