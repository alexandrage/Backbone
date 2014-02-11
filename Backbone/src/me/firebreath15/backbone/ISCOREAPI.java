package me.firebreath15.backbone;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/* ISCOREAPI by Firebreath15 v1.5
 * 
 * Written for private use. Do not duplicate or republish.
 */


public class ISCOREAPI
{
  Scoreboard board;
  Objective obj;

  public ISCOREAPI()
  {
    this.board = Bukkit.getScoreboardManager().getNewScoreboard();
  }

  public void setScoreboard(Player p) {
    this.board = p.getScoreboard();
    this.obj = this.board.getObjective(DisplaySlot.SIDEBAR);
  }

  public void createObjective(String name, String desc) {
    if (this.board.getObjective(name) == null) {
      this.obj = this.board.registerNewObjective(name, desc);
      this.obj.setDisplayName(name);
      this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
  }

  public void removeObjective(String obj) {
	  if(board.getObjective(obj)!=null){
		  this.board.getObjective(obj).unregister();
	  }
  }

  public void createTeam(String name) {
    if (this.board.getTeam(name) == null) {
      Team team = this.board.registerNewTeam(name);
      team.setDisplayName(name);
    }
  }

  public void removeTeam(String team) {
	  if(this.board.getTeam(team) != null){
		  this.board.getTeam(team).unregister();
	  }
  }

  public void addPlayerToTeam(String team, Player p) {
    this.board.getTeam(team).addPlayer(p);
  }

  public int getScores(OfflinePlayer p) {
    Score score = this.obj.getScore(p);
    return score.getScore();
  }

  public void setScore(OfflinePlayer p, int sc) {
    Score score = this.obj.getScore(p);
    score.setScore(sc);
  }

  public void removePlayerFromTeam(String team, Player p) {
	  if(this.board.getTeam(team)!=null){
		  if(this.board.getTeam(team).hasPlayer(p)){
			  this.board.getTeam(team).removePlayer(p);
		  }
	  }
  }

  public void refreshPlayerScoreboard(Player p) {
    p.setScoreboard(this.board);
  }

  public Scoreboard getPlayerScoreboard(Player p) {
    return p.getScoreboard();
  }

  public void removePlayerScoreboard(Player p) {
	  //MUST set this to player's scoreboard first!
      board.getObjective(DisplaySlot.SIDEBAR).unregister();
  }
}