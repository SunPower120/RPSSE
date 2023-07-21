package RockPaperScisors;

import RockPaperScisors.Exceptions.OutOfRangeException;
import RockPaperScisors.GameComponents.FightingTools;
import RockPaperScisors.GameComponents.MoveChoices;
import RockPaperScisors.GameComponents.PcPlayerNames;
import RockPaperScisors.GameComponents.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private static final Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
    private final GameState gameState;
    private final MoveChoices moveChoices;
    private final GameConfig gameConfig;

    public GameController(GameState gameState, MoveChoices moveChoices, GameConfig gameConfig) {
        this.gameState = gameState;
        this.moveChoices = moveChoices;
        this.gameConfig = gameConfig;

    }

    void runPcGames() {
        for (int round = 0; round < gameState.getNumberOfRounds(); round++) {
            for (int i = 0; i < gameState.getPlayers().size(); i++) {
                for (int j = i + 1; j < gameState.getPlayers().size(); j++) {
                    FightingTools move1 = (i == 0) ? moveChoices.getPlayerMove() : moveChoices.getRandomChoice();
                    FightingTools move2 = moveChoices.getRandomChoice();
                    String resultMessage;

                    if (gameConfig.getIsStrongerThan(move1).contains(move2)) {
                        gameState.getPlayers().get(i).incrementScore();
                        gameState.incrementScoreInMatrix(i, j, 0);
                        gameState.incrementScoreInMatrix(j, i, 1);
                        resultMessage = gameState.getPlayers().get(i).getName() + " wins. " + move1 + " " + gameConfig.getAction(move1, move2) + " " + move2;

                    } else if (gameConfig.getIsStrongerThan(move2).contains(move1)) {
                        gameState.getPlayers().get(j).incrementScore();
                        gameState.incrementScoreInMatrix(i, j, 1);
                        gameState.incrementScoreInMatrix(j, i, 0);
                        resultMessage = gameState.getPlayers().get(j).getName() + " wins. " + move2 + " " + gameConfig.getAction(move2, move1) + " " + move1;

                    } else {
                        resultMessage = "It's a draw.";
                    }
                    consoleLogger.info("Round " + (round + 1) + ": " + gameState.getPlayers().get(i).getName() + " (" + move1 + ") VS " + gameState.getPlayers().get(j).getName() + " (" + move2 + ") - " + resultMessage);
                }
            }
        }
    }

    public void initGame() {
        Scanner scan = new Scanner(System.in);
        int numberOfComputerPlayers;

        consoleLogger.info("Enter your name:");
        String playerName = scan.nextLine();

        gameState.addPlayerToList(new Player(playerName));

        while (true) {
            try {
                consoleLogger.info("Enter number of computer players (from 1 up to 9):");
                numberOfComputerPlayers = scan.nextInt();
                if (numberOfComputerPlayers < 1 || numberOfComputerPlayers > 9) {
                    throw new OutOfRangeException("Number of players must be between 1 and 9.");
                }
                break;
            } catch (InputMismatchException e) {
                consoleLogger.error("Invalid input. Please enter an integer number.");
                scan.next();
            } catch (OutOfRangeException e) {
                consoleLogger.error(e.getMessage());
            }
        }

        while (true) {
            try {
                consoleLogger.info("Enter number of rounds (from 1 up to 5):");
                int numberOfRounds = scan.nextInt();
                if (numberOfRounds < 1 || numberOfRounds > 5) {
                    throw new OutOfRangeException("Number of rounds must be between 1 and 5.");
                }
                gameState.setNumberOfRounds(numberOfRounds);
                break;
            } catch (InputMismatchException e) {
                consoleLogger.error("Invalid input. Please enter an integer number.");
                scan.next();
            } catch (OutOfRangeException e) {
                consoleLogger.error(e.getMessage());
            }
        }

        List<String> randomNames = PcPlayerNames.getRandomNames(numberOfComputerPlayers);

        for (String name : randomNames) {
            gameState.addPlayerToList(new Player(name));
        }
    }

}
