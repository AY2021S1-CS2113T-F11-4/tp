package seedu.duke.command.sprint;

import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ViewSprintCommand extends SprintCommand {

    public ViewSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList);
    }

    public void execute() {
        chooseProject();
        chooseSprint();


        //Output to user
        Ui.showToUserLn(this.projOwner.toIDString());
        Ui.showToUser(this.sprintOwner.toString());

    }
}
