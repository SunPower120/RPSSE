package RockPaperScisors.GameComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PcPlayerNames {

    private static final List<String> NAMES = Arrays.asList(
            "ByteDancer", "Codezilla", "NoSuchElement", "OverFlow", "ArraySlayer",
            "NullPointer", "JavaJuice", "GarbageCollector", "Heapster", "AsyncAvenger",
            "CurlyBraces", "BooleanBoss", "CaffeinatedCompiler", "AlgorithmAce", "InfiniteLoop",
            "RustySemaphore", "LambdaLuchador", "ExceptionExplorer", "SyntaxSniper", "RuntimeRider"
    );

    public static List<String> getRandomNames(int number) {
        if (number > NAMES.size()) {
            throw new IllegalArgumentException("Number too large, can't generate that many unique names");
        }

        List<String> shuffledNames = new ArrayList<>(NAMES);
        Collections.shuffle(shuffledNames);
        return shuffledNames.subList(0, number);
    }
}

