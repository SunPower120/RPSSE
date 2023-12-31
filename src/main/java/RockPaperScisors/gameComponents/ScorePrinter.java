package RockPaperScisors.gameComponents;

import RockPaperScisors.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScorePrinter {

    private static final Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
    private final GameController gameController;

    StringBuilder stringbuilder = new StringBuilder();

    public ScorePrinter(GameController gameController) {
        this.gameController = gameController;
    }

    public void printScores() {
        int maxNameLength = findMaxNameLength();
        int cellWidth = determineCellWidth(maxNameLength);

        String format = "|%-" + cellWidth + "s";
        String formatN = "|%-" + cellWidth + "s|";

        printHeader(format, formatN, cellWidth);
        printScoresForEachPlayer(format, formatN, cellWidth);
    }

    private int findMaxNameLength() {
        return gameController.getGamestate().getPlayers().stream()
                .mapToInt(player -> player.getName().length())
                .max()
                .orElse(15);
    }

    private int determineCellWidth(int maxNameLength) {
        return Math.max(maxNameLength + 2, 15);
    }

    private void printHeader(String format, String formatN, int cellWidth) {
        stringbuilder.append("\nScore Matrix:\n");
        stringbuilder.append(String.format(format, ""));
        for (Player player : gameController.getGamestate().getPlayers()) {
            stringbuilder.append(String.format(format, center(player.getName(), cellWidth)));
        }
        stringbuilder.append(String.format(formatN, center("Total", cellWidth)));
        consoleLogger.info(stringbuilder.toString());
        stringbuilder.setLength(0);
    }

    private void printScoresForEachPlayer(String format, String formatN, int cellWidth) {
        for (int i = 0; i < gameController.getGamestate().getPlayers().size(); i++) {
            stringbuilder.append(String.format(format, center(gameController.getGamestate().getPlayers().get(i).getName(), cellWidth)));
            int totalScore = 0;
            for (int j = 0; j < gameController.getGamestate().getPlayers().size(); j++) {
                if (i != j) {
                    stringbuilder.append(String.format(format, center(gameController.getGamestate().getScoreMatrix()[i][j][0] + " - " + gameController.getGamestate().getScoreMatrix()[i][j][1], cellWidth)));
                    totalScore += gameController.getGamestate().getScoreMatrix()[i][j][0];
                } else {
                    stringbuilder.append(String.format(format, center("-", cellWidth)));
                }
            }
            stringbuilder.append(String.format(formatN, center(String.valueOf(totalScore), cellWidth)));
            consoleLogger.info(stringbuilder.toString());
            stringbuilder.setLength(0);
        }
    }

    private String center(String text, int len) {
        String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
        float mid = ((float) out.length() / 2);
        float start = mid - ((float) len / 2);
        float end = start + len;
        return out.substring((int) start, (int) end);
    }

}