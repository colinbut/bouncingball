/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.bouncingball;

import java.awt.event.*;

/**
 * The Controller
 * 
 * <p>
 * Overview:
 * 
 * <p>
 * AnimationEventListener is a MVC Controller class that responds
 * to all sorts of external events - timer, mouse, and keyboard.
 * 
 * <p>
 * It owns and sends semantic actions to the model (the ball)
 * 
 * <p>
 * Extending the MouseAdapter gives us empty methods for the MouseListener 
 * interface: mouseClicked, mouseEntered, mouseExited, mousePressed, and 
 * mouseReleased.
 * 
 * <p>
 * For this example, we only concerned with mouseClicked so only overriding that.
 * 
 * @author colin
 * 
 */
public class AnimationEventListener extends MouseAdapter implements
		MouseMotionListener, KeyListener, ActionListener {

	// model
	private Ball ball;

	/**
	 * Constructor
	 * 
	 * @param b the ball (model)
	 */
	public AnimationEventListener(Ball b) {
		super();
		ball = b;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		ball.randomBump();
	}

	/* Here's the MouseMotionListener interface */
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// do nothing
	}

	/* Here's the KeyListener interface */
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		// if one of the keys A-J is pressed it triggers the ball to
		// be bumped in a random direction.
		int keynum = e.getKeyCode();

		if ((keynum >= 65) && (keynum <= 74)) {
			System.out.println("keypress " + e.getKeyCode());
			ball.randomBump();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	/* this is the callback for the timer */
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// make changes to the logical animation state - the Model
		// All repainting is done in the AnimationPanel View
		ball.move();
		
	}
}
