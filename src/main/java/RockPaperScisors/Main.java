package RockPaperScisors;

import RockPaperScisors.GameComponents.MoveChoices;
import RockPaperScisors.GameComponents.ScorePrinter;
import RockPaperScisors.GameComponents.WinnerDecider;

public class Main {

    public static void main(String[] args) {

        MoveChoices moveChoices = new MoveChoices();
        GameConfig gameConfig = new GameConfig();
        GameController gameController = new GameController(moveChoices, gameConfig);
        ScorePrinter scorePrinter = new ScorePrinter(gameController.getGamestate());
        WinnerDecider winnerDecider = new WinnerDecider(gameController.getGamestate());

        gameController.initGame();
        gameController.getGamestate()
                .setScoreMatrix(new int[gameController.getGamestate().getPlayers().size()]
                        [gameController.getGamestate().getPlayers().size()][2]);
        gameController.runPcGames();
        scorePrinter.printScores();
        winnerDecider.getResults();
    }
}

