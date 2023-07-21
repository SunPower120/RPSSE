package RockPaperScisors;

import RockPaperScisors.GameComponents.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class GameState {
    private static final Logger fileLogger = LoggerFactory.getLogger("fileLogger");
    private List<Player> players = new ArrayList<>();
    private int[][][] scoreMatrix;
    private int numberOfRounds;

    public GameState(List<Player> players, int[][][] scoreMatrix, int numberOfRounds) {
        this.players = players;
        this.scoreMatrix = scoreMatrix;
        this.numberOfRounds = numberOfRounds;
    }

    public GameState() {

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int[][][] getScoreMatrix() {
        return scoreMatrix;
    }

    public void setScoreMatrix(int[][][] scores) {
        this.scoreMatrix = scores;
        fileLogger.debug("Score matrix dimensions set to: [{}][{}][{}]",
                scores.length,
                (scores.length > 0 ? scores[0].length : 0),
                (scores.length > 0 && scores[0].length > 0 ? scores[0][0].length : 0));
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        fileLogger.debug("Rounds set to: {}", numberOfRounds);
    }

    public void incrementScoreInMatrix(int i, int j, int k) {
        scoreMatrix[i][j][k]++;
        fileLogger.debug("Increased score in coordinates: {} {} {} {}", i, j, k + " new score: ", scoreMatrix[i][j][k]);
    }

    public void addPlayerToList(Player player) {
        players.add(player);
        fileLogger.debug("Added player: {}", player.getName());
    }
}
