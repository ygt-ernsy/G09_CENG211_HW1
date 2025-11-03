import java.util.Arrays;

/**
 * Match
 * 
 * Represents a match played by a gamer, consisting of multiple games, rounds,
 * and various points calculations.
 * Calculates raw points, skill points, bonus points, and total match points
 * based on input and game data.
 */
public class Match {
    private int id; // Unique identifier for the match
    private Game[] games; // Array of games played in this match (assigned randomly)
    private int[] rounds; // Number of rounds played for each game (calculated randomly)
    private Gamer gamer; // Gamer who played this match (provided to constructor)
    private int rawPoints; // Total points scored before skill and bonus (calculated by method)
    private int skillPoints; // Points after applying gamer's experience (calculated by method)
    private int bonusPoints; // Additional bonus points based on raw points (calculated by method)
    private int matchPoints; // Final match points (skillPoints + bonusPoints; calculated in constructor)

    /**
     * Copy constructor creates a new Match object from another Match instance.
     * 
     * @param match The Match instance to copy
     */
    public Match(Match match) {
        this(match.getId(), match.getGames(), match.getRounds(), match.getGamer());
    }

    /**
     * Constructs a Match with all fields specified.
     * Calculates raw, skill, bonus, and match points upon creation.
     * 
     * @param id     Unique identifier for the match
     * @param games  Array of games played in the match
     * @param rounds Number of rounds played for each game
     * @param gamer  Gamer who played the match
     */
    public Match(int id, Game[] games, int[] rounds, Gamer gamer) {
        if (id == 0)
            throw new IllegalArgumentException("Id cannot be 0");
        if (games == null || games.length != 3)
            throw new NullPointerException();
        if (rounds == null || rounds.length != 3)
            throw new NullPointerException();
        if (gamer == null)
            throw new NullPointerException();

        this.id = id;
        this.games = games;
        this.rounds = rounds;
        this.gamer = gamer;
        this.rawPoints = calculateRawPoints(); // Calculate raw points based on games and rounds
        this.skillPoints = calculateSkillPoints(); // Calculate skill points based on experience
        this.bonusPoints = calculateBonusPoints(); // Calculate bonus points based on raw points
        this.matchPoints = this.skillPoints + this.bonusPoints; // Sum for total match points
    }

    /**
     * Calculates the total raw points for the match based on games played and
     * rounds.
     * 
     * @return The sum of (rounds[i] * basePointPerRound) for each game
     */
    private int calculateRawPoints() {
        int points = 0;
        for (int i = 0; i < rounds.length; i++) {
            points += rounds[i] * games[i].getBasePointPerRound();
        }
        return points;
    }

    /**
     * Calculates the skill points, applying a bonus based on the gamer's
     * experience.
     * Each year of experience (up to 10) gives an additional 2% to the raw points.
     * 
     * @return The skill points as an integer
     */
    private int calculateSkillPoints() {
        int gamerExperienceUsed = Math.min(gamer.getExperienceYears(), 10); // Max 10 years considered
        double points = Math.floor(rawPoints * (1 + gamerExperienceUsed * 0.02)); // 2% per year
        return (int) points;
    }

    /**
     * Calculates bonus points based on the raw points scored.
     * 
     * @return The bonus points awarded for this match
     */
    private int calculateBonusPoints() {
        int index = rawPoints / 200; // Divide raw points by 200 to determine bonus bracket
        switch (index) {
            case 0: // For 0-199
                return 10;
            case 1: // For 200-399
                return 25;
            case 2: // For 400-599
                return 50;
            default: // For 600+ (index 3 or higher)
                return 100;
        }
    }

    /** @return The skill points for this match */
    public int getSkillPoints() {
        return skillPoints;
    }

    /** @return The array of rounds played per game */
    public int[] getRounds() {
        return rounds;
    }

    /** @return The raw points scored in this match */
    public int getRawPoints() {
        return rawPoints;
    }

    /** @return The total match points (skill + bonus) */
    public int getMatchPoints() {
        return matchPoints;
    }

    /** @return The unique ID of the match */
    public int getId() {
        return id;
    }

    /**
     * Returns a copy of the games array for this match.
     * 
     * @return An array containing the games played in the match
     */
    public Game[] getGames() {
        int length = games.length;
        Game[] tempGames = new Game[length];
        for (int i = 0; i < length; i++) {
            tempGames[i] = new Game(games[i]);
        }
        return tempGames;
    }

    public String[] getGamesName(Game[] games) {
        String[] names = new String[games.length];
        for (int i=0; i<games.length; i++) {
            names[i] = games[i].getName();
        }
        return names;
    }

    /**
     * Returns a copy of the Gamer object for this match.
     * 
     * @return A copy of the gamer who played the match
     */
    public Gamer getGamer() {
        return new Gamer(gamer);
    }

    /** @return The bonus points awarded for this match */
    public int getBonusPoints() {
        return bonusPoints;
    }

    @Override
    public String toString() {

        return "Match ID:" + this.id +
                "\nGames: " + Arrays.toString(getGamesName(this.games)) +
                "\nRounds: " + Arrays.toString(this.rounds) +
                "\nPoints: " + this.matchPoints +
                "\nRaw Points: " + this.rawPoints +
                "\nSkill Points: " + this.skillPoints +
                "\nBonus Points: " + this.bonusPoints +
                "\nMatch Points: " + this.matchPoints;
    }
}
