package RockPaperScisors;

import java.util.*;

public class Main {

    private static final List<Player> players = new ArrayList<>();
    private static int numberOfRounds;
    private static int[][][] scores;

    public static void main(String[] args) {

        initGame();
        scores = new int[players.size()][players.size()][2];
        runPcGames();
        printScores();
        determineWinner();
    }

    public static void initGame() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter your name:");
        String playerName = scan.nextLine();

        players.add(new Player(playerName));

        System.out.println("Enter number of computer players (from 1 up to 9):");
        int numberOfComputerPlayers = Math.min(Math.max(scan.nextInt(), 1), 9);

        System.out.println("Enter number of rounds (from 1 up to 5):");
        numberOfRounds = Math.min(Math.max(scan.nextInt(), 1), 5);

        List<String> randomNames = PcPlayerNames.getRandomNames(numberOfComputerPlayers);

        for (String name : randomNames) {
            players.add(new Player(name));
        }
    }

    public static GameMove getPlayerMove() {
        Scanner scan = new Scanner(System.in);
        GameMove playerMove = null;
        while (playerMove == null) {
            System.out.println("Enter your move (R for Rock, P for Paper, S for Scissors, L for Lizard, SP for Spock):");
            String move = scan.nextLine().toUpperCase();

            switch (move) {
                case "R" -> playerMove = GameMove.ROCK;
                case "P" -> playerMove = GameMove.PAPER;
                case "S" -> playerMove = GameMove.SCISSORS;
                case "L" -> playerMove = GameMove.LIZARD;
                case "SP" -> playerMove = GameMove.SPOCK;
                default -> System.out.println("Invalid move, please try again");
            }
        }
        return playerMove;
    }

    private static String center(String text, int len) {
        String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
        float mid = ((float) out.length() / 2);
        float start = mid - ((float) len / 2);
        float end = start + len;
        return out.substring((int) start, (int) end);
    }

    private static void printScores() {

        int maxNameLength = players.stream().mapToInt(player -> player.getName().length()).max().orElse(15);

        int cellWidth = Math.max(maxNameLength + 2, 15);

        System.out.println("\nScore Matrix:");
        System.out.printf("|%-" + cellWidth + "s|", "");
        for (Player player : players) {
            System.out.printf("%-" + cellWidth + "s|", center(player.getName(), cellWidth));
        }
        System.out.printf("%-" + cellWidth + "s|\n", center("Total", cellWidth));

        for (int i = 0; i < players.size(); i++) {
            System.out.printf("|%-" + cellWidth + "s|", center(players.get(i).getName(), cellWidth));
            int totalScore = 0;
            for (int j = 0; j < players.size(); j++) {
                if (i != j) {
                    System.out.printf("%-" + cellWidth + "s|", center(scores[i][j][0] + " - " + scores[i][j][1], cellWidth));
                    totalScore += scores[i][j][0];
                } else {
                    System.out.printf("%-" + cellWidth + "s|", center("-", cellWidth));
                }
            }
            System.out.printf("%-" + cellWidth + "s|\n", center(String.valueOf(totalScore), cellWidth));
        }
    }

    private static void determineWinner() {
        List<Player> winners = new ArrayList<>();
        int maxScore = 0;

        for (Player player : players) {
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
                        int playerIndex = players.indexOf(player);
                        int opponentIndex = players.indexOf(opponent);
                        directScore += scores[playerIndex][opponentIndex][0];
                    }
                }

                if (directScore > finalMaxScore) {
                    finalMaxScore = directScore;
                    finalWinner = player;
                } else if (directScore == finalMaxScore && finalWinner != null) {
                    System.out.println("Draw between " + player.getName() + " and " + finalWinner.getName());
                    finalWinner = null; // indicates a draw
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

    private static void runPcGames() {
        for (int round = 0; round < numberOfRounds; round++) {
            for (int i = 0; i < players.size(); i++) {
                for (int j = i + 1; j < players.size(); j++) {
                    GameMove move1 = (i == 0) ? getPlayerMove() : GameMove.getRandomChoice();
                    GameMove move2 = GameMove.getRandomChoice();

                    if (move1.getIsStrongerThan().contains(move2.name())) {
                        players.get(i).incrementScore();
                        scores[i][j][0]++;
                        scores[j][i][1]++;
                    } else if (move2.getIsStrongerThan().contains(move1.name())) {
                        players.get(j).incrementScore();
                        scores[i][j][1]++;
                        scores[j][i][0]++;
                    }

                    System.out.println("Round " + (round + 1) + ": " + players.get(i).getName() + " (" + move1 + ") VS " + players.get(j).getName() + " (" + move2 + ")");
                }
            }
        }
    }
}
