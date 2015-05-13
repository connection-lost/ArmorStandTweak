package me.crafter.mc.armorstandtweak;

import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorStandTweak extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Mincraft");
	public final ArmorListener pl = new ArmorListener();
	

    public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.pl, this);
        }
 

    public void onDisable() {
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args){

    	if (!(sender instanceof Player)){
    		sender.sendMessage("Only player can do this!");
    		return true;
    	}
    	
    	Player p = (Player)sender;
    	
    	if (cmd.getName().equals("as")){
    		
    		if (args.length == 0){
    			p.sendMessage(ChatColor.RED + "/as <name/small/base/arms/setangel>");
    			return true;
    		} else {
    			
    			if (!ArmorStandWorker.hasValidTarget(p)){
    				p.sendMessage(ChatColor.RED + "You don't have a target! Try sneak+right click an ArmorStand!");
    				return true;
    			}
    			
    			switch (args[0]){
    			case "name":
    				if (!p.hasPermission("armorstandtweak.name")){
    					p.sendMessage(ChatColor.RED + "You cannot use this feature.");
    					return true;
    				}
        			if (args.length == 1){
        				ArmorStandWorker.setName(p, null);
        				p.sendMessage(ChatColor.YELLOW + "You removed the name.");
        			} else {
        				String newname = "";
        				for (String x : args){
        					newname += x + " ";
        				}
        				newname = ChatColor.translateAlternateColorCodes('&', newname);
        				newname = newname.substring(5, newname.length()-1);
        				if (newname.length() > 31){
        					p.sendMessage(ChatColor.RED + "This name is too long!");
        					return true;
        				}
        				ArmorStandWorker.setName(p, newname);
        				p.sendMessage(ChatColor.YELLOW + "You set the name to: " + newname);
        			}
        			break;
    			case "small":
    				if (!p.hasPermission("armorstandtweak.small")){
    					p.sendMessage(ChatColor.RED + "You cannot use this feature.");
    					return true;
    				}
        			if (ArmorStandWorker.setSmall(p)){
        				p.sendMessage(ChatColor.YELLOW + "You shrinked the ArmorStand.");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "You enlarged the ArmorStand");
        			}
        			break;
    			case "base":
    				if (!p.hasPermission("armorstandtweak.base")){
    					p.sendMessage(ChatColor.RED + "You cannot use this feature.");
    					return true;
    				}
        			if (ArmorStandWorker.setBase(p)){
        				p.sendMessage(ChatColor.YELLOW + "You added the bottom plate.");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "You removed the bottom plate.");
        			}
        			break;
    			case "arms":
    				if (!p.hasPermission("armorstandtweak.arms")){
    					p.sendMessage(ChatColor.RED + "You cannot use this feature.");
    					return true;
    				}
        			if (ArmorStandWorker.setArms(p)){
        				p.sendMessage(ChatColor.YELLOW + "You added the arms.");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "You removed the arms.");
        			}
        			break;
    			case "visible":
    			case "invisible":
    				if (!p.hasPermission("armorstandtweak.invisible")){
    					p.sendMessage(ChatColor.RED + "You cannot use this feature.");
    					return true;
    				}
        			if (ArmorStandWorker.setInvisible(p)){
        				p.sendMessage(ChatColor.YELLOW + "You made it visible!");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "You made it invisible! Make sure keep track where it is!");
        			}
        			break;
    			case "info":
    				ArmorStandWorker.sendReport(p);
    				break;

    			case "setangle":
    			case "setrotation":
    				if (!p.hasPermission("armorstandtweak.setangle")){
    					p.sendMessage(ChatColor.RED + "You cannot use this feature.");
    					return true;
    				}
    				if (args.length != 5){
    					p.sendMessage(ChatColor.RED + "/as setangle <head/body/leftarm/rightarm/leftleg/rightleg> x y z");
    					return true;
    				}
    				double x = 0;
    				double y = 0;
    				double z = 0;
        			
    				try {
    					x = Double.parseDouble(args[2]);
    					y = Double.parseDouble(args[3]);
    					z = Double.parseDouble(args[4]);
    				} catch (Exception ex){
    					p.sendMessage("Enter correct value(s).");
    				}

    				x = (x + 180D) % 360D - 180;
    				y = (y + 180D) % 360D - 180;
    				z = (z + 180D) % 360D - 180;

    				x /= 57.295D;
    				y /= 57.295D;
    				z /= 57.295D;
    				
    				if (ArmorStandWorker.setAngle(p, args[1], x, y, z)){
    					p.sendMessage(ChatColor.YELLOW + "You set " + args[1] + "'s angle to: " + args[2] + " " + args[3] + " " + args[4]);
    				} else {
    					p.sendMessage(ChatColor.RED + "Available: head/body/leftarm/rightarm/leftleg/rightleg");
    				}
        			break;
        			
        			
        		default:
        			p.sendMessage(ChatColor.RED + "/as <name/small/base/arms/setangel>");
        			break;
    			}
        		
    		}

    		return true;
    	}
    	
    	
    	
    	return true;
    }
    
    
    

	
}
