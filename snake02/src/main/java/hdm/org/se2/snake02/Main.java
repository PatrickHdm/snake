package hdm.org.se2.snake02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start window...");
		 new Thread() {
	            @Override
	            public void run() {
	                javafx.application.Application.launch(Window.class);
	            }
	        }.start();

	}

}
