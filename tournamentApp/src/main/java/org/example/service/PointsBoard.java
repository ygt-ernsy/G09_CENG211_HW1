package org.example.service;

import org.example.model.*;

/**
 * PointsBoard manages and calculates information related to a group of gamers
 * and their match results.
 * It stores the gamers, their matches, calculates total and average points, and
 * determines medals for each gamer.
 */
public class PointsBoard {
    // Array of all gamers participating
    private final Gamer[] gamers;
    // 2D array representing matches for each gamer
    private Match[][] matches;
    // Total points scored by each gamer
    private final int[] totalPoints;
    // Average points per match for each gamer
    private final double[] averagePointPerMatch;
    // Medal awarded to each gamer based on total points
    private final String[] gamerMedal;

    /**
     * Default constructor. Initializes with no gamers and no matches.
     */
    public PointsBoard() {
        this(null, null);
    }

    /**
     * Copy constructor. Creates a deep copy of another PointsBoard.
     * 
     * @param pointsBoard the PointsBoard instance to copy
     */
    public PointsBoard(PointsBoard pointsBoard) {
        this.gamers = pointsBoard.getGamers();
        this.matches = pointsBoard.getMatches();

        this.totalPoints = pointsBoard.getTotalPoints();
        this.averagePointPerMatch = pointsBoard.getAveragePointPerMatch();
        this.gamerMedal = pointsBoard.getGamerMedal();
    }

    /**
     * Main constructor. Initializes the board with gamers and their matches.
     * Calculates total and average points and assigns medals.
     * 
     * @param gamers  Array of gamers
     * @param matches 2D array of matches for each gamer
     */
    public PointsBoard(Gamer[] gamers, Match[][] matches) {
        this.gamers = gamers;
        this.matches = matches;

        this.totalPoints = calculateTotalPoints();
        this.averagePointPerMatch = calculateAveragePointsPerMatch(totalPoints);
        this.gamerMedal = calculateGamerMedal(totalPoints);
    }

    /**
     * Calculates total points for each gamer by summing up the points from all
     * their matches.
     * 
     * @return Array of total points for each gamer
     */
    private int[] calculateTotalPoints() {
        int numberOfGamers = this.gamers.length;
        int[] totalPoints = new int[numberOfGamers];

        for (int eachGamer = 0; eachGamer < numberOfGamers; eachGamer++) {
            int gamerTotal = 0;

            for (int eachMatch = 0; eachMatch < 15; eachMatch++) {
                Match match = this.matches[eachGamer][eachMatch];
                gamerTotal += match.getMatchPoints();
            }
            totalPoints[eachGamer] = gamerTotal;
        }
        return totalPoints;
    }

    /**
     * Calculates the average points per match for each gamer.
     * 
     * @param totalPoints Array of total points for each gamer
     * @return Array of average points per match for each gamer
     */
    private double[] calculateAveragePointsPerMatch(int[] totalPoints) {

        double[] averagePoints = new double[totalPoints.length];

        for (int eachGamer = 0; eachGamer < totalPoints.length; eachGamer++) {
            double gamerAverage = 0;
            gamerAverage = (double) totalPoints[eachGamer] / 15.0;
            averagePoints[eachGamer] = gamerAverage;
        }
        return averagePoints;
    }

    /**
     * Determines which medal (if any) each gamer receives based on their total
     * points.
     * 
     * @param totalPoints Array of total points for each gamer
     * @return Array of medal strings for each gamer
     */
    private String[] calculateGamerMedal(int[] totalPoints) {
        String[] gamerMedal = new String[totalPoints.length];

        for (int eachGamer = 0; eachGamer < totalPoints.length; eachGamer++) {
            String medal = assignMedal(totalPoints[eachGamer]);
            gamerMedal[eachGamer] = medal;
        }
        return gamerMedal;

    }

    /**
     * Assigns a medal based on the total score.
     * 
     * @param totalScore Total points of the gamer
     * @return Medal as a string ("GOLD", "SILVER", "BRONZE", or "NONE")
     */
    private String assignMedal(int totalScore) {
        String medal;

        if (totalScore >= 4400) {
            medal = "GOLD";
        } else if (totalScore >= 3800) {
            medal = "SILVER";
        } else if (totalScore >= 3500) {
            medal = "BRONZE";
        } else {
            medal = "NONE";
        }
        return medal;
    }

    /**
     * Returns a copy of the array of total points for each gamer.
     * 
     * @return Array of total points
     */
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

    /**
     * Returns a deep copy of the matches array.
     * 
     * @return 2D array of Match objects
     */
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
                matchesCopy[i][j] = new Match(this.matches[i][j]);
            }
        }

        return matchesCopy;
    }

    /**
     * Returns a copy of the array of gamers.
     * 
     * @return Array of Gamer objects
     */
    public Gamer[] getGamers() {

        if (this.gamers == null) {
            return null;
        }

        int length = gamers.length;
        Gamer[] tempArray = new Gamer[length];

        for (int i = 0; i < length; i++) {
            tempArray[i] = new Gamer(gamers[i]);
        }

        return tempArray;
    }

    /**
     * Returns a copy of the array of medals assigned to each gamer.
     * 
     * @return Array of medal strings
     */
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

    /**
     * Returns a copy of the array of average points per match for each gamer.
     * 
     * @return Array of average points per match
     */
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
