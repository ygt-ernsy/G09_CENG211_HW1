public class Query {
    // Array of all gamers participating in the tournament
    private Gamer[] allGamers;
    // 2D array of all matches: allMatches[gamerIndex][matchIndex]
    private Match[][] allMatches;
    // Total number of gamers
    private int totalGamers;
    // Constant for the number of matches played per gamer
    private final int matchesPerGamer = 15;
    // PointsBoard instance, used to calculate and retrieve points and medals for each gamer
    private PointsBoard pointsBoard;

    /**
     * Constructor to receive the finalized tournament data.
     * @param allGamers Array of all gamers
     * @param allMatches 2D array of all matches, indexed by gamer and match
     */
    public Query(Gamer[] allGamers, Match[][] allMatches) {
        this.allGamers = allGamers;
        this.allMatches = allMatches;
        this.totalGamers = allGamers.length;
        this.pointsBoard = new PointsBoard(allGamers,allMatches);
    }

    public Query() {
        this.allGamers = new Gamer[0];
        this.allMatches = new Match[0][0];
        this.totalGamers = 0;
        this.pointsBoard = new PointsBoard(allGamers,allMatches);
    }
    /**
     * Prints the results of all queries in order, separated by "---"
     */
    public void printAllQueries() {
        printHighestScoringMatch();
        System.out.println("\n---");
        printLowestScoringMatch();
        System.out.println("\n---");
        printLowestBonusPoints();
        System.out.println("\n---");
        printHighestScoringGamer();
        System.out.println("\n---");
        printTotalTournamentPoints();
        System.out.println("\n---");
        printMedalDistribution();
    }

    /**
     * Query 1: Finds and prints the match with the highest total score.
     */
    public void printHighestScoringMatch() {
        // Start with the first match as the highest
        Match highestMatch = allMatches[0][0];

        // Iterate over all matches to find the one with the highest points
        for (int gamerIndex = 0; gamerIndex < totalGamers; gamerIndex++) {
            for (int matchIndex = 0; matchIndex < matchesPerGamer; matchIndex++) {
                Match currentMatch = allMatches[gamerIndex][matchIndex];
                if (currentMatch.getMatchPoints() > highestMatch.getMatchPoints()) {
                    highestMatch = currentMatch;
                }
            }
        }

        // Print the highest-scoring match details
        System.out.println("Highest-Scoring Match:");
        System.out.println(highestMatch.toString());
    }

    /**
     * Query 2: Finds the lowest-scoring match and the game within it that contributed the most points.
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

        // Part 2: Find the most contributing game in the lowest-scoring match
        Game[] games = lowestMatch.getGames(); // Array of games in the match
        int[] rounds = lowestMatch.getRounds(); // Number of rounds played in each game

        int maxContribution = -1; // Highest point contribution by a game
        Game topGame = games[0];  // Game with the highest contribution
        int topGameRounds = 0;    // Number of rounds for the top contributing game

        // Loop through each game to find the one contributing the most points
        for (int roundIndex = 0; roundIndex < 3; roundIndex++) {
            int contribution = rounds[roundIndex] * games[roundIndex].getBasePointPerRound();
            if (contribution > maxContribution) {
                maxContribution = contribution;
                topGame = games[roundIndex];
                topGameRounds = rounds[roundIndex];
            }
        }

        // Print the lowest-scoring match and its most contributing game
        System.out.println("Lowest-Scoring Match:");
        System.out.println(lowestMatch.toString());
        System.out.println("Most Contributing Game in this Match:");
        System.out.println("Game: " + topGame.getName());
        System.out.println("Contribution: " + topGameRounds + " rounds * " + topGame.getBasePointPerRound() + " points = " + maxContribution);
    }

    /**
     * Query 3: Finds and prints the match with the lowest bonus points.
     */
    public void printLowestBonusPoints() {
        Match lowestBonusMatch = allMatches[0][0];

        // Iterate to find the match with the least bonus points
        for (int gamerIndex = 0; gamerIndex < totalGamers; gamerIndex++) {
            for (int matchIndex = 0; matchIndex < matchesPerGamer; matchIndex++) {
                if (allMatches[gamerIndex][matchIndex].getBonusPoints() < lowestBonusMatch.getBonusPoints()) {
                    lowestBonusMatch = allMatches[gamerIndex][matchIndex];
                }
            }
        }

        // Print the match with the lowest bonus points
        System.out.println("Match with Lowest Bonus Points:");
        System.out.println(lowestBonusMatch.toString());
    }

    /**
     * Query 4: Finds and prints the gamer with the highest total points, including details and medal.
     */
    public void printHighestScoringGamer() {
        // Assume the first gamer is the highest scorer initially
        int highestGamerIndex = 0;

        // Loop through all gamers to find the one with the most points
        for (int gamerIndex = 1; gamerIndex < totalGamers; gamerIndex++) {
            if (pointsBoard.getTotalPoints()[gamerIndex] > pointsBoard.getTotalPoints()[highestGamerIndex]) {
                highestGamerIndex = gamerIndex;
            }
        }
        Gamer highestGamer = allGamers[highestGamerIndex];
        int totalPoints = pointsBoard.getTotalPoints()[highestGamerIndex];

        // Print the highest-scoring gamer's details
        System.out.println("Highest-Scoring Gamer:");
        System.out.println("Nickname: " + highestGamer.getNickname());
        System.out.println("Name: " + highestGamer.getName());
        System.out.println("Total Points: " + totalPoints);
        System.out.println("Average Per Match: " + (double)totalPoints/15);
        System.out.println("Medal: " + pointsBoard.getGamerMedal()[highestGamerIndex]);
    }

    /**
     * Query 5: Calculates and prints the total points from all matches in the tournament.
     */
    public void printTotalTournamentPoints() {
        // Use long to prevent integer overflow for large tournaments
        long totalTournamentPoints = 0;

        // Sum up all match points for every gamer
        for (int gamerIndex = 0; gamerIndex < totalGamers; gamerIndex++) {
            for (int matchIndex = 0; matchIndex < matchesPerGamer; matchIndex++) {
                totalTournamentPoints += allMatches[gamerIndex][matchIndex].getMatchPoints();
            }
        }

        // Print the total tournament point sum and the number of matches
        int totalMatches = totalGamers * matchesPerGamer;
        System.out.println("Total Tournament Points across " + totalMatches + " matches: " + totalTournamentPoints);
    }

    /**
     * Query 6: Calculates and prints the distribution of medals among all gamers.
     * Shows the count and percentage for each medal type.
     */
    public void printMedalDistribution() {
        // Counters for each medal type
        int goldCount = 0;
        int silverCount = 0;
        int bronzeCount = 0;
        int noneCount = 0;

        // Count medals for all gamers
        for (String medal : pointsBoard.getGamerMedal()){
            switch (medal) {
                case "GOLD":
                    goldCount++;
                    break;
                case "SILVER":
                    silverCount++;
                    break;
                case "BRONZE":
                    bronzeCount++;
                    break;
                case "NONE":
                    noneCount++;
                    break;
            }
        }

        // Calculate percentages for each medal type
        double goldPercent = (double) goldCount / totalGamers * 100.0;
        double silverPercent = (double) silverCount / totalGamers * 100.0;
        double bronzePercent = (double) bronzeCount / totalGamers * 100.0;
        double nonePercent = (double) noneCount / totalGamers * 100.0;

        // Print the medal distribution summary
        System.out.println("Medal Distribution:");
        System.out.println("GOLD: " + goldCount + " gamers (" + String.format("%.1f", goldPercent) + "%)");
        System.out.println("SILVER: " + silverCount + " gamers (" + String.format("%.1f", silverPercent) + "%)");
        System.out.println("BRONZE: " + bronzeCount + " gamers (" + String.format("%.1f", bronzePercent) + "%)");
        System.out.println("NONE: " + noneCount + " gamers (" + String.format("%.1f", nonePercent) + "%)");
    }
}
