package hdm.org.se2.snake02;

import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	Game currentGame;
	Snake player01;
	Snake player02;
	public static GameClock gc;
	GridPane gridArea = new GridPane();
	boolean isMultiplayer = false;

	@FXML
	private BorderPane GridField;
	
	@FXML
	private Label score;
		
	public void main()	{
		// TODO - Request for one or two player
		if(isMultiplayer == true)	{
			player01 = new Snake(24 / 2, 24 / 2, 3);
			player02 = new Snake(24 / 4, 24 / 4, 3);
			currentGame = new Game(gridArea,player01,player02,score,24, 24, 20);	
			gc = new GameClock();
			gc.setCurrentGame(currentGame, player01, player02);	
		} else	{
			player01 = new Snake(24 / 2, 24 / 2, 3);			
			currentGame = new Game(gridArea,player01,null,score,24, 24, 20);
			gc = new GameClock();
			gc.setCurrentGame(currentGame, player01, null);
		}
		currentGame.setTemplate("standard");
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		main();
		GridField.setCenter(gridArea);
		GridField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){

		@Override
		public void handle(KeyEvent event) {
				KeyCode eventKey = event.getCode();
				
				// TODO - Separate for multiplayer.
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
