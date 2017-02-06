package hdm.org.se2.snake02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Highscore {

	public static Logger log = Logger.getLogger(Highscore.class.getName());

	static Window currentWindow;
	final static String highscorecsv = "config/Highscore.csv";

	public Highscore() {

	}

	public void setHighscore(String name, String score, String date) throws Exception {

		// Create String from Score, Name, Date & Write to File
		String temp = new StringBuilder("\"").append(score).append("\",\"").append(name).append("\",\"").append(date)
				.append("\";").toString();
		writeToFile(highscorecsv, temp);


		currentWindow.restart(currentWindow.windowStage);

	}

	public static ArrayList<String> readFromFile() {
		ArrayList<String> TempList = new ArrayList<String>();

		try {
			File file = new File(highscorecsv);
			if (file.exists()) {
				log.info("HighScoreFile Found");
			} else {
				file.createNewFile();
				log.info("No HighscoreFile Available");
			}

			// init Reader
			BufferedReader breader = new BufferedReader(new FileReader(file));
			if(breader.ready())	{
				String temp = breader.readLine();

				if(!temp.isEmpty())	{
					String[] ntemp = temp.split(";");
					breader.close(); // Close Reader

					for (int i = 0; i < ntemp.length; i++) { // Abfragen&ArrayList
						TempList.add(ntemp[i]);
					}
				}
			}

		} catch (IOException e) {
			log.log(Level.SEVERE, "an exception was thrown", e);
		}

		return TempList;
	}

	public void writeToFile(String scoresv, String temp) {

		try {
			// check if File exists. if not, create it. if yes, go on.
			File file = new File(highscorecsv);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write(temp);
			bw.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, "an exception was thrown", e);
		}

	}

}
