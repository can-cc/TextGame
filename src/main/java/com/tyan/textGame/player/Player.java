package com.tyan.textGame.player;


public interface Player {
	
	public int move(int x, int y, int operation);

	public int getHp();

	public void setHp(int hp);

	public int getAttack();

	public void setAttack(int attack);

	public int getDefense();

	public void setDefense(int defense);

	public int getxPos();

	public void setxPos(int xPos);

	public int getyPos();

	public void setyPos(int yPos);
}
