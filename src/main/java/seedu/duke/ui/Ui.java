package seedu.duke.ui;

import seedu.duke.common.Messages;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi that handles user interaction.
 */
public class Ui {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;

    /**
     * Prints welcome message.
     */
    public static void showWelcomeScreen() {
        showToUserLn(Messages.MESSAGE_LOGO);
        showToUserLn(Messages.MESSAGE_WELCOME);
    }

    /**
     * Accepts input from user via input stream.
     *
     * @return Input from user
     */
    public static String getUserCommand() {
        showToUser(Messages.MESSAGE_PROMPT_INPUT);
        String input = in.nextLine().strip();
        while (input.isEmpty()) {
            input = in.nextLine().trim();
        }
        return input;
    }

    /**
     * Print messages to user.
     *
     * @param messages Specify messages to print
     */
    public static void showToUserLn(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }

    public static void showError(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }
    public static void showToUser(String... messages) {
        for (String message : messages) {
            out.print(message);
        }
    }



}
