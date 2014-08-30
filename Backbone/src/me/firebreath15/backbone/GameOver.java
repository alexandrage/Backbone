package me.firebreath15.backbone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.kitteh.tag.TagAPI;

public class GameOver extends BukkitRunnable{
	
	String a;
	main plugin;
	GameOver(main c, String arena){
		plugin=c;
		a=arena;
	}
	
	public void run(){
		if(plugin.getConfig().getInt("rplayers"+a) >= 1){
			int rp = plugin.getConfig().getInt("rpoints"+a);
			int bp = plugin.getConfig().getInt("bpoints"+a);
			
			if(rp > bp){
				plugin.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.RED+"Red Team won the game in arena "+a+"!");
			}
			
			if(bp > rp){
				plugin.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.BLUE+"Blue Team won the game in arena "+a+"!");
			}
			
			if(bp == rp){
				plugin.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GREEN+"Arena "+a+" ended in a tie.");
			}
			
			plugin.getConfig().set("queue"+a, 0); //reset the lobby queue - the game is over!
			plugin.saveConfig();
			
			@SuppressWarnings("deprecation")
			Player[] players = Bukkit.getServer().getOnlinePlayers();
			for(int i=0; i<players.length; i++){
				Player p = players[i];
				
				if(plugin.getServer().getPluginManager().isPluginEnabled("TagAPI")){
					TagAPI.refreshPlayer(p); //refresh so they no longer see nametags
				}
				
				plugin.cc.removeAndTeleport(p);
			}
		}
	}
}
