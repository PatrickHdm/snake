package hdm.org.se2.snake02.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import hdm.org.se2.snake02.Highscore;
import hdm.org.se2.snake02.Window;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class HighscoreController implements Initializable {

	Logger log = Logger.getLogger(HighscoreController.class.getName());

	@FXML
	private BorderPane HighscoreField;

	@FXML 
	private GridPane pHighscore;

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


		String[][] scores = Highscore.readFromFile();

		if(scores != null)	{

			Arrays.sort(scores, 
		            Comparator.comparing((String[] entry) -> Double.parseDouble(entry[0]))
		                      .reversed());
			
			// Create Header for List
			Label scoreLabel = new Label("Score");
			Label nameLabel = new Label("Name");
			Label dateLabel = new Label("Date");
			Label difficultyLabel = new Label("Difficulty");
			Label themeLabel = new Label("Theme");
			Label modeLabel = new Label("Mode");
			pHighscore.add(scoreLabel, 0, 0);
			pHighscore.add(nameLabel, 1, 0);
			pHighscore.add(dateLabel, 2, 0);
			pHighscore.add(difficultyLabel, 3, 0);
			pHighscore.add(themeLabel, 4, 0);
			pHighscore.add(modeLabel, 5, 0);

			for(int i = 0; i < scores.length; i++)	{
				for(int j = 0; j < scores[i].length; j++)	{
					Label nLabel = new Label(scores[i][j]);
					pHighscore.add(nLabel, j, i + 1);
				}
			}

		}


	}

}
