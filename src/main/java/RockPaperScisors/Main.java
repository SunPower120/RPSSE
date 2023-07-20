package RockPaperScisors;

import RockPaperScisors.GameComponents.MoveChoices;
import RockPaperScisors.GameComponents.ScorePrinter;

public class Main {

    public static void main(String[] args) {

        GameState gameState = new GameState();
        MoveChoices moveChoices = new MoveChoices();
        GameConfig gameConfig = new GameConfig();
        GameController gameController = new GameController(gameState, moveChoices, gameConfig);
        ScorePrinter scorePrinter = new ScorePrinter(gameState);

        gameController.initGame();
        gameState.scores = new int[gameState.players.size()][gameState.players.size()][2];
        gameController.runPcGames();
        scorePrinter.printScores();
        gameController.determineWinner();
    }
}

