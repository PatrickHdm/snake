package hdm.org.se2.snake02.windows;

import org.omg.CORBA.portable.ApplicationException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Settings extends Application {
	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Snake0two - Settings");
		Group root = new Group();
		Scene scene = new Scene(root, 720, 720, Color.WHITE);

		// Create the grid (Layout)
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(10));
		gridpane.setHgap(10);
		gridpane.setVgap(10);

		// Create the buttons and there functions
		Button backToMenu = new Button("Back");
		backToMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Back");
			}
		});

		// Setup the style
		int btnWidth = 120;

		// Adding the styling
		backToMenu.setMinWidth(btnWidth);


		// Place the buttons in the grid
		gridpane.add(backToMenu, 0, 3);

		root.getChildren().add(gridpane);        
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
