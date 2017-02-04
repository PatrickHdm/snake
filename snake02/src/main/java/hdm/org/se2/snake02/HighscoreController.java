package hdm.org.se2.snake02;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class HighscoreController implements Initializable {

	Logger log = Logger.getLogger(HighscoreController.class.getName());
	
	@FXML
	private BorderPane HighscoreField;
	
	@FXML
	private void toMenu()	{
		try {
			Window.sceneHandler(Window.menu);
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Highscore highscore = new Highscore();
		ArrayList<String> scores = highscore.readFromFile();
		int sScores = scores.size();
		for (int i = 0; i < sScores; i++) {
			
			Label label = new Label(scores.get(i));
			HighscoreField.setCenter(label);
		}
		
		
	}
	
}
