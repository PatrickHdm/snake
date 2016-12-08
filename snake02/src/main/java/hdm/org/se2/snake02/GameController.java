package hdm.org.se2.snake02;

import java.awt.event.KeyListener;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameController implements Initializable, KeyListener {
	Logger log = Logger.getLogger(GameController.class.getName());

	Game currentGame;
	Snake player;
	GameClock gc;
	GridPane gridArea = new GridPane();
		
	public void main()	{
		player = new Snake(24 / 2, 24 / 2, 3);
		currentGame = new Game(gridArea,player,24, 24, 20);
		gc = new GameClock();
		gc.setCurrentGame(currentGame);
		gc.runGameLoop("start");
	}


	@FXML
	private BorderPane GridField;
	
	public void initialize(URL location, ResourceBundle resources) {
		main();
		GridField.setCenter(gridArea);
	}

	/**
	 * Close the program safely.
	 */
	@FXML
	private void toMenu()	{
		Window myWindow = new Window();
		try {
			gc.runGameLoop("break");
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

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		System.out.println("Pressed");
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		System.out.println("Released");
		
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		System.out.println("Typed");
		
	}
	
}
