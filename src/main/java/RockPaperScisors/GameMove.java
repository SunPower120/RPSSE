package RockPaperScisors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum ToolChoice {
    ROCK(List.of("SCISSORS", "LIZARD"), List.of("crushes", "crushes")),
    PAPER(List.of("ROCK", "SPOCK"), List.of("covers", "disproves")),
    SCISSORS(List.of("PAPER", "LIZARD"), List.of("cuts", "decapitates")),
    LIZARD(List.of("SPOCK", "PAPER"), List.of("poisons", "eats")),
    SPOCK(List.of("SCISSORS", "ROCK"), List.of("smashes", "vaporizes"));

    private final ArrayList<String> isStrongerThan;
    private final ArrayList<String> action;

    ToolChoice(List<String> isStrongerThan, List<String> action) {
        this.isStrongerThan = new ArrayList<>(isStrongerThan);
        this.action = new ArrayList<>(action);
    }

    public static ToolChoice randomPattern() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    public static ToolChoice userPattern(String userInput) {
        return switch (userInput) {
            case "S" -> SCISSORS;
            case "P" -> PAPER;
            case "T" -> ROCK;
            case "L" -> LIZARD;
            case "SP" -> SPOCK;
            default -> null;
        };
    }

    public List<String> getIsStrongerThan() {
        return isStrongerThan;
    }

    public List<String> getFunction() {
        return action;
    }
}
