package com.tyan.textGame.adventure;

import com.tyan.textGame.enemy.Enemy;
import com.tyan.textGame.entironment.GameMap;
import com.tyan.textGame.entironment.MapAlpha;
import com.tyan.textGame.player.Fight;
import com.tyan.textGame.player.FightAlpha;
import com.tyan.textGame.player.Player;
import com.tyan.textGame.player.PlayerAlpha;

public class AppController {
	private GameMap gameMap = new MapAlpha(20, 30);
	//private Map<String, String> cached = new HashMap<String, String>();
	
	public void mapInit() {
		gameMap.build(0);
		gameMap.generatePath(0, 0, null);
		gameMap.generateHouse(30);
	}
	
	public String ask(Player player) {
		String rst = "";
		rst += "Cross Terrain:\n";
		rst += gameMap.lookCross(player.getxPos(), player.getyPos());
		return rst;
	}
	
	public Player createPalyer() {
		return new PlayerAlpha(20, 5, 5);
	}
	
	public String init(Player player) {
		gameMap.putPlayer(player);
		return ask(player);
	}
	
	public void askRestart() {
		System.exit(0);
	}
	
	public String command(Player player, String command){
		Fight fight = new FightAlpha();
		
		command = command.toLowerCase();
		int x = gameMap.getX();
		int y = gameMap.getY();
		int judge = 1;
		if (command.equals("m up")) {
			judge = player.move(x, y, 1);
		} else if (command.equals("m down")) {
			judge = player.move(x, y, 2);
		} else if (command.equals("m left")) {
			judge = player.move(x, y, 3);
		} else if (command.equals("m right")) {
			judge = player.move(x, y, 4);
		}
		
		  else if (command.equals("f up")) {
			Enemy enemy = gameMap.getEnemy(player, 1);
			judge = fight.fight(player, enemy);
			gameMap.cleckAlive(enemy);
		} else if (command.equals("f down")) {
			Enemy enemy = gameMap.getEnemy(player, 2);
			judge = fight.fight(player, enemy);
			gameMap.cleckAlive(enemy);
		} else if (command.equals("f left")) {
			Enemy enemy = gameMap.getEnemy(player, 3);
			judge = fight.fight(player, enemy);
			gameMap.cleckAlive(enemy);
		} else if (command.equals("f right")) {
			Enemy enemy = gameMap.getEnemy(player, 4);
			judge = fight.fight(player, enemy);
			gameMap.cleckAlive(enemy);
		}
		
		String rst = "";
		switch (judge) {
		case 0:
			rst = "Can't move beyond the boundaries！";
			break;
		case 2:
			rst = "enemy is dead!";
			break;
		case 4:
			rst = "You is dead！";
			askRestart();
			break;
		case 3:
			rst = "The enemy is not dead！";
			break;

		default:
			rst = ask(player);
		}

		return rst;
	}

}
