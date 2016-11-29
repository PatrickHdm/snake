package hdm.org.se2.snake02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.util.logging.Logger;
import java.util.logging.Level;
import hdm.org.se2.snake02.Window;


public class Menu {
		
	Logger log = Logger.getLogger(Game.class.getName());
	
	// Our layout have some buttons with the same function. That we can later handle it, we catch the buttons by there id's
	@FXML
	private Button setGameScene;
	@FXML
	private Button setHighscoreScene;
	@FXML
	private Button setSettingsScene;
	
	/**
	 * To change between the scenes.
	 * 
	 * @param event
	 */
	@FXML
	void changeScene(ActionEvent event)	{
		setGameScene.setOnAction(e -> {
			Window myWindow = new Window();
			try {
				myWindow.sceneHandler("/Game.fxml");
			} catch (Exception e1) {
				log.log(Level.SEVERE, "an exception was thrown", e1);
			}
		});
		setHighscoreScene.setOnAction(e -> {
			Window myWindow = new Window();
			try {
				myWindow.sceneHandler("/Highscore.fxml");
			} catch (Exception e1) {
				log.log(Level.SEVERE, "an exception was thrown", e1);
			}
			
		});
		setSettingsScene.setOnAction(e -> {
			Window myWindow = new Window();
			try {
				myWindow.sceneHandler("/Settings.fxml");
			} catch (Exception e1) {
				log.log(Level.SEVERE, "an exception was thrown", e1);
			}
		});
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
