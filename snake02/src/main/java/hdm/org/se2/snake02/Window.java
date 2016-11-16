package hdm.org.se2.snake02;

import java.awt.event.WindowStateListener;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
	
	
	public static Stage windowStage;
	
	Parent menuSource, gameSource, highscoreSource, settingsSource;
	public static Scene menu, game, highscore, settings;
	
		   
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception	{
		windowStage = primaryStage;
		windowStage.setTitle("Snake0two");
		
		menuSource = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
		menu = new Scene(menuSource, 1280, 720);
		
		windowStage.setScene(menu);
		windowStage.show();
		
	}
	
	
	public void sceneHandler(String scene) throws Exception	{		
		System.out.println("Change Scene... ");
		Parent sceneSource = FXMLLoader.load(getClass().getResource(scene));
		
		windowStage.setScene(new Scene(sceneSource, 1280, 720));
	}
	
	
}
