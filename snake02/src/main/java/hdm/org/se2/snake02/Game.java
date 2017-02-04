package hdm.org.se2.snake02;

import java.awt.Graphics;
import java.awt.Point;
import java.beans.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import hdm.org.se2.snake02.Window;

public class Game {	
	Logger log = Logger.getLogger(Game.class.getName());

	private int gridRow = 24;
	private int gridCol = 24;
	private int gridSize = 20;
	public GridPane gridArea = new GridPane();
	public Node[][] cellField = new Node[gridCol][gridRow];
	public Snake player01;
	public Snake player02;
	public GameClock gc;
	public Label score;
	public int COLLISION = -2, WALL = -1, EMPTY = 0, FOOD = 1;
	public static Settings settingsConf;

	public Game(GridPane gridArea, Snake player01, Snake player02, GameClock gc,Label score, int gridRow, int gridCol, int gridSize)	{
		this.player01 = player01;
		this.player02 = player02;
		this.gridRow = gridRow;
		this.gridCol = gridCol;
		this.gridSize = gridSize;
		
		if(player02 != null)	{
			this.player02 = player02;
		}
		this.score = score;
		this.gridArea = gridArea;
		int index = 0;
		for(int col = 0; col < gridCol; col++)	{
			for(int row = 0; row < gridRow; row++)	{	
				cellField[col][row] = new cellField("0", col, row, gridSize, gridSize);
				gridArea.add(cellField[col][row], col, row);
				index++;
			}
		}

		player01.entityPosition.add(player01.getPosition());
		score.setText(""+player01.getSore());
		gridArea.setHgap(1);
		gridArea.setVgap(1);
		gridArea.setPadding(new Insets(10, 10, 10, 10));
	}

	public void step(Snake player)	{
		Platform.runLater(new Runnable() {
			@Override public void run() {
				int direction = player.getDirection();
				Node currentCell = cellField[player.getPosition().x][player.getPosition().y];
				Node nextCell = cellField[0][0];
				switch(direction)	{
				case 1: // Right
					if(player.getPosition().x != gridCol - 1)	{
						nextCell = cellField[player.getPosition().x + 1][player.getPosition().y];
						player.setPosition(player.getPosition().x + 1, player.getPosition().y);
					}
					else	{
						nextCell = cellField[0][player.getPosition().y];
						player.setPosition(0, player.getPosition().y);
					}
					break;
				case 2: // Down
					if(player.getPosition().y != gridRow - 1)	{
						nextCell = cellField[player.getPosition().x][player.getPosition().y + 1];
						player.setPosition(player.getPosition().x, player.getPosition().y + 1);
					}
					else	{
						nextCell = cellField[player.getPosition().x][0];
						player.setPosition(player.getPosition().x, 0);
					}
					break;
				case 3: // Left
					if(player.getPosition().x != 0)	{
						nextCell = cellField[player.getPosition().x - 1][player.getPosition().y];
						player.setPosition(player.getPosition().x - 1, player.getPosition().y);
					}
					else	{
						nextCell = cellField[gridCol - 1][player.getPosition().y];
						player.setPosition(gridCol - 1, player.getPosition().y);
					}
					break;
				case 4: // Up
					if(player.getPosition().y != 0)	{
						nextCell = cellField[player.getPosition().x][player.getPosition().y - 1];
						player.setPosition(player.getPosition().x, player.getPosition().y - 1);
					}
					else	{
						nextCell = cellField[player.getPosition().x][gridRow - 1];
						player.setPosition(player.getPosition().x, gridRow - 1);
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
				String nextCellLabel = laFromNextCell.getText();				
				switch(nextCellLabel)	{
				case "0":	
					laFromCurrentCell.setText("0");
					reFromNextCell.setFill(Color.BLACK);
					laFromNextCell.setText("-2");
					player.entityPosition.add(new Point(player.getPosition().x,player.getPosition().y));
					Point firstEntity = player.entityPosition.getFirst();
					Node cellFromEntity = cellField[firstEntity.x][firstEntity.y];
					StackPane cellSPFromEntity = (StackPane) cellFromEntity;
					Label laCellFromEntity = (Label) cellSPFromEntity.getChildren().get(1);
					laCellFromEntity.setText("0");
					player.entityPosition.removeFirst();
					break;
				case "-1":
					player.setIsDeath(true);
					reFromNextCell.setFill(Color.RED);
					laFromNextCell.setText("0");
					player.entityPosition.removeFirst();
					break;
				case "-2":
					player.setIsDeath(true);
					reFromNextCell.setFill(Color.RED);
					laFromNextCell.setText("0");
					player.entityPosition.removeFirst();
					break;
				case "1":
					reFromNextCell.setFill(Color.BLACK);
					laFromNextCell.setText("-2");
					player.setSize(1);
					player.setScore(1);
					score.setText(""+player.getSore());
					Random randomGenerator = new Random();
					int col = randomGenerator.nextInt(gridCol - 1);
					int row = randomGenerator.nextInt(gridRow - 1);
					replaceField("FOOD", col, row);
					player.entityPosition.add(new Point(player.getPosition().x,player.getPosition().y));
					break;
				}
				
				for(Point entity : player.entityPosition)	{
					replaceField("SNAKE", entity.x, entity.y);
				}

				for(int col = 0; col < gridCol; col++)	{
					for(int row = 0; row < gridRow; row++)	{
						Node cellRepaint = cellField[col][row];
						StackPane cellSPRepaint = (StackPane) cellRepaint;
						Label laFromCellRepaint = (Label) cellSPRepaint.getChildren().get(1);
						if(laFromCellRepaint.getText().equals("0"))
							replaceField("EMPTY", col, row);
						if(laFromCellRepaint.getText().equals("-2"))
							if(player.getPlayerIndex() == 0)	{
								replaceField("SNAKE", col, row);								
							} else	{
								replaceField("SNAKE02", col, row);								
							}
					}
				}
				
			}});

	}
	
	public boolean getPlayerStatus()	{
		return player01.getIsDeath();
	}
	
	public void replaceFieldInArray(String element, int[][] grids)	{
		for(int col = 0; col < grids.length; col++)	{
			for(int row = 0; row < grids[col].length; row++)	{	
				replaceField(element, grids[col][0], grids[col][1]);
			}
		}
	}

	public void replaceField(String element, int col, int row)	{
		Node currentCell = cellField[col][row];
		StackPane currentCellSP = (StackPane) currentCell;
		Label laFromCurrentCell = (Label) currentCellSP.getChildren().get(1);
		Rectangle reFromCurrentCell = (Rectangle) currentCellSP.getChildren().get(0);


		switch (element)	{
		case "EMPTY":
			reFromCurrentCell.setFill(settingsConf.background);
			laFromCurrentCell.setText("0");
			break;
		case "SNAKE":
			reFromCurrentCell.setFill(settingsConf.player);
			laFromCurrentCell.setText("-2");
			break;
		case "SNAKE02":
			reFromCurrentCell.setFill(settingsConf.player02);
			laFromCurrentCell.setText("-2");
			break;
		case "WALL":
			reFromCurrentCell.setFill(settingsConf.wall);
			laFromCurrentCell.setText("-1");
			break;
		case "FOOD":
			if(laFromCurrentCell.getText().equals("0"))	{
				reFromCurrentCell.setFill(settingsConf.food);
				laFromCurrentCell.setText("1");
			} else	{
				Random randomGenerator = new Random();
				int newCol = randomGenerator.nextInt(gridCol - 1);
				int newRow = randomGenerator.nextInt(gridRow - 1);
				replaceField("FOOD", newCol, newRow);
			}
			break;
		}
	}
	
	public void setTemplate(String name)	{
		int[][] food = new int[][]	{
			{6,4},{12,9},{7,2}
			};
		int[][] standardWalls = new int[][]	{
			// Up left
			{0,0},{0,1},{0,2},{0,3},{0,4},
			{1,0},{2,0},{3,0},{4,0},
			// Up right
			{gridCol - 1,0},{gridCol - 2,0},{gridCol - 3,0},{gridCol - 4,0},{gridCol - 5,0},
			{gridCol - 1,1},{gridCol - 1,2},{gridCol - 1,3},{gridCol - 1,4},
			// Down left
			{0,gridRow - 1},{0,gridRow - 2},{0,gridRow - 3},{0,gridRow - 4},{0,gridRow - 5},
			{1,gridRow - 1},{2,gridRow - 1},{3,gridRow - 1},{4,gridRow - 1},
			// Down right
			{gridCol - 1,gridRow - 1},{gridCol - 1,gridRow - 2},{gridCol - 1,gridRow - 3},{gridCol - 1,gridRow - 4},{gridCol - 1,gridRow - 5},
			{gridCol - 1,gridRow - 1},{gridCol - 2,gridRow - 1},{gridCol - 3,gridRow - 1},{gridCol - 4,gridRow - 1},			
			};
		int[][] standardWallsRan = new int[][]	{
			// Up left
			{1,0},{1,1},{1,2},{1,3},{1,4},
			{1,1},{2,1},{3,1},{4,1},
			// Up right
			{gridCol - 2,1},{gridCol - 3,1},{gridCol - 4,1},{gridCol - 5,1},
			{gridCol - 2,0},{gridCol - 2,1},{gridCol - 2,2},{gridCol - 2,3},{gridCol - 2,4},
			// Down left
			{1,gridRow - 1},{1,gridRow - 2},{1,gridRow - 3},{1,gridRow - 4},{1,gridRow - 5},
			{1,gridRow - 2},{2,gridRow - 2},{3,gridRow - 2},{4,gridRow - 2},
			// Down right
			{gridCol - 2,gridRow - 1},{gridCol - 2,gridRow - 2},{gridCol - 2,gridRow - 3},{gridCol - 2,gridRow - 4},{gridCol - 2,gridRow - 5},
			{gridCol - 2,gridRow - 2},{gridCol - 3,gridRow - 2},{gridCol - 4,gridRow - 2},{gridCol - 5,gridRow - 2},			
			};
		switch (name)	{
		case "Standard":	
			replaceFieldInArray("FOOD", food);			
			break;
		case "Wall of Rise":			
			//WALLS
			replaceFieldInArray("WALL", standardWalls);
			replaceFieldInArray("FOOD", food);			
			break;
		case "Templewall":			
			//WALLS
			replaceFieldInArray("WALL", standardWallsRan);
			replaceFieldInArray("FOOD", food);			
			break;
		}
	}

	public static class cellField extends StackPane	{
		public cellField(String name, double x, double y, double width, double height) {

			// create rectangle
			Rectangle rectangle = new Rectangle(x, y, width, height);
			rectangle.setStroke(settingsConf.background);
			rectangle.setFill(settingsConf.background);

			// create label
			Label label = new Label(name);

			getChildren().addAll(rectangle, label);

		}
	}

}
