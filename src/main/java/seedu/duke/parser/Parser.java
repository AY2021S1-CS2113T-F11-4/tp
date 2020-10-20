
package seedu.duke.parser;

import seedu.duke.command.member.MemberCommand;
import seedu.duke.command.project.ProjectCommand;
import seedu.duke.command.sprint.AllocateSprintTaskCommand;
import seedu.duke.command.sprint.ViewSprintCommand;
import seedu.duke.command.sprint.AddSprintTaskCommand;
import seedu.duke.command.sprint.CreateSprintCommand;
import seedu.duke.command.sprint.RemoveSprintTaskCommand;
import seedu.duke.command.task.TaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.ADDTASK;
import static seedu.duke.command.CommandSummary.BYE;
import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.DELETE;
import static seedu.duke.command.CommandSummary.REMOVETASK;
import static seedu.duke.command.CommandSummary.PROJECT;
import static seedu.duke.command.CommandSummary.TASK;
import static seedu.duke.command.CommandSummary.MEMBER;
import static seedu.duke.command.CommandSummary.SPRINT;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.DONE;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.ASSIGN;


public class Parser {
    //Groups of 3: (command) (action) (options)
    private static final Pattern CMD_PATTERN = Pattern.compile("(\\w+)\\s\\/(\\w+)\\s*(.*)");
    //Groups of 2: (option name) (option value)
    private static final Pattern ARGS_PATTERN = Pattern.compile("-(\\w+)\\s([^-]+)");
    private final Hashtable<String, String> parameters = new Hashtable<>();
    private ArrayList<String> params = new ArrayList<>();

    private boolean exit = false;


    public String parser(String userInput, ArrayList<Project> projectList) {
        if (userInput.equals(BYE)) {
            System.out.println(BYE);
            exit = true;
            return null;
        }

        Matcher cmdMatcher = CMD_PATTERN.matcher(userInput);
        if (cmdMatcher.matches()) { //Need to check if it matches, groupCount will always show 3
            String command = cmdMatcher.group(1); //capture first group (command)
            String action = cmdMatcher.group(2); //capture 2nd group (action)
            String rawArgs = cmdMatcher.group(3); //capture 3rd group (options)
            Matcher parameterMatcher = ARGS_PATTERN.matcher(rawArgs); //match the option

            if (!rawArgs.contains("-")) {
                params.clear();
                parameters.clear();
                String[] arguments = rawArgs.split(" ");
                params.addAll(Arrays.asList(arguments));
                for (int i = 0; i < arguments.length; i++) {
                    parameters.put("" + i, arguments[i]);
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
                case PROJECT:
                    new ProjectParser().parseMultipleCommandsExceptions(parameters, action, projectList);
                    break;
                case MEMBER:
                    new MemberParser().parseMultipleCommandsExceptions(parameters, action, projectList);
                    break;
                case TASK:
                    new TaskParser().parseMultipleCommandsExceptions(parameters, action, projectList);
                    break;
                case SPRINT:
                    new SprintParser().parseMultipleCommandsExceptions(parameters, action, projectList);
                    break;
                default:
                    return "Invalid command!";
                }
            } catch (DukeException e) {
                e.printExceptionMessage();
            }
        } else {
            return "Invalid command!";
        }
        return null;
    }

    public static boolean isStringContainsNumber(String s) {
        return Pattern.compile("[0-9]").matcher(s).find();
    }

    public boolean isExit() {
        return exit;
    }
}