package com.tyan.textGame.player;

import com.tyan.textGame.enemy.Enemy;

public class FightAlpha implements Fight {
	/**
	 * @author tyan
	 * enemy die : 2
	 * enemy not die : 4
	 * player die : 3
	 */

	public int fight(Player player, Enemy enemy) {
		int pAttack = player.getAttack();
		int pDefense = player.getDefense();
		int php = player.getHp();
		
		int eAttack = enemy.getAttack();
		int eDefense = enemy.getDefense();
		int ehp = enemy.getHP();
		
		
		if(pAttack > eDefense){
			enemy.setHP(ehp - (pAttack - eDefense));
		} else if(pAttack < eDefense) {
			player.setHp(php - (eDefense - pAttack));
		}
		
		php = player.getHp();
		ehp = enemy.getHP();
		if(php <= 0)
			return 3;
		else if(ehp <= 0)
			return 2;
		else
			return 3;
	}

}
