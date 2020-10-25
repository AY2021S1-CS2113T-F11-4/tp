package seedu.duke.command;

import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ProjectHelpCommand extends Command {
    public ProjectHelpCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    @Override
    public void execute() {
        Ui.showToUserLn("1. Create project");
        Ui.showToUserLn("   Format: project /create -title <title> " +
                "-desc <description> -dur <duration> -sd <sprint interval>");
        Ui.showToUserLn("   Example: project /create -title MeTube " +
                "-desc video streaming software -dur 90 -sd 10");
        Ui.showToUserLn("2. View project");
        Ui.showToUserLn("   Format & example: project /view");
        Ui.showToUserLn("3. Select project");
        Ui.showToUserLn("   Format: project /select <projectid>");
        Ui.showToUserLn("   Example: project /select 1");
    }
}
