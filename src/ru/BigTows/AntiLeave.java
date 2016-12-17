package ru.BigTows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ru.BigTows.Listeners.Events;
import ru.BigTows.Utilis.Config;
import ru.BigTows.Utilis.Runnable;
public class AntiLeave extends JavaPlugin implements Listener{

	private PluginManager PLuginManager = this.getServer().getPluginManager();
	
	public  Map<Player,Integer> DamageMap = new HashMap<Player,Integer>();
	public ArrayList<UUID> BlockJoin = new ArrayList<UUID>();
	public  Config Data = new Config("Data.yml");
	public  Config Config = new Config();
	private Runnable Damage = new Runnable(this);
	
	
	public void onEnable(){
		this.PLuginManager.registerEvents(new Events(this), this);
		this.Damage.runTaskTimer(this, 0L, (long) Config.getDouble("Settings.TicksDamager")*20);
	}
	
	public void onDisable(){
		Damage.cancel();
	}
	
}
