package hdm.org.se2.snake02;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameClock {
	Logger log = Logger.getLogger(GameClock.class.getName());

	// TODO - Set up visibility
	private volatile boolean running = false;
	public boolean paused = false;
	private int fps = 30;
	public static int speedDivi = 120000000; // As lower as faster  
	private int frameCount = 0;
	GameController gameController;
	private Game currentGame;
	private Snake player01;
	private Snake player02;
	private Thread loop;

	public void runGameLoop()	{
		loop = new Thread()
		{
			public void run()
			{
				running = true;
				gameLoop();
			}
		};
		loop.start();
	}
	
	public void gameStartStop(String mode)	{
		switch(mode)	{
		case "start":
			this.paused = false;
			break;
		case "break":
			this.paused = true;
			break;
		case "stop":
			running = false;
//			loop.stop();
			break;
		default:
			log.warning("We couldn't found the selected mode: "+mode);
		}
	}

	private void gameLoop()	{
		log.info("Start the gameclock!");
		//This value would probably be stored elsewhere.
		final double GAME_HERTZ = 30.0;
		//Calculate how many ns each frame should take for our target game hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		//At the very most we will update the game this many times before a new render.
		//If you're worried about visual hitches more than perfect timing, set this to 1.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		//We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		//Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		//If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

		//Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		while (running)
		{
			double now = System.nanoTime();
			int updateCount = 0;

			if (!paused)
			{
				//Do as many game updates as we need to, potentially playing catchup.
				while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
				{
					updateGame();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				//If for some reason an update takes forever, we don't want to do an insane number of catchups.
				//If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
				if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
				{
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				//Render. To do so, we need to calculate interpolation for a smooth render.
				float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
				//drawGame(interpolation);
				lastRenderTime = now;

				//Update the frames we got.
				int thisSecond = (int) (lastUpdateTime / speedDivi);
				if (thisSecond > lastSecondTime)
				{
					fps = frameCount;
					frameCount = 0;
					updateGameByFrame();	
					lastSecondTime = thisSecond;
				}
				//Yield until it has been at least the target time between renders. This saves the CPU from hogging.
				while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
				{
					Thread.yield();

					//This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
					//You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
					//FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
					try {Thread.sleep(1);} catch(Exception e) {} 

					now = System.nanoTime();
				}
			}
		}
	}

	/**
	 * Update   the game.
	 * 
	 */
	public void updateGame()	{		
		if(currentGame.getPlayerStatus() == true)	{
			try {
				log.info("GAME OVER");
				running = false;
				gameController.toGameOver();
			} catch (Exception e1) {
				log.log(Level.SEVERE, "an exception was thrown", e1);

			}
		}
	}
	
	/**
	 * Update the game by frame.
	 * 
	 */
	public void updateGameByFrame()	{
		if(player02 == null)	{
			currentGame.step(player01);
		} else	{
			currentGame.step(player01);
			currentGame.step(player02);				
		}
	}
	
	/**
	 * Set the current game to the gameclock to use.
	 * 
	 * @param game
	 */
	public void setCurrentGame(GameController gameController, Game game, Snake player01, Snake player02)	{
		this.gameController = gameController;
		this.currentGame = game;
		this.player01 = player01;
		this.player02 = player02;
	}
}
