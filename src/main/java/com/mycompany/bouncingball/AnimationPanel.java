/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.bouncingball;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

/**
 * Part of the View.
 * 
 * This is an area on the screen in which a bouncing ball animation
 * happens. This class have 2 modes: "on" and "off". During the "on" mode the ball moves.
 * During the "off" mode the ball does not move.
 * 
 * @author colin
 */
public class AnimationPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -1692042378205692151L;
	
	private AnimationEventListener eventListener; //
	
	private Timer timer;
	private boolean mode;
	
	private Ball ball; // Reference to the Model

	/**
	 * Constructor
	 * 
	 * @param b the ball (model)
	 */
	public AnimationPanel(Ball b) {
		
		super(); 
		
		ball = b; // set up Model reference
		
		setBackground(Color.DARK_GRAY);
		
		// Make View a dependent of Model
		ball.addObserver(this);

		// Creates the Controller (AnimationEventListener)
		// The setMode method sets up the AnimationEventListener to
		// also catch all mouse events on the View
		eventListener = new AnimationEventListener(ball);

		// this only initializes the timer, we actually start and stop the
		// timer in the setMode() method

		// The first parameter is how often (in milliseconds) the timer
		// should call us back. 50 milliseconds = 20 frames/second
		timer = new Timer(50, eventListener);
		mode = false;
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Component#isFocusable()
	 */
	@Override
	public boolean isFocusable() {
		// This is just here so that we can accept the keyboard focus
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);

		// the "clip rectangle" is the area of the screen that needs to be changed
		Rectangle clipRect = g.getClipBounds();

		if (clipRect.intersects(ball.boundingBox())) {
			
			// Paint the ball reflection the current position of ball
			
			// this is a very important performance optimization if there
			// are lots of objects all over the screen rather than just the single ball
			g.setColor(ball.getColor());
			g.fillOval(ball.getX() - ball.getRadius(),
					ball.getY() - ball.getRadius(),
					ball.getRadius() + ball.getRadius(), ball.getRadius()
							+ ball.getRadius());
			// g.fillRect(200, 157, 150, 150);
			// g.fillRoundRect(20, 30, 120, 120, 4, 5);
			// g.fillOval(5, 5, 100, 340);

		}
	}

	/**
	 * 
	 * @param m
	 */
	public void setMode(boolean m) {

		if (mode == true) {
			// we're about to change mode: turn off all the old listeners
			removeMouseListener(eventListener);
			removeMouseMotionListener(eventListener);
			removeKeyListener(eventListener);
		}

		mode = m;

		if (mode == true) {
			// the mode is true: turn on the listeners
			addMouseListener(eventListener);
			addMouseMotionListener(eventListener);
			addKeyListener(eventListener);
			requestFocus(); // make sure keyboard is directed to us
			timer.start();
		} 
		else {
			timer.stop();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {

		// model passes a rectangle of the changed area to be repainted
		Rectangle repaintArea = (Rectangle) arg;

		// Have Swing tell this class to run
		// its paint method in order to repaint the screen.
		// Could call repaint(), but this would repaint entire window as opposed to only
		// the portion that has changed which this is much more efficient.
		repaint(repaintArea.x, repaintArea.y, repaintArea.width,
				repaintArea.height);
	}
}
