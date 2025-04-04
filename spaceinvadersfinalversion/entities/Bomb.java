package spaceinvadersfinalversion.entities;

import spaceinvadersfinalversion.Constants;

import java.io.File;

/**
 * This class provides a blueprint for the bomb entities in the game. The bombs can move vertically, but
 * not laterally.
 */
public class Bomb extends Entity {
    private File bombImageFile = new File(BOMB_IMAGE_FILE_NAME);

    /**
    * Constructor for spaceinvadersfinalversion.entities.Bomb. This creates the object with correct position and velocity
    * relative to the alien which is dropping it. Images are loaded each time a bomb is
     * created.
    * @x The x coordinate of the alien
    * @y The y coordinate of the alien
    */
    public Bomb(int x, int y) {
        this.setIsVisible(true);
        this.setx(x);
        this.sety(y);
        this.setdx(0);
        this.setdy(Constants.BOMB_SPEED);
        this.setImageFromFile(bombImageFile);
    }

    /**
    * The method checkHeightBounds exists because we don't want to reset the 
    * position of the bullet if it goes out of bounds
    */
    public void checkHeightBounds() {
    }
}
