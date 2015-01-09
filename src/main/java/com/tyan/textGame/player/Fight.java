package com.tyan.textGame.player;

import com.tyan.textGame.enemy.Enemy;

public interface Fight {
	/**
	 * @author tyan
	 * enemy die : 2
	 * enemy not die : 4
	 * player die : 3
	 */
	public int fight(Player player, Enemy enemy);

}
