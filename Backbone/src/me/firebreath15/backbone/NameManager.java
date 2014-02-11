package me.firebreath15.backbone;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class NameManager implements Listener{
	main plugin;
	NameManager(main c){
		plugin=c;
	}
	
	//Optional support for TagAPI. Players will have colored name tags while playing Backbone
	//if TagAPI is installed on the server and is running properly.
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerReceiveNameTag(AsyncPlayerReceiveNameTagEvent e){
		Player p = e.getNamedPlayer();
		
		if(plugin.getConfig().contains("rplist1."+p.getName()) || plugin.getConfig().contains("rplist2."+p.getName()) || plugin.getConfig().contains("rplist3."+p.getName()) || plugin.getConfig().contains("rplist4."+p.getName()) || plugin.getConfig().contains("rplist5."+p.getName())){
			e.setTag(ChatColor.RED+p.getName());
		}
		
		if(plugin.getConfig().contains("bplist1."+p.getName()) || plugin.getConfig().contains("bplist2."+p.getName()) || plugin.getConfig().contains("bplist3."+p.getName()) || plugin.getConfig().contains("bplist4."+p.getName()) || plugin.getConfig().contains("bplist5."+p.getName())){
			e.setTag(ChatColor.BLUE+p.getName());
		}
		
	}
}
