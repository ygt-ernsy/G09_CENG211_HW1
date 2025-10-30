/**
 * PointsBoard
 */
public class PointsBoard {
	private final Gamer[] gamers;
	private Match[][] matches;
	private final int[] totalPoints;
	private final double[] averagePointPerMatch;
	private final String[] gamerMedal;

	public PointsBoard(Gamer[] gamers, Match[][] matches) {
		this.gamers = gamers;
		this.matches = matches;
	}
}
