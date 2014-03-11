package me.firebreath15.backbone;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class CoreCode {
	main plugin;
	CoreCode(main c){
		plugin=c;
	}
	
	public void removeAndTeleport(Player p){
		
		if(plugin.getConfig().contains("spawn.x")){
			if(plugin.getConfig().contains("players1."+p.getName()) || plugin.getConfig().contains("players2."+p.getName()) || plugin.getConfig().contains("players3."+p.getName()) || plugin.getConfig().contains("players4."+p.getName()) || plugin.getConfig().contains("players5."+p.getName()) || Startgame.map.containsKey(p.getName())){
				Location spawn = new Location(Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world")), plugin.getConfig().getDouble("spawn.x"), plugin.getConfig().getDouble("spawn.y"), plugin.getConfig().getDouble("spawn.z"));
				p.teleport(spawn);
			}
		}else{
			plugin.getLogger().log(Level.SEVERE, "Backbone was unable to teleport the player to the endpoint!");
		}
		
		if (this.plugin.getConfig().contains("rplist1." + p.getName())){
	      this.plugin.getConfig().set("rplist1." + p.getName(), null);
	      this.plugin.getConfig().set("players1." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("rplayers1");
	      this.plugin.getConfig().set("rplayers1", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue1") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue1");
	    	  plugin.getConfig().set("queue1", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("bplist1." + p.getName())){
	      this.plugin.getConfig().set("bplist1." + p.getName(), null);
	      this.plugin.getConfig().set("players1." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("bplayers1");
	      this.plugin.getConfig().set("bplayers1", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	     
	      if(plugin.getConfig().getInt("queue1") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue1");
	    	  plugin.getConfig().set("queue1", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("rplist2." + p.getName())){
	      this.plugin.getConfig().set("rplist2." + p.getName(), null);
	      this.plugin.getConfig().set("players2." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("rplayers2");
	      this.plugin.getConfig().set("rplayers2", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue2") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue2");
	    	  plugin.getConfig().set("queue2", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("bplist2." + p.getName())){
	      this.plugin.getConfig().set("bplist2." + p.getName(), null);
	      this.plugin.getConfig().set("players2." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("bplayers2");
	      this.plugin.getConfig().set("bplayers2", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue2") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue2");
	    	  plugin.getConfig().set("queue2", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("rplist3." + p.getName())){
	      this.plugin.getConfig().set("rplist3." + p.getName(), null);
	      this.plugin.getConfig().set("players3." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("rplayers3");
	      this.plugin.getConfig().set("rplayers3", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue3") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue3");
	    	  plugin.getConfig().set("queue3", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("bplist3." + p.getName())){
	      this.plugin.getConfig().set("bplist3." + p.getName(), null);
	      this.plugin.getConfig().set("players3." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("bplayers3");
	      this.plugin.getConfig().set("bplayers3", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue3") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue3");
	    	  plugin.getConfig().set("queue3", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("rplist4." + p.getName())){
	      this.plugin.getConfig().set("rplist4." + p.getName(), null);
	      this.plugin.getConfig().set("players4." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("rplayers4");
	      this.plugin.getConfig().set("rplayers4", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();

	      if(plugin.getConfig().getInt("queue4") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue4");
	    	  plugin.getConfig().set("queue4", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("bplist4." + p.getName())){
	      this.plugin.getConfig().set("bplist4." + p.getName(), null);
	      this.plugin.getConfig().set("players4." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("bplayers4");
	      this.plugin.getConfig().set("bplayers4", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue4") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue4");
	    	  plugin.getConfig().set("queue4", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("rplist5." + p.getName())){
	      this.plugin.getConfig().set("rplist5." + p.getName(), null);
	      this.plugin.getConfig().set("players5." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("rplayers5");
	      this.plugin.getConfig().set("rplayers5", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue5") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue5");
	    	  plugin.getConfig().set("queue5", q-1);
	    	  plugin.saveConfig();
	      }
	    }

	    if (this.plugin.getConfig().contains("bplist5." + p.getName())){
	      this.plugin.getConfig().set("bplist5." + p.getName(), null);
	      this.plugin.getConfig().set("players5." + p.getName(), null);
	      int np = this.plugin.getConfig().getInt("bplayers5");
	      this.plugin.getConfig().set("bplayers5", Integer.valueOf(np - 1));
	      this.plugin.saveConfig();
	      
	      if(plugin.getConfig().getInt("queue5") > 0){ //if it is not 0 and is not -1 (in session)
	    	  int q = plugin.getConfig().getInt("queue5");
	    	  plugin.getConfig().set("queue5", q-1);
	    	  plugin.saveConfig();
	      }
	    }
		
	    ISCOREAPI api = new ISCOREAPI();
	    api.setScoreboard(p);
	    api.removePlayerFromTeam("Player", p);
		api.removeTeam("Player");
		api.removeObjective("Arena_1");
		api.removeObjective("Arena_2");
		api.removeObjective("Arena_3");
		api.removeObjective("Arena_4");
		api.removeObjective("Arena_5");
		api.refreshPlayerScoreboard(p);
	    
	    plugin.iapi.restorePlayerInventory(p.getName());
        plugin.iapi.restorePlayerArmor(p.getName());
	    
	    PvpEngine.pal.remove(p.getName());
	    
	    if(Startgame.map.containsKey(p.getName())){
	    	String a = Startgame.map.get(p.getName());
	    	Startgame.map.remove(p.getName());
	    	
	    	if(plugin.getConfig().getInt("queue"+a) > 0){
		    	  int q = plugin.getConfig().getInt("queue"+a);
		    	  plugin.getConfig().set("queue"+a, q-1);
		    	  plugin.saveConfig();
		    }
	    	
	    }
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	public void joinGame(Player p, String a){
		p.setGameMode(GameMode.SURVIVAL);
		if(plugin.getConfig().contains("redspawn"+a+".x") && plugin.getConfig().contains("bluespawn"+a+".x")){
			  int bplayers = plugin.getConfig().getInt("bplayers"+a);
	          int rplayers = plugin.getConfig().getInt("rplayers"+a);
	          if(bplayers >= rplayers){
	        	  
	        	  //join red, since they have less
	        	  double x = this.plugin.getConfig().getDouble("redspawn"+a+".x");
	              double y = this.plugin.getConfig().getDouble("redspawn"+a+".y");
	              double z = this.plugin.getConfig().getDouble("redspawn"+a+".z");
	              String wn = this.plugin.getConfig().getString("redspawn"+a+".world");
	              World w = this.plugin.getServer().getWorld(wn);
	              Location l = new Location(w, x, y, z);
	              p.teleport(l);
	              plugin.getConfig().set("rplayers"+a, rplayers+1);
	              plugin.getConfig().createSection("rplist"+a+"." + p.getName());
	              plugin.getConfig().createSection("players"+a+"." + p.getName());
	              plugin.saveConfig();
	              p.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.RED + "You joined the red team!");
	              PvpEngine.pal.put(p.getName(), a);
	              
	              plugin.iapi.storePlayerInventory(p.getName());
	              plugin.iapi.storePlayerArmor(p.getName());
	              p.setHealth(20d);
	              p.setFoodLevel(20);
	              p.setExp(0);
	              p.setLevel(40);
	              
	              ItemStack brod = new ItemStack(Material.BLAZE_ROD, 1);
	              ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
	              ItemStack store = new ItemStack(Material.BOOK);
	              ItemMeta bim = brod.getItemMeta();
	              ItemMeta sim = sword.getItemMeta();
	              ItemMeta stim = store.getItemMeta();
	              bim.setDisplayName(ChatColor.GOLD + "Snowball Gun");
	              sim.setDisplayName(ChatColor.BLUE + "Close-Range Combat Sword");
	              stim.setDisplayName(ChatColor.GREEN + "Open Store");
	              brod.setItemMeta(bim);
	              sword.setItemMeta(sim);
	              store.setItemMeta(stim);
	              p.getInventory().addItem(brod);
	              p.getInventory().addItem(sword);
	              p.getInventory().addItem(store);
	              p.updateInventory();
	              
	              ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
	              LeatherArmorMeta meta = (LeatherArmorMeta)helm.getItemMeta();
	              meta.setColor(Color.RED);
	              helm.setItemMeta(meta);
	              helm.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setHelmet(helm);
	              ItemStack ches = new ItemStack(Material.LEATHER_CHESTPLATE);
	              LeatherArmorMeta metat = (LeatherArmorMeta)ches.getItemMeta();
	              metat.setColor(Color.RED);
	              ches.setItemMeta(metat);
	              ches.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setChestplate(ches);
	              ItemStack legg = new ItemStack(Material.LEATHER_LEGGINGS);
	              LeatherArmorMeta metatt = (LeatherArmorMeta)legg.getItemMeta();
	              metatt.setColor(Color.RED);
	              legg.setItemMeta(metatt);
	              legg.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setLeggings(legg);
	              ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
	              LeatherArmorMeta metattt = (LeatherArmorMeta)boot.getItemMeta();
	              metattt.setColor(Color.RED);
	              boot.setItemMeta(metattt);
	              boot.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setBoots(boot);
	          }else{
	        	  //blue needs more players
	        	  
	        	  double x = this.plugin.getConfig().getDouble("bluespawn"+a+".x");
	              double y = this.plugin.getConfig().getDouble("bluespawn"+a+".y");
	              double z = this.plugin.getConfig().getDouble("bluespawn"+a+".z");
	              String wn = this.plugin.getConfig().getString("bluespawn"+a+".world");
	              World w = this.plugin.getServer().getWorld(wn);
	              Location l = new Location(w, x, y, z);
	              p.teleport(l);
	              plugin.getConfig().set("bplayers"+a, bplayers+1);
	              plugin.getConfig().createSection("bplist"+a+"." + p.getName());
	              plugin.getConfig().createSection("players"+a+"." + p.getName());
	              plugin.saveConfig();
	              p.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.BLUE + "You joined the blue team!");
	              PvpEngine.pal.put(p.getName(), a);
	              
	              plugin.iapi.storePlayerInventory(p.getName());
	              plugin.iapi.storePlayerArmor(p.getName());
	              p.setHealth(20d);
	              p.setFoodLevel(20);
	              p.setExp(0);
	              p.setLevel(40);
	              
	              ItemStack brod = new ItemStack(Material.BLAZE_ROD, 1);
	              ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
	              ItemStack store = new ItemStack(Material.BOOK);
	              ItemMeta bim = brod.getItemMeta();
	              ItemMeta sim = sword.getItemMeta();
	              ItemMeta stim = store.getItemMeta();
	              bim.setDisplayName(ChatColor.GOLD + "Snowball Gun");
	              sim.setDisplayName(ChatColor.BLUE + "Close-Range Combat Sword");
	              stim.setDisplayName(ChatColor.GREEN + "Open Store");
	              brod.setItemMeta(bim);
	              sword.setItemMeta(sim);
	              store.setItemMeta(stim);
	              p.getInventory().addItem(brod);
	              p.getInventory().addItem(sword);
	              p.getInventory().addItem(store);
	              p.updateInventory();
	              
	              ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
	              LeatherArmorMeta meta = (LeatherArmorMeta)helm.getItemMeta();
	              meta.setColor(Color.BLUE);
	              helm.setItemMeta(meta);
	              helm.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setHelmet(helm);
	              ItemStack ches = new ItemStack(Material.LEATHER_CHESTPLATE);
	              LeatherArmorMeta metat = (LeatherArmorMeta)ches.getItemMeta();
	              metat.setColor(Color.BLUE);
	              ches.setItemMeta(metat);
	              ches.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setChestplate(ches);
	              ItemStack legg = new ItemStack(Material.LEATHER_LEGGINGS);
	              LeatherArmorMeta metatt = (LeatherArmorMeta)legg.getItemMeta();
	              metatt.setColor(Color.BLUE);
	              legg.setItemMeta(metatt);
	              legg.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setLeggings(legg);
	              ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
	              LeatherArmorMeta metattt = (LeatherArmorMeta)boot.getItemMeta();
	              metattt.setColor(Color.BLUE);
	              boot.setItemMeta(metattt);
	              boot.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
	              p.getInventory().setBoots(boot);
	              
	              int bp = plugin.getConfig().getInt("bplayers"+a);
		          int rp = plugin.getConfig().getInt("rplayers"+a);
	          }
		}else{
			p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Sorry, that arena hasn't been set up yet.");
		}
	}
}
