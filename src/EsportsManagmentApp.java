import java.io.IOException;
import java.util.Random;

/**
 * EsportsManagmentApp
 */
public class EsportsManagmentApp {
    public static void main(String[] args) {

        System.out.println("Starting The E-Sports Tournament Challenge...");

        Game[] allGames;
        Gamer[] allGamers;

        try {
            GameFileIO gameReader = new GameFileIO("games.csv");
            GamersFileIO gamerReader = new GamersFileIO("gamers.csv");

            allGames = gameReader.getGames();
            allGamers = gamerReader.getGamers();

            System.out.println("Loaded " + allGamers.length + " gamers.");
            System.out.println("Loaded " + allGames.length + " games.");
            System.out.println("----------------------------------------");

        } catch (IOException e) {
            System.out.println("FATAL ERROR: Could not read input files.");
            System.out.println("Please make sure 'games.csv' and 'gamers.csv' exist in the root directory.");
            e.printStackTrace();
            return;
        }

        Random random = new Random();

        MatchManagement matchManager = new MatchManagement(allGamers, allGames, random);

        Match[][] allMatches = matchManager.getMatches();
        System.out.println("Simulation complete: " + (allGamers.length * 15) + " matches generated.");
        System.out.println("----------------------------------------");

        Query query = new Query(allGamers, allMatches);

        query.printAllQueries();

        System.out.println("----------------------------------------");
        System.out.println("Tournament simulation finished.");

    }
}
