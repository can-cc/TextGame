package com.tyan.textGame.enemy;

public interface EnemyFactory {
	
	public Enemy generate(int xPos, int yPos, int type);
}
