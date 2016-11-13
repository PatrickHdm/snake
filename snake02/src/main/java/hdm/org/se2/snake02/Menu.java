package hdm.org.se2.snake02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import hdm.org.se2.snake02.Window;


public class Menu {
	
	Scene menu, game, highscore, settings;
	
	@FXML
	private Button setGameScene;
	@FXML
	private Button setHighscoreScene;
	@FXML
	private Button setSettingsScene;
	
	@FXML
	void changeScene(ActionEvent event)	{
		setGameScene.setOnAction(e -> {
			Window.getStage().setScene(game);
		});
		setHighscoreScene.setOnAction(e -> {
			Window.getStage().setScene(highscore);
		});
		setSettingsScene.setOnAction(e -> {
			Window.getStage().setScene(settings);
		});
	}
	
	
	@FXML
	private void closeProgram()	{
		System.out.println("Save Game...");
		//window.close();
		System.exit(0);
	}
	
}
