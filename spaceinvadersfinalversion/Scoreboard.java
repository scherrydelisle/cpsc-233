package spaceinvadersfinalversion;

import javax.swing.*;
import java.io.*;

/**
 * This class contains the scoreboard functionality. It reads and writes scores to a file. The highest score
 * from the file will be displayed as a high score during the game.
 *
 */

public class Scoreboard {
    private Connector connector;
    private String userName = "ErrorWithPlayerUserName";
    //This is the user's score at the end of the game. The value of this score will be written
    //to the file at the end of the game
    int userHighScoreValue = 0;
    private int allTimeHighScore = 0;
    private String allTimeHighScoreName = "ErrorWithHighScoreUsername";

    public String toString(String newUserName, int newUserHighScoreValue) {
        //This method puts the users name and score together into a string to be used
        //later by the writeScoreToFile method.
        return newUserName + " " + newUserHighScoreValue;
    }

    public void setUserHighScoreValue(int newHighScore) {
        userHighScoreValue = newHighScore;
    }

    public int getUserHighScoreValue() {
        return userHighScoreValue;
    }

    public String getUserName() {
        return userName;
    }


    public Scoreboard(Connector newConnector) {
        connector = newConnector;
        readScoresFromFile();
    }

    public String getAllTimeHighScoreSignature() {
        return toString(allTimeHighScoreName, allTimeHighScore);
    }

    public void writeScoreToFile(int newUserHighScoreValue) {
        //This method opens the already created highscore.txt file for appending and
        //adds the highscore that the current user just got to the file.
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(Constants.SCORE_BOARD_FILE_NAME, true));
            outputStream.println(userName + " " + newUserHighScoreValue);
            outputStream.close();
            System.out.println("Finished Writing to file.");
        } catch (FileNotFoundException exception) {
            System.out.println("Could not find the file " + Constants.SCORE_BOARD_FILE_NAME);
        }
    }

    /**
     * The method readScoresFromFile reads the list of scores from the file under the file path
     * Constants.Score_BOARD_FILE_NAME. Each score is stored as a name and a value separated by a space.
     * The method readScoresFromFile reads each line of the file and
     */
    public void readScoresFromFile() {
        BufferedReader readFile = null;
        String line;
        try {
            readFile = new BufferedReader(new FileReader(Constants.SCORE_BOARD_FILE_NAME));
        } catch (FileNotFoundException exception) {
            System.out.println("Could not find the file " + Constants.SCORE_BOARD_FILE_NAME);
            System.exit(0);
        }
        try {
            while ((line = readFile.readLine()) != null) {
                String[] lineOfInputs = line.split(" ");
                parseAndStoreFileInput(lineOfInputs);
            }


        } catch (IOException exception) {
            System.out.println("IO Exception Error: Could not read the file " + Constants.SCORE_BOARD_FILE_NAME);
        }
    }

    /**
     * This method parses the line. The first entry in the line is the name, and the next entry is the score. If the score
     * in the line - thisValue - is greater than the current allTimeHighScore, the allTimeHighScore is set to thisValue and the
     * allTimeHighScoreName is set to thisName - the name on the line.
     * @param newLineOfInputs
     */

    public void parseAndStoreFileInput(String newLineOfInputs[]) {
        int thisValue = 0;
        String thisName = "Noname";
        if (newLineOfInputs[0] != null) {
            thisName = newLineOfInputs[0];
        }
        if (newLineOfInputs[1] != null) {
            thisValue = Integer.parseInt(newLineOfInputs[1]);
        }

        if (allTimeHighScore < thisValue) {
            allTimeHighScore = thisValue;
            allTimeHighScoreName = thisName;
        }

    }

    /**
     * Displays a JOption pane asking the user to input initials for the scoreboard.
     */
    public void requestUserName() {
        boolean requestingName = true;
        while (requestingName) {
            userName = JOptionPane.showInputDialog("Type the three initials of your name to be used for the scoreboard: ");
            if (userName.length() == 3) {
                requestingName = false;
            }
        }
    }
}