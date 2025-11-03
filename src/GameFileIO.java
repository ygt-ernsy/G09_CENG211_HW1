import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * GameFileIO
 * 
 * Responsible for reading game data from a CSV file and converting each record
 * into a Game object.
 * Handles parsing, error management, and returns an array of Game objects.
 */
public class GameFileIO {

    private String filePath; // Path to the CSV file containing game data

    /**
    * Copy constructor
    *
    * @param GameFileIo object
    */
    public GameFileIO(GameFileIO gameFileIO) {
        this.filePath = gameFileIO.getFilePath();
    }

    
    /**
     * Constructor to initialize the file path.
     * 
     * @param filePath Path to the CSV file to be read
     */
    public GameFileIO(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads all game records from the CSV file and returns them as an array of Game
     * objects.
     * Skips the header row and parses each subsequent line.
     * 
     * @return Array of Game objects parsed from the CSV file
     */
    public Game[] getGames() {

        int count = getRowCount(); // Get the number of game records (excluding header)
        Game[] gameArray = new Game[count];

        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(filePath));

            // Skip the header
            inputStream.readLine();
            for (int i = 0; i < count; i++) {
                String csvLine = inputStream.readLine();
                gameArray[i] = parseGame(csvLine); // Parse each line into a Game object
            }

        } catch (IOException e) {
            System.out.println("FATAL ERROR: Could not read file: " + filePath);
            System.out.println(e.getMessage());
            System.exit(1);
        } finally {
            // Ensure resources are closed to prevent memory leaks
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // Can't do much here, but we should at least report it.
                System.out.println("Error: Failed to close the file stream.");
                e.printStackTrace();
            }
        }

        return gameArray;
    }

    /**
     * Counts the number of data rows (excluding the header) in the CSV file.
     * 
     * @return Number of data rows (games) in the file
     */
    private int getRowCount() {
        BufferedReader inputStream = null;
        int count = 0;

        try {
            inputStream = new BufferedReader(new FileReader(filePath));

            // Skip the header
            inputStream.readLine();
            while (inputStream.readLine() != null) {
                count++; // Increment count for each non-header line
            }
        } catch (IOException e) {
            System.out.println("FATAL ERROR: Could not read file: " + filePath);
            System.out.println(e.getMessage());
            System.exit(1);
        } finally {
            // Ensure resources are closed to prevent memory leaks
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // Can't do much here, but we should at least report it.
                System.out.println("Error: Failed to close the file stream.");
                e.printStackTrace();
            }
        }

        return count;
    }

    /**
     * Parses a single CSV line into a Game object.
     * Handles conversion of string values to their respective types.
     * Handles and reports malformed lines gracefully.
     * 
     * @param csvLine The CSV-formatted line representing a game
     * @return A Game object, or null if the line is invalid or incomplete
     */
    private Game parseGame(String csvLine) {
        StringTokenizer tokenizer = new StringTokenizer(csvLine, ",");

        try {
            // Parse expected columns: id, gameName, basePointPerRound
            int id = Integer.parseInt(tokenizer.nextToken());
            String gameName = tokenizer.nextToken();
            int basePointPerRound = Integer.parseInt(tokenizer.nextToken());

            return new Game(id, gameName, basePointPerRound);

        } catch (NumberFormatException e) {
            // Handles cases where integer parsing fails
            System.out.println("Warning: Skipping invalid game line. Could not parse number.");
            System.out.println("  Line: " + csvLine);
            return null;
        } catch (NoSuchElementException e) {
            // Handles cases where columns are missing
            System.out.println("Warning: Skipping invalid game line. Missing columns.");
            System.out.println("  Line: " + csvLine);
            return null;
        }

    }

    public String getFilePath() {
        return filePath;
    }

}
