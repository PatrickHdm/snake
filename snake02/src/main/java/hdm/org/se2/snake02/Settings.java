package hdm.org.se2.snake02;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public class Settings {


	//TODO - change visibility
	Point resolution = new Point(); 
	String difficulty;
	String theme;
	String mode;
	Color background, player, wall, food;

	public Settings()	{
		this.resolution.x = 1024;
		this.resolution.y = 768;
		this.difficulty = "easy";
		this.theme = "standard";
		this.mode = "standard";
		this.background = Color.WHITE;
		this.player = Color.ORANGE;
		this.wall = Color.BLACK;
		this.food = Color.GREEN;
	}

	public static void setSettings(Point resolution, String difficulty, String theme, String mode, Color background, Color player, Color wall, Color food) {

		final String settingscsv = "temp/Settings.csv";

		// Create String from Score, Name, Date & Write to File
		String temp = 
				new StringBuilder("\"").append(resolution.x).append("x").append(resolution.y).append("\",\"").append(difficulty)
				.append("\",\"").append(theme).append("\",\"").append(mode).append("\",\"").append(background.toString()).append("\",\"")
				.append(player.toString()).append("\",\"").append(wall.toString()).append("\",\"").append(food.toString())
				.append("\";").toString();
		writeToFile(settingscsv, temp);

	}

	public static void writeToFile(String settingscsv, String temp) {

		try {
			// check if File exists. if not, create it. if yes, go on.
			File file = new File(settingscsv);
			if (file.exists()) {
				System.out.println("Found");
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));

			bw.write(temp);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}

	public static void readFromFile(Settings settings){

		String csvFile = "temp/Settings.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try{
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] settingsFile = line.split(cvsSplitBy);
				settingsFile[settingsFile.length - 1] = settingsFile[settingsFile.length - 1].replace(";", "");

				System.out.println("resolution = " + settingsFile[0].replace("\"", "") + " difficulty =" +  settingsFile[1] + 
						" theme = " + settingsFile[2] + " mode = " + settingsFile[3] + " background = " + 
						settingsFile[4] + " player = " + settingsFile[5] + " wall = " + settingsFile[6] + 
						" food = " + settingsFile[7]);



				int resolutionX, resolutionY;
				String[] resSplit = settingsFile[0].replace("\"", "").split("x");		
				resolutionX = Integer.parseInt(resSplit[0]);
				resolutionY = Integer.parseInt(resSplit[1]);		
				settings.setResolution(resolutionX, resolutionY);	
				settings.setDifficulty(settingsFile[1].replace("\"", ""));
				settings.setTheme(settingsFile[2].replace("\"", ""));
				settings.setMode(settingsFile[3].replace("\"", ""));
				
				
				Color backgroundFix = picker2color(settingsFile[4]);
				Color playerFix = picker2color(settingsFile[5]);
				Color wallFix = picker2color(settingsFile[6]);
				Color foodFix = picker2color(settingsFile[7]);
				
				
				settings.setColors(backgroundFix, playerFix, wallFix, foodFix);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
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


