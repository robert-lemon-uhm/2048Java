package project;

import java.awt.Color;
import java.awt.event.KeyEvent;

// Class written by Robert Lemon
public class Utility extends Main{

	public static void InitializeInstructions() {
		
		boolean StartGame = false;
		
		// print the game instructions on the screen
		EZImage Instructions = EZ.addImage("2048Instructions.jpg", 290, 290);
		
		// wait for the player to start the game
		while(!StartGame) {
			
			// keystroke doesn't register without delay before if statement
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(EZInteraction.isKeyDown(KeyEvent.VK_ENTER))
				StartGame = true;
		}
		
		// remove the instruction picture
		EZ.removeEZElement(Instructions);
	}
	
	public static void InitializeVariables() {
		
		// initialize x coordinates
		for(int i = 0; i < 4; i++) {
			xCoords[i][0] = 80;
			xCoords[i][1] = 220;
			xCoords[i][2] = 360;
			xCoords[i][3] = 500;
		}
		
		// initialize y coordinates
		for(int i = 0; i < 4; i++) {
			yCoords[0][i] = 80;
			yCoords[1][i] = 220;
			yCoords[2][i] = 360;
			yCoords[3][i] = 500;
		}
		
		// place background tiles
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				EZ.addRectangle(xCoords[i][j], yCoords[i][j], 120, 120,
						new Color(205, 193, 180), true);
		
		
		// initialize tiles, all blank
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				Tiles[i][j] = new Tile(xCoords[i][j], yCoords[i][j]);
			
		// place the starter tiles
		AddNewTile();
		AddNewTile();

	}
	
	public static void MoveUp() {
		
		boolean addTile = false; // prevent new tile if nothing changed
		
		for(int j = 0; j < 4; j++)
			for(int i = 1; i < 4; i++)
				for(int x = i; x > 0; x--) {
					
					// move tile if the next cell is blank
					if(Tiles[x-1][j].getScore() == 0) {
						
						// add new tile if something changed
						if(Tiles[x][j].getScore() != 0)
							addTile = true;

						ReplaceTiles(Tiles[x][j], Tiles[x-1][j]);
					}

					// combine tile if the next cell has the same score
					else if(Tiles[x-1][j].getScore() == Tiles[x][j].getScore() && Tiles[x-1][j].Combine() && Tiles[x][j].Combine()) {
						
						Tiles[x][j].updateCombine(false);
						Tiles[x-1][j].updateCombine(false);
						
						ReplaceTiles(Tiles[x][j], Tiles[x-1][j]);
						IncreaseTile(Tiles[x-1][j]);
						
						addTile = true;
					}			
				}
		
		// reset the Combine() variable for each tile
		ResetCombine();
		
		// place a new tile after each player movement
		if(addTile)
			AddNewTile();
	}
	
	public static void MoveDown() {
		
		boolean addTile = false; // prevent new tile if nothing changed
		
		for(int j = 0; j < 4; j++)
			for(int i = 2; i >= 0; i--)
				for(int x = i; x < 3; x++) {
					
					// move tile if the next cell is blank
					if(Tiles[x+1][j].getScore() == 0) {
						
						// add new tile if something changed
						if(Tiles[x][j].getScore() != 0)
							addTile = true;
			
						ReplaceTiles(Tiles[x][j], Tiles[x+1][j]);
					}

					// combine tile if the next cell has the same score
					else if(Tiles[x+1][j].getScore() == Tiles[x][j].getScore() && Tiles[x+1][j].Combine() && Tiles[x][j].Combine()) {

						Tiles[x][j].updateCombine(false);
						Tiles[x+1][j].updateCombine(false);

						ReplaceTiles(Tiles[x][j], Tiles[x+1][j]);
						IncreaseTile(Tiles[x+1][j]);
						
						addTile = true;
					}			
				}
		
		// reset the Combine() variable for each tile
		ResetCombine();
		
		// place a new tile after each player movement
		if(addTile)
			AddNewTile();
	}
	
	public static void MoveRight() {
		
		boolean addTile = false; // prevent new tile if nothing changed
		
		for(int i = 0; i < 4; i++)
			for(int j = 2; j >= 0; j--)
				for(int x = j; x < 3; x++) {
					
					// move tile if the next cell is blank
					if(Tiles[i][x+1].getScore() == 0) {

						// add new tile if something changed
						if(Tiles[i][x].getScore() != 0)
							addTile = true;
						
						ReplaceTiles(Tiles[i][x], Tiles[i][x+1]);
					}

					// combine tile if the next cell has the same score
					else if(Tiles[i][x+1].getScore() == Tiles[i][x].getScore() && Tiles[i][x+1].Combine() && Tiles[i][x].Combine()) {
						
						Tiles[i][x].updateCombine(false);
						Tiles[i][x+1].updateCombine(false);
						
						ReplaceTiles(Tiles[i][x], Tiles[i][x]);
						IncreaseTile(Tiles[i][x+1]);
						
						addTile = true;
					}			
				}
		
		// reset the Combine() variable for each tile
		ResetCombine();
		
		// place a new tile after each player movement
		if(addTile)
			AddNewTile();
	}
	
	public static void MoveLeft() {
		
		boolean addTile = false; // prevent new tile if nothing changed
		
		for(int i = 0; i < 4; i++)
			for(int j = 1; j < 4; j++)
				for(int x = j; x > 0; x--) {
					
					// move tile if the next cell is blank
					if(Tiles[i][x-1].getScore() == 0) {
						
						// add new tile if something changed
						if(Tiles[i][x].getScore() != 0)
							addTile = true;
						
						ReplaceTiles(Tiles[i][x], Tiles[i][x-1]);
					}

					// combine tile if the next cell has the same score
					else if(Tiles[i][x-1].getScore() == Tiles[i][x].getScore() && Tiles[i][x-1].Combine() && Tiles[i][x].Combine()) {
						
						Tiles[i][x].updateCombine(false);
						Tiles[i][x-1].updateCombine(false);
						
						ReplaceTiles(Tiles[i][x], Tiles[i][x]);
						IncreaseTile(Tiles[i][x-1]);
						
						addTile = true;
					}			
				}
		
		// reset the Combine() variable for each tile
		ResetCombine();
		
		// place a new tile after each player movement
		if(addTile)
			AddNewTile();
	}
	
	public static void IncreaseTile(Tile tile) {
		
		// increase tile score
		tile.incScore();
		
		// check if user won the game
		if(tile.getScore() == 2048) {

			// pause before showing win screen
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			EndOfGame("2048Won.jpg");
		}
	}
	
	public static void EndOfGame(String image) {
		
		boolean UserInput = false;
		
		EZImage WaitScreen = EZ.addImage(image, 290, 290);
		
		// wait for user input
		while(!UserInput) {
			
			// reset the game
			if(EZInteraction.wasKeyPressed("r")) {
				UserInput = true;
				ResetGame();
			}
			
			// close the game
			else if(EZInteraction.wasKeyPressed("q")) {
				UserInput = true;
				ContinueGame = false;
			}
		}
		
		// remove wait screen picture
		EZ.removeEZElement(WaitScreen);
	}

	public static void ResetGame() {
		
		// reset the game
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				Tiles[i][j].setScore(0);
		
		AddNewTile();
		AddNewTile();
	}
	
	private static void ReplaceTiles(Tile a, Tile b) {
		
		// replace tile b with tile a, then reset tile a
		b.setScore(a.getScore());
		b.updateCombine(a.Combine());
		a.reset();
	}
		
	private static void AddNewTile() {
				
		int newX, newY;
		boolean full = true;
		
		// wait to place the next tile
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// get a blank tile
		do {
			newX = (int) (4*Math.random());
			newY = (int) (4*Math.random());
		}
		while(Tiles[newX][newY].getScore() != 0);
		
		// place a new 2 in the blank tile
		Tiles[newX][newY].newTile();
		
		// make one in every ten tiles a 4
		if(Math.random() >= 0.9)
			Tiles[newX][newY].incScore();
		
		// check if all tiles are full
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(Tiles[i][j].getScore() == 0)
					full = false;
		
		// check if player lost
		if(full)
			CheckLost();
		
	}

	private static void CheckLost() {
		
		boolean lost = true;
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++) {
				
				// check if the player can move up
				if(i != 0)
					if(Tiles[i][j].getScore() == Tiles[i-1][j].getScore())
						lost = false;
				
				// check if the player can move down
				if(i != 3)
					if(Tiles[i][j].getScore() == Tiles[i+1][j].getScore())
						lost = false;
				
				// check if the player can move left
				if(j != 0)
					if(Tiles[i][j].getScore() == Tiles[i][j-1].getScore())
						lost = false;
				
				// check if the player can move right
				if(j != 3)
					if(Tiles[i][j].getScore() == Tiles[i][j+1].getScore())
						lost = false;
	
			}
		
		// tell player they lost
		if(lost) {
			
			// pause before showing lose screen
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			EndOfGame("2048Lost.jpg");
		}
	
	}
	
	private static void ResetCombine() {
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				Tiles[i][j].updateCombine(true);
	}

}
