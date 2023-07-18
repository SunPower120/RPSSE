package RockPaperScisors.GameComponents;

import RockPaperScisors.GameState;

public class ScorePrinter {

    private static String center(String text, int len) {
        String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
        float mid = ((float) out.length() / 2);
        float start = mid - ((float) len / 2);
        float end = start + len;
        return out.substring((int) start, (int) end);
    }

    public static void printScores() {

        int maxNameLength = GameState.players.stream().mapToInt(player -> player.getName().length()).max().orElse(15);

        int cellWidth = Math.max(maxNameLength + 2, 15);

        System.out.println("\nScore Matrix:");
        System.out.printf("|%-" + cellWidth + "s|", "");
        for (Player player : GameState.players) {
            System.out.printf("%-" + cellWidth + "s|", center(player.getName(), cellWidth));
        }
        System.out.printf("%-" + cellWidth + "s|\n", center("Total", cellWidth));

        for (int i = 0; i < GameState.players.size(); i++) {
            System.out.printf("|%-" + cellWidth + "s|", center(GameState.players.get(i).getName(), cellWidth));
            int totalScore = 0;
            for (int j = 0; j < GameState.players.size(); j++) {
                if (i != j) {
                    System.out.printf("%-" + cellWidth + "s|", center(GameState.scores[i][j][0] + " - " + GameState.scores[i][j][1], cellWidth));
                    totalScore += GameState.scores[i][j][0];
                } else {
                    System.out.printf("%-" + cellWidth + "s|", center("-", cellWidth));
                }
            }
            System.out.printf("%-" + cellWidth + "s|\n", center(String.valueOf(totalScore), cellWidth));
        }
    }
}
