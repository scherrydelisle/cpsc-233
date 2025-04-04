package spaceinvadersfinalversion;

import spaceinvadersfinalversion.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The connector class connects many of the other classes in the game.
 * In the connector class, instances of spaceinvadersfinalversion.GameLogic and spaceinvadersfinalversion.GUI are
 * fed through. This class contains a timer through which
 * action events are handled, a key listener, and a method to update the graphics of the game.
 */
public class Connector extends JPanel implements Constants, ActionListener, KeyListener {

    private GUI gui = new GUI(this);
    private GameLogic gameLogic = new GameLogic(this);
    private Timer timer = new Timer(Constants.TIMER_INTERVAL, this);
    private String allTimeHighScoreSignature = gameLogic.getAllTimeHighScoreSignature();


    public int getLevel() {
        return gameLogic.getLevel();
    }
    public int getLives() {
        return gameLogic.getLives();
    }
    public int getScore() {
        return gameLogic.getScore();
    }

    public int getUserHighScoreValue() {
        return gameLogic.getUserHighScoreValue();
    }

    /**
     * In the constructor for Connector, we start a timer after an initial delay. This timer
     * is used for the action handling.
     */

    public Connector() {
        timer.setInitialDelay(Constants.TIMER_INITIAL_DELAY);
        timer.start();
    }

    /**
     * The method paintComponent overrides the similarly named method in the class Graphics.
     * This method calls the drawing methods contained within the class GUI to draw instances of entities
     * from the gameLogic object onto the screen.
     * @param graphics
     */

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        gui.drawAliens(graphics, gameLogic.getAliens());
        gui.drawPlayer(graphics, gameLogic.getPlayer());
        gui.drawBullets(graphics, gameLogic.getBullets());
        gui.drawBombs(graphics, gameLogic.getBombs());
        gui.inGameDisplay(graphics, allTimeHighScoreSignature);

    }

    /**
     * This method overrides the actionPerformed method in ActionListener. When the timer clicks,
     * this method performs the inGameLoop of the gameLogic object. After the inGameLoop is performed,
     * the screen is repainted to update the changes.
     * @param event
     */

    public void actionPerformed(ActionEvent event) {
        gameLogic.inGameLoop(this);
        repaint();
        if (gameLogic.isStopTimer()){timer.stop();}
    }

    /**
     * This method overrides the keyPressed method in KeyListener. When a key is pressed, this method
     * checks if the key pressed is a space key, a left key, or a right key. If the keyPressed method
     * determines that one of the aforementioned keys was pressed, the corresponding loop associated with the
     * gameLogic object is called.
     * @param event
     */

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameLogic.rightKeyPressedLoop();
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            gameLogic.leftKeyPressedLoop();
        } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            gameLogic.spaceKeyPressedLoop();
        }
    }

    /**
     * The description of this method is analogous to the above, save for the fact that it registers
     * key releases rather than key presses.
     * @param event
     */


    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameLogic.rightKeyReleasedLoop();
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            gameLogic.leftKeyReleasedLoop();
        } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameLogic.isStopTimer()) {
            gameLogic.setStopTimer(false);
            gameLogic.setDisplayEnd(false);
            timer.start();

            }
        }
    }

    /**
     * The method keyTyped must be in place to override the method in the parent class.
     * @param event
     */
    public void keyTyped(KeyEvent event) {
    }



}
