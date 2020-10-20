package seedu.duke.command.project;

import seedu.duke.project.Project;

import java.util.ArrayList;
import java.util.Hashtable;

public class SelectProjectCommand extends ProjectCommand{

    /**
     * Creates a new Sprint command with arguments.
     *
     * @param parameters
     */
    public SelectProjectCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    public void execute() {
        selectedProject = Integer.parseInt(parameters.get("0"));
    }

}
