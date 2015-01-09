package com.tyan.textGame.adventure;

import java.util.Scanner;

import com.tyan.textGame.player.Player;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome" );
        AppController appCtrl = new AppController();
        appCtrl.mapInit();
        
        Player me = appCtrl.createPalyer();
        appCtrl.init(me);
        
        Scanner scanner = new Scanner(System.in);
        print(appCtrl.ask(me));
        while(true){
        	print("please input command:");
        	String command = scanner.nextLine();
        	print(appCtrl.command(me, command));
        }
        
    }
    
    public static void print(String string) {
    	System.out.println(string);
    }
}
