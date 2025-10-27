/**
 * Match
 */
public class Match {
	private int id;
	private Game[] games;
	private int[] rounds;
	private Gamer gamer;
	private int rawPoints;
	private int skillPoints;
	private int bonusPoints;
	private int matchPoints;

	public Match() {

	}

	private int calculateRawPoints() {
		int points = 0;

		for (int i = 0; i < rounds.length; i++) {
			points += rounds[i] * games[i].getBasePointPerRound();
		}

		return points;
	}

	private double calculateSkillPoints() {
		int gamerExperianceUsed = Math.min(gamer.getExperianceYears(), 10);
		double points = Math.floor(rawPoints * (1 + gamerExperianceUsed * 0.02));

		return points;
	}

	private int calculateBonusPoints() {

	}
}
