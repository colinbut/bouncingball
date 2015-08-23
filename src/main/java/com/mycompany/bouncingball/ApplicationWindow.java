/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.bouncingball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Part of the View - the main window
 * 
 * This is a top level program window that contains a toolbar and an 
 * animation panel embedded within which shows an animated bouncing ball
 * bouncing from wall to wall
 * 
 * @author colin
 */
public class ApplicationWindow extends JFrame {

	private static final long serialVersionUID = 8923235811766192762L;

	private AnimationPanel animationWindow;

	/**
	 * Constructor
	 * 
	 * @param ball the bouncing ball
	 */
	public ApplicationWindow(Ball ball) {

		super("Bouncing Ball");

		// respond to the window system asking us to quit
		addWindowListener(new WindowAdapter() {
			
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		JToolBar toolBar = new JToolBar();
		addButtons(toolBar);

		// Create the animation area used for output and pass model on
		animationWindow = new AnimationPanel(ball);

		// Put it in a scrollPane, (this makes a border)
		JScrollPane scrollPane = new JScrollPane(animationWindow);

		// Lay out the content pane.
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(510, 530));
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setContentPane(contentPane);
	}

	/**
	 * Sets up the buttons on the toolbar
	 * 
	 * This method adds the 'Run', 'Stop', 'Quit' buttons to the
	 * toolbar.
	 * 
	 * @param toolBar the toolbar to add buttons to
	 */
	protected void addButtons(JToolBar toolBar) {
		
		JButton button = null;

		button = new JButton("Run");
		button.setToolTipText("Start the animation");
		
		/* Uses anonymous inner class for event handling */
		
		/* 
		 * TODO: could put this code in another separate "Controller" class maybe
		 * for e.g. ApplicationController.java
		 */
		
		// when this button is pushed it calls animationWindow.setMode(true)
		button.addActionListener(new ActionListener() {
			
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				animationWindow.setMode(true);
			}
		});
		toolBar.add(button);

		button = new JButton("Stop");
		button.setToolTipText("Stop the animation");
		// when this button is pushed it calls animationWindow.setMode(false)
		button.addActionListener(new ActionListener() {
			
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				animationWindow.setMode(false);
			}
		});
		toolBar.add(button);

		button = new JButton("Quit");
		button.setToolTipText("Quit the program");
		button.addActionListener(new ActionListener() {
			
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(button);
	}
}
