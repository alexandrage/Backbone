package me.firebreath15.backbone;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.kitteh.tag.TagAPI;

public class Startgame extends BukkitRunnable{
	
	main plugin;
	Startgame(main c){
		plugin=c;
	}
	
	public static HashMap<String, String> map = new HashMap<String, String>();
	
	//When this runs, the players playing will be sent to CoreCode to be sent to the game. The HashMap is storing info on their arena #.
	
	public void run(){
		//the countdown has ended
		Player[] ops = Bukkit.getServer().getOnlinePlayers();
		String a="";
		
		if(map.size() >= 2){ //if 2 players are queued and ready to play, then lets go!
			
			for(int i=0; i<ops.length; i++){
				Player p = ops[i];
				if(map.containsKey(p.getName())){
					p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+"Countdown has ended!");
					a = map.get(p.getName());
					plugin.cc.joinGame(p, a);
					map.remove(p.getName());
					TagAPI.refreshPlayer(p); //let them refresh to see colored nametags
				}
			}
			
			plugin.getConfig().set("queue"+a, -1); //set to -1 to lock out players after game starts
			plugin.saveConfig();
			
			@SuppressWarnings("unused")
			BukkitTask endGame=new GameOver(plugin, a).runTaskLater(plugin, 6000); //START TE CLOCK
			
		}else{ //if a player left during the countdown, they ruined everyone's fun! Lets tell on them >:D
			
			for(int i=0; i<ops.length; i++){
				Player p = ops[i];
				if(map.containsKey(p.getName())){
					p.sendMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Error: There aren't enough players!");
				}
			}
			
		}
	}
}
