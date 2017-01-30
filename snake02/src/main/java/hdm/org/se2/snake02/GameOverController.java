package hdm.org.se2.snake02;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class GameOverController implements Initializable {
		
	public static Highscore highscoreConf;
	
	public int highScore = 000;

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
	
	public void saveHighscore()	{
		highscoreConf.setHighscore(name.getText(), ""+highScore, new Date().toString());
		try {
			Window.sceneHandler(Window.highscore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
