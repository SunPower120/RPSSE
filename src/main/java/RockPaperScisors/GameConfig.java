package RockPaperScisors;

import RockPaperScisors.GameComponents.FightingTools;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameConfig {
    private final Map<FightingTools, Map<FightingTools, String>> rulesMap;

    public GameConfig() {
        rulesMap = new HashMap<>();

        rulesMap.put(FightingTools.ROCK, Map.of(FightingTools.SCISSORS, "crushes", FightingTools.LIZARD, "crushes"));
        rulesMap.put(FightingTools.PAPER, Map.of(FightingTools.ROCK, "covers", FightingTools.SPOCK, "disproves"));
        rulesMap.put(FightingTools.SCISSORS, Map.of(FightingTools.PAPER, "cuts", FightingTools.LIZARD, "decapitates"));
        rulesMap.put(FightingTools.LIZARD, Map.of(FightingTools.SPOCK, "poisons", FightingTools.PAPER, "eats"));
        rulesMap.put(FightingTools.SPOCK, Map.of(FightingTools.SCISSORS, "smashes", FightingTools.ROCK, "vaporizes"));
    }

    public Set<FightingTools> getIsStrongerThan(FightingTools move) {
        Map<FightingTools, String> strengths = rulesMap.get(move);
        return (strengths != null) ? strengths.keySet() : Collections.emptySet();
    }

    public String getAction(FightingTools move, FightingTools opponentMove) {
        Map<FightingTools, String> actions = rulesMap.get(move);
        return (actions != null) ? actions.get(opponentMove) : null;
    }
}
