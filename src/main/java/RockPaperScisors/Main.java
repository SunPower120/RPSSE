package RockPaperScisors;

import RockPaperScisors.GameComponents.ScorePrinter;

public class Main {

    public static void main(String[] args) {

        GameController.initGame();
        GameState.scores = new int[GameState.players.size()][GameState.players.size()][2];
        GameController.runPcGames();
        ScorePrinter.printScores();
        GameController.determineWinner();
    }
}

