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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class Settings {
	

	//TODO - change visibility
	Point resolution = new Point(); 
	String difficulty;
	String theme;
	String mode;

	public Settings()	{
		resolution.x = 1024;
		resolution.y = 768;
		
		difficulty = "easy";
		
		theme = "standard";
		
		mode = "standard";
	}
	
	public static void setSettings(Point resolution, String difficulty, String theme, String mode) {
		
		final String settingscsv = "temp/Settings.csv";

		// Create String from Score, Name, Date & Write to File
		String temp = new StringBuilder("\"").append(resolution.x).append("x").append(resolution.y).append("\",\"").append(difficulty).append("\",\"").append(theme).append("\",\"").append(mode)
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
			e.printStackTrace(); }
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

                System.out.println("resolution = " + settingsFile[0].replace("\"", "") + " difficulty =" +  settingsFile[1] + 
                		" theme = " + settingsFile[2] + " mode = " + settingsFile[3]);
                
                
               
                int resolutionX, resolutionY;
    			String[] resSplit = settingsFile[0].replace("\"", "").split("x");		
    			resolutionX = Integer.parseInt(resSplit[0]);
    			resolutionY = Integer.parseInt(resSplit[1]);		
    			settings.setResolution(resolutionX, resolutionY);	
    			settings.setDifficulty(settingsFile[1].replace("\"", ""));
    			settings.setTheme(settingsFile[2].replace("\"", ""));
    			settings.setMode(settingsFile[3].replace("\"", ""));
    			
    			
                
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
	
	
	}
	

