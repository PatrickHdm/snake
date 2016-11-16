package hdm.org.se2.snake02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import hdm.org.se2.snake02.Window;


public class Menu {
		
	Parent gameSource, highscoreSource, settingsSource;
	
	@FXML
	private Button setGameScene;
	@FXML
	private Button setHighscoreScene;
	@FXML
	private Button setSettingsScene;
	
	@FXML
	void changeScene(ActionEvent event)	{
		setGameScene.setOnAction(e -> {
			Window myWindow = new Window();
			try {
				myWindow.sceneHandler("/Game.fxml");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		setHighscoreScene.setOnAction(e -> {
			Window myWindow = new Window();
			try {
				myWindow.sceneHandler("/Highscore.fxml");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		setSettingsScene.setOnAction(e -> {
			Window myWindow = new Window();
			try {
				myWindow.sceneHandler("/Settings.fxml");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	
	@FXML
	private void closeProgram()	{
		System.out.println("Save Game...");
		//window.close();
		System.exit(0);
	}
	
}
