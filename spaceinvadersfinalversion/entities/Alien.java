package spaceinvadersfinalversion.entities;

import spaceinvadersfinalversion.Constants;

import java.io.File;

/**
 * The class alien provides a blueprint for the aliens within the game.
 * The aliens can move vertically, but not laterally, and so the class Alien overrides the
 * corresponding methods from Entity.java.
 */

public class Alien extends Entity {
    //Creating a file from a filepath specified in Constants
    private File alienImageFile = new File(Constants.ALIEN_IMAGE_FILE_NAME);

    public Alien(int x, int y) {
        //Initializes the position and motion of the alien.
        this.setIsVisible(true);
        this.setx(x);
        this.sety(y);
        this.setdx(0); // The alien cannot move laterally, so its horizontal velocity should be zero.
        this.setdy(Constants.ALIEN_SPEED);
        this.setImageFromFile(alienImageFile); //The image attached to the alien is loaded each time an alien is created.
    }

    /** 
    * Overrides height checking method from spaceinvadersfinalversion.Entity so that
    *  no action occurs from here when it goes out of bounds. When the alien goes out
     *  of bounds, it should be removed from the array aliens in GameLogic - its directionality
     *  should not reverse.
    */
    public void checkHeightBounds() {
    }
}
