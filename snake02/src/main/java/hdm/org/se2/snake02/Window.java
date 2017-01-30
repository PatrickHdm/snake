package hdm.org.se2.snake02;

import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
	static Logger log = Logger.getLogger(Window.class.getName());
	
	
	public static Stage windowStage; // This is the window itself. It's public so that we can modify it, like setting a new scene.
	public static Scene game, highscore, settings, menu, gameOver;
	public static Parent menuSource, gameSource, highscoreSource, settingsSource, gameOverSource;
	public static int resX = 1280;
	public static int resY = 720;
		   
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception	{
		windowStage = primaryStage;
		windowStage.setTitle("Snake0two");
		
		// Get settings or set new Settings
		Settings settingsConf = new Settings();
		Highscore highscoreConf = new Highscore();
		settingsConf.readFromFile(settingsConf);
		this.resX = settingsConf.resolution.x;
		this.resY = settingsConf.resolution.y;
		SettingsController.settingsConf = settingsConf;
		Game.settingsConf = settingsConf;
		GameOverController.highscoreConf = highscoreConf;
		
		// Before we set a new scene, we need to create our layout. We have it as a fxml, so that we need to load it.
		menuSource = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
		gameSource = FXMLLoader.load(getClass().getResource("/Game.fxml"));
		highscoreSource = FXMLLoader.load(getClass().getResource("/Highscore.fxml"));
		settingsSource = FXMLLoader.load(getClass().getResource("/Settings.fxml"));
		gameOverSource = FXMLLoader.load(getClass().getResource("/GameOver.fxml"));
		// Now we can create the first scene, our menu. Also to add are the scene resolution.
		menu = new Scene(menuSource, resX, resY);
		menu.getStylesheets().add("/css/menu.css");
		game = new Scene(gameSource, resX, resY);
		highscore = new Scene(highscoreSource, resX, resY);
		settings = new Scene(settingsSource, resX, resY);
		gameOver = new Scene(gameOverSource, resX, resY);
		
		// After we create our scene, we have to give it to our window.
		windowStage.setScene(menu);
		// Now we can let show the new and fresh window.
		windowStage.show();
		
	}
	
	
	/**
	 * Handle the scenes for our window
	 * 
	 * @param scene
	 * @throws Exception
	 */
	public static void sceneHandler(Scene scene) throws Exception	{		
		log.info("Change Scene... "); // Just a little debug!
		// Now we can setup a new scene and push it to our window.
		windowStage.setScene(scene);
	}
	
	
}
