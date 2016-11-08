package hdm.org.se2.snake02;

import javax.management.openmbean.OpenDataException;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class Window extends Application {	    
	    @Override
	    public void start(Stage primaryStage) {

	    	primaryStage.setTitle("Snake0two");
	    	Group root = new Group();
	        Scene scene = new Scene(root, 720, 720, Color.WHITE);
	        
	        // Create the grid (Layout)
	        GridPane gridpane = new GridPane();
	        gridpane.setAlignment(Pos.CENTER);
	        gridpane.setHgap(10);
	        gridpane.setVgap(10);

	        // Create the buttons and there functions
	        Button play = new Button("Play");
	        play.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	                System.out.println("Play");
	            }
	        });
	        Button highscore = new Button("Highscore's");
	        highscore.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	                System.out.println("highscore's");
	            }
	        });
	        Button settings = new Button("Settings");
	        settings.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {	            	
	            	Class Settings;           	
	            	
	            }
	        });
	        Button quit = new Button("Quit");
	        quit.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	                System.exit(0);
	            }
	        });
	        
	        // Setup the style
	        int btnWidth = 120;
	        
	        // Adding the styling
	        play.setMinWidth(btnWidth);
	        highscore.setMinWidth(btnWidth);
	        settings.setMinWidth(btnWidth);
	        quit.setMinWidth(btnWidth);
	        
	        
	        // Place the buttons in the grid of a VBox	        
	        VBox menu = new VBox();
	        menu.setSpacing(10);
	        menu.setPadding(new Insets(0, 20, 10, 20)); 
	        menu.getChildren().addAll(play, highscore, settings, quit);
	        
	        gridpane.add(menu, 0, 2, 2, 1);
	       
	        root.getChildren().add(gridpane);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
}
