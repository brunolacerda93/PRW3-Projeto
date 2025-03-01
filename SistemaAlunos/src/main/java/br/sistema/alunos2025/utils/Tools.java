package br.sistema.alunos2025.utils;

import java.util.List;
import java.util.Scanner;

/**
 * A set of useful functions developed through the semester.
 *
 * <p>Currently the following functions are available:
 * {@snippet :
 *     pause();
 *     clearScreen();
 *     averageOf(List<? extends Number> array);
 *}
 */
public class Tools {
    /**
     * Takes the average value of a given {@code List} of anything
     * that is a {@code Number}, ie. anything that {@code extends} {@code Number}
     *
     * @param ls A {@code List} of anything that is a {@code Number}.
     * @return The average of the values of the given {@code List}.
     */
    public static Double averageOf(List<? extends Number> ls) {
        if (ls == null || ls.isEmpty()) {
            return null;
        }

        double sum = 0.0;
        for (Number n : ls) {
            sum += n.doubleValue();
        }
        return sum / ls.size();
    }

    /**
     * Flushes the console. Only works on cmd and Shells.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Waits for <i>enter</i> key to be pressed.
     * <p> Calls {@code Scanner.nextLine()}
     */
    public static void pause() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press <enter> to continue... ");
        sc.nextLine();
    }
}
