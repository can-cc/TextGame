package com.tyan.textGame.enemy;

public class Dog extends Enemy {

	/**
	 * Dog info
	 * hp:10
	 * attack:3
	 * defense:3
	 */
	
	public Dog(int xPos, int yPos){
		super.HP = 10;
		super.attack = 3;
		super.defense = 2;
		super.xPos = xPos;
		super.yPos = yPos;
	}
	
}
