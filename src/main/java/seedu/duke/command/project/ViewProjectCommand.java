package seedu.duke.command.project;

import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class ViewProjectCommand extends ProjectCommand {

    private final ArrayList<Project> projectList;

    public ViewProjectCommand(Hashtable<String, String> parameters, ArrayList<Project> projectList) {
        super(parameters);
        this.projectList = projectList;
    }

    public void execute() {
        assert projectList.size() != 0 : "No projects created \n.";

        if (projectList.isEmpty()) {
            Ui.showError("No projects are created.");
        } else {
            Project project = projectList.get(selectedProject-1);
            assert project != null : "The project is null";
            Ui.showToUserLn(project.toString());

        }
    }
}
