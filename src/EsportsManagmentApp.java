import java.io.IOException;
import java.util.Random;

/**
 * EsportsManagmentApp
 */
public class EsportsManagmentApp {
    public static void main(String[] args) {

        System.out.println("Starting The E-Sports Tournament Challenge...");

        // --- 1. LOAD DATA ---
        // These arrays will hold the data from the files.
        // They are declared outside the try-block to be accessible later.
        Game[] allGames;
        Gamer[] allGamers;

        try {
            // Instantiate your file readers
            GameFileIO gameReader = new GameFileIO("games.csv");
            GamersFileIO gamerReader = new GamersFileIO("gamers.csv");

            // Read the data from the files
            allGames = gameReader.getGames();
            allGamers = gamerReader.getGamers();

            System.out.println("Loaded " + allGamers.length + " gamers.");
            System.out.println("Loaded " + allGames.length + " games.");
            System.out.println("----------------------------------------");

        } catch (IOException e) {
            // This is a fatal error. If files can't be read, the program cannot continue.
            System.out.println("FATAL ERROR: Could not read input files.");
            System.out.println("Please make sure 'games.csv' and 'gamers.csv' exist in the root directory.");
            e.printStackTrace();
            return; // Stop the program
        }

        // --- 2. RUN SIMULATION ---
        // Create a single Random object to pass to the simulation
        Random random = new Random();

        // Instantiate MatchManagement, which runs the simulation in its constructor
        MatchManagement matchManager = new MatchManagement(allGamers, allGames, random);

        // Get the 2D array of all simulated matches
        Match[][] allMatches = matchManager.getMatches();
        System.out.println("Simulation complete: " + (allGamers.length * 15) + " matches generated.");
        System.out.println("----------------------------------------");

        // --- 3. CALCULATE RESULTS & PRINT QUERIES ---
        // (Your Query class is designed to handle the PointsBoard creation internally)

        // Instantiate the Query class, giving it all the data it needs
        Query query = new Query(allGamers, allMatches);

        // Call the main print method to output all 6 query results
        query.printAllQueries();

        System.out.println("----------------------------------------");
        System.out.println("Tournament simulation finished.");

    }
}
