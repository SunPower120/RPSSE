package RockPaperScisors;

import RockPaperScisors.GameComponents.MoveChoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameConfig {
    private static final Map<MoveChoice, List<MoveChoice>> strongerThanMap = new HashMap<>();
    private static final Map<MoveChoice, List<String>> actionMap = new HashMap<>();

    static {
        strongerThanMap.put(MoveChoice.ROCK, List.of(MoveChoice.SCISSORS, MoveChoice.LIZARD));
        strongerThanMap.put(MoveChoice.PAPER, List.of(MoveChoice.ROCK, MoveChoice.SPOCK));
        strongerThanMap.put(MoveChoice.SCISSORS, List.of(MoveChoice.PAPER, MoveChoice.LIZARD));
        strongerThanMap.put(MoveChoice.LIZARD, List.of(MoveChoice.SPOCK, MoveChoice.PAPER));
        strongerThanMap.put(MoveChoice.SPOCK, List.of(MoveChoice.SCISSORS, MoveChoice.ROCK));

        actionMap.put(MoveChoice.ROCK, List.of("crushes", "crushes"));
        actionMap.put(MoveChoice.PAPER, List.of("covers", "disproves"));
        actionMap.put(MoveChoice.SCISSORS, List.of("cuts", "decapitates"));
        actionMap.put(MoveChoice.LIZARD, List.of("poisons", "eats"));
        actionMap.put(MoveChoice.SPOCK, List.of("smashes", "vaporizes"));
    }

    public static List<MoveChoice> getIsStrongerThan(MoveChoice move) {
        return strongerThanMap.get(move);
    }

    public static List<String> getAction(MoveChoice move) {
        return actionMap.get(move);
    }
}

