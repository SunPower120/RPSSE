package RockPaperScisors;

import RockPaperScisors.GameComponents.Player;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    public final List<Player> players = new ArrayList<>();
    public int[][][] scores;
    int numberOfRounds;
}
