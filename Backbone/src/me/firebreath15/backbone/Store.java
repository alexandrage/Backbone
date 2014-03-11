package me.firebreath15.backbone;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Store implements Listener{
	
	main plugin;
	Store(main c){
		plugin=c;
	}
	
	public static Inventory store = Bukkit.createInventory(null, 18, "Backbone Store");
	
	static ItemStack reload = new ItemStack(Material.EXP_BOTTLE, 40);
	static ItemStack jump = new Potion(PotionType.SPEED).toItemStack(1);
	static ItemStack speed = new Potion(PotionType.SPEED).toItemStack(1);
	static ItemStack enderpearls = new ItemStack(Material.ENDER_PEARL, 5);
	static ItemStack ammo = new ItemStack(Material.SNOW_BALL);
	static ItemStack fake = new ItemStack(Material.LEATHER_CHESTPLATE);
	static ItemStack bow = new ItemStack(Material.BOW);
	static ItemStack nausea = new ItemStack(Material.GOLD_HOE, 2);
	static ItemStack points = new ItemStack(Material.BEACON, 1);
	
    static {
    	ItemMeta im = reload.getItemMeta();
    	im.setDisplayName(ChatColor.YELLOW+ChatColor.BOLD.toString()+"Reload"+ChatColor.GREEN+" [100]");
    	List<String> re = new ArrayList<String>();
    	re.add(ChatColor.GRAY+"Instantly reload your health, food, and ammo");
    	im.setLore(re);
    	reload.setItemMeta(im);
    	
    	ItemMeta im2 = jump.getItemMeta();
    	im2.setDisplayName(ChatColor.GOLD+"Jump Boost"+ChatColor.GREEN+" [300]");
    	List<String> ju = new ArrayList<String>();
    	ju.add(ChatColor.GRAY+"Increase your jump height temporarily");
    	im2.setLore(ju);
    	jump.setItemMeta(im2);
    	
    	ItemMeta im3 = speed.getItemMeta();
    	im3.setDisplayName(ChatColor.GOLD+"Speed Boost"+ChatColor.GREEN+" [300]");
    	List<String> sp = new ArrayList<String>();
    	sp.add(ChatColor.GRAY+"Increase your run speed temporarily");
    	im3.setLore(sp);
    	speed.setItemMeta(im3);
    	
    	ItemMeta im4 = enderpearls.getItemMeta();
    	im4.setDisplayName(ChatColor.GOLD+"Enderpearl"+ChatColor.GREEN+" [500]");
    	List<String> en = new ArrayList<String>();
    	en.add(ChatColor.GRAY+"Get 5 working enderpearls");
    	im4.setLore(en);
    	enderpearls.setItemMeta(im4);
    	
    	ItemMeta im6 = ammo.getItemMeta();
    	im6.setDisplayName(ChatColor.GOLD+"Ammo Upgrade"+ChatColor.GREEN+" [650]");
    	List<String> am = new ArrayList<String>();
    	am.add(ChatColor.GRAY+"Upgrade your rounds to 100 once");
    	im6.setLore(am);
    	ammo.setItemMeta(im6);
    	
    	ItemMeta im7 = fake.getItemMeta();
    	im7.setDisplayName(ChatColor.DARK_GRAY+ChatColor.BOLD.toString()+"Fake Team-Change"+ChatColor.GREEN+" [1000]");
    	List<String> fa = new ArrayList<String>();
    	fa.add(ChatColor.GRAY+"Change your armor color, fooling your real enemies");
    	im7.setLore(fa);
    	fake.setItemMeta(im7);
    	
    	ItemMeta im8 = bow.getItemMeta();
    	im8.setDisplayName(ChatColor.DARK_RED+ChatColor.BOLD.toString()+"BombBow"+ChatColor.GREEN+" [1100]");
    	List<String> bo = new ArrayList<String>();
    	bo.add(ChatColor.GRAY+"Get the BombBow and fire exploding arrows");
    	im8.setLore(bo);
    	bow.setItemMeta(im8);
    	
    	ItemMeta im9 = nausea.getItemMeta();
    	im9.setDisplayName(ChatColor.DARK_GREEN+"Nausea Stick"+ChatColor.GREEN+" [1500]");
    	List<String> na = new ArrayList<String>();
    	na.add(ChatColor.GRAY+"Get 2 nausea sticks. Punch an enemy to make them dizzy");
    	im9.setLore(na);
    	nausea.setItemMeta(im9);
    	
    	ItemMeta im0 = points.getItemMeta();
    	im0.setDisplayName(ChatColor.YELLOW+"Your Points");
    	List<String> po = new ArrayList<String>();
    	po.add(ChatColor.GRAY+"Click to see your points");
    	im0.setLore(po);
    	points.setItemMeta(im0);
    	
    	store.setItem(0, reload);
    	store.setItem(1, jump);
    	store.setItem(2, speed);
    	store.setItem(3, enderpearls);
    	store.setItem(4, ammo);
    	store.setItem(5, fake);
    	store.setItem(6, bow);
    	store.setItem(7, nausea);
    	store.setItem(17, points);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
    	Player p = (Player) event.getWhoClicked();
	    ItemStack clicked = event.getCurrentItem();
	    Inventory inventory = event.getInventory();
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == Material.EXP_BOTTLE){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 100){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	p.setExp(0);
			    	p.setLevel(40);
			    	p.setHealth(20d);
			    	p.setFoodLevel(20);
			    	plugin.getConfig().set(p.getName(), points-100);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == jump.getType()){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 300){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, 1200, 2);
					p.addPotionEffect(effect);
					plugin.getConfig().set(p.getName(), points-300);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == speed.getType()){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 300){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 1200, 2);
					p.addPotionEffect(effect);
					plugin.getConfig().set(p.getName(), points-300);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == Material.ENDER_PEARL){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 500){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	p.getInventory().addItem(enderpearls);
			    	plugin.getConfig().set(p.getName(), points-500);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == Material.SNOW_BALL){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 650){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	p.setLevel(100);
			    	plugin.getConfig().set(p.getName(), points-650);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == Material.LEATHER_CHESTPLATE){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 1000){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	if(plugin.getConfig().contains("rplist1."+p.getName()) || plugin.getConfig().contains("rplist2."+p.getName()) || plugin.getConfig().contains("rplist3."+p.getName()) || plugin.getConfig().contains("rplist4."+p.getName()) || plugin.getConfig().contains("rplist5."+p.getName())){
			    		ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
						LeatherArmorMeta meta = (LeatherArmorMeta) helm.getItemMeta();
						meta.setColor(Color.BLUE);
						helm.setItemMeta(meta);
						p.getInventory().setHelmet(helm);
						ItemStack ches = new ItemStack(Material.LEATHER_CHESTPLATE);
						LeatherArmorMeta metat = (LeatherArmorMeta) ches.getItemMeta();
						metat.setColor(Color.BLUE);
						ches.setItemMeta(metat);
						p.getInventory().setChestplate(ches);
						ItemStack legg = new ItemStack(Material.LEATHER_LEGGINGS);
						LeatherArmorMeta metatt = (LeatherArmorMeta) legg.getItemMeta();
						metatt.setColor(Color.BLUE);
						legg.setItemMeta(metatt);
						p.getInventory().setLeggings(legg);
						ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
						LeatherArmorMeta metattt = (LeatherArmorMeta) boot.getItemMeta();
						metattt.setColor(Color.BLUE);
						boot.setItemMeta(metattt);
						p.getInventory().setBoots(boot);
			    	}else{
			    		ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
						LeatherArmorMeta meta = (LeatherArmorMeta) helm.getItemMeta();
						meta.setColor(Color.RED);
						helm.setItemMeta(meta);
						p.getInventory().setHelmet(helm);
						ItemStack ches = new ItemStack(Material.LEATHER_CHESTPLATE);
						LeatherArmorMeta metat = (LeatherArmorMeta) ches.getItemMeta();
						metat.setColor(Color.RED);
						ches.setItemMeta(metat);
						p.getInventory().setChestplate(ches);
						ItemStack legg = new ItemStack(Material.LEATHER_LEGGINGS);
						LeatherArmorMeta metatt = (LeatherArmorMeta) legg.getItemMeta();
						metatt.setColor(Color.RED);
						legg.setItemMeta(metatt);
						p.getInventory().setLeggings(legg);
						ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
						LeatherArmorMeta metattt = (LeatherArmorMeta) boot.getItemMeta();
						metattt.setColor(Color.RED);
						boot.setItemMeta(metattt);
						p.getInventory().setBoots(boot);
			    	}
			    	plugin.getConfig().set(p.getName(), points-1000);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == Material.BOW){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 1100){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	p.getInventory().addItem(new ItemStack(Material.BOW));
			    	p.getInventory().addItem(new ItemStack(Material.ARROW,15));
			    	plugin.getConfig().set(p.getName(), points-1100);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == Material.GOLD_HOE){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		if(points >= 1500){
			    	event.setCancelled(true);
			    	p.closeInventory();
			    	p.getInventory().addItem(new ItemStack(Material.GOLD_HOE,2));
			    	plugin.getConfig().set(p.getName(), points-1500);
			    	plugin.saveConfig(); p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Purchase Successful!");
	    		}else{
	    			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have enough points!");
	    			event.setCancelled(true);
			    	p.closeInventory();
	    		}
	    	}
	    }
	    if (inventory.getName().equals(store.getName())) {
	    	if(clicked.getType() == Material.BEACON){
	    		int points = plugin.getConfig().getInt(p.getName());
	    		event.setCancelled(true);
		    	p.closeInventory();
		    	p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.YELLOW+"You have "+points+" points!");
	    	}
	    }
    }
    
    @EventHandler
    public void onPlayerSummonStore(PlayerInteractEvent e){
    	if(e.getAction()==Action.RIGHT_CLICK_AIR || e.getAction()==Action.RIGHT_CLICK_BLOCK){
	    	if(e.getPlayer().getItemInHand().getType()==Material.BOOK){
	    		Player p = e.getPlayer();
	    		if(plugin.getConfig().contains("players1."+p.getName()) || plugin.getConfig().contains("players2."+p.getName()) || plugin.getConfig().contains("players3."+p.getName()) || plugin.getConfig().contains("players4."+p.getName()) || plugin.getConfig().contains("players5."+p.getName())){
	    			p.openInventory(store);
	    		}
	    	}
    	}
    }
}
