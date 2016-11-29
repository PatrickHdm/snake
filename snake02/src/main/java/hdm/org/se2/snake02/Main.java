package hdm.org.se2.snake02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class Main {

	static Logger log = Logger.getLogger(Game.class.getName());
	
	public static void main(String[] args) { 
		log.info("Start window...");
		 new Thread() {
	            @Override
	            public void run() {
	                javafx.application.Application.launch(Window.class);
	            }
	        }.start();

	}

}
