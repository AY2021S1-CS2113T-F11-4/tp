package seedu.duke.command;

import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class InvalidCommand extends Command {

    public InvalidCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        Ui.showToUser("Invalid action!");
    }
}
