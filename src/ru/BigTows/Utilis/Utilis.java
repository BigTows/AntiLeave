package ru.BigTows.Utilis;

import org.bukkit.entity.Player;

public class Utilis {

	public static String ReplaceMask(String Mask,Player Player){
		Mask = Mask.replaceAll("%player%", Player.getName());
		Mask = Mask.replaceAll("%health%", Player.getHealth()+"");
		Mask = Mask.replaceAll("%maxhealth%", Player.getMaxHealth()+"");
		return Mask;
	}
	
}
