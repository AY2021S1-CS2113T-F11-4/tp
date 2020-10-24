package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.member.AddMemberCommand;
import seedu.duke.command.member.DeleteMemberCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.DELETE;

public class MemberParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {
        if (!parameters.containsKey("0")) {
            throw new DukeException("Missing name.");
        } else if (projectListManager.size() == 0) {
            throw new DukeException("You currently have no projects created.");
        }
        switch (action.toLowerCase()) {
        case ADD:
            return new AddMemberCommand(parameters, projectListManager);
        case DELETE:
            return new DeleteMemberCommand(parameters, projectListManager);
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
