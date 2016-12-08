package hdm.org.se2.snake02;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class Settings {
	
	Logger log = Logger.getLogger(Game.class.getName());
	
	@FXML
	private Button Back;
	
	
	//TODO - change visibility
	Point resolution = new Point(); 
	String difficulty;
	String theme;
	String mode;

	public Settings()	{
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
	
	
	@FXML
	private void toMenu()	{
		Window myWindow = new Window();
		try {
			myWindow.sceneHandler("/Menu.fxml");
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);

		}
	}
	
}
