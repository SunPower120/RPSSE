package RockPaperScisors.GameComponents;

import RockPaperScisors.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DetermineWinner {

    private static final Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
    private final GameState gameState;
    private final List<Player> winners = new ArrayList<>();
    private int maxScore = 0;

    public DetermineWinner(GameState gameState) {
        this.gameState = gameState;
    }

    public void getResults() {

        filterWinners();
        checkForMultipleWinners();

    }

    private void filterWinners() {

        for (Player player : gameState.getPlayers()) {
            int score = player.getScore();
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(player);
            } else if (score == maxScore) {
                winners.add(player);
            }
        }
    }

    private void checkForMultipleWinners() {

        if (winners.size() > 1) {
            consoleLogger.info("There are multiple winners. Determining the winner based on direct encounters...");
            Player finalWinner = null;
            int finalMaxScore = 0;

            for (Player player : winners) {
                int directScore = 0;

                for (Player opponent : winners) {
                    if (!player.equals(opponent)) {
                        int playerIndex = gameState.getPlayers().indexOf(player);
                        int opponentIndex = gameState.getPlayers().indexOf(opponent);
                        directScore += gameState.getScoreMatrix()[playerIndex][opponentIndex][0];
                    }
                }

                if (directScore > finalMaxScore) {
                    finalMaxScore = directScore;
                    finalWinner = player;
                } else if (directScore == finalMaxScore && finalWinner != null) {
                    consoleLogger.info("Draw between " + player.getName() + " and " + finalWinner.getName());
                    finalWinner = null;
                }
            }

            if (finalWinner != null) {
                consoleLogger.info("Winner after considering direct encounters: " + finalWinner.getName());
            } else {
                consoleLogger.info("There is a draw among the top players even after considering direct encounters.");
            }
        } else {
            consoleLogger.info("Winner: " + winners.get(0).getName());
        }
    }

}

