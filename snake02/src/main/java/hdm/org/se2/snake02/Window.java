package hdm.org.se2.snake02;

import java.awt.event.WindowStateListener;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
	
	
	public static Stage windowStage; // This is the window itself. It's public so that we can modify it, like setting a new scene.
	public int resX = 1280;
	public int resY = 720;
		   
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception	{
		windowStage = primaryStage;
		windowStage.setTitle("Snake0two");
		
		// Get settings or set new Settings
		Settings settings = new Settings();
		this.resX = settings.resolution.x;
		this.resY = settings.resolution.y;
		
		// Before we set a new scene, we need to create our layout. We have it as a fxml, so that we need to load it.
		Parent menuSource = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
		// Now we can create the first scene, our menu. Also to add are the scene resolution.
		Scene menu = new Scene(menuSource, resX, resY);
		
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
	public void sceneHandler(String scene) throws Exception	{		
		System.out.println("Change Scene... "); // Just a little debug!
		// Again, we firstly load our layout
		Parent sceneSource = FXMLLoader.load(getClass().getResource(scene));
		// Now we can setup a new scene and push it to our window.
		windowStage.setScene(new Scene(sceneSource, resX, resY));
	}
	
	
}
