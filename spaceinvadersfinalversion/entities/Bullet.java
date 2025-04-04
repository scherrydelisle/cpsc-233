package spaceinvadersfinalversion.entities;

import spaceinvadersfinalversion.Constants;

import java.io.File;

/**
 * This class provides a blueprint for the bullet entities in the game. The bullets can move vertically, but
 * not laterally.
 */

public class Bullet extends Entity {
    private File bulletImageFile = new File(Constants.BULLET_IMAGE_FILE_NAME);

    /**
    * Constructor for spaceinvadersfinalversion.Bullet. This creates the object with correct position and velocity
    * relative to the player. Images are loaded each time a bullet is created
    * @x The current x coordinate of the player
    * @y The current y coordinate of the player
    */
    public Bullet(int x, int y) {
        this.setIsVisible(true);
        this.setx(x);
        this.sety(y);
        this.setdx(0);
        this.setdy(Constants.BULLET_SPEED);
        this.setImageFromFile(bulletImageFile);
    }

    /** 
    * Overwrites height checking method from spaceinvadersfinalversion.Entity so that
    * no action occurs from here when it goes out of bounds.
    */
    public void checkHeightBounds() {
    }
}
