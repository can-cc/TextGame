package com.tyan.textGame.entironment;

import java.util.Collection;

public class Npc extends People {
	protected Collection<String> randomTalk;
	
	public Npc(String name) {
		super.name = name;
	}
}
