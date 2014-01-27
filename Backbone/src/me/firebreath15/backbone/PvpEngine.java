package me.firebreath15.backbone;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PvpEngine implements Listener{
	
	main plugin;
	PvpEngine(main c){
		plugin=c;
	}
	
	public static HashMap<String, String> pal = new HashMap<String, String>();
	
	@EventHandler
	public void managePvp(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
/*Snow*/    if(e.getDamager() instanceof Snowball){
				Player v = (Player)e.getEntity();
				Snowball s = (Snowball)e.getDamager();
				Player d = (Player)s.getShooter();
				if(pal.containsKey(v.getName()) && pal.containsKey(d.getName())){
					String a = pal.get(v.getName());
					if((plugin.getConfig().contains("rplist"+a+"."+v.getName()) && plugin.getConfig().contains("bplist"+a+"."+d.getName())) || (plugin.getConfig().contains("bplist"+a+"."+v.getName()) && plugin.getConfig().contains("rplist"+a+"."+d.getName()))){
						Damageable da = v;
						if(da.getHealth() >= 3d){
							e.setDamage(2d);
						}else{
							
							//if on red
							if(plugin.getConfig().contains("rplist"+a+"."+v.getName())){
								Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("redspawn"+a+".world")), plugin.getConfig().getDouble("redspawn"+a+".x"), plugin.getConfig().getDouble("redspawn"+a+".y"), plugin.getConfig().getDouble("redspawn"+a+".z"));
								v.teleport(l);
								
								Firework firework = (Firework)v.getWorld().spawn(v.getLocation(), Firework.class);
				                FireworkMeta data = firework.getFireworkMeta();
				                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build() });
				                data.setPower(0);
				                firework.setFireworkMeta(data);
				                
				                v.setExp(0);
				                v.setLevel(40);
				                v.setHealth(20d);
				                v.setFoodLevel(20);
				                
				                int pts = plugin.getConfig().getInt(d.getName());
				                plugin.getConfig().set(d.getName(), pts+100);
				                plugin.saveConfig();
				                d.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You killed " + ChatColor.RED + v.getName() + ChatColor.GOLD + "! " + ChatColor.DARK_PURPLE + "+100");
				                v.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You were killed by " + ChatColor.RED + d.getName());
				                
				                int teamscore = plugin.getConfig().getInt("bpoints"+a);
				                plugin.getConfig().set("bpoints"+a, teamscore+1);
				                plugin.saveConfig();
				                
				                Player[] online = Bukkit.getServer().getOnlinePlayers();
				                for (int i = 0; i < online.length; i++) {
				                	Player o = online[i];
				                	if(plugin.getConfig().contains("players"+a+"."+o.getName())){
				                		plugin.sapi.setScoreboard(o);
				                        int sc = plugin.sapi.getScores(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"));
				                        plugin.sapi.setScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"), sc + 1);
				                        o.playSound(o.getLocation(), Sound.ORB_PICKUP, 10, 7);
				                	}
				                }
							}
							
							//if on blue
							if(plugin.getConfig().contains("bplist"+a+"."+v.getName())){
								Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("bluespawn"+a+".world")), plugin.getConfig().getDouble("bluespawn"+a+".x"), plugin.getConfig().getDouble("bluespawn"+a+".y"), plugin.getConfig().getDouble("bluespawn"+a+".z"));
								v.teleport(l);
								
								Firework firework = (Firework)v.getWorld().spawn(v.getLocation(), Firework.class);
				                FireworkMeta data = firework.getFireworkMeta();
				                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build() });
				                data.setPower(0);
				                firework.setFireworkMeta(data);
				                
				                v.setExp(0);
				                v.setLevel(40);
				                v.setHealth(20d);
				                v.setFoodLevel(20);
				                
				                int pts = plugin.getConfig().getInt(d.getName());
				                plugin.getConfig().set(d.getName(), pts+100);
				                plugin.saveConfig();
				                d.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You killed " + ChatColor.RED + v.getName() + ChatColor.GOLD + "! " + ChatColor.DARK_PURPLE + "+100");
				                v.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You were killed by " + ChatColor.RED + d.getName());
				                
				                int teamscore = plugin.getConfig().getInt("rpoints"+a);
				                plugin.getConfig().set("rpoints"+a, teamscore+1);
				                plugin.saveConfig();
				                
				                Player[] online = Bukkit.getServer().getOnlinePlayers();
				                for (int i = 0; i < online.length; i++) {
				                	Player o = online[i];
				                	if(plugin.getConfig().contains("players"+a+"."+o.getName())){
				                		plugin.sapi.setScoreboard(o);
				                        int sc = plugin.sapi.getScores(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"));
				                        plugin.sapi.setScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"), sc + 1);
				                        o.playSound(o.getLocation(), Sound.ORB_PICKUP, 10, 7);
				                	}
				                }
							}
						}
					}else{
						e.setCancelled(true);
					}
				}else{
					if(pal.containsKey(v.getName())){
						e.setCancelled(true);
					}
				}
			}
			
/*Arrow*/   if(e.getDamager() instanceof Arrow){		//Arrow is important since the BombBow is a buyable item and can be used to kill
				Player v = (Player)e.getEntity();
				Arrow arr = (Arrow)e.getDamager();
				if(arr.getShooter() instanceof Player){
					Player d = (Player)arr.getShooter();
					if(pal.containsKey(v.getName()) && pal.containsKey(d.getName())){
						String a = pal.get(v.getName());
						if((plugin.getConfig().contains("rplist"+a+"."+v.getName()) && plugin.getConfig().contains("bplist"+a+"."+d.getName())) || (plugin.getConfig().contains("bplist"+a+"."+v.getName()) && plugin.getConfig().contains("rplist"+a+"."+d.getName()))){
							Damageable da = v;
							if(da.getHealth() >= 3d){
								e.setDamage(2d);
							}else{
								
								//if on red
								if(plugin.getConfig().contains("rplist"+a+"."+v.getName())){
									Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("redspawn"+a+".world")), plugin.getConfig().getDouble("redspawn"+a+".x"), plugin.getConfig().getDouble("redspawn"+a+".y"), plugin.getConfig().getDouble("redspawn"+a+".z"));
									v.teleport(l);
									
									Firework firework = (Firework)v.getWorld().spawn(v.getLocation(), Firework.class);
					                FireworkMeta data = firework.getFireworkMeta();
					                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build() });
					                data.setPower(0);
					                firework.setFireworkMeta(data);
					                
					                v.setExp(0);
					                v.setLevel(40);
					                v.setHealth(20d);
					                v.setFoodLevel(20);
					                
					                int pts = plugin.getConfig().getInt(d.getName());
					                plugin.getConfig().set(d.getName(), pts+100);
					                plugin.saveConfig();
					                d.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You killed " + ChatColor.RED + v.getName() + ChatColor.GOLD + "! " + ChatColor.DARK_PURPLE + "+100");
					                v.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You were killed by " + ChatColor.RED + d.getName());
					                
					                int teamscore = plugin.getConfig().getInt("bpoints"+a);
					                plugin.getConfig().set("bpoints"+a, teamscore+1);
					                plugin.saveConfig();
					                
					                Player[] online = Bukkit.getServer().getOnlinePlayers();
					                for (int i = 0; i < online.length; i++) {
					                	Player o = online[i];
					                	if(plugin.getConfig().contains("players"+a+"."+o.getName())){
					                		plugin.sapi.setScoreboard(o);
					                        int sc = plugin.sapi.getScores(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"));
					                        plugin.sapi.setScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"), sc + 1);
					                        o.playSound(o.getLocation(), Sound.ORB_PICKUP, 10, 7);
					                	}
					                }
								}
								
								//if on blue
								if(plugin.getConfig().contains("bplist"+a+"."+v.getName())){
									Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("bluespawn"+a+".world")), plugin.getConfig().getDouble("bluespawn"+a+".x"), plugin.getConfig().getDouble("bluespawn"+a+".y"), plugin.getConfig().getDouble("bluespawn"+a+".z"));
									v.teleport(l);
									
									Firework firework = (Firework)v.getWorld().spawn(v.getLocation(), Firework.class);
					                FireworkMeta data = firework.getFireworkMeta();
					                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build() });
					                data.setPower(0);
					                firework.setFireworkMeta(data);
					                
					                v.setExp(0);
					                v.setLevel(40);
					                v.setHealth(20d);
					                v.setFoodLevel(20);
					                
					                int pts = plugin.getConfig().getInt(d.getName());
					                plugin.getConfig().set(d.getName(), pts+100);
					                plugin.saveConfig();
					                d.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You killed " + ChatColor.RED + v.getName() + ChatColor.GOLD + "! " + ChatColor.DARK_PURPLE + "+100");
					                v.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You were killed by " + ChatColor.RED + d.getName());
					                
					                int teamscore = plugin.getConfig().getInt("rpoints"+a);
					                plugin.getConfig().set("rpoints"+a, teamscore+1);
					                plugin.saveConfig();
					                
					                Player[] online = Bukkit.getServer().getOnlinePlayers();
					                for (int i = 0; i < online.length; i++) {
					                	Player o = online[i];
					                	if(plugin.getConfig().contains("players"+a+"."+o.getName())){
					                		plugin.sapi.setScoreboard(o);
					                        int sc = plugin.sapi.getScores(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"));
					                        plugin.sapi.setScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"), sc + 1);
					                        o.playSound(o.getLocation(), Sound.ORB_PICKUP, 10, 7);
					                	}
					                }
								}
							}
						}else{
							e.setCancelled(true);
						}
					}else{
						if(pal.containsKey(v.getName())){
							e.setCancelled(true);
						}
					}
				}
			}
			
/*Player*/	if(e.getDamager() instanceof Player){
				Player v = (Player)e.getEntity();
				Player d = (Player)e.getDamager();
				if(pal.containsKey(v.getName()) && pal.containsKey(d.getName())){
					String a = pal.get(v.getName());
					if((plugin.getConfig().contains("rplist"+a+"."+v.getName()) && plugin.getConfig().contains("bplist"+a+"."+d.getName())) || (plugin.getConfig().contains("bplist"+a+"."+v.getName()) && plugin.getConfig().contains("rplist"+a+"."+d.getName()))){
						if(d.getItemInHand().getType()==Material.DIAMOND_SWORD){ //if the attacker is using his sword
							Damageable da = v;
							if(da.getHealth() >= 5d){
								e.setDamage(4d);
							}else{
								
								//if on red
								if(plugin.getConfig().contains("rplist"+a+"."+v.getName())){
									Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("redspawn"+a+".world")), plugin.getConfig().getDouble("redspawn"+a+".x"), plugin.getConfig().getDouble("redspawn"+a+".y"), plugin.getConfig().getDouble("redspawn"+a+".z"));
									v.teleport(l);
									
									Firework firework = (Firework)v.getWorld().spawn(v.getLocation(), Firework.class);
					                FireworkMeta data = firework.getFireworkMeta();
					                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build() });
					                data.setPower(0);
					                firework.setFireworkMeta(data);
					                
					                v.setExp(0);
					                v.setLevel(40);
					                v.setHealth(20d);
					                v.setFoodLevel(20);
					                
					                int pts = plugin.getConfig().getInt(d.getName());
					                plugin.getConfig().set(d.getName(), pts+100);
					                plugin.saveConfig();
					                d.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You killed " + ChatColor.RED + v.getName() + ChatColor.GOLD + "! " + ChatColor.DARK_PURPLE + "+100");
					                v.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You were killed by " + ChatColor.RED + d.getName());
					                
					                int teamscore = plugin.getConfig().getInt("bpoints"+a);
					                plugin.getConfig().set("bpoints"+a, teamscore+1);
					                plugin.saveConfig();
					                
					                Player[] online = Bukkit.getServer().getOnlinePlayers();
					                for (int i = 0; i < online.length; i++) {
					                	Player o = online[i];
					                	if(plugin.getConfig().contains("players"+a+"."+o.getName())){
					                		plugin.sapi.setScoreboard(o);
					                        int sc = plugin.sapi.getScores(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"));
					                        plugin.sapi.setScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"), sc + 1);
					                        o.playSound(o.getLocation(), Sound.ORB_PICKUP, 10, 7);
					                	}
					                }
								}
								
								//if on blue
								if(plugin.getConfig().contains("bplist"+a+"."+v.getName())){
									Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("bluespawn"+a+".world")), plugin.getConfig().getDouble("bluespawn"+a+".x"), plugin.getConfig().getDouble("bluespawn"+a+".y"), plugin.getConfig().getDouble("bluespawn"+a+".z"));
									v.teleport(l);
									
									Firework firework = (Firework)v.getWorld().spawn(v.getLocation(), Firework.class);
					                FireworkMeta data = firework.getFireworkMeta();
					                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build() });
					                data.setPower(0);
					                firework.setFireworkMeta(data);
					                
					                v.setExp(0);
					                v.setLevel(40);
					                v.setHealth(20d);
					                v.setFoodLevel(20);
					                
					                int pts = plugin.getConfig().getInt(d.getName());
					                plugin.getConfig().set(d.getName(), pts+100);
					                plugin.saveConfig();
					                d.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You killed " + ChatColor.RED + v.getName() + ChatColor.GOLD + "! " + ChatColor.DARK_PURPLE + "+100");
					                v.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "You were killed by " + ChatColor.RED + d.getName());
					                
					                int teamscore = plugin.getConfig().getInt("rpoints"+a);
					                plugin.getConfig().set("rpoints"+a, teamscore+1);
					                plugin.saveConfig();
					                
					                Player[] online = Bukkit.getServer().getOnlinePlayers();
					                for (int i = 0; i < online.length; i++) {
					                	Player o = online[i];
					                	if(plugin.getConfig().contains("players"+a+"."+o.getName())){
					                		plugin.sapi.setScoreboard(o);
					                        int sc = plugin.sapi.getScores(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"));
					                        plugin.sapi.setScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"), sc + 1);
					                        o.playSound(o.getLocation(), Sound.ORB_PICKUP, 10, 7);
					                	}
					                }
								}
							}
						}else{
							//player isn't using sword. is he using his nausea stick?
							if(d.getItemInHand().getType()==Material.GOLD_HOE){
								e.setCancelled(true);
								int amt = d.getItemInHand().getAmount();
								d.getItemInHand().setAmount(amt-1);
								v.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 2));
								v.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 2));
								v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 1));
							}else{
								e.setCancelled(true);
								d.sendMessage(ChatColor.DARK_PURPLE + "[Backbone] " + ChatColor.GOLD + "Use your Diamond Sword!");
							}
						}
					}else{
						e.setCancelled(true);
					}
				}else{
					if(pal.containsKey(v.getName())){
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void arenaDeath(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player)e.getEntity();
			String a = pal.get(p.getName());
			if(plugin.getConfig().contains("rplist"+a+"."+p.getName())){
				if(e.getCause().equals(DamageCause.BLOCK_EXPLOSION) || e.getCause().equals(DamageCause.DROWNING) || e.getCause().equals(DamageCause.FALL) || e.getCause().equals(DamageCause.FIRE) || e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.MAGIC) || e.getCause().equals(DamageCause.LIGHTNING) || e.getCause().equals(DamageCause.VOID) || e.getCause().equals(DamageCause.SUFFOCATION)){
					Damageable da = p;
					if(da.getHealth() >= 3){
						e.setDamage(2d);
					}else{
						e.setCancelled(true);
						Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("redspawn"+a+".world")), plugin.getConfig().getDouble("redspawn"+a+".x"), plugin.getConfig().getDouble("redspawn"+a+".y"), plugin.getConfig().getDouble("redspawn"+a+".z"));
						p.teleport(l);
						
						Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
		                FireworkMeta data = firework.getFireworkMeta();
		                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build() });
		                data.setPower(0);
		                firework.setFireworkMeta(data);
		                
		                p.setExp(0);
		                p.setLevel(40);
		                p.setHealth(20d);
		                p.setFoodLevel(20);
		                p.setFireTicks(0);
					}
				}
			}
			if(plugin.getConfig().contains("bplist"+a+"."+p.getName())){
				if(e.getCause().equals(DamageCause.BLOCK_EXPLOSION) || e.getCause().equals(DamageCause.DROWNING) || e.getCause().equals(DamageCause.FALL) || e.getCause().equals(DamageCause.FIRE) || e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.MAGIC) || e.getCause().equals(DamageCause.LIGHTNING) || e.getCause().equals(DamageCause.VOID) || e.getCause().equals(DamageCause.SUFFOCATION)){
					Damageable da = p;
					if(da.getHealth() >= 3){
						e.setDamage(2d);
					}else{
						e.setCancelled(true);
						Location l = new Location(Bukkit.getWorld(plugin.getConfig().getString("bluespawn"+a+".world")), plugin.getConfig().getDouble("bluespawn"+a+".x"), plugin.getConfig().getDouble("bluespawn"+a+".y"), plugin.getConfig().getDouble("bluespawn"+a+".z"));
						p.teleport(l);
						
						Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
		                FireworkMeta data = firework.getFireworkMeta();
		                data.addEffects(new FireworkEffect[] { FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build() });
		                data.setPower(0);
		                firework.setFireworkMeta(data);
		                
		                p.setExp(0);
		                p.setLevel(40);
		                p.setHealth(20d);
		                p.setFoodLevel(20);
		                p.setFireTicks(0);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void bombBowHandler(ProjectileHitEvent e){
		if(e.getEntity().getShooter() instanceof Player){
			if(e.getEntity() instanceof Arrow){
				Player p = (Player)e.getEntity().getShooter();
				if(plugin.getConfig().contains("players1."+p.getName()) || plugin.getConfig().contains("players2."+p.getName()) || plugin.getConfig().contains("players3."+p.getName()) || plugin.getConfig().contains("players4."+p.getName()) || plugin.getConfig().contains("players5."+p.getName())){
					e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 0);
					e.getEntity().remove();
				}
			}
		}
	}
}
