public class Query {
    private Gamer[] allGamers;
    private Match[][] allMatches;
    private int totalGamers;
    private int matchesPerGamer = 15;

    // Constructor to receive the finalized data
    public Query(Gamer[] allGamers, Match[][] allMatches) {
        this.allGamers = allGamers;
        this.allMatches = allMatches;
        this.totalGamers = allGamers.length;
    }

    public void printAllQueries() {
        printHighestScoringMatch();
        System.out.println("\n---"); // Ayıraç
        printLowestScoringMatch();
        System.out.println("\n---"); // Ayıraç
        printLowestBonusPoints();
        System.out.println("\n---"); // Ayıraç
        printHighestScoringGamer();
        System.out.println("\n---"); // Ayıraç
        printTotalTournamentPoints();
        System.out.println("\n---"); // Ayıraç
        printMedalDistribution();
    }

    public void printHighestScoringMatch() {
        Match highestMatch = allMatches[0][0];

        for (int gamerIndex = 0; gamerIndex < totalGamers; gamerIndex++) {
            for (int matchIndex = 0; matchIndex < matchesPerGamer; matchIndex++) {
                Match currentMatch = allMatches[gamerIndex][matchIndex];
                if (currentMatch.getMatchPoints() > highestMatch.getMatchPoints()) {
                    highestMatch = currentMatch;
                }
            }
        }

        // Print the results in the specified format
        System.out.println("Highest-Scoring Match:");
        System.out.println("Match ID: " + highestMatch.getId());
        System.out.println("Games: " + highestMatch.getGames());
        System.out.println("Rounds: " + highestMatch.getRounds());
        System.out.println("Raw Points: " + highestMatch.getRawPoints());
        System.out.println("Skill Points: " + highestMatch.getSkillPoints());
        System.out.println("Bonus Points: " + highestMatch.getBonusPoints());
        System.out.println("Match Points: " + highestMatch.getMatchPoints());
    }
    /**
     * Query 2: Finds the lowest-scoring match and the most contributing game within it.
     */
    public void printLowestScoringMatch() {
        // Part 1: Find the lowest scoring match
        Match lowestMatch = allMatches[0][0];

        for (int gamerIndex = 0; gamerIndex < totalGamers; gamerIndex++) {
            for (int matchIndex = 0; matchIndex < matchesPerGamer; matchIndex++) {
                if (allMatches[gamerIndex][matchIndex].getMatchPoints() < lowestMatch.getMatchPoints()) {
                    lowestMatch = allMatches[gamerIndex][matchIndex];
                }
            }
        }
