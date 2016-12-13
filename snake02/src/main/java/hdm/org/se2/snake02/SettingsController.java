package hdm.org.se2.snake02;


import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class SettingsController extends Settings {

	Logger log = Logger.getLogger(Game.class.getName());
	
	@FXML
	Button Back;
	
	@FXML
	ComboBox resolution;

	@FXML
	ToggleGroup difficulty;

	@FXML
	ToggleGroup theme;

	@FXML
	ToggleGroup mode;

	@FXML	
	public void saveSettings(){

		// Set the resolution
		String resString = resolution.getValue().toString();
		int resolutionX, resolutionY;
		String[] resSplit = resString.split("x");		
		resolutionX = Integer.parseInt(resSplit[0]);
		resolutionY = Integer.parseInt(resSplit[1]);		
		setResolution(resolutionX, resolutionY);		
		
	}




	@FXML
	private void toMenu()	{
		Window myWindow = new Window();
		try {
			myWindow.sceneHandler("/Menu.fxml");
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);

		}
	}
}



