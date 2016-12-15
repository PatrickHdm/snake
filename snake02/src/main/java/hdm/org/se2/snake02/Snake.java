package hdm.org.se2.snake02;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake {

	Point position;
	int score;
	int size;
	LinkedList<Point> entityPosition = new LinkedList<Point>();
	int direction;
	Object cur;
	boolean isDeath;
	
	/**
	 * Create a snake, with a position and a size.
	 * 
	 * @param y
	 * @param x
	 * @param size
	 */
	public Snake(int y, int x, int size)	{
		this.position = new Point(y, x);
		this.score = 0;
		this.size = size;
		this.direction = 1;
		this.isDeath = false;
	}
	
	/**
	 * Get the current position of the snake
	 * 
	 * @return - Current position.
	 */
	public Point getPosition()	{
		return this.position;
	}
	
	/**
	 * Set a new position for the snake
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y)	{
		position.y = y;
		position.x = x;
	}
	
	/**
	 * Get the current score of the snake
	 * 
	 * @return - The current score.
	 */
	public int getSore()	{
		return this.score;
	}
	
	/**
	 * Add a score to the snake.
	 * 
	 * @param size - A score to add on the current score.
	 */
	public void setScore(int score)	{
		this.score += score;
	}
	
	/**
	 * Get the current size of the snake
	 * 
	 * @return - The current size.
	 */
	public int getSize()	{
		return this.size;
	}
	
	/**
	 * Add a size to the snake.
	 * 
	 * @param size - A size to add on the current size.
	 */
	public void setSize(int size)	{
		this.size += size;
	}
	
	/**
	 * Get the current direction of the snake
	 * 
	 * @return - The current direction.
	 */
	public int getDirection()	{
		return this.direction;
	}
	
	/**
	 * Set the direction of the snake.
	 * 
	 * @param direction - The direction of the snake.
	 */
	public void setDirection(int direction)	{
		this.direction = direction;
	}
	
	/**
	 * Get the current status of the players life
	 * 
	 * @return - The players life
	 */
	public boolean getIsDeath() {
		return this.isDeath;
	}
	
	/**
	 * Set the current status of the players life
	 * 
	 * @param rip - Set the status to true or false
	 */
	public void setIsDeath(Boolean rip)	{
		this.isDeath = rip;
	}
	
}
