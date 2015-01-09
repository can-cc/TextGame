package com.tyan.textGame.enemy;

import com.tyan.textGame.exception.NoEnemyException;


public class EnemyFactoryAlpha implements EnemyFactory {
	/**
	 * enemy table
	 * dog : 501
	 * cat : 502
	 * skeleton : 503
	 */
	
	public Enemy generate(int xPos, int yPos, int type) {
		Enemy enemy;
		switch (type) {
		case 501:
			enemy = new Dog(xPos, yPos);
			break;
		case 502:
			enemy = new Cat(xPos, yPos);
			break;
		default:
			throw new NoEnemyException();
		}
		return enemy;
	}
}
