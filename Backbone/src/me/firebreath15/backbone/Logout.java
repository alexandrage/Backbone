package me.firebreath15.backbone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Logout implements Listener{
	main plugin;
	Logout(main c){
		plugin=c;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(plugin.getConfig().contains("players1."+p.getName()) || plugin.getConfig().contains("players2."+p.getName()) || plugin.getConfig().contains("players3."+p.getName()) || plugin.getConfig().contains("players4."+p.getName()) || plugin.getConfig().contains("players5."+p.getName()) || Startgame.map.containsKey(p.getName())){
			plugin.cc.removeAndTeleport(e.getPlayer());
			Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+e.getPlayer().getName()+" left the game.");
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e){
		Player p = e.getPlayer();
		if(plugin.getConfig().contains("players1."+p.getName()) || plugin.getConfig().contains("players2."+p.getName()) || plugin.getConfig().contains("players3."+p.getName()) || plugin.getConfig().contains("players4."+p.getName()) || plugin.getConfig().contains("players5."+p.getName()) || Startgame.map.containsKey(p.getName())){
			plugin.cc.removeAndTeleport(e.getPlayer());
			Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"[Backbone] "+ChatColor.GOLD+e.getPlayer().getName()+" left the game.");
		}
	}
}
