package spaceinvadersfinalversion;

/**
 * This interface contains all of the constants referenced in the game. This enables for easy
 * changing of parameters pertaining to usability.
 */

public interface Constants {
    final static int FRAME_BORDER = 10;
    final static int FRAME_HEIGHT = 500;
    final static int FRAME_WIDTH = 1000;
    final static int SCREEN_LEFT_BOUND = 10;
    final static int SCREEN_RIGHT_BOUND = FRAME_WIDTH - FRAME_BORDER;
    final static int SCREEN_TOP_BOUND = FRAME_BORDER;
    final static int SCREEN_BOTTOM_BOUND = FRAME_HEIGHT - FRAME_BORDER;
    final static int COLS_OF_ALIENS = 10;
    final static int ROWS_OF_ALIENS = 2;
    final static int PLAYER_Y_POSITION_OFFSET = 100;

    final static int PLAYER_SPEED = 2;
    final static int ALIEN_SPEED = 1;
    final static int BULLET_SPEED = -1;
    final static int BOMB_SPEED = 1;

    final static int INITIAL_LIVES = 3;

    final static int TIMER_INTERVAL = 5;
    final static int TIMER_INITIAL_DELAY = 200;

    //The constant SCORE_BOARD_FILE_NAME is the name of the file from which the scores are read and
    //to which the scores are written
    final static String SCORE_BOARD_FILE_NAME = "scoreboard.txt";


    //The following constants give the file names of the images used in the game
    final static String PLAYER_IMAGE_FILE_NAME_DEFAULT = "spaceship.png";
    final static String PLAYER_IMAGE_FILE_NAME_BLUE = "spaceship b.png";
    final static String PLAYER_IMAGE_FILE_NAME_RED = "spaceship r.png";
    final static String PLAYER_IMAGE_FILE_NAME_GREEN = "spaceship g.png";
    final static String PLAYER_IMAGE_FILE_NAME_ORANGE = "spaceship o.png";

    final static String ALIEN_IMAGE_FILE_NAME = "alien.png";
    final static String BULLET_IMAGE_FILE_NAME = "bullet.png";
    final static String DEFAULT_IMAGE_FILE_NAME = "default.png";
    final static String BOMB_IMAGE_FILE_NAME = "bomb.png";

    //The constant collision tolerance gives a buffer for collision processing.
    // This is used in the GameLogic class.
    final static int COLLISION_TOLERANCE = 10;

    //When INITIAL_ALIEN_DESCENT_DELAY is greater, it takes longer for the aliens to descend
    final static int INITIAL_ALIEN_DESCENT_DELAY = 200;

    //When INITIAL_BOMB_GENERATION_DELAY is greater, fewer bombs are generated
    final static int INITIAL_BOMB_GENERATION_DELAY = 200;
}
