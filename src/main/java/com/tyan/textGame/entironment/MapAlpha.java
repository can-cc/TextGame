package com.tyan.textGame.entironment;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.tyan.textGame.enemy.Enemy;
import com.tyan.textGame.enemy.EnemyFactory;
import com.tyan.textGame.enemy.EnemyFactoryAlpha;
import com.tyan.textGame.exception.NoEnemyException;
import com.tyan.textGame.player.Player;


public class MapAlpha implements GameMap {
	
	/**
	 * symbolCode table
	 * lawn : 100
	 * path : 101
	 * house : 201
	 */
	protected int x;
	protected int y;
	protected int size;
	protected int[][] map;
	protected EnemyFilter enemyFilter;
	
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public MapAlpha(int x, int y) {
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.map = new int[y][x];
		enemyFilter = new EnemyFilter();
	}
	
	//to merge
	public static Map<Integer, String> symbolCode = new HashMap<Integer, String>();
	static{
		symbolCode.put(100, "ﺴ");
		symbolCode.put(101, "❏");
		symbolCode.put(102, "♛");
	}
	public static Map<Integer, String> enemyCode = new HashMap<Integer, String>();
	static{
		enemyCode.put(501, "Dog");
		enemyCode.put(502, "Cat");
		enemyCode.put(503, "skeleton");
	}
	public static Map<Integer, String> terrainCode = new HashMap<Integer, String>();
	static{
		terrainCode.put(100, "lawn");
		terrainCode.put(101, "path");
		terrainCode.put(102, "house");
	}
	
	private void generateHouse(int seed, int n){
		Random random = new Random(seed);
		for(int i=0; i<n; i++){
			int x = random.nextInt(this.x);
			int y = random.nextInt(this.y);
			map[x][y] = 102;
		}
	}

	public void putPlayer(Player player) {
		Random random = new Random();
		while(true){
			int xPos = random.nextInt(x);
			int yPos = random.nextInt(y);
			if(map[yPos][xPos] < 200){
				player.setxPos(xPos);
				player.setyPos(yPos);
				break;
			}
		}
	}
	
	public void generateHouse(int n){
		Random random = new Random();
		for(int i=0; i<n; i++){
			int x = random.nextInt(this.x);
			int y = random.nextInt(this.y);
			map[y][x] = 102;
		}
	}
	
	public Enemy getEnemy(Player player, int direction) {
		int xPos = player.getxPos();
		int yPos = player.getyPos();
		
		int mx = 0, my = 0;
		switch (direction) {
		case 1:
			mx = xPos;
			my = yPos - 1;
			break;
		case 2:
			mx = xPos;
			my = yPos + 1;
			break;
		case 3:
			mx = xPos - 1;
			my = yPos;
			break;
		case 4:
			mx = xPos + 1;
			my = yPos;
			break;
		}
		return enemyFilter.getEnemy(mx, my);
	}
	
	private static int[] random_serial(int limit) {
		int[] result = new int[limit];
		for (int i = 0; i < limit; i++)
			result[i] = i + 1;
		int w;
		Random rand = new Random();
		for (int i = limit - 1; i > 0; i--) {
			w = rand.nextInt(i);
			int t = result[i];
			result[i] = result[w];
			result[w] = t;
		}
		return result;
	}
	
	public void build(int seed) {
		//full with lawn
		for (int i = 0; i < size; i++) {
			map[i / x][i % x] = 100;
		}
	}
	
	public void show() {
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				System.out.print(symbolCode.get(map[i][j]) + " ");
			}
			System.out.println();
		}
	}
	
	public void show(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	//x , y is start point
	public void generatePath(int x, int y, int[][] pathMap) {
		if (pathMap == null) {
			int px = (this.x - 1) / 2;
			int py = (this.y - 1) / 2;
			int size = px * py;
			pathMap = new int[py][px];
			for (int i = 0; i < size; i++) {
				pathMap[i / px][i % px] = 0;
			}
		}
		pathMap[y][x] = 1;
		int[] direction = random_serial(4);
		for(int d : direction) {
			int nx = 0, ny = 0;
			switch (d) {
			case 1:
				nx = x;
				ny = y - 1;
				break;
			case 2:
				nx = x;
				ny = y + 1;
				break;
			case 3:
				nx = x - 1;
				ny = y;
				break;
			case 4:
				nx = x + 1;
				ny = y;
				break;
			}
			if( nx < 0 || nx >= ((this.x - 1) / 2) || ny < 0 || ny >= ((this.y - 1) / 2) ){}
			else{
				if(pathMap[ny][nx] != 1){
					int mx = 2*x + 1;
			        int my = 2*y + 1;
			        switch (d) {
			        case 1:
			        	map[my][mx] = 101;
						map[my-1][mx] = 101;
						break;
					case 2:
						map[my][mx] = 101;
						map[my+1][mx] = 101;
						break;
					case 3:
						map[my][mx] = 101;
						map[my][mx+1] = 101;
						break;
					case 4:
						map[my][mx] = 101;
						map[my][mx+1] = 101;
						break;
					}
			        generatePath(nx, ny, pathMap);
				}
			}
		}
	}
	
	
	public String lookCross(int xPos, int yPos) {
		String rst = "";
		String[] cases = new String[4];
		for(String ecase : cases){
			ecase = "";
		}
		int enemyN = 0;
		int[] xD = {0, 0, -1, 1}, yD = {-1, 1, 0, 0};
		for(int i=0; i<4; i++){
			int mx = xPos + xD[i];
			int my = yPos + yD[i];
			if(mx >= x || mx < 0 || my >= y || my < 0){
				cases[i] = "borderline!!!!";
				continue;
			}
			cases[i] = terrainCode.get(map[my][mx]);
			if(enemyFilter.enemyMap[my][mx] != 0) {
				cases[i] = enemyCode.get(enemyFilter.enemyMap[my][mx]);
				enemyN ++;
			}
		}
		if(enemyN == 0) this.enemyFilter.generateEnemy(xPos, yPos, 5);
		rst += "\t" + cases[0] + "\t\t\n";
		rst += cases[2] + "\t";
		rst += terrainCode.get(map[yPos][xPos]) + "\t";
		rst += cases[3] + "\t\n";
		rst += "\t" + cases[1] + "\t\t\n";
		//show(enemyFilter.enemyMap);
		//System.out.println();
		return rst;
	}
	
	public String look(int xPos, int yPos) {
		String rst = "";
		String[] cases = new String[4];
		for(String ecase : cases){
			ecase = "";
		}
		int[] xD = {0, 0, -1, 1}, yD = {-1, 1, 0, 0};
		for(int i=0; i<4; i++){
			int mx = xPos + xD[i];
			int my = yPos + yD[i];
			if(mx > x || mx < 0 || my > y || my < 0){
				cases[i] = "borderline!!!!";
				continue;
			}
			cases[i] = terrainCode.get(map[my][mx]);
			if(enemyFilter.enemyMap[my][mx] != 0)
				cases[i] = enemyCode.get(enemyFilter.enemyMap[my][mx]);
		}
		rst += "You are current in " + terrainCode.get(map[yPos][xPos]) + "\n";
		rst += "Up is " + cases[0] + "\n";
		rst += "Down is " + cases[1] + "\n";
		rst += "Left is " + cases[2] + "\n";
		rst += "Right is " + cases[3] + "\n";
		return rst;
	}
	
	public void generateEnemy(Player player, int n) {
		this.enemyFilter.generateEnemy(player.getxPos(), player.getyPos(), n);
	}
	
	
	public static void main(String args[]){
		MapAlpha map = new MapAlpha(20,30);
		map.build(0);
		map.generatePath(0, 0, null);
		map.generateHouse(20);
		map.show();
	}

	class EnemyFilter {
		protected int[][] enemyMap = new int[y][x];
		protected EnemyFactory eFactory = new EnemyFactoryAlpha(); 
		
		public EnemyFilter() {
			enemyMap = new int[y][x];
			build();
		}
		
		public Enemy getEnemy(int mx, int my) {
			int enemyCode = enemyMap[my][mx];
			if(enemyCode == 0) throw new NoEnemyException();
			return eFactory.generate(mx, my, enemyCode);
		}

		private void build() {
			for (int i = 0; i < size; i++) {
				enemyMap[i / x][i % x] = 0;
			}
		}
		
		public void clean() {
			build();
		}
		
		public void cleanBody(int xPos, int yPos){
			
		}
		
		public void generateEnemy(int xPos, int yPos, int n) {
			Random random = new Random();
			for(int i=0; i<n; i++){
				int ex = random.nextInt(xPos + 3) % 6 + (xPos - 3);
				int ey = random.nextInt(yPos + 3) % 6 + (xPos - 3);
				ex = ex>=x ? x-1 : ex;
				ex = ex<0 ? 0 : ex;
				ey = ey>=y ? y-1 : ey;
				ey = ey<0 ? 0 : ey;
				if(map[ey][ex] < 200){
					int enemyType = random.nextInt(3) + 501;
					enemyMap[ey][ex] = enemyType;
				}
			}
		}
		
		public int check(int xPos, int yPos) {
			return enemyMap[yPos][xPos];
		}
		
		public void remove(int xPos, int yPos) {
			enemyMap[yPos][xPos] = 0;
		}
	}

	public void cleckAlive(Enemy enemy) {
		if(enemy.getHP() <= 0){
			enemyFilter.cleanBody(enemy.getxPos(), enemy.getyPos());
		}
	}
}
