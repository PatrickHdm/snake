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

	Game currentGame;
	Snake player;
	GameClock gc;
	GridPane gridArea = new GridPane();

	@FXML
	private BorderPane GridField;
	
	@FXML
	private Label score;
		
	public void main()	{
		player = new Snake(24 / 2, 24 / 2, 3);
		currentGame = new Game(gridArea,player,score,24, 24, 20);
		gc = new GameClock();
		gc.setCurrentGame(currentGame);
		gc.runGameLoop("start");
		currentGame.setTemplate("standard");
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		main();
		GridField.setCenter(gridArea);
		GridField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){

		@Override
		public void handle(KeyEvent event) {
				KeyCode eventKey = event.getCode();
				if (eventKey == eventKey.UP || eventKey == eventKey.W)	{
					if(player.getDirection() != 2)
						player.setDirection(4);
				} else if (eventKey == eventKey.DOWN || eventKey == eventKey.S)	{
					if(player.getDirection() != 4)
						player.setDirection(2);					
				} else if (eventKey == eventKey.LEFT || eventKey == eventKey.A)	{
					if(player.getDirection() != 1)
						player.setDirection(3);					
				} else if (eventKey == eventKey.RIGHT || eventKey == eventKey.D)	{
					if(player.getDirection() != 3)
						player.setDirection(1);					
				}
			}			
		});
	}

	/**
	 * Close the program safely.
	 */
	@FXML
	private void toMenu()	{
		Window myWindow = new Window();
		try {
			gc.runGameLoop("stop");
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
		System.exit(0); // TODO - Maybe replace with a BitCoin miner :-P
	}
	
}
