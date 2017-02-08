package hdm.org.se2.snake02.controller;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.event.ChangeListener;

import hdm.org.se2.snake02.Game;
import hdm.org.se2.snake02.GameClock;
import hdm.org.se2.snake02.PlayerFactory;
import hdm.org.se2.snake02.Snake;
import hdm.org.se2.snake02.Window;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameController implements Initializable {
	Logger log = Logger.getLogger(GameController.class.getName());

	// TODO - Setup visibility
	public Game currentGame;
	public Snake player01;
	public Snake player02;
	public GameClock gc;
	public GridPane gridArea = new GridPane();
	public boolean isMultiplayer = false;
	public int gridSize, gridCol, gridRow;
	public int speedDivi;
	public static GameOverController goc;

	@FXML
	private BorderPane GridField;
	
	@FXML
	private Label score;
	
	public void main()	{
		gameSetup();
		// TODO - Request for one or two player
		if(isMultiplayer == true)	{
			player01 = PlayerFactory.getPlayer("Snake");
			player01.setPosition(gridRow / 2, gridCol / 2);
			player02 = PlayerFactory.getPlayer("Snake");
			player02.setPosition(gridRow / 2 + 1, gridCol / 2);
			player02.setPlayerIndex(1);
			gc = new GameClock();
			currentGame = new Game(gridArea,player01,player02,gc,score, gridRow, gridCol, gridSize);
			gc.setCurrentGame(this, currentGame, player01, player02);
		} else	{
			player01 = PlayerFactory.getPlayer("Snake");
			player01.setPosition(gridRow / 2, gridCol / 2);	
			gc = new GameClock();
			currentGame = new Game(gridArea,player01,null,gc,score, gridRow, gridCol, gridSize);
			gc.setCurrentGame(this, currentGame, player01, null);
		}
		gc.speedDivi = this.speedDivi;
		currentGame.setTemplate(SettingsController.settingsConf.getMode());
		gc.runGameLoop();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		main();
		GridField.setCenter(gridArea);
		GridField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){

		@Override
		public void handle(KeyEvent event) {
				KeyCode eventKey = event.getCode();
				
				if(isMultiplayer != true)	{
					if (eventKey == eventKey.UP || eventKey == eventKey.W)	{
						if(player01.getDirection() != 2)
							player01.setDirection(4);
					} else if (eventKey == eventKey.DOWN || eventKey == eventKey.S)	{
						if(player01.getDirection() != 4)
							player01.setDirection(2);					
					} else if (eventKey == eventKey.LEFT || eventKey == eventKey.A)	{
						if(player01.getDirection() != 1)
							player01.setDirection(3);					
					} else if (eventKey == eventKey.RIGHT || eventKey == eventKey.D)	{
						if(player01.getDirection() != 3)
							player01.setDirection(1);					
					}
				} else	{
					if (eventKey == eventKey.UP)	{
						if(player01.getDirection() != 2)
							player01.setDirection(4);
					} else if (eventKey == eventKey.DOWN)	{
						if(player01.getDirection() != 4)
							player01.setDirection(2);					
					} else if (eventKey == eventKey.LEFT)	{
						if(player01.getDirection() != 1)
							player01.setDirection(3);					
					} else if (eventKey == eventKey.RIGHT)	{
						if(player01.getDirection() != 3)
							player01.setDirection(1);					
					}
					if (eventKey == eventKey.W)	{
						if(player02.getDirection() != 2)
							player02.setDirection(4);
					} else if (eventKey == eventKey.S)	{
						if(player02.getDirection() != 4)
							player02.setDirection(2);					
					} else if (eventKey == eventKey.A)	{
						if(player02.getDirection() != 1)
							player02.setDirection(3);					
					} else if (eventKey == eventKey.D)	{
						if(player02.getDirection() != 3)
							player02.setDirection(1);					
					}
				}
			}			
		});
	}
		
	private void gameSetup()	{
		String mode = SettingsController.settingsConf.theme;
		
		switch(mode)	{
		case "Small":
			this.gridCol = 24;
			this.gridRow = 24;	
			this.gridSize = 20;
			break;
		case "Middle":
			this.gridSize = 30;	
			this.gridCol = 16;
			this.gridRow = 16;		
			break;
		case "Big":
			this.gridSize = 40;
			this.gridCol = 13;
			this.gridRow = 13;
			break;
		}

		switch(SettingsController.settingsConf.getDifficulty())	{
			case "Easy":
				this.speedDivi = 120000000;
				break;
			case "Medium":
				this.speedDivi = 90000000;
				break;
			case "Hard":
				this.speedDivi = 20000000;
				break;
		}
		
		if(SettingsController.settingsConf.getMultiplayer().equals("Yes")) 	{
			this.isMultiplayer = true;
		}
	}
	
	public void toGameOver()	{
		// We are currently in a higher Thread because of the gameClock. To use now the sceneHandler from the lower Thread we do following:
			Platform.runLater(new Runnable() {
		        @Override
		        public void run() {
					try {
						goc.setHighScore(player01.getSore()); // First we set the highscore of the current game
						goc.main(); // Now we reinitialize the main from the goc
						main(); // After the first two steps we can rebuild the gamearea and create new players for the next game
						Window.sceneHandler(Window.gameOver); // Change the scene
					} catch (Exception e) {
						log.log(Level.SEVERE, "an exception was thrown", e);
					}
		        }
		   });
	}

	/**
	 * Close the program safely.
	 */
	@FXML
	private void toMenu()	{
		try {
			gc.gameStartStop("stop");
			main();
			Window.sceneHandler(Window.menu);
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
		System.exit(0); // TODO - Maybe replace with a BitCoin miner :-P
	}
	
}
