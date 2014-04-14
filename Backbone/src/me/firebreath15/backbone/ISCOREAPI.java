package me.firebreath15.backbone;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/* Created by Firebreath15 [v2.0]
 * 
 * iScoreAPI is a developer resource for managing scoreboards in Minecraft.
 * Using these simple methods, one can create, destroy, change, update, and manage scoreboards
 * for anyone / everyone on a server. This class is free to use so long as this comment is not
 * removed and the correct author is given credit.
 * 
 */


public class ISCOREAPI{
	Scoreboard board;
	Objective obj;
	
	public ISCOREAPI(){
		board = Bukkit.getScoreboardManager().getNewScoreboard();
	}
	
	public void setScoreboard(Player p){
		board = p.getScoreboard();
		obj = board.getObjective(DisplaySlot.SIDEBAR);
	}
	
	public void createObjective(String name, String desc){
		if(board.getObjective(name) == null){
			obj = board.registerNewObjective(name, desc);
			obj.setDisplayName(name);
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
	}
	
	public String getObjective(){
		return obj.getName();
	}
	
	public void removeObjective(String obj){
		if(board.getObjective(obj)!=null){
			board.getObjective(obj).unregister();
		}
	}
	
	public void createTeam(String name){
		if(board.getTeam(name) == null){
			Team team = board.registerNewTeam(name);
			team.setDisplayName(name);
		}
	}
	
	public void removeTeam(String team){
		if(board.getTeam(team)!=null){
			board.getTeam(team).unregister();
		}
	}
	
	public void addPlayerToTeam(String team, Player p){
		if(board.getTeam(team)!=null){
			board.getTeam(team).addPlayer(p);
		}
	}
	
	@SuppressWarnings("deprecation")
	public int getScores(OfflinePlayer p){
		Score score = obj.getScore(p);
		return score.getScore();
	}
	
	@SuppressWarnings("deprecation")
	public void setScore(OfflinePlayer p, int sc){
		Score score = obj.getScore(p);
		score.setScore(sc);
	}
	
	public void removePlayerFromTeam(String team, Player p){
		if(board.getTeam(team)!=null){
			board.getTeam(team).removePlayer(p);
		}
	}
	
	public void refreshPlayerScoreboard(Player p){
		p.setScoreboard(board);
	}
	
	public Scoreboard getPlayerScoreboard(Player p){
		return p.getScoreboard();
	}
	
	public void removePlayerScoreboard(Player p){
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
}
