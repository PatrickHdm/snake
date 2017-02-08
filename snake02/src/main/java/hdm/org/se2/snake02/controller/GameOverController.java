package hdm.org.se2.snake02.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import hdm.org.se2.snake02.Highscore;
import hdm.org.se2.snake02.Settings;
import hdm.org.se2.snake02.Window;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class GameOverController implements Initializable {
	
	Logger log = Logger.getLogger(GameOverController.class.getName());
		
	public static Highscore highscoreConf;
	public static Settings settingsConf;
	
	public int highScore = 000;

	@FXML
	private Button restartGame;

	@FXML
	private Button saveHighscore;
	
	@FXML
	private TextField name;
	
	@FXML
	private Label score;

	public void main() {
		GameController.goc = this;
		score.setText(""+highScore);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		main();
	}

	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public void saveHighscore() throws Exception	{
		highscoreConf.setHighscore(name.getText(), ""+highScore, new Date().toString(), settingsConf.getDifficulty(), settingsConf.getTheme(), settingsConf.getMode());
		try {
			Window.sceneHandler(Window.highscore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restart game.
	 */
	@FXML
	private void restartGame()	{
		try {
			Window.sceneHandler(Window.game);
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
		}
	}
	
}
