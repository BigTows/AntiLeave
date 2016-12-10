package ru.BigTows.Utilis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import ru.BigTows.AntiLeave;

import org.bukkit.entity.Player;

public class Runnable extends BukkitRunnable {

	private ItemStack[] InventoryContent;
	private Entity Mob;
	private Player Player;

	private String Mode;

	public Runnable(ItemStack[] InventoryContent, Entity Mob, Player Player) {
		this.InventoryContent = InventoryContent;
		this.Mob = Mob;
		this.Player = Player;
		this.Mode = "Mob";
	}

	public Runnable() {
		this.Mode = "Check";
	}

	@Override
	public void run() {
		switch (this.Mode) {
		case "Mob": {
			if (this.Mob.isDead()) {
				for (int i = 0; i < InventoryContent.length; i++) {
					if (InventoryContent[i] != null) {
						this.Mob.getWorld().dropItemNaturally(this.Mob.getLocation(), InventoryContent[i]);
					}
				}
				AntiLeave.Data.addPlayer("Players", Player.getName());
				this.cancel();
			} else {
				Mob.remove();
				this.cancel();
			}
			break;
		}
		case "Check": {
			for (Player Player: Bukkit.getOnlinePlayers()){
				if (!AntiLeave.DamageMap.containsKey(Player)){
					AntiLeave.DamageMap.put(Player, 0);
				}
				if (AntiLeave.DamageMap.get(Player)!=0){
					AntiLeave.DamageMap.put(Player,AntiLeave.DamageMap.get(Player)-1);
				}
			}
			break;
		}
		default: this.cancel();
		}
	}

}
