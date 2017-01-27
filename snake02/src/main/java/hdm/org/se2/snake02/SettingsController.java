package hdm.org.se2.snake02;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import hdm.org.se2.snake02.Settings;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class SettingsController extends Settings implements Initializable {

	Logger log = Logger.getLogger(Game.class.getName());
	
	static Settings settingsConf;
	
	@FXML
	Button Back;
	
	@FXML
	Button Save;
	
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
		try	{
			String resString = resolution.getValue().toString();
			int resolutionX, resolutionY;
			String[] resSplit = resString.split("x");		
			resolutionX = Integer.parseInt(resSplit[0]);
			resolutionY = Integer.parseInt(resSplit[1]);		
			setResolution(resolutionX, resolutionY);	
			
		}catch(Exception e){
			log.info("no res found");
		}
		
		// Set the difficulty
		try	{
			String tempDiffString = difficulty.getSelectedToggle().toString();
			String [] arr = tempDiffString.split("]");
			String tempDiffString2 = arr[1];
			String difficultyString = tempDiffString2.substring(1, tempDiffString2.length()-1);
			setDifficulty(difficultyString);
		}catch(Exception e){
			log.info("no diff found");
		}
		
		
		//Set theme
		try{
			String tempThemeString = theme.getSelectedToggle().toString();
			String [] arr1 = tempThemeString.split("]");
			String tempDiffString2 = arr1[1];
			String themeString = tempDiffString2.substring(1, tempDiffString2.length()-1);
			setTheme(themeString);
		}catch(Exception e){
			log.info("no theme entered");
		}
		
		//Set mode
		try{
			String tempModeString = mode.getSelectedToggle().toString();
			String [] arr2 = tempModeString.split("]");
			String tempModeString2 = arr2[1];
			String modeString = tempModeString2.substring(1, tempModeString2.length()-1);
			setMode(modeString);
		}catch(Exception e){
			log.info("no mode found");
		}
		Settings.setSettings(getResolution(),getDifficulty(),getTheme(),getMode());

		try {
			Window.sceneHandler(Window.menu);
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
		}
	
	}
	
	public void initialize(URL location, ResourceBundle resources){		
				
		resolution.getSelectionModel().select(settingsConf.getResolution().x+"x"+settingsConf.getResolution().y);
		resolution.setPromptText(resolution.getConverter().toString(resolution.getValue()));	
		
		for(int i = 0; i < difficulty.getToggles().size();i++){
			if(difficulty.getToggles().get(i).toString().contains(settingsConf.getDifficulty()))
				difficulty.getToggles().get(i).setSelected(true);		
		}
		
		for(int i = 0; i < theme.getToggles().size();i++){
			if(theme.getToggles().get(i).toString().contains(settingsConf.getTheme()))
				theme.getToggles().get(i).setSelected(true);
		}
		
		for(int i = 0; i < mode.getToggles().size();i++){
			if(mode.getToggles().get(i).toString().contains(settingsConf.getMode()))
				mode.getToggles().get(i).setSelected(true);		
		}

		
	}
	

	@FXML
	private void toMenu()	{
		try {
			Window.sceneHandler(Window.menu);
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
		}
	}
	
	
}



