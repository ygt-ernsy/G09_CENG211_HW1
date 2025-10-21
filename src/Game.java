class Game {
	private int id;
	private String name;
	private int basePointPerRound;

	public Game() {
		this(0, null, 0);
	}

	public Game(int id, String name, int basePointPerRound) {
		this.id = id;
		this.name = name;
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
}
