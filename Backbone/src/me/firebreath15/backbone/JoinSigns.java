package me.firebreath15.backbone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;

public class JoinSigns implements Listener{
	
	main plugin;
	JoinSigns(main c){
		plugin=c;
	}
	
	@EventHandler
	public void onSignCreate(SignChangeEvent e){
		Player p = e.getPlayer();
		if(e.getLine(0).equalsIgnoreCase("[backbone]")){
			if(p.hasPermission("backbone.sign")){
				e.setLine(0, ChatColor.DARK_PURPLE+"[Backbone]");
				
				if(e.getLine(1).equalsIgnoreCase("join")){
					if(e.getLine(2) != null){
						if(e.getLine(2).equalsIgnoreCase("1") || e.getLine(2).equalsIgnoreCase("2") || e.getLine(2).equalsIgnoreCase("3") || e.getLine(2).equalsIgnoreCase("4") || e.getLine(2).equalsIgnoreCase("5")){
							e.setLine(1, ChatColor.YELLOW+"Join");
							String l2 = e.getLine(2);
							e.setLine(2,"Arena: "+l2);
							e.setLine(3, ChatColor.DARK_BLUE+"Click to play!");
							p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Sign created!");
						}else{
							e.setLine(0, ChatColor.DARK_AQUA+"Warning");
							e.setLine(1, ChatColor.YELLOW+"!ERROR!");
							e.setLine(2, ChatColor.DARK_RED+"Unsupported");
							e.setLine(3, ChatColor.DARK_RED+"Arena #!");
							p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Sign creation error: arena max is 5");
						}
					}else{
						e.setLine(1, ChatColor.YELLOW+"!ERROR!");
						e.setLine(2, ChatColor.DARK_RED+"Unsupported");
						e.setLine(3, ChatColor.DARK_RED+"Action!");
						p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Sign creation error: is it following the format?");
					}
				}else{
					e.setLine(1, ChatColor.YELLOW+"!ERROR!");
					e.setLine(2, ChatColor.DARK_RED+"Unsupported");
					e.setLine(3, ChatColor.DARK_RED+"Action!");
					p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Sign creation error: is it following the format?");
				}
			}else{
				e.setCancelled(true);
				p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Sign creation error: you don't have permission!");
			}
		}
	}
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction()==Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType()==Material.SIGN_POST || e.getClickedBlock().getType()==Material.WALL_SIGN || e.getClickedBlock().getType()==Material.SIGN)){
			Sign s = (Sign)e.getClickedBlock().getState();
			if(s.getLine(0).equalsIgnoreCase(ChatColor.DARK_PURPLE+"[Backbone]")){
				if(s.getLine(1).equalsIgnoreCase(ChatColor.YELLOW+"Join")){
					String l3 = s.getLine(2);
					String a = l3.replaceAll("Arena: ", "");
					if(!(plugin.getConfig().contains("players1."+p.getName()) || plugin.getConfig().contains("players2."+p.getName()) || plugin.getConfig().contains("players3."+p.getName()) || plugin.getConfig().contains("players4."+p.getName()) || plugin.getConfig().contains("players5."+p.getName()))){
						if(!(Startgame.map.containsKey(p.getName()))){
							if(!(plugin.getConfig().getInt("queue"+a)==-1)){
								if(plugin.getConfig().contains("lobby.x")){
									double x = plugin.getConfig().getDouble("lobby.x");
						            double y = plugin.getConfig().getDouble("lobby.y");
						            double z = plugin.getConfig().getDouble("lobby.z");
						            String wn = plugin.getConfig().getString("lobby.world");
						            World w = plugin.getServer().getWorld(wn);
						            Location l = new Location(w, x, y, z);
						            p.teleport(l);
								
									int que = plugin.getConfig().getInt("queue"+a);
									plugin.getConfig().set("queue"+a, que+1);
									plugin.saveConfig();
									
									ISCOREAPI api = new ISCOREAPI();
									api.createObjective("Arena_"+a, "Backbone");
									api.createTeam("Player");
									api.setScore(Bukkit.getOfflinePlayer(ChatColor.RED+"Red"), 0);
									api.setScore(Bukkit.getOfflinePlayer(ChatColor.BLUE+"Blue"), 0);
									api.addPlayerToTeam("Player", p);
									api.refreshPlayerScoreboard(p);
									
									Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+p.getName()+ChatColor.GOLD+" joined arena "+a+"!");
									int q = plugin.getConfig().getInt("queue"+a);
									@SuppressWarnings("unused")
									BukkitTask pre = new Pregame(plugin, a, p, q).runTaskLater(plugin, 20);
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"The game lobby has not been setup!");
								}
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"The game is still running!");
							}
						}else{
							p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You are already queued to play!");
						}
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You are already in a game!");
					}					
				}
			}
		}
	}
}
