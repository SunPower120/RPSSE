package RockPaperScisors;

import RockPaperScisors.GameComponents.MoveChoice;
import RockPaperScisors.GameComponents.PcPlayerNames;
import RockPaperScisors.GameComponents.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    public static void initGame() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter your name:");
        String playerName = scan.nextLine();

        GameState.players.add(new Player(playerName));

        System.out.println("Enter number of computer players (from 1 up to 9):");
        int numberOfComputerPlayers = Math.min(Math.max(scan.nextInt(), 1), 9);

        System.out.println("Enter number of rounds (from 1 up to 5):");
        GameState.numberOfRounds = Math.min(Math.max(scan.nextInt(), 1), 5);

        List<String> randomNames = PcPlayerNames.getRandomNames(numberOfComputerPlayers);

        for (String name : randomNames) {
            GameState.players.add(new Player(name));
        }
    }

    public static MoveChoice getPlayerMove() {
        Scanner scan = new Scanner(System.in);
        MoveChoice playerMove = null;
        while (playerMove == null) {
            System.out.println("Enter your move (R for Rock, P for Paper, S for Scissors, L for Lizard, SP for Spock):");
            String move = scan.nextLine().toUpperCase();

            switch (move) {
                case "R" -> playerMove = MoveChoice.ROCK;
                case "P" -> playerMove = MoveChoice.PAPER;
                case "S" -> playerMove = MoveChoice.SCISSORS;
                case "L" -> playerMove = MoveChoice.LIZARD;
                case "SP" -> playerMove = MoveChoice.SPOCK;
                default -> System.out.println("Invalid move, please try again");
            }
        }
        return playerMove;
    }

    static void determineWinner() {
        List<Player> winners = new ArrayList<>();
        int maxScore = 0;

        for (Player player : GameState.players) {
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
                        int playerIndex = GameState.players.indexOf(player);
                        int opponentIndex = GameState.players.indexOf(opponent);
                        directScore += GameState.scores[playerIndex][opponentIndex][0];
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

    static void runPcGames() {
        for (int round = 0; round < GameState.numberOfRounds; round++) {
            for (int i = 0; i < GameState.players.size(); i++) {
                for (int j = i + 1; j < GameState.players.size(); j++) {
                    MoveChoice move1 = (i == 0) ? GameController.getPlayerMove() : MoveChoice.getRandomChoice();
                    MoveChoice move2 = MoveChoice.getRandomChoice();
                    String resultMessage;

                    if (GameConfig.getIsStrongerThan(move1).contains(move2)) {
                        GameState.players.get(i).incrementScore();
                        GameState.scores[i][j][0]++;
                        GameState.scores[j][i][1]++;
                        resultMessage = GameState.players.get(i).getName() + " wins. " + move1 + " " + GameConfig.getAction(move1).get(0) + " " + move2;
                    } else if (GameConfig.getIsStrongerThan(move2).contains(move1)) {
                        GameState.players.get(j).incrementScore();
                        GameState.scores[i][j][1]++;
                        GameState.scores[j][i][0]++;
                        resultMessage = GameState.players.get(j).getName() + " wins. " + move2 + " " + GameConfig.getAction(move2).get(0) + " " + move1;
                    } else {
                        resultMessage = "It's a draw.";
                    }
                    System.out.println("Round " + (round + 1) + ": " + GameState.players.get(i).getName() + " (" + move1 + ") VS " + GameState.players.get(j).getName() + " (" + move2 + ") - " + resultMessage);
                }
            }
        }
    }
}
