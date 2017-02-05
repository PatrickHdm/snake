package hdm.org.se2.snake02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Highscore {
	final String scorecsv = "temp/Highscore.csv";

	public Highscore() {

	}

	public void setHighscore(String name, String score, String date) {

		// Create String from Score, Name, Date & Write to File
		String temp = new StringBuilder("\"").append(score).append("\",\"").append(name).append("\",\"").append(date)
				.append("\";").toString();
		writeToFile(scorecsv, temp);

	}

	public ArrayList<String> readFromFile() {
		ArrayList<String> TempList = new ArrayList<String>();

		try {
			File file = new File("temp/Highscore.csv");
			if (file.exists()) {
				System.out.println("HighScoreFile Found");
			}
			if (!file.exists()) {
				System.out.println("No HighscoreFile Available");
			}
			// init Reader
			BufferedReader breader = new BufferedReader(new FileReader(file));
			String temp = breader.readLine();
			
			
			String[] ntemp = temp.split(";");
			breader.close(); // Close Reader
			
			for (int i = 0; i < ntemp.length; i++) { // Abfragen&ArrayList
				TempList.add(ntemp[i]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return TempList;
	}

	public void writeToFile(String scoresv, String temp) {

		try {
			// check if File exists. if not, create it. if yes, go on.
			File file = new File(scorecsv);
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
