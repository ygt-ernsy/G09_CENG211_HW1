public class Game {
    // Unique identifier for the game
    private int id;
    // Name of the game
    private String name;
    // Base points awarded per round in the game
    private int basePointPerRound;

    // Copy constructor to create a new Game object with the same values as another
    // Game object
    public Game(Game game) {
        this(game.getId(), game.getName(), game.getBasePointPerRound());
    }

    // Parameterized constructor to initialize Game with specific values
    public Game(int id, String gameName, int basePointPerRound) {
        if (id == 0)
            throw new IllegalArgumentException("Id cannot be 0");
        if (basePointPerRound == 0)
            throw new IllegalArgumentException("Base point per round cannot be 0");
        if (gameName == null)
            throw new NullPointerException();

        this.id = id;
        this.name = gameName;
        this.basePointPerRound = basePointPerRound;
    }

    // Getter for the game ID
    public int getId() {
        return id;
    }

    // Getter for the game name
    public String getName() {
        return name;
    }

    // Getter for the base points per round
    public int getBasePointPerRound() {
        return basePointPerRound;
    }

    // Returns a string representation of the Game object
    public String toString() {
        return "Id: " + getId() + " Name: " + getName() + " BasePoingPerRound: " + getBasePointPerRound();
    }
}
