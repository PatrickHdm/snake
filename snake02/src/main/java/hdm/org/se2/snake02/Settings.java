package hdm.org.se2.snake02;

import java.awt.Point;

public class Settings {
	
	//TODO - change visibility
	Point resolution; 
	String difficulty;
	String theme;
	String mode;

	private Settings()	{
		resolution.x = 1024;
		resolution.y = 768;
		
		difficulty = "easy";
		
		theme = "standard";
		
		mode = "standard";
	}

	public Point getResolution() {
		return resolution;
	}

	public void setResolution(int x, int y) {
		this.resolution.x = x;
		this.resolution.y = y;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
}
