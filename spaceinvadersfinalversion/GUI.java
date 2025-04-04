package spaceinvadersfinalversion;

import spaceinvadersfinalversion.entities.Alien;
import spaceinvadersfinalversion.entities.Bomb;
import spaceinvadersfinalversion.entities.Bullet;
import spaceinvadersfinalversion.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.Graphics;

/**
 * This class contains all of the drawing methods that are used within the game.
 */

public class GUI extends JPanel {
    private Connector guiConnector;

    public GUI(Connector connector) {
        guiConnector = connector;
    }

    /**
    * Each time this method is called, the array list is iterated through
    * and graphics2D paints the objects at their current locations
    *
    * @param graphics is default, needed to draw
    * @param aliens: an Arraylist filled with alien objects.
    */

    public void drawAliens(Graphics graphics, ArrayList<Alien> aliens) {
        for (int counter = 0; counter < aliens.size(); counter++) {
            Alien alien = aliens.get(counter);
            Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.drawImage(alien.getImage(), alien.getx(), alien.gety(), null);
        }
    }
    /**
    * Each time this method is called, the array list is iterated through
    * and graphics2D paints the objects at their current locations
    *
    * @param graphics is default, needed to draw
    * @param bullets: an Arraylist filled with bullet objects.
    */
    public void drawBullets(Graphics graphics, ArrayList<Bullet> bullets) {
        for (int counter = 0; counter < bullets.size(); counter++) {
            Bullet bullet = bullets.get(counter);
            Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.drawImage(bullet.getImage(),bullet.getx(), bullet.gety(), null);
        }
    }
    /**
    * Each time this method is called, the array list is iterated through
    * and graphics2D paints the objects at their current locations
    *
    * @param graphics is default, needed to draw
    * @param bombs: an Arraylist filled with bomb objects.
    */
    public void drawBombs(Graphics graphics, ArrayList<Bomb> bombs) {
        for (int counter = 0; counter < bombs.size(); counter ++) {
            Bomb bomb = bombs.get(counter);
            Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.drawImage(bomb.getImage(), bomb.getx(), bomb.gety(), null);
        }
    }
    /**
    * Each time this method is called, the array list is iterated through
    * and graphics2D paints the objects at their current locations
    *
    * @param graphics is default, needed to draw
    * @param player: the current player object.
    */
    public void drawPlayer(Graphics graphics, Player player) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.drawImage(player.getImage(), player.getx(), player.gety(), null);
    }

    /**
     * The method inGameDisplay is used to display the level, the current score, the number of lives, and the
     * the allTimeHighScore when the game is in session. The allTimeHighScore is read from a file in Scoreboard
     * @param graphics
     * @param allTimeHighScore
     */


    public void inGameDisplay(Graphics graphics, String allTimeHighScore) {
        graphics.setFont(new Font("Ariel", Font.BOLD, 14));
        graphics.drawString("Level " + Integer.toString(guiConnector.getLevel()), 40, 25);
        graphics.drawString("Your score is " + Integer.toString(guiConnector.getScore()), 775, 25);
        graphics.drawString("Lives " + Integer.toString(guiConnector.getLives()), 40, 40);
        graphics.drawString("High score is " + allTimeHighScore, 775, 40);
    }
}
