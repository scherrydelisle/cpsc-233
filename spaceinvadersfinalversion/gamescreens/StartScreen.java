package spaceinvadersfinalversion.gamescreens;

import spaceinvadersfinalversion.Constants;
import spaceinvadersfinalversion.gamescreens.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Sets up and creates the "spaceinvadersfinalversion.StartScreen" Frame and executes
 * orders based on which buttons are pressed.
 */

public class StartScreen extends JFrame implements ActionListener, Constants {

    //Sets all the instance variables.
    private JFrame startScreen;
    private JPanel menu;
    private JPanel title;
    private JButton start;
    private JButton exit;
    private BufferedImage logo;

    /**
     * Runs the entire spaceinvadersfinalversion.GUI.
     */


    public void start() {
        gui();
    }


    /**
     * Sets up the entire spaceinvadersfinalversion.GUI.
     */
    public void gui() {

        //Sets up the JFrame.
        startScreen = new JFrame("Space Invaders");
        startScreen.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setResizable(false);

        //Sets up the JPanels.
        menu = new JPanel(new GridBagLayout());
        menu.setBackground(Color.CYAN);

        title = new JPanel();
        title.setBackground(Color.BLACK);

        //Sets up the JLabels.
        try {
            logo = ImageIO.read(new File("SpaceInvadersLogo.png"));
        } catch (IOException f) {
            f.printStackTrace();
        }
        JLabel logoLabel = new JLabel(new ImageIcon(logo));

        //Sets up the JButtons.
        start = new JButton("Start Game");
        start.setToolTipText("Starts the game");
        start.addActionListener(this);

        exit = new JButton("Exit");
        exit.setToolTipText("Exit the game");
        exit.addActionListener(this);

        //Sets up a grid to place all the JButtons.
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);

        //Sets the position of all the JButtons and places them on a JPanel.
        c.gridx = 0;
        c.gridy = 1;
        menu.add(start, c);

        c.gridx = 0;
        c.gridy = 3;
        menu.add(exit, c);

        //Places all labels on the JPanels.
        title.add(logoLabel);

        //Places the JPanels on the JFrame.
        startScreen.add(title);
        startScreen.add(menu, BorderLayout.SOUTH);
        startScreen.setVisible(true);
    }

    /**
     * Action listener for the JButtons.
     */
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == start) {
            startScreen.dispose();
            Options options = new Options();
            options.start();

        }

        //Closes the game when the "exit" button is clicked.
        if (actionEvent.getSource() == exit) {
            System.exit(0);
        }
    }
}