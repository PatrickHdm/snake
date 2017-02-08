package hdm.org.se2.snake02.controller;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import hdm.org.se2.snake02.Game;
import hdm.org.se2.snake02.Settings;
import hdm.org.se2.snake02.Window;

import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class SettingsController extends Settings implements Initializable, ActionListener {

	Logger log = Logger.getLogger(Game.class.getName());

	public static Settings settingsConf;
	public static Window currentWindow;

	@FXML
	Button Back;

	@FXML
	Button Save;

	@FXML
	ComboBox resolutionFxml;

	@FXML
	ToggleGroup difficultyFxml;

	@FXML
	ToggleGroup themeFxml;

	@FXML
	ToggleGroup modeFxml;

	@FXML
	ToggleGroup multiplayerFxml;

	@FXML
	ColorPicker colorPickerBackgroundFxml;

	@FXML
	ColorPicker colorPickerPlayerFxml;

	@FXML
	ColorPicker colorPickerWallFxml;

	@FXML
	ColorPicker colorPickerFoodFxml;

	@FXML	
	public void saveSettings() throws Exception{

		boolean isReadyToSave = false;
		boolean isOpen = true;

		// Set the resolution
		try	{
			String resString = resolutionFxml.getValue().toString();
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
			String tempDiffString = difficultyFxml.getSelectedToggle().toString();
			String [] arr = tempDiffString.split("]");
			String tempDiffString2 = arr[1];
			String difficultyString = tempDiffString2.substring(1, tempDiffString2.length()-1);
			setDifficulty(difficultyString);
		}catch(Exception e){
			log.info("no diff found");
		}


		//Set theme
		try{
			String tempThemeString = themeFxml.getSelectedToggle().toString();
			String [] arr1 = tempThemeString.split("]");
			String tempDiffString2 = arr1[1];
			String themeString = tempDiffString2.substring(1, tempDiffString2.length()-1);
			setTheme(themeString);
		}catch(Exception e){
			log.info("no theme entered");
		}

		//Set mode
		try{
			String tempModeString = modeFxml.getSelectedToggle().toString();
			String [] arr2 = tempModeString.split("]");
			String tempModeString2 = arr2[1];
			String modeString = tempModeString2.substring(1, tempModeString2.length()-1);
			setMode(modeString);
		}catch(Exception e){
			log.info("no mode found");
		}

		//Set mode
		try{
			String tempMultiplayerString = multiplayerFxml.getSelectedToggle().toString();
			String [] arr3 = tempMultiplayerString.split("]");
			String tempMultiplayerString2 = arr3[1];
			String multiplayerString = tempMultiplayerString2.substring(1, tempMultiplayerString2.length()-1);
			setMultiplayer(multiplayerString);
		}catch(Exception e){
			log.info("no multiplayer found");
		}

		try{			
			setColors(picker2color(colorPickerBackgroundFxml), picker2color(colorPickerPlayerFxml), picker2color(colorPickerWallFxml), picker2color(colorPickerFoodFxml));
		} catch(Exception e1)	{
			log.log(Level.SEVERE, "an exception was thrown", e1);			
		}

		Settings.setSettings(getResolution(),getDifficulty(),getTheme(),getMode(),getBackground(),getPlayer(),getWall(),getFood(),getMultiplayer());

		currentWindow.restart(currentWindow.windowStage);

	}

	public void saveAllSettings()	{
		try {
			saveSettings();
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
		}

	}

	public void initialize(URL location, ResourceBundle resources){		

		resolutionFxml.getSelectionModel().select(settingsConf.getResolution().x+"x"+settingsConf.getResolution().y);
		resolutionFxml.setPromptText(resolutionFxml.getConverter().toString(resolutionFxml.getValue()));	

		for(int i = 0; i < difficultyFxml.getToggles().size();i++){
			if(difficultyFxml.getToggles().get(i).toString().contains(settingsConf.getDifficulty()))
				difficultyFxml.getToggles().get(i).setSelected(true);		
		}

		for(int i = 0; i < themeFxml.getToggles().size();i++){
			if(themeFxml.getToggles().get(i).toString().contains(settingsConf.getTheme()))
				themeFxml.getToggles().get(i).setSelected(true);
		}

		for(int i = 0; i < modeFxml.getToggles().size();i++){
			if(modeFxml.getToggles().get(i).toString().contains(settingsConf.getMode()))
				modeFxml.getToggles().get(i).setSelected(true);		
		}

		for(int i = 0; i < multiplayerFxml.getToggles().size();i++){
			if(multiplayerFxml.getToggles().get(i).toString().contains(settingsConf.getMultiplayer()))
				multiplayerFxml.getToggles().get(i).setSelected(true);		
		}

		colorPickerBackgroundFxml.setValue(getBackground());
		colorPickerPlayerFxml.setValue(getPlayer());
		colorPickerWallFxml.setValue(getWall());
		colorPickerFoodFxml.setValue(getFood());


	}


	@FXML
	private void toMenu()	{
		try {
			Window.sceneHandler(Window.menu);
		} catch (Exception e1) {
			log.log(Level.SEVERE, "an exception was thrown", e1);
		}
	}

	/**
	 * 
	 * @param colorStr e.g. "#FFFFFF"
	 * @return 
	 */
	public static Color picker2color(ColorPicker colorPicker) {
		Color nColor = null;
		String colorString = colorPicker.toString().format( "#%02X%02X%02X",
				(int)( colorPicker.getValue().getRed() * 255 ),
				(int)( colorPicker.getValue().getGreen() * 255 ),
				(int)( colorPicker.getValue().getBlue() * 255 ) );
		nColor = nColor.web(colorString);
		return nColor;
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {		
	}


}



