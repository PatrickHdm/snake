package hdm.org.se2.snake02;

import java.awt.Point;

public class Snake {

	Point position;
	int size;
	int direction;
	
	
	/**
	 * Create a snake, with a position and a size.
	 * 
	 * @param y
	 * @param x
	 * @param size
	 */
	public Snake(int y, int x, int size)	{
		this.position = new Point(y, x);
		this.size = size;
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
		return this.size;
	}
	
	/**
	 * Set the direction of the snake.
	 * 
	 * @param direction - The direction of the snake.
	 */
	public void setDirection(int direction)	{
		this.direction = direction;
	}
	
}
