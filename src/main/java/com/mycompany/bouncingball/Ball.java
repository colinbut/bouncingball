/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.bouncingball;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Observable;

/**
 * This is the model of a MVC. Just the ball. 
 * 
 * A bouncing ball is a mutable data type where it simulates a 
 * rubber ball bouncing inside a 2 dimensional box.
 * 
 * @author colin
 */
public class Ball extends Observable {
	
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int radius;

	private Color color;

	/**
	 * Constructor
	 */
	public Ball() {
		super();
		
		x = (int) ((Math.random() * 100.0) + 100.0);
		y = (int) ((Math.random() * 100.0) + 100.0);
		
		vx = (int) ((Math.random() * 10.0) + 10.0);
		vy = (int) ((Math.random() * 10.0) + 10.0);
		
		radius = 6;
		
		color = new Color(255, 0, 0);
	}

	/**
	 * Gets the colour of the ball
	 * 
	 * @return the ball colour
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Gets the radius of the circle ball
	 * 
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Gets the x coordinate of the ball
	 * 
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y coordinate of the ball
	 * 
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Moves the ball according to its velocity. It reflects off the walls which it
	 * cause the ball to change direction.
	 */
	public void move() {
		
		// Build the repaint area;
		Rectangle oldPos = this.boundingBox();

		x += vx;
		if (x <= radius) {
			x = radius;
			vx = -vx;
		}
		if (x >= 500 - radius) {
			x = 500 - radius;
			vx = -vx;
		}

		y += vy;
		if (y <= radius) {
			y = radius;
			vy = -vy;
		}
		if (y >= 500 - radius) {
			y = 500 - radius;
			vy = -vy;
		}

		// Add to repaint area
		Rectangle repaintArea = oldPos.union(this.boundingBox());

		// Cause an update to the View with repaint area as argument
		// Need to set changed before calling notifyObservers

		this.setChanged();
		this.notifyObservers(repaintArea);

	}

	/**
	 * Changes the velocity of the ball by a random amount
	 */
	public void randomBump() {
		vx += (int) ((Math.random() * 10.0) - 5.0);
		vx = -vx;
		vy += (int) ((Math.random() * 10.0) - 5.0);
		vy = -vy;
	}

	

	/**
	 * Returns the smallest rectangle that completely covers the 
	 * current position of the ball.
	 * 
	 * A rectangle is the x,y for the upper left corner and then the 
	 * width and height
	 * 
	 * @return the smallest rectangle 
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x - radius, y - radius, radius + radius + 1,
				radius + radius + 1);
	}
}
