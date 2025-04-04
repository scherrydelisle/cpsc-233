package spaceinvadersfinalversion.entities;

import spaceinvadersfinalversion.Constants;
import spaceinvadersfinalversion.gamescreens.Options;

import java.io.File;

/**
 * The class player sets up a the player object for the game. The user of the game is able to choose
 * a spaceship image to associate with their player.
 */

public class Player extends Entity implements Constants {

    private String playerImageFilePath = Constants.PLAYER_IMAGE_FILE_NAME_DEFAULT ;
    File playerImageFile = null;

    /**
    * spaceinvadersfinalversion.Player object constructor. Sets all values to defaults found in
    * spaceinvadersfinalversion.Constants. Takes no parameters
    */
    public Player() {
        int filePathCase =  Options.getChoiceOfShipModel();
        System.out.println(filePathCase);
        int playerStartY = FRAME_HEIGHT - FRAME_BORDER - PLAYER_Y_POSITION_OFFSET;
        this.setx(FRAME_BORDER);
        this.sety(playerStartY);
        this.setdy(0);
        this.setdx(PLAYER_SPEED);

        /**
         * This switch block enables the player to change the image associated with their spaceship
         * by selecting an image with a different file path.
         */

        switch (filePathCase) {
            case 1:
                playerImageFilePath = Constants.PLAYER_IMAGE_FILE_NAME_BLUE;
                break;
            case 2:
                playerImageFilePath = Constants.PLAYER_IMAGE_FILE_NAME_GREEN;
                break;
            case 3:
                playerImageFilePath = Constants.PLAYER_IMAGE_FILE_NAME_RED;
                break;
            case 4:
                playerImageFilePath = Constants.PLAYER_IMAGE_FILE_NAME_ORANGE;
                break;
            default:
                playerImageFilePath = Constants.PLAYER_IMAGE_FILE_NAME_DEFAULT;
                break;
        }
        playerImageFile = new File(playerImageFilePath);
        this.setImageFromFile(playerImageFile);
    }

}
