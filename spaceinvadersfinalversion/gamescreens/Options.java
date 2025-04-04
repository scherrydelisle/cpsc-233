package spaceinvadersfinalversion.gamescreens;

import spaceinvadersfinalversion.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Sets up and creates the Options Frame and executes
 * orders based on which buttons are pressed.
 */

public class Options extends JFrame implements ActionListener, Constants {
    // Sets up the JFrame options
    private JFrame options;

    private JPanel menu;
    private JPanel title;
    private JButton blue;
    private JButton red;
    private JButton green;
    private JButton orange;
    private BufferedImage blueShip;
    private BufferedImage redShip;
    private BufferedImage greenShip;
    private BufferedImage orangeShip;
    private String spaceShipModel;
    private static int choiceOfShipModel;


    public static int getChoiceOfShipModel() {
        return choiceOfShipModel;
    }

    public static void setChoiceOfShipModel(int Choice) {
        choiceOfShipModel = Choice;
    }

    /**
     * Runs the entire spaceinvadersfinalversion.GUI.
     */
    public void start(){
        gui();
    }

    /**
     * Sets up the entire spaceinvadersfinalversion.GUI for JFrame and Jpanel for spaceinvadersfinalversion.Options.
     */
    public void gui(){
        //Sets up the JFrame.
        options = new JFrame("CHOOSE YOUR COLOUR");
        options.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets up the JPanels
        menu = new JPanel(new GridBagLayout());
        menu.setBackground(Color.CYAN);

        title = new JPanel(new GridBagLayout());
        title.setBackground(Color.GRAY);

        //Sets up the JLabels, loads, catches exceptions
        try{
            blueShip = ImageIO.read(new File(Constants.PLAYER_IMAGE_FILE_NAME_BLUE));
        }
        catch(IOException f){f.printStackTrace();}
        JLabel blueShipLabel = new JLabel(new ImageIcon(blueShip));

        try{
            redShip = ImageIO.read(new File(Constants.PLAYER_IMAGE_FILE_NAME_RED));
        }
        catch(IOException f){f.printStackTrace();}
        JLabel redShipLabel = new JLabel(new ImageIcon(redShip));

        try{
            greenShip = ImageIO.read(new File(Constants.PLAYER_IMAGE_FILE_NAME_GREEN));
        }
        catch(IOException f){f.printStackTrace();}
        JLabel greenShipLabel = new JLabel(new ImageIcon(greenShip));

        try{
            orangeShip = ImageIO.read(new File(Constants.PLAYER_IMAGE_FILE_NAME_ORANGE));
        }
        catch(IOException f){f.printStackTrace();}
        JLabel orangeShipLabel = new JLabel(new ImageIcon(orangeShip));

        //Sets up the JButtons
        blue   = new JButton("  Slip-Stream  ");
        blue.setToolTipText("Use the blue ship");
        blue.addActionListener(this);

        red    = new JButton("Crimson Avenger");
        red.setToolTipText("Use the red ship");
        red.addActionListener(this);

        green  = new JButton(" Golden Zephyr ");
        green.setToolTipText("Use the green ship");
        green.addActionListener(this);

        orange = new JButton("  Terrashaker  ");
        orange.setToolTipText("Use the orange ship");
        orange.addActionListener(this);

        //Sets up a grid to place all the JButtons.
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);

        //Sets the position of all the JButtons and places them on the JPanels

        c.gridx = 0;
        c.gridy = 1;
        menu.add(blue,c);

        c.gridx = 1;
        c.gridy = 1;
        menu.add(red,c);

        c.gridx = 2;
        c.gridy = 1;
        menu.add(green,c);

        c.gridx = 3;
        c.gridy = 1;
        menu.add(orange,c);

        c.gridx = 0;
        c.gridy = 0;
        title.add(blueShipLabel,c);

        c.gridx = 1;
        c.gridy = 0;
        title.add(redShipLabel,c);

        c.gridx = 2;
        c.gridy = 0;
        title.add(greenShipLabel,c);

        c.gridx = 3;
        c.gridy = 0;
        title.add(orangeShipLabel,c);

        //Places the JPanels on the JFrame
        options.add(title);
        options.add(menu,BorderLayout.SOUTH);
        options.setVisible(true);
    }

    /**
     * Action Listener for the JButtons
     */
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == blue){
            spaceShipModel = "Slip-Stream";
            options.dispose();
            setChoiceOfShipModel(1);
            new App();
        }
        if (e.getSource() == green){

            spaceShipModel = "Golden Zephyr";
            options.dispose();
            Options.setChoiceOfShipModel(2);
            new App();

        }
        if (e.getSource() == red){

            spaceShipModel = "Crimson Avenger";
            options.dispose();
            Options.setChoiceOfShipModel(3);
            new App();

        }

        if (e.getSource() == orange){
            spaceShipModel = "Terrashaker";
            options.dispose();
            setChoiceOfShipModel(4);
            new App();
        }
    }

}
