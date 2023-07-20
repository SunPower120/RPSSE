package RockPaperScisors;

import RockPaperScisors.GameComponents.FightingTools;
import RockPaperScisors.GameComponents.MoveChoices;
import RockPaperScisors.GameComponents.PcPlayerNames;
import RockPaperScisors.GameComponents.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {

    private final GameState gameState;
    private final MoveChoices moveChoices;
    private final GameConfig gameConfig;


    public GameController(GameState gameState, MoveChoices moveChoices, GameConfig gameConfig) {
        this.gameState = gameState;
        this.moveChoices = moveChoices;
        this.gameConfig = gameConfig;

    }

    void determineWinner() {
        List<Player> winners = new ArrayList<>();
        int maxScore = 0;

        for (Player player : gameState.players) {
            int score = player.getScore();
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(player);
            } else if (score == maxScore) {
                winners.add(player);
            }
        }

        if (winners.size() > 1) {
            System.out.println("There are multiple winners. Determining the winner based on direct encounters...");
            Player finalWinner = null;
            int finalMaxScore = 0;

            for (Player player : winners) {
                int directScore = 0;

                for (Player opponent : winners) {
                    if (!player.equals(opponent)) {
                        int playerIndex = gameState.players.indexOf(player);
                        int opponentIndex = gameState.players.indexOf(opponent);
                        directScore += gameState.scores[playerIndex][opponentIndex][0];
                    }
                }

                if (directScore > finalMaxScore) {
                    finalMaxScore = directScore;
                    finalWinner = player;
                } else if (directScore == finalMaxScore && finalWinner != null) {
                    System.out.println("Draw between " + player.getName() + " and " + finalWinner.getName());
                    finalWinner = null;
                }
            }

            if (finalWinner != null) {
                System.out.println("Winner after considering direct encounters: " + finalWinner.getName());
            } else {
                System.out.println("There is a draw among the top players even after considering direct encounters.");
            }
        } else {
            System.out.println("Winner: " + winners.get(0).getName());
        }
    }

    void runPcGames() {
        for (int round = 0; round < gameState.numberOfRounds; round++) {
            for (int i = 0; i < gameState.players.size(); i++) {
                for (int j = i + 1; j < gameState.players.size(); j++) {
                    FightingTools move1 = (i == 0) ? moveChoices.getPlayerMove() : moveChoices.getRandomChoice();
                    FightingTools move2 = moveChoices.getRandomChoice();
                    String resultMessage;

                    if (gameConfig.getIsStrongerThan(move1).contains(move2)) {
                        gameState.players.get(i).incrementScore();
                        gameState.scores[i][j][0]++;
                        gameState.scores[j][i][1]++;
                        resultMessage = gameState.players.get(i).getName() + " wins. " + move1 + " " + gameConfig.getAction(move1, move2) + " " + move2;
                    } else if (gameConfig.getIsStrongerThan(move2).contains(move1)) {
                        gameState.players.get(j).incrementScore();
                        gameState.scores[i][j][1]++;
                        gameState.scores[j][i][0]++;
                        resultMessage = gameState.players.get(j).getName() + " wins. " + move2 + " " + gameConfig.getAction(move2, move1) + " " + move1;
                    } else {
                        resultMessage = "It's a draw.";
                    }
                    System.out.println("Round " + (round + 1) + ": " + gameState.players.get(i).getName() + " (" + move1 + ") VS " + gameState.players.get(j).getName() + " (" + move2 + ") - " + resultMessage);
                }
            }
        }
    }

    public void initGame() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter your name:");
        String playerName = scan.nextLine();

        gameState.players.add(new Player(playerName));

        System.out.println("Enter number of computer players (from 1 up to 9):");
        int numberOfComputerPlayers = Math.min(Math.max(scan.nextInt(), 1), 9);

        System.out.println("Enter number of rounds (from 1 up to 5):");
        gameState.numberOfRounds = Math.min(Math.max(scan.nextInt(), 1), 5);

        List<String> randomNames = PcPlayerNames.getRandomNames(numberOfComputerPlayers);

        for (String name : randomNames) {
            gameState.players.add(new Player(name));
        }
    }

}
