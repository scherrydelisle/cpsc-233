package spaceinvadersfinalversion.entities;

import spaceinvadersfinalversion.Constants;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

/**
 * The class Entity is an abstract blueprint for all of the entities in the game -
 * players, aliens, bullets and bombs.
 */


public abstract class Entity implements Constants {
    private File defaultImageFile = new File(Constants.DEFAULT_IMAGE_FILE_NAME);
    private BufferedImage image;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean isVisible;

    public void setx(int newx) {
        x = newx;
    }

    public int getx() {
        return x;
    }

    public void sety(int newy) {
        y = newy;
    }

    public int gety() {
        return y;
    }

    public void setdx(int newdx) {
        dx = newdx;
    }

    public int getdx() {
        return dx;
    }

    public void setdy(int newdy) {
        dy = newdy;
    }


    public int getdy() {
        return dy;
    }

    public BufferedImage getImage() {
        BufferedImage copyImage = image;
        return copyImage;
    }

    /**
    * Getter for current object's image width
    * This method always returns 0 if no image found
    * @return an integer representing pixels
    */
    public int getImageWidth() {
        if (!(this.getImage().equals(null))) {
            return this.getImage().getWidth(null);
        } else {
            return 0;
        }
    }
    /** Getter for current object's image height
    * This method always returns 0 if no image found
    * @return an integer representing pixels
    */
    public int getImageHeight() {
        if (!(this.getImage().equals(null))) {
            return this.getImage().getHeight(null);
        } else {
            return 0;
        }

    }

    public void setIsVisible(boolean newIsVisible) {
        isVisible = newIsVisible;
    }

    public Entity() {
        this.setImageFromFile(defaultImageFile);
    }

    /**
    *Sets the file uploaded by each subclass to an BufferedImage
    * Wrapped in a try block due to risks of IO
    * Accepts GIF, PNG, JPEG, BMP, and WBMP
    * param Takes file set by subclass
     */
    public void setImageFromFile(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Image read error: spaceinvadersfinalversion.Entity.setImageFromFile");
        }
    }

    /** Reverses the velocity when an instance of entity moves off-screen horizontally
    * (Note: Resetting x instead of dx results in "key sticking" and some lag)
     */
    public void checkWidthBounds() {
        if ((this.getdx() > 0) && (this.getx() >= Constants.SCREEN_RIGHT_BOUND)) {
            this.setdx(-(this.getdx()));
        }
        if ((this.getdx() < 0) && (this.getx() <= Constants.SCREEN_LEFT_BOUND)) {
            this.setdx(-(this.getdx()));
        }
    }

    /** Reverses the velocity when an instance of Entity moves off-screen vertically
    * Overriden in some child classes.
     */
    public void checkHeightBounds() {
        if ((this.getdy() > 0) && (this.gety() >= Constants.SCREEN_TOP_BOUND)) {
            this.setdy(-(this.getdy()));
        }
        if ((this.getdy() < 0) && (this.gety() <= Constants.SCREEN_BOTTOM_BOUND)) {
            this.setdy(-(this.getdy()));
        }
    }
    /** Checks the dx/dy of the entity, position, bounds
    * If all are within requirements it will adjust the x/y location
    * of the entity, multiplied by the refresh rate in order to smooth
    * animation on-screen.
     */
    public void move() {
        if ((this.getdy() > 0) && (this.gety() < Constants.SCREEN_BOTTOM_BOUND)) {
            this.sety(this.gety() + (Constants.TIMER_INTERVAL * this.getdy()));
            checkHeightBounds();
        } else if ((this.getdy() < 0) && (this.gety() > Constants.SCREEN_TOP_BOUND)) {
            this.sety(this.gety() + (Constants.TIMER_INTERVAL * this.getdy()));
            checkHeightBounds();
        }

        if ((this.getdx() > 0) && (this.getx() < Constants.SCREEN_RIGHT_BOUND)) {
            this.setx(this.getx() + (Constants.TIMER_INTERVAL * this.getdx()));
            checkWidthBounds();
        } else if ((this.getdx() < 0) && (this.getx() > Constants.SCREEN_LEFT_BOUND)) {
            this.setx(this.getx() + (Constants.TIMER_INTERVAL * this.getdx()));
            checkWidthBounds();
        }
    }
}
