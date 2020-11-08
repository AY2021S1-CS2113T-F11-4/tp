package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.EmptyCommand;
import seedu.duke.command.help.MainHelpCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.CommandSummary.BYE;
import static seedu.duke.command.CommandSummary.HELP;
import static seedu.duke.command.CommandSummary.MEMBER;
import static seedu.duke.command.CommandSummary.PROJECT;
import static seedu.duke.command.CommandSummary.SPRINT;
import static seedu.duke.command.CommandSummary.STORAGE;
import static seedu.duke.command.CommandSummary.TASK;


public class ParserManager {
    //Groups of 3: (command) (action) (options)
    private static final Pattern CMD_PATTERN = Pattern.compile("(\\w+)\\s\\/(\\w+)\\s*(.*)");
    //Groups of 2: (option name) (option value)
    private static final Pattern ARGS_PATTERN = Pattern.compile("-(\\w+)\\s([^-]+)");
    private final Hashtable<String, String> parameters = new Hashtable<>();
    private boolean exit = false;

    /**
     * Checks the user input and splits the it into command, action and arguments before
     * calling the respective subparsers required to return the respective commands.
     * @param userInput commands input by the user.
     * @param projectListManager manager of projects.
     * @return a command to be executed by SCRUMptious.
     */
    public Command parser(String userInput, ProjectManager projectListManager) {
        if (userInput.equals(BYE)) {
            System.out.println(BYE);
            exit = true;
            ScrumLogger.LOGGER.info("Exit SCRUMptious");
            return new EmptyCommand(parameters);
        }
        if (userInput.equals(HELP)) {
            return new MainHelpCommand(parameters);
        }

        Matcher cmdMatcher = CMD_PATTERN.matcher(userInput);
        if (cmdMatcher.matches()) { //Need to check if it matches, groupCount will always show 3
            String command = cmdMatcher.group(1); //capture first group (command)
            String action = cmdMatcher.group(2); //capture 2nd group (action)
            String rawArgs = cmdMatcher.group(3); //capture 3rd group (options)
            Matcher parameterMatcher = ARGS_PATTERN.matcher(rawArgs); //match the option

            if (!rawArgs.contains("-")) {
                parameters.clear();
                if (!rawArgs.isBlank()) {
                    String[] arguments = rawArgs.split(" ");
                    for (int i = 0; i < arguments.length; i++) {
                        parameters.put("" + i, arguments[i]);
                    }
                }
            } else {
                parameters.clear();
                while (parameterMatcher.find()) { //go through each occurrence of options
                    //put the options into the hashtable (similar to dictionary)
                    parameters.put(parameterMatcher.group(1), parameterMatcher.group(2).trim());
                }
            }

            try {
                switch (command.toLowerCase()) {
                case HELP:
                    return new HelpParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case PROJECT:
                    return new ProjectParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case MEMBER:
                    return new MemberParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case TASK:
                    return new TaskParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case SPRINT:
                    return new SprintParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case STORAGE:
                    return new StorageParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                default:
                    return new InvalidCommand(parameters);
                }
            } catch (DukeException e) {
                e.printExceptionMessage();
                ScrumLogger.LOGGER.warning(e.getMessage());
            }
        } else {
            return new InvalidCommand(parameters);
        }
        return new EmptyCommand(parameters);
    }

    /**
     * Checks if the string is an integer.
     * @param s string
     * @return boolean value
     */
    public static boolean isStringIntParsable(String s) {
        try {
            assert Integer.parseInt(s) >= 0;
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the boolean value of exit.
     * @return boolean value
     */
    public boolean isExit() {
        return exit;
    }
}