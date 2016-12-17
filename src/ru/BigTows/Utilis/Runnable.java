package ru.BigTows.Utilis;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import ru.BigTows.AntiLeave;

import org.bukkit.entity.Player;

public class Runnable extends BukkitRunnable {

	private ItemStack[] InventoryContent;
	private LivingEntity Mob;
	private Player Player;
	private AntiLeave Plugin;
	private String Mode;

	public Runnable(ItemStack[] InventoryContent, LivingEntity Mob, Player Player,AntiLeave Plugin) {
		this.InventoryContent = InventoryContent;
		this.Mob = Mob;
		this.Player = Player;
		this.Mode = "Mob";
		this.Plugin=Plugin;
	}

	public Runnable(AntiLeave Plugin) {
		this.Mode = "Check";
		this.Plugin=Plugin;
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
				Plugin.Data.addPlayer("Players", Player.getName());
				this.cancel();
			} else {
				Mob.remove();
				this.cancel();
			}
			Plugin.BlockJoin.remove(Player.getUniqueId());
			break;
		}
		case "Check": {
			for (Player Player: Bukkit.getOnlinePlayers()){
				if (!Plugin.DamageMap.containsKey(Player)){
					Plugin.DamageMap.put(Player, 0);
				}
				if (Plugin.DamageMap.get(Player)!=0){
					Plugin.DamageMap.put(Player,Plugin.DamageMap.get(Player)-1);
				}
			}
			break;
		}
		default: this.cancel();
		}
	}

}
