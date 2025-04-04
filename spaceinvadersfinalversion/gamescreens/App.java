package spaceinvadersfinalversion.gamescreens;

import spaceinvadersfinalversion.Connector;
import spaceinvadersfinalversion.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * The class App initializes the JFrame and JPanel settings and attaches the connector
 * to the JFrame. It also contains the main method for the game.
 */

public class App extends Canvas implements Constants {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private Connector connector = new Connector();

    /**
     *Constructor for spaceinvadersfinalversion.App class
     * Initializes Jframe & Jpanel settings, attaches connector - an Connector object -  to JFrame
     * and adds connector's KeyListener to the JPanel.
     */

    public App() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.setVisible(true);


        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
        panel.add(this);
        panel.add(connector);
        panel.addKeyListener(connector);
        panel.setFocusable(true);


    }

    public static void main(String[] args) {
		StartScreen startScreen = new StartScreen();
		startScreen.start();
    }


}
