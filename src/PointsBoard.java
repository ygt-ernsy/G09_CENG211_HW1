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
		this.averagePointPerMatch = calculateAveragePointsPerMatch(totalPoints);
		this.gamerMedal = calculateGamerMedal(totalPoints);
	}

	private int[] calculateTotalPoints() {
        int numberOfGamers = this.gamers.length;
        int [] totalPoints = new int[numberOfGamers];


        for(int eachGamer = 0; eachGamer < numberOfGamers; eachGamer++) {
            int gamerTotal = 0;

            for(int eachMatch = 0; eachMatch < 15; eachMatch++) {
                Match match = this.matches[eachGamer][eachMatch];
                gamerTotal += match.getMatchPoints();
            }
            totalPoints[eachGamer] = gamerTotal;
        }
        return totalPoints;
	}

	private double[] calculateAveragePointsPerMatch(int[] totalPoints) {

       double [] averagePoints = new double[totalPoints.length];

       for (int eachGamer = 0; eachGamer < totalPoints.length; eachGamer++) {
           double gamerAverage = 0;
           gamerAverage = (double)totalPoints[eachGamer] / 15.0;
           averagePoints[eachGamer] = gamerAverage;
       }
       return averagePoints;
	}

	private String[] calculateGamerMedal(int[] totalPoints) {
        String [] gamerMedal = new String[totalPoints.length];

        for (int eachGamer = 0; eachGamer < totalPoints.length; eachGamer++) {
            String medal = assignMedal(totalPoints[eachGamer]);
            gamerMedal[eachGamer] = medal;
        }
        return gamerMedal;

	}
    private  String assignMedal(int totalScore){
        String medal;

        if(totalScore >= 2000){
            medal = "GOLD";

        }
        else if(totalScore >= 1200){
            medal = "SILVER";
        }
        else if (totalScore >= 700){
            medal = "BRONZE";
        }
        else{
            medal = "NONE";
        }
        return medal;
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
