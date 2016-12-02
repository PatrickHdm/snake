package hdm.org.se2.snake02;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Cell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.*;

import hdm.org.se2.snake02.Window;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Game implements Initializable {
	Logger log = Logger.getLogger(Game.class.getName());

	private final int GridSize = 20;
	private final int CellSize = 20;
	private Snake snake;
	private boolean isDeath = false;
	private int curDir = 0;
	private int[][] grid = new int[GridSize][GridSize];
		
	GridLayout gameBoard = new GridLayout(GridSize, GridSize);
	GridPane gameArea = new GridPane();
	Cell[][] cell = new Cell[GridSize][GridSize];
	
	
	public static final int
	WALL = 1, SNAKE = 2, FOOD = 3, COLLISION = 4, EMPTY = 5;


	public Game()	{
		snake = new Snake(3, 10, 10);

		for(int row = 0; row < GridSize; row++)
		for(int col = 0; col < GridSize; col++)
		// gameArea.add(cell[row][col]) = new cell(row, col, this));


	}


	protected void paintComponent(Graphics g) {
		for(int row = 0; row < GridSize; row++)	{
			for(int col = 0; col < GridSize; col++)	{
				int cell = grid[row][col];

				g.drawRect(row * CellSize, col * CellSize, CellSize, CellSize);
				if(cell == WALL || cell == SNAKE)	{
					g.fillRect(row * CellSize, row * CellSize, CellSize, CellSize);
				} else if (cell > EMPTY)	{
					g.drawString("" + cell, row * CellSize, (row + 1) * CellSize);
				}
			}
		}
	}

	void step() {

		int dir = snake.getDirection();
		int nextCell = grid[snake.getPosition().x + dir][snake.getPosition().y + dir];
		if (nextCell < EMPTY) {
			isDeath = true;
		} else {
			if (nextCell > EMPTY)
				snake.setSize(nextCell);
			isDeath = false;

			if (snake.getSize() == 0)
				grid[snake.getPosition().x][snake.getPosition().y] = EMPTY;

			step();

			int len = snake.size;
			snake.cur = snake.getPosition();
			while (len > 0) {
				grid[snake.getPosition().x][snake.getPosition().y] = SNAKE;
				snake.cur = snake.cur;
				len--;
			}
			paintComponent(null);
		}
	}

	@FXML 
	private BorderPane GridField;
	
	public void main() {
		//GridField.setCenter(value); // Put the Grid into the center of the game
	}

	public void initialize(URL location, ResourceBundle resources) {
		main();
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
}
