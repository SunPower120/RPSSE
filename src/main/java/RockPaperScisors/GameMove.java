package RockPaperScisors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum GameMove {
    ROCK(List.of("SCISSORS", "LIZARD"), List.of("crushes", "crushes")),
    PAPER(List.of("ROCK", "SPOCK"), List.of("covers", "disproves")),
    SCISSORS(List.of("PAPER", "LIZARD"), List.of("cuts", "decapitates")),
    LIZARD(List.of("SPOCK", "PAPER"), List.of("poisons", "eats")),
    SPOCK(List.of("SCISSORS", "ROCK"), List.of("smashes", "vaporizes"));

    private final ArrayList<String> isStrongerThan;
    private final ArrayList<String> action;

    GameMove(List<String> isStrongerThan, List<String> action) {
        this.isStrongerThan = new ArrayList<>(isStrongerThan);
        this.action = new ArrayList<>(action);
    }

    public ArrayList<String> getIsStrongerThan() {
        return isStrongerThan;
    }

    public ArrayList<String> getAction() {
        return action;
    }

    public static GameMove getRandomChoice() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

