package com.tyan.textGame.player;

public class PlayerAlpha implements Player {
	protected int Hp;
	protected int attack;
	protected int defense;
	protected int xPos;
	protected int yPos;
	
	public PlayerAlpha(int hp, int attack, int defense, int xPos, int yPos) {
		super();
		Hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public PlayerAlpha(int hp, int attack, int defense) {
		super();
		Hp = hp;
		this.attack = attack;
		this.defense = defense;
	}
	
	public int getHp() {
		return Hp;
	}
	public void setHp(int hp) {
		Hp = hp;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public int move(int x, int y, int operation) {
		int mx = 0, my = 0;
		switch (operation) {
		case 1:
			mx = this.xPos;
			my = this.yPos - 1;
			break;
		case 2:
			mx = this.xPos;
			my = this.yPos + 1;
			break;
		case 3:
			mx = this.xPos - 1;
			my = this.yPos;
			break;
		case 4:
			mx = this.xPos + 1;
			my = this.yPos;
			break;
		}
		if(mx<0 || mx >=x || my<0 || my>=y)
			return 0;
		else{
			this.xPos = mx;
			this.yPos = my;
			return 1;
		}
		
	}
}
