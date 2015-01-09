package com.tyan.textGame.exception;

public class NoEnemyException extends RuntimeException {
	
	public NoEnemyException(){
		System.err.println("Not Enemy exist");
	}
}
