package RockPaperScisors;

import java.util.Scanner;

public class StonePaperScissorsMain {

    public static void main(String[] args) {

        String inputPattern = "";
        Scanner scan = new Scanner(System.in);

        while (!inputPattern.equals("q")) {
            StonePaperScissors randomChoice = StonePaperScissors.randomPattern();
            String randomChoiceIsStrongerThan = randomChoice.getIsStrongerThan();

            System.out.println("(Enter s for scissor, p for paper, t for stone, q to quit):");
            inputPattern = scan.nextLine();

            if (inputPattern.equals("q")) {
                System.out.println("Thanks for playing");
            } else if (!inputPattern.equals("s") && !inputPattern.equals("p") && !inputPattern.equals("t")) {
                System.out.println("Wrong input");
            } else if (randomChoiceIsStrongerThan.equals(StonePaperScissors.userPattern(inputPattern).name())) {
                System.out.println(randomChoice + " " + randomChoice.getFunction() + " " +
                        StonePaperScissors.userPattern(inputPattern) + " I won");
            } else if (StonePaperScissors.userPattern(inputPattern).getIsStrongerThan().equals(randomChoice.name())) {
                System.out.println(StonePaperScissors.userPattern(inputPattern) + " " +
                        StonePaperScissors.userPattern(inputPattern).getFunction() + " " +
                        randomChoice + " You won");
            } else System.out.println("Tie");

        }
    }
}
