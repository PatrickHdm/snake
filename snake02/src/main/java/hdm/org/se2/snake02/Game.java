package hdm.org.se2.snake02;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import hdm.org.se2.snake02.Window;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Game implements Initializable {
	Logger log = Logger.getLogger(Game.class.getName());
	
	int gridH = 16, gridW = 16;
	
    GridDisplay gridDisplay;
    GameClock game;
    GridController gridArea;
    Snake snake;
    
	
	@FXML 
	private BorderPane GridField;
	
	public void main() {
		game = new GameClock(); // Create a new GameClock
		game.setGameStuff(gridArea, snake); // Give the GameClock all important variables
		gridArea = new GridController(gridH, gridW); // Create a GridField
		snake = new Snake(gridH / 2, gridW / 2, 1); // Create a player
		GridField.setCenter(gridArea.getGrid()); // Put the Grid into the center of the game
		gridArea.setSnakeAtGrid(gridArea.getGrid(), snake); // Put the player on the grid
		
		game.runGameLoop(); // Let the game start!
    }
	
	public void initialize(URL location, ResourceBundle resources) {
		main();
    }
	
	
	/**
	 * Close the program safely.
	 */
	@FXML
	private void toMenu()	{
		Window myWindow = new Window();
		try {
			myWindow.sceneHandler("/Menu.fxml");
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
			
		}
	}
	
	/**
	 * Close the program safely.
	 */
	@FXML
	private void closeProgram()	{
		
		log.info("Save Game...");
		//window.close();
		System.exit(0);
	}
}
