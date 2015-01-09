package com.tyan.textGame.enemy;

public class Cat extends Enemy {
	
	public Cat(int xPos, int yPos) {
		super.HP = 10;
		super.attack = 2;
		super.defense = 3;
		super.xPos = xPos;
		super.yPos = yPos;
	}
}
