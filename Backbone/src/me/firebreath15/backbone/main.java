package me.firebreath15.backbone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class main extends JavaPlugin{
	
	public ISCOREAPI sapi;
	public INVAPI iapi;
	public CoreCode cc;
	
	public void onEnable(){
		//set defaults
		sapi=new ISCOREAPI();
		cc=new CoreCode(this);
		iapi=new INVAPI();
		this.getServer().getPluginManager().registerEvents(new Store(this), this);
		this.getServer().getPluginManager().registerEvents(new Gun(this), this);
		this.getServer().getPluginManager().registerEvents(new Logout(this), this);
		this.getServer().getPluginManager().registerEvents(new PvpEngine(this), this);
		this.getServer().getPluginManager().registerEvents(new JoinSigns(this), this);
		this.getServer().getPluginManager().registerEvents(new OnReload(this), this);
		this.getServer().getPluginManager().registerEvents(new BlockBlocker(this), this);
		
		if(this.getServer().getPluginManager().isPluginEnabled("TagAPI")){
			this.getServer().getPluginManager().registerEvents(new NameManager(this), this);
			System.out.println("[Backbone] Hooked into TagAPI successfully!");
		}else{
			System.out.println("[Backbone] Unable to hook into TagAPI. Colored nametags will not be used.");
		}
		
		this.getConfig().set("queue1", 0);
		this.getConfig().set("queue2", 0);
		this.getConfig().set("queue3", 0);
		this.getConfig().set("queue4", 0);
		this.getConfig().set("queue5", 0);
		
		this.getConfig().set("rpoints1", 0);
		this.getConfig().set("rpoints2", 0);
		this.getConfig().set("rpoints3", 0);
		this.getConfig().set("rpoints4", 0);
		this.getConfig().set("rpoints5", 0);
		
		this.getConfig().set("bpoints1", 0);
		this.getConfig().set("bpoints2", 0);
		this.getConfig().set("bpoints3", 0);
		this.getConfig().set("bpoints4", 0);
		this.getConfig().set("bpoints5", 0);
		this.saveConfig();
	}
	
	public void onDisable(){
		Startgame.map.clear();
		PvpEngine.pal.clear();
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("bb") || cmd.getName().equalsIgnoreCase("backbone")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length==0){
					p.sendMessage(ChatColor.RED+"Usage: /bb help");
				}
				if(args.length==1){
					if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("setend") ||  args[0].equalsIgnoreCase("setlobby") || args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("store")){
		/*Help*/		if(args[0].equalsIgnoreCase("help")){
							p.sendMessage(ChatColor.GOLD+"==========["+ChatColor.DARK_PURPLE+" Backbone Commands "+ChatColor.GOLD+"]==========");
							p.sendMessage(ChatColor.RED+"/bb help "+ChatColor.YELLOW+"- show this help page");
							p.sendMessage(ChatColor.RED+"/bb join <arena> "+ChatColor.YELLOW+"- join an arena");
							p.sendMessage(ChatColor.RED+"/bb bspawn <arena> "+ChatColor.YELLOW+"- set the Blue team's spawn");
							p.sendMessage(ChatColor.RED+"/bb rspawn <arena> "+ChatColor.YELLOW+"- set the Red team's spawn");
							p.sendMessage(ChatColor.RED+"/bb setreload <arena> "+ChatColor.YELLOW+"- set the reloader block");
							p.sendMessage(ChatColor.RED+"/bb setend "+ChatColor.YELLOW+"- set the exit");
							p.sendMessage(ChatColor.RED+"/bb setlobby "+ChatColor.YELLOW+"- set the pre-game waitingroom");
							p.sendMessage(ChatColor.RED+"/bb leave "+ChatColor.YELLOW+"- leave the game");
							p.sendMessage(ChatColor.GOLD+"==========["+ChatColor.DARK_PURPLE+" Backbone Commands "+ChatColor.GOLD+"]==========");
						}
		/*SetEnd*/		if(args[0].equalsIgnoreCase("setend")){
							if(p.hasPermission("backbone.setend")){
								this.getConfig().set("spawn.x", p.getLocation().getX());
								this.getConfig().set("spawn.y", p.getLocation().getY());
								this.getConfig().set("spawn.z", p.getLocation().getZ());
								this.getConfig().set("spawn.world", p.getWorld().getName());
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"You set the game's endpoint!");
								this.saveConfig();
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have permission!");
								}
						}
        /*SetLobby*/	if(args[0].equalsIgnoreCase("setlobby")){
							if(p.hasPermission("backbone.setlobby")){
								this.getConfig().set("lobby.x", p.getLocation().getX());
								this.getConfig().set("lobby.y", p.getLocation().getY());
								this.getConfig().set("lobby.z", p.getLocation().getZ());
								this.getConfig().set("lobby.world", p.getWorld().getName());
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"You set the lobby!");
								this.saveConfig();
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have permission!");
								}
						}
		/*Leave*/		if(args[0].equalsIgnoreCase("leave")){
							if(p.hasPermission("backbone.leave")){
								if(this.getConfig().contains("players1."+p.getName()) || this.getConfig().contains("players2."+p.getName()) || this.getConfig().contains("players3."+p.getName()) || this.getConfig().contains("players4."+p.getName()) || this.getConfig().contains("players5."+p.getName()) || Startgame.map.containsKey(p.getName())){
									//if the player is in an arena, or if they are waiting, lets remove them.
									if(this.getConfig().contains("players1."+p.getName()) || this.getConfig().contains("players2."+p.getName()) || this.getConfig().contains("players3."+p.getName()) || this.getConfig().contains("players4."+p.getName()) || this.getConfig().contains("players5."+p.getName())){
										cc.removeAndTeleport(p);
										p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Thanks for playing!");
										Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+p.getName()+" left the game.");
									}
									
									if(Startgame.map.containsKey(p.getName())){
										String a = Startgame.map.get(p.getName());
										
										if(this.getConfig().getInt("queue"+a) > 0){
									    	  int q = this.getConfig().getInt("queue"+a);
									    	  this.getConfig().set("queue"+a, q-1);
									    	  this.saveConfig();
									      }
										
										Startgame.map.remove(p.getName());
										cc.removeAndTeleport(p);
									}
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You aren't playing!");
								}
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have permission!");
							}
						}
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Unknown command or wrong usage!");
					}
				}
				
				if(args.length==2){
					if(args[0].equalsIgnoreCase("join")||args[0].equalsIgnoreCase("bspawn")||args[0].equalsIgnoreCase("rspawn")||args[0].equalsIgnoreCase("setreload")){
		/*Join*/		if(args[0].equalsIgnoreCase("join")){
							if(p.hasPermission("backbone.join")){
								String a = args[1];
								
								if(!(this.getConfig().contains("players1."+p.getName()) || this.getConfig().contains("players2."+p.getName()) || this.getConfig().contains("players3."+p.getName()) || this.getConfig().contains("players4."+p.getName()) || this.getConfig().contains("players5."+p.getName()))){
									if(!(Startgame.map.containsKey(p.getName()))){
										if(a.equalsIgnoreCase("1") || a.equalsIgnoreCase("2") || a.equalsIgnoreCase("3") || a.equalsIgnoreCase("4") || a.equalsIgnoreCase("5")){
											if(!(this.getConfig().getInt("queue"+a)==-1)){
												if(this.getConfig().contains("lobby.x") && this.getConfig().contains("spawn.x")){
													double x = this.getConfig().getDouble("lobby.x");
										            double y = this.getConfig().getDouble("lobby.y");
										            double z = this.getConfig().getDouble("lobby.z");
										            String wn = this.getConfig().getString("lobby.world");
										            World w = this.getServer().getWorld(wn);
										            Location l = new Location(w, x, y, z);
										            p.teleport(l);
													
													int que = this.getConfig().getInt("queue"+a);
													this.getConfig().set("queue"+a, que+1);
													this.saveConfig();
													
													ISCOREAPI api = new ISCOREAPI();
													api.createObjective("Arena_"+a, "Backbone");
													api.createTeam("Player");
													api.setScore(Bukkit.getOfflinePlayer(ChatColor.RED+"Red"), 0);
													api.setScore(Bukkit.getOfflinePlayer(ChatColor.BLUE+"Blue"), 0);
													api.addPlayerToTeam("Player", p);
													api.refreshPlayerScoreboard(p);
													
													Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+p.getName()+ChatColor.GOLD+" joined arena "+a+"!");
													int q = this.getConfig().getInt("queue"+a);
													@SuppressWarnings("unused")
													BukkitTask pre = new Pregame(this, a, p, q).runTaskLater(this, 1);
												}else{
													p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"The game is not completely setup!");
												}
											}else{
												p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"The game is still running!");
											}
										}else{
											p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You must specify which arena!");
										}
									}else{
										p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You are already queued to play!");
									}
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You are already in a game!");
								}
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have permission!");
							}
						}
		/*Bspawn*/		if(args[0].equalsIgnoreCase("bspawn")){
							String a = args[1];
							if(a.equalsIgnoreCase("1")||a.equalsIgnoreCase("2")||a.equalsIgnoreCase("3")||a.equalsIgnoreCase("4")||a.equalsIgnoreCase("5")){
								if(p.hasPermission("backbone.bspawn")){
									this.getConfig().set("bluespawn"+a+".x", p.getLocation().getX());
									this.getConfig().set("bluespawn"+a+".y", p.getLocation().getY());
									this.getConfig().set("bluespawn"+a+".z", p.getLocation().getZ());
									this.getConfig().set("bluespawn"+a+".world", p.getWorld().getName());
									this.saveConfig();
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"You set the Blue team's spawnpoint for arena "+a+"!");
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have permission!");
								}
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Backbone only supports 5 arenas. The arena number must be between 1 and 5!");
							}
						}
		/*Rspawn*/		if(args[0].equalsIgnoreCase("rspawn")){
							String a = args[1];
							if(a.equalsIgnoreCase("1")||a.equalsIgnoreCase("2")||a.equalsIgnoreCase("3")||a.equalsIgnoreCase("4")||a.equalsIgnoreCase("5")){
								if(p.hasPermission("backbone.rspawn")){
									this.getConfig().set("redspawn"+a+".x", p.getLocation().getX());
									this.getConfig().set("redspawn"+a+".y", p.getLocation().getY());
									this.getConfig().set("redspawn"+a+".z", p.getLocation().getZ());
									this.getConfig().set("redspawn"+a+".world", p.getWorld().getName());
									this.saveConfig();
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"You set the Red team's spawnpoint for arena "+a+"!");
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have permission!");
								}
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Backbone only supports 5 arenas. The arena number must be between 1 and 5!");
							}
						}
		/*SetReload*/	if(args[0].equalsIgnoreCase("setreload")){
							String a = args[1];
							if(a.equalsIgnoreCase("1")||a.equalsIgnoreCase("2")||a.equalsIgnoreCase("3")||a.equalsIgnoreCase("4")||a.equalsIgnoreCase("5")){
								if(p.hasPermission("backbone.setreload")){
									this.getServer().getPluginManager().registerEvents(new SetReload(this,a), this);
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Right-click the new Reloader block.");
								}else{
									p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"You don't have permission!");
								}
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Backbone only supports 5 arenas. The arena number must be between 1 and 5!");
							}
						}
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Unknown command or wrong usage!");
					}
				}
				
				if(args.length>=3){
					p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Unknown command or wrong usage!");
				}
			}
			return true;
		}
		return false;
	}
}
