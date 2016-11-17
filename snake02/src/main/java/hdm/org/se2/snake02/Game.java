package hdm.org.se2.snake02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;
import hdm.org.se2.snake02.Window;

public class Game implements Initializable {
	
	int gridH = 4, gridW = 4;
	
	private int nRows, nCols;
	
	
	
	//Class containing grid (see below)
    private GridDisplay gridDisplay;
	
	@FXML 
	private BorderPane GridField;	
	
	public void updateGridPane() {		
		gridDisplay = new GridDisplay(gridH, gridW);
		
		GridField.setCenter(gridDisplay.getDisplay());
    }

	
	public void initialize(URL location, ResourceBundle resources) {
		updateGridPane();
    }
	
	
	/**
	 * Close the program safely.
	 */
	@FXML
	private void toMenu()	{
		Window myWindow = new Window();
		try {
			myWindow.sceneHandler("/Menu.fxml");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Close the program safely.
	 */
	@FXML
	private void closeProgram()	{
		System.out.println("Save Game...");
		//window.close();
		System.exit(0);
	}
}
