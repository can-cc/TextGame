package com.tyan.textGame.entironment;

import com.tyan.textGame.enemy.Enemy;
import com.tyan.textGame.player.Player;

public interface GameMap{
	public void putPlayer(Player player);
	public String look(int xPos, int yPos);
	public String lookCross(int xPos, int yPos);
	public int getX();
	public int getY();
	public void build(int seed);
	public void generateHouse(int n);
	public void generatePath(int x, int y, int[][] pathMap);
	public Enemy getEnemy(Player player, int direction);
	public void cleckAlive(Enemy enemy);
}
