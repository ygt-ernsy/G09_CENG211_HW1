import java.io.IOException;
import java.util.Random;

/**
 * Main application class for managing an E-Sports Tournament simulation.
 * Handles loading of game and gamer data, simulates matches, and prints 
 * statistics and results.
 */
public class EsportsManagmentApp {
    public static void main(String[] args) {

        // Inform the user that the tournament simulation is starting
        System.out.println("Starting The E-Sports Tournament Challenge...");

        Game[] allGames;
        Gamer[] allGamers;

        try {
            // Initialize file readers for games and gamers data
            GameFileIO gameReader = new GameFileIO("G09_CENG211_HW1-main/games.csv");
            GamersFileIO gamerReader = new GamersFileIO("G09_CENG211_HW1-main/gamers.csv");

            // Load all games and gamers from their respective CSV files
            allGames = gameReader.getGames();
            allGamers = gamerReader.getGamers();

            // Print statistics about loaded data
            System.out.println("Loaded " + allGamers.length + " gamers.");
            System.out.println("Loaded " + allGames.length + " games.");
            System.out.println("----------------------------------------");

        } catch (IOException e) {
            // Handle file reading errors gracefully
            System.out.println("FATAL ERROR: Could not read input files.");
            System.out.println("Please make sure 'games.csv' and 'gamers.csv' exist in the root directory.");
            e.printStackTrace();
            return;
        }

        // Create a random number generator for match simulation
        Random random = new Random();

        // Initialize match management with loaded gamers and games
        MatchManagement matchManager = new MatchManagement(allGamers, allGames, random);

        // Generate matches for the tournament
        Match[][] allMatches = matchManager.getMatches();
        System.out.println("Simulation complete: " + (allGamers.length * 15) + " matches generated.");
        System.out.println("----------------------------------------");

        // Initialize the Query class for tournament statistics and queries
        Query query = new Query(allGamers, allMatches);

        // Print all requested queries/statistics
        query.printAllQueries();

        System.out.println("----------------------------------------");
        System.out.println("Tournament simulation finished.");

    }
}
