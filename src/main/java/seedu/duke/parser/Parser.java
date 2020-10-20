package seedu.duke.parser;

import seedu.duke.command.member.MemberCommand;
import seedu.duke.command.project.CreateProjectCommand;
import seedu.duke.command.project.ProjectCommand;
import seedu.duke.command.project.SelectProjectCommand;
import seedu.duke.command.project.ViewProjectCommand;
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
import static seedu.duke.command.CommandSummary.SELECT;

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

            switch (command.toLowerCase()) {
            case PROJECT:
                try {
                    new ProjectParser().parseMultipleCommandsExceptions(parameters, action);
                } catch (DukeException e) {
                    e.printExceptionMessage();
                    break;
                }
                switch (action.toLowerCase()) {
                case CREATE:
                    new CreateProjectCommand(parameters, projectList).execute();
                    break;
                case VIEW:
                    new ViewProjectCommand(parameters, projectList).execute();
                    break;
                case SELECT:
                    new SelectProjectCommand(parameters).execute();
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case MEMBER:
                try {
                    new MemberParser().parseSingleCommandsExceptions(parameters);
                } catch (DukeException e) {
                    e.printExceptionMessage();
                    break;
                }
                switch (action.toLowerCase()) {
                case ADD:
                    new MemberCommand().addMemberCommand(params, projectList);
                    break;
                case DELETE:
                    new MemberCommand().deleteMemberCommand(params, projectList);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case TASK:
                try {
                    new TaskParser().parseMultipleCommandsExceptions(parameters, action);
                } catch (DukeException e) {
                    e.printExceptionMessage();
                    break;
                }
                switch (action.toLowerCase()) {
                case ADD:
                    try {
                        new TaskCommand().addTaskCommand(parameters, projectList);
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                    break;
                case DELETE:
                    new TaskCommand().deleteTaskCommand(params, projectList);
                    break;
                case VIEW:
                    new TaskCommand().viewTaskCommand(params, projectList);
                    break;
                case PRIORITY:
                    try {
                        new TaskCommand().changeTaskPriorityCommand(parameters, projectList);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                case DONE:
                    new TaskCommand().doneTaskCommand(params, projectList);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action!");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case SPRINT:
                try {
                    new SprintParser().parseMultipleCommandsExceptions(parameters, action);
                } catch (DukeException e) {
                    e.printExceptionMessage();
                    break;
                }
                switch (action.toLowerCase()) {
                case CREATE:
                    new CreateSprintCommand(parameters, projectList).execute();
                    break;
                case ADDTASK:
                    new AddSprintTaskCommand(parameters, projectList).execute();
                    break;
                case REMOVETASK:
                    new RemoveSprintTaskCommand(parameters, projectList).execute();
                    break;
                case VIEW:
                    new ViewSprintCommand(parameters, projectList).execute();
                    break;
                case ASSIGN:
                    new AllocateSprintTaskCommand(parameters, projectList).execute();
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action!");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            default:
                return "Invalid command!";
            }
        } else {
            return "Invalid command!";
        }
        return null;
    }

    public static boolean stringContainsNumber(String s) {
        return Pattern.compile("[0-9]").matcher(s).find();
    }

    public boolean isExit() {
        return exit;
    }
}