/**
 * Match
 */
public class Match {
	private int id;
	private Game[] games; // Assign randomly
	private int[] rounds; // Calculate randomly
	private Gamer gamer; // Given to constructor
	private int rawPoints; // Calculated by method
	private int skillPoints; // Calculated by method
	private int bonusPoints; // Calculated by method
	private int matchPoints; // Calculated in constructor

	public Match() {
		this(0, null, null, null);
	}

	public Match(Match match) {
		this(match.getId(), match.getGames(), match.getRounds(), match.getGamer());
	}

	public Match(int id, Game[] games, int[] rounds, Gamer gamer) {
		this.id = id;
		this.games = games;
		this.rounds = rounds;
		this.gamer = gamer;
		this.rawPoints = calculateRawPoints();
		this.skillPoints = calculateSkillPoints();
		this.bonusPoints = calculateBonusPoints();
		this.matchPoints = this.skillPoints + this.bonusPoints;
	}

	private int calculateRawPoints() {
		int points = 0;

		for (int i = 0; i < rounds.length; i++) {
			points += rounds[i] * games[i].getBasePointPerRound();
		}

		return points;
	}

	private int calculateSkillPoints() {
		int gamerExperianceUsed = Math.min(gamer.getExperianceYears(), 10);
		double points = Math.floor(rawPoints * (1 + gamerExperianceUsed * 0.02));

		return (int) points;
	}

	private int calculateBonusPoints() {
		int index = rawPoints / 200; // TODO: Check if it is skill points

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

	public int getSkillPoints() {
		return skillPoints;
	}

	public int[] getRounds() {
		return rounds;
	}

	public int getRawPoints() {
		return rawPoints;
	}

	public int getMatchPoints() {
		return matchPoints;
	}

	public int getId() {
		return id;
	}

	public Game[] getGames() {
		Game[] tempGames = new Game[3];
		int i = 0;

		for (Game game : this.games) {
			tempGames[i] = game;
			i++;
		}

		return tempGames;
	}

	public Gamer getGamer() {
		Gamer tempGamer = new Gamer(gamer);
		return tempGamer;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}
}
