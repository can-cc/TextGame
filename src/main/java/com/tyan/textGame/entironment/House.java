package com.tyan.textGame.entironment;

import java.util.ArrayList;
import java.util.Collection;

public class House extends Build {
	protected String name;
	protected Collection<People> npcs;

	public House(int x, int y) {
		this.name = "unname";
		super.x = x;
		super.y = y;
	}
	public House(int x, int y, String name) {
		this.name = name;
		super.x = x;
		super.y = y;
	}
	
	public void addNpc(People npc) {
		if(npcs ==null)
			npcs = new ArrayList<People>();
		npcs.add(npc);
	}
}
