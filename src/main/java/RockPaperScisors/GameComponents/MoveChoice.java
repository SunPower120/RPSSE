package RockPaperScisors.GameComponents;

import java.util.Random;

public enum MoveChoice {
    ROCK,
    PAPER,
    SCISSORS,
    LIZARD,
    SPOCK;

    public static MoveChoice getRandomChoice() {
        Random random = new Random();
        return MoveChoice.values()[random.nextInt(MoveChoice.values().length)];
    }
}