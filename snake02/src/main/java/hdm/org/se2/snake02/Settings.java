package hdm.org.se2.snake02;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public class Settings {

	public static Logger log = Logger.getLogger(Settings.class.getName());


	Point resolution = new Point(); 
	public String difficulty;
	public String theme;
	public String mode;
	public String multiplayer;
	public Color background, player, player02, wall, food;
	final static String settingscsv = "config/Settings.csv";

	public Settings()	{
		
		File file = new File(settingscsv);
		if (file.exists()) {
			log.info("SettingsFile Found");
			readFromFile(this);
		}
		if (!file.exists()) {
			log.info("No SettingsFile Available");
			this.resolution.x = 1024;
			this.resolution.y = 768;
			this.difficulty = "Medium";
			this.theme = "Middle";
			this.mode = "Standard";
			this.background = Color.WHITE;
			this.player = Color.ORANGE;
			this.player02 = Color.RED;
			this.wall = Color.BLACK;
			this.food = Color.GREEN;
			this.multiplayer = "No";
			setSettings(getResolution(), getDifficulty(), getTheme(), getMode(), getBackground(), getPlayer(), getWall(), getFood(), getMultiplayer());
		}
	}

	public static void setSettings(Point resolution, String difficulty, String theme, String mode, Color background, Color player, Color wall, Color food, String multiplayer) {


		// Create String from Score, Name, Date & Write to File
		String temp = 
				new StringBuilder("\"").append(resolution.x).append("x").append(resolution.y).append("\",\"").append(difficulty)
				.append("\",\"").append(theme).append("\",\"").append(mode).append("\",\"").append(background.toString()).append("\",\"")
				.append(player.toString()).append("\",\"").append(wall.toString()).append("\",\"").append(food.toString())
				.append("\",\"").append(multiplayer).append("\";").toString();
		writeToFile(settingscsv, temp);

	}

	public static void writeToFile(String settingscsv, String temp) {

		try {
			// check if File exists. if not, create it. if yes, go on.
			File file = new File(settingscsv);
			if (file.exists()) {
				log.info("SettingsFile Found");
			}
			if (!file.exists()) {
				log.info("No SettingsFile Available");
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));

			bw.write(temp);
			bw.close();

		} catch (IOException e) {
			log.log(Level.SEVERE, "an exception was thrown", e);	
		}
	}

	public static void readFromFile(Settings settings){

		String csvFile = "temp/Settings.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try{
			br = new BufferedReader(new FileReader(settingscsv));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] settingsFile = line.split(cvsSplitBy);
				settingsFile[settingsFile.length - 1] = settingsFile[settingsFile.length - 1].replace(";", "");

				log.info("resolution = " + settingsFile[0].replace("\"", "") + " difficulty =" +  settingsFile[1] + 
						" theme = " + settingsFile[2] + " mode = " + settingsFile[3] + " background = " + 
						settingsFile[4] + " player = " + settingsFile[5] + " wall = " + settingsFile[6] + 
						" food = " + settingsFile[7] + " Multiplayer = " + settingsFile[8]);



				int resolutionX, resolutionY;
				String[] resSplit = settingsFile[0].replace("\"", "").split("x");		
				resolutionX = Integer.parseInt(resSplit[0]);
				resolutionY = Integer.parseInt(resSplit[1]);		
				settings.setResolution(resolutionX, resolutionY);	
				settings.setDifficulty(settingsFile[1].replace("\"", ""));
				settings.setTheme(settingsFile[2].replace("\"", ""));
				settings.setMode(settingsFile[3].replace("\"", ""));
				settings.setMultiplayer(settingsFile[8].replace("\"", ""));


				Color backgroundFix = picker2color(settingsFile[4]);
				Color playerFix = picker2color(settingsFile[5]);
				Color wallFix = picker2color(settingsFile[6]);
				Color foodFix = picker2color(settingsFile[7]);


				settings.setColors(backgroundFix, playerFix, wallFix, foodFix);

			}

		} catch (IOException e) {
			log.log(Level.SEVERE, "an exception was thrown", e);		
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.log(Level.SEVERE, "an exception was thrown", e);	
				}
			}
		}
	}



	public Point getResolution() {
		return resolution;
	}

	public void setResolution(int x, int y) {
		this.resolution.x = x;
		this.resolution.y = y;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMultiplayer() {
		return multiplayer;
	}

	public void setMultiplayer(String multiplayer) {
		this.multiplayer = multiplayer;
	}

	public void setColors(Color background, Color player, Color wall, Color food)	{
		this.background = background;
		this.player = player;
		this.wall = wall;
		this.food = food;

	}

	public Color getBackground()	{
		return background;
	}

	public Color getPlayer()	{
		return player;
	}

	public Color getWall()	{
		return wall;
	}

	public Color getFood()	{
		return food;
	}

	/**
	 * 
	 * @param colorStr e.g. "#FFFFFF"
	 * @return 
	 */
	public static Color picker2color(String colorStr) {
		colorStr = colorStr.substring(2);
		colorStr = colorStr.replace("x", "#");
		colorStr = colorStr.replace("\"", "");
		Color nColor = Color.web(colorStr);
		return nColor;
	}	

}


