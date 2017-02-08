package hdm.org.se2.snake02;

import java.awt.event.WindowStateListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.sun.media.jfxmedia.AudioClip;

import hdm.org.se2.snake02.controller.GameController;
import hdm.org.se2.snake02.controller.GameOverController;
import hdm.org.se2.snake02.controller.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
	static Logger log = Logger.getLogger(Window.class.getName());


	public static Stage windowStage; // This is the window itself. It's public so that we can modify it, like setting a new scene.
	public static Scene game, highscore, settings, menu, gameOver,unknown;
	public static Parent menuSource, gameSource, highscoreSource, settingsSource, gameOverSource;
	public static int resX = 1280;
	public static int resY = 720;
	public static String homeDir = System.getProperty("user.home");
	public static Settings settingsConf;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		saveDirectory();
		startGame(primaryStage);
		playSound();
	}

	public void startGame(Stage primaryStage) throws Exception	{
		windowStage = primaryStage;
		windowStage.setTitle("Snake0two");

		// Set a reference to settings and highscore
		this.settingsConf = new Settings();
		Highscore highscoreConf = new Highscore();
		settingsConf.readFromFile(settingsConf);
		this.resX = settingsConf.resolution.x;
		this.resY = settingsConf.resolution.y;
		SettingsController.currentWindow = this;
		highscoreConf.currentWindow = this;
		SettingsController.settingsConf = settingsConf;
		Game.settingsConf = settingsConf;
		GameOverController.settingsConf = settingsConf;
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
		game.getStylesheets().add("/css/Game.css");
		highscore = new Scene(highscoreSource, resX, resY);
		highscore.getStylesheets().add("/css/Highscore.css");
		settings = new Scene(settingsSource, resX, resY);
		settings.getStylesheets().add("/css/Settings.css");
		gameOver = new Scene(gameOverSource, resX, resY);
		gameOver.getStylesheets().add("/css/Game.css");

		// After we create our scene, we have to give it to our window.
		windowStage.setScene(menu);
		// Now we can let show the new and fresh window.
		windowStage.show();

	}

	public void cleanup() {
		// stop animations reset model ect.
	}

	public void restart(Stage stage) throws Exception {
		cleanup();
		startGame(stage);
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

	public void saveDirectory()	{
		if(new File("config").exists())	{
		} else	{
			boolean success = (new File("config")).mkdirs();
			if(!success)	{
				log.warning("Could not create folder for config");
			}
		}	

	}

	public static synchronized void playSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					URL isa = getClass().getResource("/sounds/"+settingsConf.getSound()+".wav");

					AudioInputStream stream = AudioSystem.getAudioInputStream(isa);
					Clip clip = AudioSystem.getClip();
					clip.open(stream);
					clip.start();

				} catch (Exception e) {
					log.log(Level.SEVERE, "an exception was thrown", e);
				}
			}
		}).start();
	}


}
