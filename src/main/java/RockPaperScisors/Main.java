package RockPaperScisors;

import RockPaperScisors.GameComponents.DetermineWinner;
import RockPaperScisors.GameComponents.MoveChoices;
import RockPaperScisors.GameComponents.ScorePrinter;

public class Main {

    public static void main(String[] args) {

        GameState gameState = new GameState();
        MoveChoices moveChoices = new MoveChoices();
        GameConfig gameConfig = new GameConfig();
        GameController gameController = new GameController(gameState, moveChoices, gameConfig);
        ScorePrinter scorePrinter = new ScorePrinter(gameState);
        DetermineWinner determineWinner = new DetermineWinner(gameState);

        gameController.initGame();
        gameState.setScoreMatrix(new int[gameState.getPlayers().size()][gameState.getPlayers().size()][2]);
        gameController.runPcGames();
        scorePrinter.printScores();
        determineWinner.getResults();
    }
}

