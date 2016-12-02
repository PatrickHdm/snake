package hdm.org.se2.snake02;

import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class Game implements Initializable {	
	Logger log = Logger.getLogger(Game.class.getName());
	
	int gridRow = 24;
	int gridCol = 24;
	int gridSize = 20;
	GridPane gridArea = new GridPane();
	Node[][] cellField = new Node[gridCol][gridRow];
	Snake player;
	int COLLISION = -2, WALL = -1, EMPTY = 0, FOOD = 1;
	
	public Game()	{
		player = new Snake(gridRow / 2, gridCol, 3);
		int index = 0;
		for(int col = 0; col < gridCol; col++)	{
			for(int row = 0; row < gridRow; row++)	{
				try	{			
					cellField[col][row] = new cellField("", col, row, gridSize, gridSize);
					gridArea.add(cellField[col][row], col, row);
					index++;
				} catch(Exception e)	{
					System.out.println(e);
				}
			}
		}
		gridArea.setHgap(1);
		gridArea.setVgap(1);
		gridArea.setPadding(new Insets(10, 10, 10, 10));
	}
	
	@FXML
	private BorderPane GridField;
	
	public void main() {
		GridField.setCenter(gridArea);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		main();
		step();
	}
	
	public void step()	{
		int direction = player.getDirection();
		Node nextCell = cellField[0][0];
		switch(direction)	{
		case 1:
			if(player.getPosition().x != gridCol)
				nextCell = cellField[player.getPosition().x + 1][player.getPosition().y];
			else
				nextCell = cellField[0][player.getPosition().y];
			break;
		case 2:
			if(player.getPosition().y != gridRow)
			nextCell = cellField[player.getPosition().x][player.getPosition().y + 1];
			else
				nextCell = cellField[0][player.getPosition().y];
			break;
		case 3:
			if(player.getPosition().x != 0)
			nextCell = cellField[player.getPosition().x - 1][player.getPosition().y];
			else
				nextCell = cellField[gridCol][player.getPosition().y];
			break;
		case 4:
			if(player.getPosition().y != 0)
			nextCell = cellField[player.getPosition().x][player.getPosition().y - 1];
			else
				nextCell = cellField[player.getPosition().x][gridRow];
			break;
		default:
			log.log(Level.WARNING, "Something gut mest up with the direction O.o ...");
		 }
		
		System.out.println(nextCell);

//		if(nextCell.getParent() = COLLISION)	{
//			
//		}
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
			log.log(Level.SEVERE, "an exception was thrown", e1);

		}
	}

	/**
	 * Close the program safely.
	 */
	@FXML
	private void closeProgram()	{

		log.info("Save Game...");
		//window.close();
		System.exit(0);
	}
	
	public static class cellField extends StackPane	{
		public cellField( String name, double x, double y, double width, double height) {

            // create rectangle
            Rectangle rectangle = new Rectangle(x, y, width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTBLUE);

            // create label
            Label label = new Label(name);
            
            getChildren().addAll( rectangle, label);
            
        }
	}
	
}
