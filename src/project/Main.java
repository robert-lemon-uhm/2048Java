package project;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Main {

	/*	2048 by Robert Lemon
	 * 
	 * 	This program utilizes 2D arrays, inheritance, and public/private member variables/functions
	 * 
	 * 	Instructions appear on screen when the game is started.  Press "y" to instantly win, and press "u" to instantly lose
	 * 
	 */
	
	
	// public variables
	public static boolean ContinueGame = true;
	public static int[][] xCoords = new int[4][4];
	public static int[][] yCoords = new int[4][4];
	public static Tile[][] Tiles = new Tile[4][4];
	
	
	public static void main(String[] args) {
		
		EZ.initialize(580, 580);
		EZ.setBackgroundColor(new Color(187, 173, 160));
		
		// print instructions
		Utility.InitializeInstructions();
		
		// initialize the game
		Utility.InitializeVariables();
		
		// process user input
		while(ContinueGame ) {
			
			if(EZInteraction.wasKeyPressed("w") || EZInteraction.wasKeyPressed("W") || EZInteraction.isKeyDown(KeyEvent.VK_UP))
				Utility.MoveUp();
			
			else if(EZInteraction.wasKeyPressed("s") || EZInteraction.wasKeyPressed("S") || EZInteraction.isKeyDown(KeyEvent.VK_DOWN))
				Utility.MoveDown();
			
			else if(EZInteraction.wasKeyPressed("d") || EZInteraction.wasKeyPressed("D") || EZInteraction.isKeyDown(KeyEvent.VK_RIGHT))
				Utility.MoveRight();
			
			else if(EZInteraction.wasKeyPressed("a") || EZInteraction.wasKeyPressed("A") || EZInteraction.isKeyDown(KeyEvent.VK_LEFT))
				Utility.MoveLeft();
			
			else if(EZInteraction.wasKeyPressed("r") || EZInteraction.wasKeyPressed("R"))
				Utility.ResetGame();
			
			else if(EZInteraction.wasKeyPressed("q") || EZInteraction.wasKeyPressed("Q"))
				ContinueGame = false;
			
			else if(EZInteraction.wasKeyPressed("u") || EZInteraction.wasKeyPressed("U"))
				Utility.EndOfGame("2048Lost.jpg");
			
			else if(EZInteraction.wasKeyPressed("y") || EZInteraction.wasKeyPressed("Y")) {
				Tiles[0][0].setScore(1024);
				Utility.IncreaseTile(Tiles[0][0]);
			}
			
			EZ.refreshScreen();
		}
		
		// close the game
		EZ.closeWindowWithIndex(0);

	}

}