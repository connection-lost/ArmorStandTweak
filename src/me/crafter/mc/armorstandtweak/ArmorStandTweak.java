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
    				p.sendMessage(ChatColor.RED + "��û��ѡ��Ŀ�꣬ѡ��Ŀ����ҪǱ��+�Ҽ�ѡ����׼ܡ�");
    				return true;
    			}
    			
    			switch (args[0]){
    			case "name":
        			if (args.length == 1){
        				ArmorStandWorker.setName(p, null);
        				p.sendMessage(ChatColor.YELLOW + "���Ƴ��˿��׼ܵ����֡�");
        			} else {
        				String newname = "";
        				for (String x : args){
        					newname += x + " ";
        				}
        				newname = ChatColor.translateAlternateColorCodes('&', newname);
        				newname = newname.substring(5, newname.length()-1);
        				if (newname.length() > 31){
        					p.sendMessage(ChatColor.RED + "�������̫���ˣ�");
        					return true;
        				}
        				ArmorStandWorker.setName(p, newname);
        				p.sendMessage(ChatColor.YELLOW + "�㽫���׼ܵ�������Ϊ: " + newname);
        			}
        			break;
    			case "small":
        			if (ArmorStandWorker.setSmall(p)){
        				p.sendMessage(ChatColor.YELLOW + "���������׼���С�ˡ�");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "���������׼ܵĴ�С��ԭ�ˡ�");
        			}
        			break;
    			case "base":
        			if (ArmorStandWorker.setBase(p)){
        				p.sendMessage(ChatColor.YELLOW + "���������׼ܵĵ׶˸�ԭ�ˡ�");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "��ѿ��׼ܵĵ׶�ȥ���ˡ�");
        			}
        			break;
    			case "arms":
        			if (ArmorStandWorker.setArms(p)){
        				p.sendMessage(ChatColor.YELLOW + "������׼ܼ����˸첲��");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "���Ƴ��˿��׼ܵĸ첲��");
        			}
        			break;
    			case "visible":
    			case "invisible":
    				if (!p.hasPermission("armorstandtweak.admin")){
    					p.sendMessage(ChatColor.RED + "��û��Ȩ���������");
    					return true;
    				}
        			if (ArmorStandWorker.setInvisible(p)){
        				p.sendMessage(ChatColor.YELLOW + "���ÿ��׼���ʾ�����ˡ�");
        			} else {
        				p.sendMessage(ChatColor.YELLOW + "�������˿��׼ܡ�");
        			}
        			break;
    			case "info":
    				ArmorStandWorker.sendReport(p);
    				break;

    			case "setangel":
    			case "setrotation":
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
    					p.sendMessage("��������ȷ����ֵ��");
    				}

    				x = (x + 180D) % 360D - 180;
    				y = (y + 180D) % 360D - 180;
    				z = (z + 180D) % 360D - 180;

    				x /= 57.295D;
    				y /= 57.295D;
    				z /= 57.295D;
    				
    				if (ArmorStandWorker.setAngle(p, args[1], x, y, z)){
    					p.sendMessage(ChatColor.YELLOW + "�㽫" + args[1] + "�ĽǶ��趨Ϊ: " + args[2] + " " + args[3] + " " + args[4]);
    				} else {
    					p.sendMessage(ChatColor.RED + "���ò�λ: head/body/leftarm/rightarm/leftleg/rightleg");
    				}
        			break;
        			
    			}
        		
    		}

    		return true;
    	}
    	
    	
    	
    	return true;
    }
    
    
    

	
}
