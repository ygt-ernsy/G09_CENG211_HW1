class Game {
	private int id;
	private String name;
	private int basePointPerRound;

	public Game() {
		this(0, null, 0);
	}

	// Copy constructor
	public Game(Game game) {
		this(game.getId(), game.getName(), game.getBasePointPerRound());
	}

	public Game(int id, String gameName, int basePointPerRound) {
		this.id = id;
		this.name = gameName;
		this.basePointPerRound = basePointPerRound;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBasePointPerRound() {
		return basePointPerRound;
	}

	public String toString() {
		return "Id: " + id + " Name: " + name + " BasePoingPerRound: " + basePointPerRound;
	}
}
