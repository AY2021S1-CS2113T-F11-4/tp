package seedu.duke.parser;

import seedu.duke.command.*;
import seedu.duke.command.help.MemberHelpCommand;
import seedu.duke.command.help.ProjectHelpCommand;
import seedu.duke.command.help.SprintHelpCommand;
import seedu.duke.command.help.TaskHelpCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

public class HelpParser implements ExceptionsParser {
    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {
        if (!Parser.isStringContainsNumber(action)) {
            throw new DukeException("Please give me a number!");
        }
        if (Integer.parseInt(action) < 1 || Integer.parseInt(action) > 4) {
            throw new DukeException("The command number is not in the list!");
        }
        switch (action) {
        case "1":
            return new ProjectHelpCommand(parameters);
        case "2":
            return new MemberHelpCommand(parameters);
        case "3":
            return new TaskHelpCommand(parameters);
        case "4":
            return new SprintHelpCommand(parameters);
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
