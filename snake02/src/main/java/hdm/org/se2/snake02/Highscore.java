package hdm.org.se2.snake02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Highscore {

	public Highscore() {
		// defaults
	}

	public void setHighscore(String name, String score, String date) {
		final String scorecsv = "temp/Highscore.csv";

		// Create String from Score, Name, Date & Write to File
		String temp = new StringBuilder("\"").append(score).append("\",\"").append(name).append("\",\"").append(date)
				.append("\";").toString();
		writeToFile(scorecsv, temp);

	}

	public ArrayList<String> getHighscore(){
		final String scorecsv = "temp/Highscore.csv";
		String temp = readFromFile(scorecsv);
		ArrayList<String> TempList = new ArrayList<String>(); 
		
		for(int i = 0; i < temp.length();i++){ // Abfragen&ArrayList
			// add split when semicolon for one set
			// then divide set in 3 strings and schwupsdiwups
		}
		
		return TempList;
	}

	public String readFromFile(String scorecsv) {
		String Highscores = null;
		try {
			File file = new File(scorecsv);
			if (file.exists()) {
				System.out.println("Found");
			}
			if (!file.exists()) {
				System.out.println("No Highscores Available");
			}
			// init Reader
			BufferedReader breader = new BufferedReader(new FileReader(file));
			Highscores = breader.readLine();
			// Close Reader
			breader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Highscores;
	}

	public void writeToFile(String scorecsv, String temp) {

		try {
			// check if File exists. if not, create it. if yes, go on.
			File file = new File(scorecsv);
			if (file.exists()) {
				System.out.println("Found");
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write(temp);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
