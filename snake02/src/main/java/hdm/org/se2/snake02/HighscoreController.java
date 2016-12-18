package hdm.org.se2.snake02;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;

public class HighscoreController {

	Logger log = Logger.getLogger(HighscoreController.class.getName());
	
	@FXML
	private void toMenu()	{
		try {
			Window.sceneHandler(Window.menu);
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
		}
	}
	
}
