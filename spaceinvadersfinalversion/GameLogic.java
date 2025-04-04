package spaceinvadersfinalversion; /**
 * Referenced some of the structure of the class spaceinvadersfinalversion.GameLogic from the following sites:
 * http://zetcode.com/tutorials/javagamestutorial/movingsprites/
 * http://zetcode.com/tutorials/javagamestutorial/spaceinvaders/
 * http://www.cokeandcode.com/info/tut2d.html
 * Referenced material on looping through arrays of entities from the following site:
 * http://zetcode.com/tutorials/javagamestutorial/spaceinvaders/
 */
import spaceinvadersfinalversion.entities.Alien;
import spaceinvadersfinalversion.entities.Bomb;
import spaceinvadersfinalversion.entities.Bullet;
import spaceinvadersfinalversion.entities.Player;
import spaceinvadersfinalversion.gamescreens.EndScreen;

import java.util.*;
import javax.swing.JPanel;

/**
 * The class gameLogic contains the methods for collision detection, entity initialization, and game updating.
 */

public class GameLogic extends JPanel implements Constants {
    private Player player = new Player();
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private int timeCounter = 0;
    //An alien is constructed to determine the image dimensions of the aliens in the array
    //for collision handling. This works because all aliens have the same image (and thus
    //the same dimensions)
    private Alien alienForImageDimensions = new Alien(0, 0);
    private int alienWidth = alienForImageDimensions.getImageWidth();
    private int alienHeight = alienForImageDimensions.getImageHeight();
    private boolean gameOver;
    private Connector connector;
    private Scoreboard scoreboard = new Scoreboard(connector);
    private Random random = new Random();
    private String allTimeHighScoreSignature = scoreboard.getAllTimeHighScoreSignature();
    private int alienDescentDelay = Constants.INITIAL_ALIEN_DESCENT_DELAY;
    private int bombGenerationDelay = Constants.INITIAL_BOMB_GENERATION_DELAY;

    private boolean endLevel = false;
    private boolean endGame = false;
    private boolean displayEnd = false;
    private boolean stopTimer = false;

    public int score = 0;
    public int lives = 0;
    public int level = 1;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDisplayEnd(boolean displayEnd) {
        this.displayEnd = displayEnd;
    }


    public String getAllTimeHighScoreSignature() {
        return allTimeHighScoreSignature;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void incrementLives(int life){
        this.lives += life;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isEndLevel() {
        return endLevel;
    }

    public void setEndLevel(boolean endLevel) {
        this.endLevel = endLevel;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public boolean isStopTimer() {
        return stopTimer;
    }

    public void setStopTimer(boolean stopTimer) {
        this.stopTimer = stopTimer;
    }

    public int getUserHighScoreValue() {
        return scoreboard.getUserHighScoreValue();
    }

    public Player getPlayer() {
        Player copyPlayer = player;
        return copyPlayer;
    }

    public ArrayList<Alien> getAliens() {
        ArrayList<Alien> copyAliens;
        copyAliens = aliens;
        return copyAliens;
    }

    public ArrayList<Bomb> getBombs() {
        ArrayList<Bomb> copyBombs;
        copyBombs = bombs;
        return copyBombs;
    }

    public ArrayList<Bullet> getBullets() {
        ArrayList<Bullet> copyBullets;
        copyBullets = bullets;
        return copyBullets;
    }


    /**
     *This function checks the dimensions of the frame from spaceinvadersfinalversion.Constants,
     * and the width/height of the alien sprites to create rows/columns
     * at the beginning of the game.
     *
     * For each iteration of the nest loop a new alien is created and
      * added to the ArrayList we use to paint from.
    */
    public void initAliens() {

        int xGapsBetweenAliens;
        //We place this in case we decide to let COLS_OF_ALIENS = 1 (to prevent division by zero)
        if (Constants.COLS_OF_ALIENS > 1) {
            xGapsBetweenAliens = (int) (((Constants.FRAME_WIDTH - 2 * Constants.FRAME_BORDER) - (Constants.COLS_OF_ALIENS * alienWidth)) / (Constants.COLS_OF_ALIENS - 1));
        } else {
            xGapsBetweenAliens = 0;
        }
        int yGapsBetweenAliens = 10;
        for (int rowCounter = 0; rowCounter < Constants.ROWS_OF_ALIENS; rowCounter++) {

            int yPosition = (Constants.FRAME_BORDER + 2 * alienHeight) + ((yGapsBetweenAliens * (rowCounter - 1)) + rowCounter * alienHeight);
            for (int colCounter = 0; colCounter < Constants.COLS_OF_ALIENS; colCounter++) {

                int xPosition = Constants.FRAME_BORDER + ((xGapsBetweenAliens * (colCounter - 1)) + colCounter * alienWidth);
                if (xPosition > 0) { //This is added to ensure that negative coordinates are not produced
                    aliens.add(new Alien(xPosition, yPosition));
                }

            }
        }
    }

    /**
    * Constructor for GameLogic, counters are initialized, player instantiated
    * aliens placed in array, main timer is started.
    */
    public GameLogic(Connector newConnector) {
        connector = newConnector;
        timeCounter = 0;
        initAliens();
        lives = Constants.INITIAL_LIVES;
        score = 0;
        level = 1;
        gameOver = false;

    }

    /**
    * Checks alien ArrayList, once all aliens are destroyed (length = 0)
    * the player has won and the game is over
    */
    public void checkAllAliensDestroyed() {

        if (aliens.size() <= 0) {
            setEndLevel(true);
            incrementScore(1000);
        }
    }

    /**
    * Each time this is called it checks the positions of each alien to see
    * if it has made contact with the player. If so the player has lost,
    * and the game is over
    */
    public void collisionCheckPlayerAlien() {
        for (int counter = 0; counter < aliens.size(); counter++) {
            Alien alien = aliens.get(counter);
            int alienX = alien.getx();
            int alienY = alien.gety();
            int playerX = player.getx();
            int playerY = player.gety();

            if ((playerX <= alienX + alienWidth ) && (playerX >= alienX - alienWidth) && (playerY <= alienY + alienHeight) && (playerY >= alienY - alienHeight)) {
                playerDied(); // decrements points and lives
                setEndLevel(true);

            }
        }
    }

    /**
    * Each time this is called it gets if the aliens have reached the bottom of the screen
    * If so the aliens have won, and the game is over
    */
    public void collisionCheckAlienBottomOfScreen() {
        for (int counter = 0; counter < aliens.size(); counter++) {
            Alien alien = aliens.get(counter);
            int alienY = alien.gety();

            if (alienY == Constants.SCREEN_BOTTOM_BOUND) {
                playerDied(); // decrements points and lives
                setEndLevel(true);
            }
        }
    }

    /**
    * Each time this is called it checks if an alien has been hit by a player bullet
    * If the bullet comes within a certain tolerance distance of the alien
    * then the alien is removed from the ArrayList, as is the bullet from its own
    */
    public void collisionCheckAlienBullet() {
        for (int bulletCounter = 0; bulletCounter < bullets.size(); bulletCounter++) {
            Bullet bullet = bullets.get(bulletCounter);
            int bulletX = bullet.getx();
            int bulletY = bullet.gety();
            for (int alienCounter = 0; alienCounter < aliens.size(); alienCounter++ ) {
                Alien alien = aliens.get(alienCounter);
                int alienX = alien.getx();
                int alienY = alien.gety();

                if ((bulletX <= (alienX + alienWidth)) && (bulletX >= alienX - Constants.COLLISION_TOLERANCE) && (bulletY <= alienY - Constants.COLLISION_TOLERANCE) && (bulletY >= (alienY - alienHeight))) {
                    bullets.remove(bulletCounter);
                    aliens.remove(alienCounter);
                    incrementScore(100);
                }
            }
        }
    }

    /**
    * Each time this is called it checks if an player has been hit by an alien bomb
    * If the bomb comes within a certain tolerance distance of the player
    * then the bomb is removed from the ArrayList and the game is over
    */
    public void collisionCheckPlayerBomb() {
        for (int bombCounter = 0; bombCounter < bombs.size(); bombCounter++) {
            Bomb bomb = bombs.get(bombCounter);
            int bombX = bomb.getx();
            int bombY = bomb.gety();
            int playerX = player.getx();
            int playerY = player.gety();
            int playerWidth = player.getImageWidth();
            int playerHeight = player.getImageHeight();

            if ((bombX <= (playerX + playerWidth)) && (bombX >= playerX - Constants.COLLISION_TOLERANCE) && (bombY <= playerY - Constants.COLLISION_TOLERANCE) && (bombY >= (playerY - playerHeight))) {
                    bombs.remove(bombCounter);
                playerDied();
                setEndLevel(true);
            }
        }
    }

    /**
    * Each time this is called it iterates through the alien ArrayList
    * and calls the move method to change their location
    * */
    public void updateAliens() {
        for (int counter = 0; counter < aliens.size(); counter++) {
            Alien alien = aliens.get(counter);
            alien.move();
        }
    }

    /**
    * Each time this is called the the ArrayList of bullets is iterated through
    * and each bullet moves according to its own method
    * Bound checking occurs here to simplify removing
    * the bullets from the spaceinvadersfinalversion.GameLogic Array.
    */
    public void updateBullets() {
        if (bullets.size() > 0) {
            for (int counter = 0; counter < bullets.size(); counter++) {
                Bullet bullet = bullets.get(counter);
                bullet.move();
            }

            for (int counter = 0; counter < bullets.size(); counter++) {
                Bullet bullet = bullets.get(counter);
                if (bullet.gety() <= Constants.SCREEN_TOP_BOUND) {
                    bullets.remove(counter);
                }
            }
        }
    }

    /**
    * An alien is chose at random via generator to fire back at the player
    * and a new bomb entity is instantiated with the x/y coordinates
    * of the firing alien.
    */
    public void generateNewBomb() {
        int bound;
        bound = aliens.size() -1 ;
        if (bound <=0) {bound =1;}
        int indexOfRandomAlien = random.nextInt(bound);
        Alien alien = aliens.get(indexOfRandomAlien);
        int alienX = alien.getx();
        int alienY = alien.gety();
        bombs.add(new Bomb(alienX, alienY));
    }


    /**
    * Each time this method is called it will iterate through all
    * bombs on screen and move them according to their own methods
    */
    public void updateBombs() {
        for(int counter = 0; counter < bombs.size(); counter++) {
            Bomb bomb = bombs.get(counter);
            bomb.move();
            if (bomb.gety() >= Constants.SCREEN_BOTTOM_BOUND) {
                bombs.remove(counter);
            }
        }
    }



    /**
    * Meant to be called repeatedly through the entire game.
    *  Each time it is called the time counters increment
    *  which allow us to generate bombs and drop the aliens
    *  at specific intervals dictated by the respective spaceinvadersfinalversion.Constants
    *
    *  All collision check methods are run here, and then the
    *  screen is repainted via paintComponent body
    */
    public void inGameLoop(Connector connector) {

            //This is to prevent the timeCounter from exceeding the bounds of integers
            if (timeCounter > 10000) {timeCounter = 0;}
            if (getScore() <= 0) {setScore(0);}
            timeCounter += 1;

                updateBullets();
                updateBombs();
                collisionCheckPlayerAlien();
                collisionCheckPlayerBomb();
                collisionCheckAlienBottomOfScreen();
                collisionCheckAlienBullet();
                checkAllAliensDestroyed();

                if ((alienDescentDelay - 10 * level) > 0) {
                    alienDescentDelay = Constants.INITIAL_BOMB_GENERATION_DELAY - 10 * level;
                }

                if ((timeCounter % (alienDescentDelay) == 0)) {
                    generateNewBomb();
                }

                if ((bombGenerationDelay - 10 * level) > 0) {
                    bombGenerationDelay = Constants.INITIAL_BOMB_GENERATION_DELAY - 10 * level;
                }

                if ((timeCounter % (bombGenerationDelay) == 0)) {
                    updateAliens();
                }

                if (lives == 0) {
                    bullets.clear();
                    setEndGame(true);
                }

                if (isEndLevel()) {
                    level++;
                    aliens.clear();
                    bullets.clear();
                    bombs.clear();
                    initAliens();
                    setEndLevel(false);
                    timeCounter = 0;
                }

                if (isEndGame()) {
                    setDisplayEnd(true);
                    scoreboard.requestUserName();
                    scoreboard.setUserHighScoreValue(score);
                    scoreboard.toString(scoreboard.getUserName(), score);
                    scoreboard.writeScoreToFile(score);
                    new EndScreen(score);
                    gameReset();
                    gameOver = true;
                    setStopTimer(true);
                    setEndGame(false);
                }



        connector.repaint();
    }

    /**
     * Performs the loop that takes place when the space key is pressed (in the class Connector).
     * A bullet is generated by the press of the space key, and the player moves.
     */

    public void spaceKeyPressedLoop() {
        generateBullet();
        player.move();
    }

    /**
     * Performs the loop that takes place when the right key is pressed (in the class Connector).
     * The player is set to move rightwards.
     */

    public void rightKeyPressedLoop() {
        player.setdx(Constants.PLAYER_SPEED);
        player.move();
    }

    /**
     * Performs the loop that takes place when the right key is pressed (in the class Connector).
     * The player is set to move leftwards.
     */

    public void leftKeyPressedLoop() {
        player.setdx(- Constants.PLAYER_SPEED);
        player.move();
    }

    /**
     * Performs the loop that takes place when the left key is released (in the class Connector).
     * The player's speed is set to zero and the player stops moving.
     */

    public void leftKeyReleasedLoop() {
        player.setdx(0);
        player.move();
    }

    /**
     * Performs the loop that takes place when the right key is released (in the class Connector).
     * The player's speed is set to zero and the player stops moving.
     */

    public void rightKeyReleasedLoop() {
        player.setdx(0);
        player.move();
    }

    /**
     * Called when the player has died by colliding with an alien or a bullet or if an alien
     * hits the bottom of the screen. Removes a life from the player and decreases the player's
     * score by 500 points.
     */

    public void playerDied(){
        incrementLives(-1);
        incrementScore(-500);
    }

    /**
     * Adds the number of points passed as an argument to the score. If the score plus the points
     * added is less than zero, the score is automatically set to zero.
     * @param points
     */

    public void incrementScore(int points){
        int tempScore = score + points;
        if (tempScore > 0) {
            this.score = tempScore;
        } else {
            this.score = 0;
        }
    }


    /**
     * Instantiates new bullets and adds to the array bullet list
     */

    public void generateBullet() {
        bullets.add(new Bullet(player.getx(), player.gety()));
    }

    /**
     * Resets the arrays aliens, bullets, and bombs and the score, level, and number of lives.
     */

    public void gameReset(){
        aliens.clear();
        bullets.clear();
        bombs.clear();
        setScore(0);
        setLevel(1);
        setLives(3);
        initAliens();
    }
}
