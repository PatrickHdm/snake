package hdm.org.se2.snake02;

import java.awt.Graphics;
import java.beans.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class Game {	
	Logger log = Logger.getLogger(Game.class.getName());
	
	int gridRow = 24;
	int gridCol = 24;
	int gridSize = 20;
	GridPane gridArea = new GridPane();
	Node[][] cellField = new Node[gridCol][gridRow];
	Snake player;
	int COLLISION = -2, WALL = -1, EMPTY = 0, FOOD = 1;
	
	public Game(GridPane gridArea, Snake player, int gridRow, int gridCol, int GridSize)	{
		this.player = player;
		int index = 0;
		for(int col = 0; col < gridCol; col++)	{
			for(int row = 0; row < gridRow; row++)	{	
				cellField[col][row] = new cellField("0", col, row, gridSize, gridSize);
				gridArea.add(cellField[col][row], col, row);
				index++;
			}
		}
		gridArea.setHgap(1);
		gridArea.setVgap(1);
		gridArea.setPadding(new Insets(10, 10, 10, 10));
	}
		
	public void step()	{
		int direction = player.getDirection();
		Node currentCell = cellField[player.getPosition().x][player.getPosition().y];
		Node nextCell = cellField[0][0];
		switch(direction)	{
		case 1:
			if(player.getPosition().x != gridCol - 1)	{
				nextCell = cellField[player.getPosition().x + 1][player.getPosition().y];
				player.setPosition(player.getPosition().x + 1, player.getPosition().y);
			}
			else	{
				nextCell = cellField[0][player.getPosition().y];
				player.setPosition(0, player.getPosition().y);
			}
			break;
		case 2:
			if(player.getPosition().y != gridRow - 1)	{
				nextCell = cellField[player.getPosition().x][player.getPosition().y + 1];
				player.setPosition(player.getPosition().x, player.getPosition().y + 1);
			}
			else	{
				nextCell = cellField[0][player.getPosition().y];
				player.setPosition(0, player.getPosition().y);
			}
			break;
		case 3:
			if(player.getPosition().x != 0)	{
				nextCell = cellField[player.getPosition().x - 1][player.getPosition().y];
				player.setPosition(player.getPosition().x - 1, player.getPosition().y);
			}
			else	{
				nextCell = cellField[gridCol][player.getPosition().y];
				player.setPosition(gridCol, player.getPosition().y);
			}
			break;
		case 4:
			if(player.getPosition().y != 0)	{
				nextCell = cellField[player.getPosition().x][player.getPosition().y - 1];
				player.setPosition(player.getPosition().x, player.getPosition().y - 1);
			}
			else	{
				nextCell = cellField[player.getPosition().x][gridRow];
				player.setPosition(player.getPosition().x + 1, gridRow);
			}
			break;
		default:
			log.log(Level.WARNING, "Something gut mest up with the direction O.o ...");
		 }
		StackPane currentCellSP = (StackPane) currentCell;
		Label laFromCurrentCell = (Label) currentCellSP.getChildren().get(1);
		Rectangle reFromCurrentCell = (Rectangle) currentCellSP.getChildren().get(0);		
		StackPane nextCellSP = (StackPane) nextCell;
		Label laFromNextCell = (Label) nextCellSP.getChildren().get(1);
		Rectangle reFromNextCell = (Rectangle) nextCellSP.getChildren().get(0);		
		reFromNextCell.setFill(Color.BLACK);

		
		if(laFromNextCell.getText() == "0")	{
			reFromCurrentCell.setFill(Color.LIGHTBLUE);
			reFromNextCell.setFill(Color.BLACK);
			laFromNextCell.setText("0");
		}
		
	}
		
	public static class cellField extends StackPane	{
		public cellField(String name, double x, double y, double width, double height) {

            // create rectangle
            Rectangle rectangle = new Rectangle(x, y, width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTBLUE);

            // create label
            Label label = new Label(name);
            
            getChildren().addAll(rectangle, label);
            
        }
	}
	
}
