/**
 * MatchManagement
 * 
 * This class is responsible for managing matches between gamers and games.
 * It generates matches for each gamer with random games and random round numbers,
 * and provides methods to access match data, gamers, and available games.
 */

import java.util.Random;

public class MatchManagement {
    // A 2D array holding matches for each gamer (gamers x 15 matches)
    private Match[][] matches;
    
    // Array of gamers participating in the matches
    private Gamer[] gamers;
    
    // Array of all games available for selection in matches
    private Game[] availableGames;
    
    // Random instance used for random selections
    private Random random;

    /**
     * Constructs a MatchManagement instance, generating matches for each gamer.
     * 
     * @param gamers Array of Gamer objects
     * @param availableGames Array of available Game objects
     * @param random Random instance for selection logic
     */
    public MatchManagement(Gamer[] gamers, Game[] availableGames, Random random) {
        matches = new Match[gamers.length][15];
        this.gamers = gamers;
        this.availableGames = availableGames;
        this.random = random;
        generateAllMatches();
    }

    /**
     * Copy constructor for MatchManagement.
     * 
     * @param management The MatchManagement instance to copy from
     */
    public MatchManagement(MatchManagement management) {
        this.matches = management.getMatches();
        this.gamers = management.getGamers();
        this.availableGames = management.getAvailableGames();
        this.random = management.getRandom();
    }

    /**
     * Generates all matches for each gamer. Each gamer gets 15 matches,
     * each with 3 randomly selected games and random round numbers.
     */
    public void generateAllMatches() {
        for (int gamerIndex = 0; gamerIndex < gamers.length; gamerIndex++) {
            Gamer currentGamer = this.gamers[gamerIndex];

            for (int matchIndex = 0; matchIndex < 15; matchIndex++) {
                int matchID = (gamerIndex * 15) + matchIndex + 1;
                Game[] selectedGames = selectRandomGames();
                int[] rounds = generateRandomRounds();

                Match currentMatch = new Match(matchID, selectedGames, rounds, currentGamer);

                this.matches[gamerIndex][matchIndex] = currentMatch;
            }
        }
    }

    /**
     * Randomly selects 3 unique games from the available games array.
     * 
     * @return Array of 3 randomly selected Game objects
     */
    private Game[] selectRandomGames() {
        Game[] selectedGames = new Game[3];
        int[] selectedIndices = new int[] { -1, -1, -1 };

        int count = 0;
        while (count < 3) {
            int randomIndex = random.nextInt(this.availableGames.length);
            boolean isRepeat = false;

            // Check if this index was already selected
            for (int i = 0; i < count; i++) {
                if (selectedIndices[i] == randomIndex) {
                    isRepeat = true;
                    break;
                }
            }
            if (!isRepeat) {
                selectedIndices[count] = randomIndex;
                selectedGames[count] = this.availableGames[randomIndex];
                count++;
            }
        }
        return selectedGames;
    }

    /**
     * Generates an array of 3 random integers (1-10) representing round numbers.
     * 
     * @return Array of 3 random round numbers
     */
    private int[] generateRandomRounds() {
        int[] rounds = new int[3];

        for (int roundIndex = 0; roundIndex < 3; roundIndex++) {
            rounds[roundIndex] = random.nextInt(10) + 1; // Random number between 1 and 10 (inclusive)
        }
        return rounds;
    }

    /**
     * Returns a copy of the available games array.
     * 
     * @return Copy of the availableGames array
     */
    public Game[] getAvailableGames() {
        int length = availableGames.length;
        Game[] tempArray = new Game[length];

        for (int i = 0; i < length; i++) {
            tempArray[i] = availableGames[i];
        }

        return tempArray;
    }

    /**
     * Returns the Random instance used.
     * 
     * @return The Random object
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Returns a deep copy of the matches array.
     * 
     * @return 2D array of Match objects (copy)
     */
    public Match[][] getMatches() {
        Match[][] matchesCopy = new Match[this.matches.length][];

        for (int i = 0; i < this.matches.length; i++) {
            matchesCopy[i] = new Match[this.matches[i].length];

            for (int j = 0; j < this.matches[i].length; j++) {
                matchesCopy[i][j] = this.matches[i][j];
            }
        }

        return matchesCopy;
    }

    /**
     * Returns a copy of the gamers array.
     * 
     * @return Copy of the gamers array
     */
    public Gamer[] getGamers() {
        int length = gamers.length;
        Gamer[] tempArray = new Gamer[length];

        for (int i = 0; i < length; i++) {
            tempArray[i] = gamers[i];
        }

        return tempArray;
    }
}
