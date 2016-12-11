package ru.BigTows.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import ru.BigTows.AntiLeave;
import ru.BigTows.Utilis.Runnable;

public class Events implements Listener {
	AntiLeave Plugin;

	public Events(AntiLeave instance) {
		this.Plugin = instance;
	}

	@EventHandler
	public void QuitEvent(PlayerQuitEvent Event) {
		Player Player = Event.getPlayer();
		if (AntiLeave.DamageMap.get(Player) >= 2) {
			ItemStack[] InventoryContent = Player.getInventory().getContents();
			Location LocQuit = Player.getLocation();
			LivingEntity QuitPlayer = (LivingEntity)LocQuit.getWorld().spawnEntity(LocQuit, EntityType.CHICKEN);
			QuitPlayer.setCustomName(AntiLeave.Config.getString("Settings.NameEntity"));
			double Health = AntiLeave.Config.getDouble("Settings.Health");
			if (Health>2048){
				Health=2048;
			}else if (Health<=0){
				Health = 200;
			}
			QuitPlayer.setMaxHealth(Health);
			QuitPlayer.setHealth(Health);
			Runnable Timer = new Runnable(InventoryContent, QuitPlayer, Player);
			Timer.runTaskTimer(this.Plugin, (long) AntiLeave.Config.getDouble("Settings.TicksEntity") * 20, 1L);
		}
	}

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent Event) {
		Player Player = Event.getPlayer();
		AntiLeave.DamageMap.put(Player, 0);
		if (AntiLeave.Data.removePlayer("Players", Player.getName())) {
			Player.getInventory().clear();
			Player.setHealth(0);
		}
	}

	@EventHandler
	public void onDamageEvent(EntityDamageByEntityEvent Event) {
		Player Player = null;
		Player Damager = null;
		if (Event.getEntity() instanceof Player) {
			Player = (Player) Event.getEntity();
		}
		if (Event.getDamager() instanceof Player) {
			Damager = (Player) Event.getDamager();
		}
		if (Player != null && Damager != null) {
			AntiLeave.DamageMap.put(Player, AntiLeave.DamageMap.get(Player) + 1);
		}
	}
}
