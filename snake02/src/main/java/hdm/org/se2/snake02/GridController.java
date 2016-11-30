package hdm.org.se2.snake02;

import java.awt.Point;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Logger;

import javax.swing.text.Position;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridController {

	Logger log = Logger.getLogger(Game.class.getName());
	
	private static final double ELEMENT_SIZE = 20;
	private static final double GAP = 1;

	private GridPane gridPane = new GridPane();
	private Group display = new Group(gridPane);
	private int nRows;
	private int nCols;
	private int col;
	private int row;
	
	

	public GridController(int nRows, int nCols)	{
		for (int col = 0; col < nCols; col++) {
			for (int row = 0; row < nRows; row++) {
				gridPane.add(createElement("BLACK"), col, row);
				log.config("COL: "+col+" ROW: "+row);
			}
		}
	}

	public void repleaceGrid(Group gridArea, int nRows, int nCols, int col, int row, String element)	{  
		col = col - 1;
		row = row - 1;
		for (Iterator iterator = getGrid().getChildren().iterator(); iterator.hasNext();) {
			GridPane grid = (GridPane) iterator.next();

			// grid.getChildren().remove((col * nCols) + row);  // TODO - Old grid have to get removed, as a garbage.
			switch(element)	{
			case "rectangle":
				grid.add(createElement("ORANGE"), col, row);
				break;
			default:
				log.warning("Not able to find element: "+element+".");
				break;
			}
		}
	}

	private Rectangle createElement(String color) {
		Rectangle rectangle = new Rectangle(ELEMENT_SIZE, ELEMENT_SIZE);
		rectangle.setStroke(Color.WHITE);
		
		switch(color)	{
		case "BLACK":
			rectangle.setFill(Color.BLACK);
			break;
		case "ORANGE":
			rectangle.setFill(Color.ORANGE);
			break;
		case "GREEN":
			rectangle.setFill(Color.GREEN);
			break;
		}


		return rectangle;
	}

	public Group getGrid() {
		return display;
	}

	public void setSnakeAtGrid(Group grid, Snake player)	{
		Point position = player.getPosition();
		int size = player.getSize();
		for (Iterator iterator = grid.getChildren().iterator(); iterator.hasNext();) {
			GridPane currentGrid = (GridPane) iterator.next();			
			// grid.getChildren().remove((col * nCols) + row);  // TODO - Old grid have to get removed, as a garbage.
			currentGrid.add(createElement("GREEN"), position.x, position.y);
		}
	}

	public void setSnakeForwardAtGrid(GridController gridArea, Snake player)	{
		Point position = player.getPosition();
		int direction = player.getDirection();
		
		switch(direction)	{
		case 1:
			if(position.x == 16)	{
				player.setPosition(0, position.y);
			} else	{
				player.setPosition(position.x + 1, position.y);
			}
			break;
		case 2:
			player.setPosition(position.x, position.y + 1);
			break;
		case 3:
			player.setPosition(position.x + 1, position.y - 1);
			break;
		case 4:
			player.setPosition(position.x - 1, position.y + 1);
			break;
		}
		
		log.config(""+player.getPosition().x);
		
		//setSnakeAtGrid(gridArea.getGrid(), player);
	}

}
