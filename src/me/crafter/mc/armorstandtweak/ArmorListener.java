package me.crafter.mc.armorstandtweak;

import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmorListener implements Listener {
	
	Logger logger = Bukkit.getLogger();
	
	@EventHandler
	public void onPlayerInteractArmorStand(PlayerInteractAtEntityEvent event){
		Player p = event.getPlayer();
		if (p.isSneaking() && event.getRightClicked() != null && event.getRightClicked() instanceof ArmorStand){
			ArmorStand as = (ArmorStand)event.getRightClicked();
			
			String customname = "ArmorStand";
			if (as.getCustomName() != null && as.getCustomName() != ""){
				customname = as.getCustomName();
			}

			p.sendMessage(ChatColor.YELLOW + "你选择了盔甲架(" + customname + ChatColor.YELLOW + ")" + ChatColor.AQUA + " #" + as.getEntityId());
			p.sendMessage(ChatColor.YELLOW + "使用指令/as调节盔甲架的信息！");
			event.setCancelled(true);
			ArmorStandWorker.aslist.put(p, as);
			
			logger.info("[ArmorStandTweak] " + p.getName() + " touched armorstand at " + as.getLocation().getBlockX() + " " + as.getLocation().getBlockY() + " " + as.getLocation().getBlockZ());
			
		}
	}
	


}


