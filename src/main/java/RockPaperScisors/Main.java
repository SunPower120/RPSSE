package RockPaperScisors;

import RockPaperScisors.gameComponents.MoveChoices;
import RockPaperScisors.gameComponents.ScorePrinter;
import RockPaperScisors.gameComponents.WinnerDecider;

public class Main {

    public static void main(String[] args) {

        MoveChoices moveChoices = new MoveChoices();
        GameConfig gameConfig = new GameConfig();
        GameController gameController = new GameController(moveChoices, gameConfig);
        ScorePrinter scorePrinter = new ScorePrinter(gameController);
        WinnerDecider winnerDecider = new WinnerDecider(gameController);

        gameController.initGame();
        gameController.getGamestate()
                .setScoreMatrix(new int[gameController.getGamestate().getPlayers().size()]
                        [gameController.getGamestate().getPlayers().size()][2]);
        gameController.runPcGames();
        scorePrinter.printScores();
        winnerDecider.getResults();
    }
}

