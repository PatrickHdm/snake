package hdm.org.se2.snake02.windows;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HighscoreWindow extends Application {

	public static void main (String [] args){
		launch(args);
		}

		
		@Override
		public void start(Stage primaryStage)  {
		//set Title
			primaryStage.setTitle("Higscore");
			primaryStage.show();
		
		//create Grid
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25,25,25,25));
			
		//set Size
			Scene scene = new Scene (grid,300,275);
			primaryStage.setScene(scene);
		
				
		//Add Text,Labels
			Text scenetitle = new Text("You reached a new Highscore");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
			grid.add(scenetitle,0,0,2,1);
		
			Label userName = new Label("Name: ");
			grid.add(userName, 0, 1);
			
			
			
		//create Textfield for username
			TextField userTextField = new TextField();
			grid.add(userTextField, 1, 1);
			
		//Add Save-Button	
			Button save = new Button("Save");
			HBox hSave = new HBox(100);
			hSave.setAlignment(Pos.BOTTOM_RIGHT);
			hSave.getChildren().add(save);
			grid.add(hSave, 1, 4);
			
		
			
		
		}
		
		
}
