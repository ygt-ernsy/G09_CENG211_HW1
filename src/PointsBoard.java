/**
 * PointsBoard
 */
public class PointsBoard {
	private final Gamer[] gamers;
	private Match[][] matches;
	private final int[] totalPoints;
	private final double[] averagePointPerMatch;
	private final String[] gamerMedal;

	public PointsBoard() {
		this(null, null);
	}

	public PointsBoard(PointsBoard pointsBoard) {
		this.gamers = pointsBoard.getGamers();
		this.matches = pointsBoard.getMatches();

		this.totalPoints = pointsBoard.getTotalPoints();
		this.averagePointPerMatch = pointsBoard.getAveragePointPerMatch();
		this.gamerMedal = pointsBoard.getGamerMedal();
	}

	public PointsBoard(Gamer[] gamers, Match[][] matches) {
		this.gamers = gamers;
		this.matches = matches;

		this.totalPoints = calculateTotalPoints();
		this.averagePointPerMatch = calculateAvaragePointsPerMatch(totalPoints);
		this.gamerMedal = calculateGamerMedal(totalPoints);
	}

	private int[] calculateTotalPoints() {

	}

	private double[] calculateAvaragePointsPerMatch(int[] totalPoints) {

	}

	private String[] calculateGamerMedal(int[] totalPoints) {
	}

	public int[] getTotalPoints() {

		if (this.totalPoints == null) {
			return null;
		}

		int length = gamers.length;
		int[] tempArray = new int[length];

		for (int i = 0; i < length; i++) {
			tempArray[i] = totalPoints[i];
		}

		return tempArray;
	}

	public Match[][] getMatches() {

		if (this.matches == null) {
			return null;
		}

		int numGamers = this.matches.length;
		Match[][] matchesCopy = new Match[numGamers][];

		for (int i = 0; i < numGamers; i++) {

			if (this.matches[i] == null) {
				matchesCopy[i] = null;
				continue;
			}

			int numMatches = this.matches[i].length;
			matchesCopy[i] = new Match[numMatches];

			for (int j = 0; j < numMatches; j++) {
				matchesCopy[i][j] = this.matches[i][j];
			}
		}

		return matchesCopy;
	}

	public Gamer[] getGamers() {

		if (this.gamers == null) {
			return null;
		}

		int length = gamers.length;
		Gamer[] tempArray = new Gamer[length];

		for (int i = 0; i < length; i++) {
			tempArray[i] = gamers[i];
		}

		return tempArray;
	}

	public String[] getGamerMedal() {

		if (this.gamerMedal == null) {
			return null;
		}

		int length = gamerMedal.length;
		String[] tempArray = new String[length];

		for (int i = 0; i < length; i++) {
			tempArray[i] = gamerMedal[i];
		}

		return tempArray;
	}

	public double[] getAveragePointPerMatch() {

		if (this.averagePointPerMatch == null) {
			return null;
		}

		double[] tempArray = new double[averagePointPerMatch.length];

		for (int i = 0; i < averagePointPerMatch.length; i++) {
			tempArray[i] = averagePointPerMatch[i];
		}

		return tempArray;
	}
}
