package spaceinvadersfinalversion.gamescreens;

import spaceinvadersfinalversion.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Sets up and creates the "spaceinvadersfinalversion.EndScreen" Frame and executes
 * orders based on which buttons are pressed.
 */

public class EndScreen extends JFrame implements ActionListener, Constants {

	//Sets all instance variables.
	private JFrame endScreen;
	private JPanel endPanel;
	private JButton endGame;
	private JLabel gameOver;
	private JLabel scoreDisplay;
	/**
	 * Runs the EndScreen GUI
	 */
	public EndScreen(int newUserHighScoreValue){
		endScreenGUI(newUserHighScoreValue);
	}

	/**
	 * Sets up the entire spaceinvadersfinalversion.GUI.
	 */
	public void endScreenGUI(int newUserHighScoreValue){

		//Sets up the JFrame.
		endScreen = new JFrame("Space Invaders");
		endScreen.setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		endScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endScreen.setResizable(false);

		//Sets up the JPanel.
		endPanel = new JPanel(new GridBagLayout());
		endPanel.setBackground(Color.CYAN);

		//Sets up the JLabels.
		gameOver = new JLabel("GAME OVER");
		scoreDisplay = new JLabel("Your score is " + newUserHighScoreValue);
		//Sets up the JButton.
		endGame = new JButton("QUIT");
		endGame.setToolTipText("Quit the game");
		endGame.addActionListener(this);

		//Sets up a grid to place all the JButtons and JLabels.
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20,20,20,20);

		//Sets the position of all the JButtons and JLabels and places them on the JPanel.
		c.gridx = 0;
		c.gridy = 0;
		endPanel.add(gameOver,c);

		c.gridx = 0;
		c.gridy = 1;
		endPanel.add(scoreDisplay,c);

		c.gridx = 0;
		c.gridy = 2;
		endPanel.add(endGame,c);

		//Places the JPanel onto the JFrame.
		endScreen.add(endPanel);
		endScreen.setVisible(true);
	}

	/**
	 * ActionListener for the JButton.
	 */
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == endGame){
			System.exit(0);
		}
	}
}