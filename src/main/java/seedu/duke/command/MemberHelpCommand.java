package seedu.duke.command;

import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class MemberHelpCommand extends Command {
    public MemberHelpCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
        Ui.showToUserLn("1. Add members");
        Ui.showToUserLn("   Format: member /add <username> [<username> ...]");
        Ui.showToUserLn("   Example: member /add john mary");
        Ui.showToUserLn("2. Remove members");
        Ui.showToUserLn("   Format: member /del <username> [<username> ...]");
        Ui.showToUserLn("   Example: member /del john mary");
    }
}
