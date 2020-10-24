package project;

import java.awt.Color;

// Class written by Robert Lemon
public class Tile {
	
	private int score = 0;
	private boolean canCombine = true;
	
	private EZRectangle rectangle;
	private EZText text;
	
	// constructor
	Tile(int x, int y) {

		// create a new tile
		rectangle = EZ.addRectangle(x, y, 120, 120, new Color(205, 193, 180), true);
		text = EZ.addText(x, y, Integer.toString(score), new Color(119, 110, 101), 60);
		
		updateTile();
	}
	
	// update tile to become a new 'two' tile
	void newTile() {
		score = 2;
		updateTile();
	}
	
	// increase the score if tiles combine
	void incScore() {
		score += score;
		updateTile();
	}
	
	// update text and tile
	void updateTile() {

		// toggle tile visibility based on score
		if(score == 0) {
			rectangle.hide();
			text.hide();
		}
		
		else {
			rectangle.show();
			text.show();
		}
		
		// toggle text size based on score
		if(score > 512)
			text.setFontSize(50);
		
		else
			text.setFontSize(60);
		
		// update text message
		text.setMsg(Integer.toString(score));
		
		// update text color
		if(score <= 4)
			text.setColor(new Color(119, 110, 101)); // initial text color
		else
			text.setColor(new Color(249, 246, 242)); // final text color
		
		// update tile color
		switch(score) {
		case 0:
			rectangle.color = new Color(205, 193, 180); // blank tile color
			break;
		case 2:
			rectangle.color = new Color(238, 228, 212);
			break;
		case 4:
			rectangle.color = new Color(237, 224, 200);
			break;
		case 8:
			rectangle.color = new Color(242, 177, 121);
			break;
		case 16:
			rectangle.color = new Color(245, 149, 99);
			break;
		case 32:
			rectangle.color = new Color(246, 124, 95);
			break;
		case 64:
			rectangle.color = new Color(246, 96, 60);
			break;
		case 128:
			rectangle.color = new Color(237, 207, 114);
			break;
		case 256:
			rectangle.color = new Color(237, 204, 97);
			break;
		case 512:
			rectangle.color = new Color(237, 200, 80);
			break;
		case 1024:
			rectangle.color = new Color(237, 197, 63);
			break;
		case 2048:
			rectangle.color = new Color(237, 194, 46);
			break;		
		default:
			rectangle.color = new Color(0, 255, 0);
		}
	}
	
	// reset tile
	void reset() {
		score = 0;
		canCombine = true;
		updateTile();
	}
	
	// methods to view class variables
	int getScore() {
		return score;
	}
	
	boolean Combine() {
		return canCombine;
	}
	
	//methods to change class variables
	void setScore(int i) {
		score = i;
		updateTile();
	}
	
	void updateCombine(boolean i) {
		canCombine = i;
	}
	
}
