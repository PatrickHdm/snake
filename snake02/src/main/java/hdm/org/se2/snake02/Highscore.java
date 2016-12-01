package hdm.org.se2.snake02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Highscore {

	public void Setter() {
		try {
			String name = "\"NTest\"";
			String score = "\"S2433243242\"";
			File file = new File("C:/test.csv");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(name);
			bw.write(score);
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Getter() {
		try {
			FileReader in = new FileReader("C:/test.csv");
			BufferedReader br = new BufferedReader(in);
			
			String temp;
			String name;
			String score;
			while ((temp = br.readLine()) != null) {
				if(temp.charAt(1)=='N'){
					name = temp;
				}
				if(temp.charAt(1)=='S'){
					score = temp;
				}
			}

			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

