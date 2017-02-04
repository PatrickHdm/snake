package hdm.org.se2.snake02;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface IPlayer {

	public int playerIndex = 0;
	public Point position = new Point(0, 0);
	public int score = 0;
	public int size = 0;
	public LinkedList<Point> entityPosition = new LinkedList<Point>();
	public int direction = 1;
	public Object cur = new Object();
	public boolean isDeath = false;
	
	
}