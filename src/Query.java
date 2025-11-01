import java.sql.SQLOutput;

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
        System.out.println(highestMatch.toString());
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

        // Part 2: Find the most contributing game in *that* match
        Game[] games = lowestMatch.getGames();
        int[] rounds = lowestMatch.getRounds();

        int maxContribution = -1;
        Game topGame = games[0];
        int topGameRounds = 0;

        for (int roundIndex = 0; roundIndex < 3; roundIndex++) {
            int contribution = rounds[roundIndex] * games[roundIndex].getBasePointPerRound();
            if (contribution > maxContribution) {
                maxContribution = contribution;
                topGame = games[roundIndex];
                topGameRounds = rounds[roundIndex];
            }
        }

        // Print all results
        System.out.println("Lowest-Scoring Match:");
        System.out.println(lowestMatch.toString());
        System.out.println("Most Contributing Game in this Match:");
        System.out.println("Game: " + topGame.getName());
        System.out.println("Contribution: " + topGameRounds + " rounds * " + topGame.getBasePointPerRound() + " points = " + maxContribution);
    }

    /**
     * Query 3: Finds and prints a match with the lowest bonus points.
     */
    public void printLowestBonusPoints() {
        Match lowestBonusMatch = allMatches[0][0];

        for (int gamerIndex = 0; gamerIndex < totalGamers; gamerIndex++) {
            for (int matchIndex = 0; matchIndex < matchesPerGamer; matchIndex++) {
                if (allMatches[gamerIndex][matchIndex].getBonusPoints() < lowestBonusMatch.getBonusPoints()) {
                    lowestBonusMatch = allMatches[gamerIndex][matchIndex];
                }
            }
        }

        System.out.println("Match with Lowest Bonus Points:");
        System.out.println("Match ID: " + lowestBonusMatch.getId());
        System.out.println("Games: " + lowestBonusMatch.getGames());
        System.out.println("Skill Points: " + lowestBonusMatch.getSkillPoints());
        System.out.println("Bonus Points: " + lowestBonusMatch.getBonusPoints());
        System.out.println("Match Points: " + lowestBonusMatch.getMatchPoints());
    }
    /**
     * Query 4: Finds and prints the highest-scoring gamer.
     */
    public void printHighestScoringGamer() {
        // 1. Assume the first gamer is the highest to start.
        Gamer highestGamer = allGamers[0];

        // 2. Loop through the rest of the gamers
        for (int gamerIndex = 1; gamerIndex < totalGamers; gamerIndex++) {
            // 3. Compare and update the champion
            if (allGamers[gamerIndex].getTotalPoints() > highestGamer.getTotalPoints()) {
                highestGamer = allGamers[gamerIndex];
            }
        }

        // 4. Print the winner's details
        System.out.println("Highest-Scoring Gamer:");
        System.out.println("Nickname: " + highestGamer.getNickname());
        System.out.println("Name: " + highestGamer.getName());
        System.out.println("Total Points: " + highestGamer.getTotalPoints());

        // 5. Format the average to 2 decimal places
        String avgFormatted = String.format("%.2f", highestGamer.getAveragePointsPerMatch());
        System.out.println("Average Per Match: " + avgFormatted);
        System.out.println("Medal: " + highestGamer.getMedal());
    }

    /**
     * Query 5: Calculates and prints the total points from all matches in the tournament.
     */
    public void printTotalTournamentPoints() {
        // 1. Use long to be safe from integer overflow
        long totalTournamentPoints = 0;

        // 2. Loop through all matches
        for (int gamerIndex = 0; gamerIndex < totalGamers; gamerIndex++) {
            for (int matchIndex = 0; matchIndex < matchesPerGamer; matchIndex++) {
                // 3. Add the points from each match to the total
                totalTournamentPoints += allMatches[gamerIndex][matchIndex].getMatchPoints();
            }
        }

        // 4. Print the final sum
        int totalMatches = totalGamers * matchesPerGamer;
        System.out.println("Total Tournament Points across " + totalMatches + " matches: " + totalTournamentPoints);
    }

    /**
     * Query 6: Calculates and prints the medal distribution.
     */
//    public void printMedalDistribution() {
//        // 1. Create counters for each medal type
//        int goldCount = 0;
//        int silverCount = 0;
//        int bronzeCount = 0;
//        int noneCount = 0;
//
//        // 2. Loop through all gamers
//        for (int i = 0; i < totalGamers; i++) {
//            String medal = allGamers[i].getMedal();
//
//            // 3. Increment the correct counter based on the gamer's medal
//            switch (medal) {
//                case "GOLD":
//                    goldCount++;
//                    break;
//                case "SILVER":
//                    silverCount++;
//                    break;
//                case "BRONZE":
//                    bronzeCount++;
//                    break;
//                case "NONE":
//                    noneCount++;
//                    break;
//            }
//        }
//
//        // 4. Calculate percentages using double-precision division
//        double goldPercent = (double) goldCount / totalGamers * 100.0;
//        double silverPercent = (double) silverCount / totalGamers * 100.0;
//        double bronzePercent = (double) bronzeCount / totalGamers * 100.0;
//        double nonePercent = (double) noneCount / totalGamers * 100.0;
//
//        // 5. Print the formatted results
//        System.out.println("Medal Distribution:");
//        System.out.println("GOLD: " + goldCount + " gamers (" + String.format("%.1f", goldPercent) + "%)");
//        System.out.println("SILVER: " + silverCount + " gamers (" + String.format("%.1f", silverPercent) + "%)");
//        System.out.println("BRONZE: " + bronzeCount + " gamers (" + String.format("%.1f", bronzePercent) + "%)");
//        System.out.println("NONE: " + noneCount + " gamers (" + String.format("%.1f", nonePercent) + "%)");
//    }
}
