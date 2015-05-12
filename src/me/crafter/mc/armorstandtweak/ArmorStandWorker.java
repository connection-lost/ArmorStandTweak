package me.crafter.mc.armorstandtweak;

import java.util.HashMap;
import java.util.Map;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

public class ArmorStandWorker {
	
	public static Map<Player, ArmorStand> aslist = new HashMap<Player, ArmorStand>();


	public static boolean hasValidTarget(Player p){
		if (aslist.get(p) != null){
			ArmorStand as = aslist.get(p);
			if (as.isValid()) return true;
		}
		return false;
	}
	
	
	public static void setName(Player p, String name){
		ArmorStand as = aslist.get(p);	
		if (name == null){
			as.setCustomName(null);
			as.setCustomNameVisible(false);
		} else {
			as.setCustomName(name);
			as.setCustomNameVisible(true);
		}
	}
	
	public static boolean setSmall(Player p){
		ArmorStand as = aslist.get(p);
		as.setSmall(!as.isSmall());
		return as.isSmall();
	}
	
	public static boolean setBase(Player p){
		ArmorStand as = aslist.get(p);
		as.setBasePlate(!as.hasBasePlate());
		return as.hasBasePlate();
	}
	
	public static boolean setArms(Player p){
		ArmorStand as = aslist.get(p);
		as.setArms(!as.hasArms());
		return as.hasArms();
	}
	
	public static boolean setInvisible(Player p){
		ArmorStand as = aslist.get(p);
		as.setVisible(!as.isVisible());
		return as.isVisible();
	}
	
	public static void sendReport(Player p){
		ArmorStand as = aslist.get(p);
		if (as.getCustomName() != null){
			p.sendMessage(ChatColor.YELLOW + "盔甲架 自定义名称: " + ChatColor.RESET + as.getCustomName());
		} else {
			p.sendMessage(ChatColor.YELLOW + "盔甲架 无自定义名称");
		}
		
		
		String pre1 = "缩小/底盘/胳膊/隐身: ";
		
		pre1 += as.isSmall() + "/";
		pre1 += as.hasBasePlate() + "/";
		pre1 += as.hasArms() + "/";
		pre1 += as.isVisible();

		pre1 = pre1.replace("true", ChatColor.GREEN + "TRUE" + ChatColor.YELLOW);
		pre1 = pre1.replace("false", ChatColor.RED + "FALSE" + ChatColor.YELLOW);
		
		p.sendMessage(ChatColor.YELLOW + pre1);

		p.sendMessage(ChatColor.YELLOW + "头部角度: " + eulerToString(as.getHeadPose()));
		p.sendMessage(ChatColor.YELLOW + "身体角度: " + eulerToString(as.getBodyPose()));
		
		p.sendMessage(ChatColor.YELLOW + "左腿角度: " + eulerToString(as.getLeftLegPose()));
		p.sendMessage(ChatColor.YELLOW + "右腿角度: " + eulerToString(as.getRightLegPose()));
		
		if (as.hasArms()){
			p.sendMessage(ChatColor.YELLOW + "左手角度: " + eulerToString(as.getLeftArmPose()));
			p.sendMessage(ChatColor.YELLOW + "右手角度: " + eulerToString(as.getRightArmPose()));
		}
		
	}
	
	public static boolean setAngle(Player p, String pos, double x, double y, double z){
		ArmorStand as = aslist.get(p);
		switch (pos){
		case "head":
			as.setHeadPose(new EulerAngle(x, y, z));
			break;
		case "body":
			as.setBodyPose(new EulerAngle(x, y, z));
			break;
		case "leftarm":
			as.setLeftArmPose(new EulerAngle(x, y, z));
			break;
		case "rightarm":
			as.setRightArmPose(new EulerAngle(x, y, z));
			break;
		case "leftleg":
			as.setLeftLegPose(new EulerAngle(x, y, z));
			break;
		case "rightleg":
			as.setRightLegPose(new EulerAngle(x, y, z));
			break;
		default:
			return false;
		}
		return true;
	}
	
	public static String eulerToString(EulerAngle euler){
		return String.format("%.2f°, %.2f°, %.2f°", euler.getX()*57.295D, euler.getY()*57.295D, euler.getZ()*57.295D);
	}
	
	
}




















