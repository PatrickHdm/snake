package hdm.org.se2.snake02;

import javax.management.openmbean.OpenDataException;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.omg.CORBA.portable.ApplicationException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

@SuppressWarnings("restriction")
public class Window extends Application {
	
	
	Stage window;
	Scene mainMenuScene, settingsScene;
	
		   
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception	{
		window = primaryStage;
		window.setTitle("Snake0two");

		// Setting up the labels
		Label h1 = new Label("Welcome to Snake 0 two");
		
		// Setting up the buttons
		Button play, highscore, settings, quit, btMm;
		play 		= new Button("Play");
		highscore 	= new Button("Highscore");
		settings 	= new Button("Settings");
		quit 		= new Button("Quit");
		btMm		= new Button("Back to MainMenu");
		

		// Event Handler for the play button (New Syntax since Java 1.8)
		play.setOnAction(e -> {
				System.out.println("Hello World");
			});
		
		settings.setOnAction(e -> window.setScene(settingsScene));

		btMm.setOnAction(e -> window.setScene(mainMenuScene));
		
		// Close functions for the game
		window.setOnCloseRequest(e -> closeProgram());
		quit.setOnAction(e -> closeProgram());
		
		// Layout 001 - Setup our layout for the menu
		// The headline on top
		HBox headline = new HBox();
		headline.getChildren().add(h1);
		// The menu at the center
		VBox menuList = new VBox(20);
		menuList.getChildren().addAll(play, highscore, settings, quit);
		
		// Setup the borderPane and put the headline and menuList inside.
		GridPane mainMenu = new GridPane();
		mainMenu.setPadding(new Insets(10,10,10,10));
		mainMenu.setVgap(8);
		mainMenu.setHgap(10);
		
		mainMenu.setConstraints(headline, 0, 0);
		mainMenu.setConstraints(menuList, 0, 1);
		
		mainMenu.getChildren().addAll(headline, menuList);
		
		mainMenuScene = new Scene(mainMenu, 1280, 720);
		
		
		
		// Layout 002 - Setup our layout for the settings
		VBox settingsLayout = new VBox(20);
		settingsLayout.getChildren().addAll(btMm);
		settingsScene = new Scene(settingsLayout, 1280, 720);
		
		
		window.setScene(mainMenuScene);
		window.show();
		
	}
	
	private void closeProgram()	{
		System.out.println("Save Game...");
		window.close();
	}
	
}
