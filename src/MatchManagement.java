/**
 * MatchManagement
 */
import java.util.Random;

public class MatchManagement
{
    private  Match[][] matches;
    private Gamer[] gamers;
    private Game[] availableGames;
    Random random = new Random();


    public MatchManagement(Gamer[] gamers, Game[] availableGames)
    {

        matches = new Match[gamers.length][15];
        this.gamers = gamers;
        this.availableGames = availableGames;
        generateAllMatches();

    }

    public void generateAllMatches() {

        for (int gamerIndex = 0; gamerIndex < gamers.length; gamerIndex++)
        {
            Gamer currentGamer = this.gamers[gamerIndex];

            for (int matchIndex = 0; matchIndex < 15; matchIndex++)
            {
                int matchID = (gamerIndex * 15) + matchIndex + 1;
                Game[] selectedGames = selectRandomGames();
                int[] rounds = generateRandomRounds();

                Match currentMatch = new Match(matchID, selectedGames, rounds, currentGamer);

                        this.matches[gamerIndex][matchIndex] = currentMatch;
            }

        }
    }

    private Game[] selectRandomGames() {
        Game[] selectedGames = new Game[3];
        int [] selectedIndices = new int[] {-1, -1, -1};

        int count = 0;
                while(count < 3){
                    int randomIndex = random.nextInt(this.availableGames.length);
                    boolean isRepeat = false;

                    for(int i = 0; i < count; i++){
                        if(selectedIndices[i] == randomIndex){
                            isRepeat = true;
                            break;
                        }
                    }
                    if(!isRepeat){
                        selectedIndices[count] = randomIndex;
                        selectedGames[count] = this.availableGames[randomIndex];
                        count++;
                    }

                }
                return selectedGames;
    }


    private int[] generateRandomRounds(){
        int[] rounds = new int[3];

        for(int roundIndex = 0; roundIndex < 3; roundIndex++){

            rounds[roundIndex] = random.nextInt(10)+1;

        }
        return rounds;
    }


}

