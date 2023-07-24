package RockPaperScisors.GameComponents;

import RockPaperScisors.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WinnerDecider {
    private static final Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
    private final List<Player> winners = new ArrayList<>();
    private final GameController gameController;

    private int maxScore = 0;

    public WinnerDecider(GameController gameController) {
        this.gameController = gameController;
    }

    public void getResults() {

        filterWinners();
        checkForMultipleWinners();

    }

    private void filterWinners() {

        for (Player player : gameController.getGamestate().getPlayers()) {
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
                        int playerIndex = gameController.getGamestate().getPlayers().indexOf(player);
                        int opponentIndex = gameController.getGamestate().getPlayers().indexOf(opponent);
                        directScore += gameController.getGamestate().getScoreMatrix()[playerIndex][opponentIndex][0];
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

