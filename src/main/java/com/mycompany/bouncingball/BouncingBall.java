package com.mycompany.bouncingball;

/**
 * The main program.
 * 
 * @author colin
 */
public class BouncingBall {

	/**
	 * Main entry
	 * 
	 * @param args program arguments
	 */
	public static void main(String[] args) {

		// create Model
		Ball ball = new Ball();

		// create View, passing reference to the Model
		ApplicationWindow frame = new ApplicationWindow(ball);

		// realizes the top level application window
		frame.setLocation(200, 200);
		frame.pack();
		frame.setVisible(true);
	}
}
