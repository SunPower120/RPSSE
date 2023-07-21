package RockPaperScisors.GameComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.Scanner;

public class MoveChoices {
    private static final Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");

    public FightingTools getRandomChoice() {
        Random random = new Random();
        return FightingTools.values()[random.nextInt(FightingTools.values().length)];
    }

    public FightingTools getPlayerMove() {
        Scanner scan = new Scanner(System.in);
        FightingTools playerMove = null;
        while (playerMove == null) {
            consoleLogger.info("Enter your move (R for Rock, P for Paper, S for Scissors, L for Lizard, SP for Spock):");
            String move = scan.nextLine().toUpperCase();

            switch (move) {
                case "R" -> playerMove = FightingTools.ROCK;
                case "P" -> playerMove = FightingTools.PAPER;
                case "S" -> playerMove = FightingTools.SCISSORS;
                case "L" -> playerMove = FightingTools.LIZARD;
                case "SP" -> playerMove = FightingTools.SPOCK;
                default -> consoleLogger.error("Invalid input, please try again");
            }
        }
        return playerMove;
    }
}
