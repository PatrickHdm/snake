package hdm.org.se2.snake02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
	
	
	Stage windowStage;
	
	Scene menu, game, highscore, settings;
	
		   
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception	{
		windowStage = primaryStage;
		windowStage.setTitle("Snake0two");
		
		Parent menuSource = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
		menu = new Scene(menuSource, 1280, 720);
		Parent gameSource = FXMLLoader.load(getClass().getResource("/Game.fxml"));
		game = new Scene(gameSource, 1280, 720);
		Parent highscoreSource = FXMLLoader.load(getClass().getResource("/Highscore.fxml"));
		highscore = new Scene(highscoreSource, 1280, 720);		
		Parent settingsSource = FXMLLoader.load(getClass().getResource("/Settings.fxml"));
		settings = new Scene(settingsSource, 1280, 720);
		
		
		
		windowStage.setScene(menu);
		windowStage.show();
		
	}

	public static Object getStage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
