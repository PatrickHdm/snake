package hdm.org.se2.snake02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import hdm.org.se2.snake02.Window;

public class Game implements Initializable {
	
	int gridH = 16, gridW = 16;
	
    private GridDisplay gridDisplay;
    
    private GridController gridArea;
	
	@FXML 
	private BorderPane GridField;
	
	public void addGridPane() {
		Snake snake = new Snake(gridH / 2, gridW / 2, 1);
		
		gridArea = new GridController(gridH, gridW);
		GridField.setCenter(gridArea.getGrid());
		
		gridArea.repleaceGrid(gridArea.getGrid(), gridH, gridW, 4, 4, "rectangle");
		gridArea.setSnakeAtGrid(gridArea.getGrid(), snake);
		gridArea.setSnakeForwardAtGrid(gridArea.getGrid(), snake);
    }
	
	public void initialize(URL location, ResourceBundle resources) {
		addGridPane();
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
