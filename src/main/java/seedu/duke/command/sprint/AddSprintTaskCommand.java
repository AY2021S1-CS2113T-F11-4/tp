package seedu.duke.command.sprint;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.sprint.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddSprintTaskCommand extends SprintCommand {
    private SprintList allSprint;
    private ProjectList projectManager;
    private Project proj;
    private static final Logger LOGGER = Logger.getLogger(AddSprintTaskCommand.class.getName());


    public AddSprintTaskCommand(Hashtable<String, String> parameters, ProjectList projectManager) {
        super(parameters);
        this.projectManager = projectManager;
        LOGGER.setLevel(Level.WARNING);
    }

    public void execute() {
        assert !projectManager.isEmpty() : "No project\n";
        if (projectManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }
        proj = projectManager.getProject();
        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            int currentSprintNo = allSprint.getCurrentSprintIndex();
            Sprint currentSprint = allSprint.getSprint(currentSprintNo);
            if (!parameters.isEmpty()) {
                for (String entry : parameters.values()) {
                    try {
                        int taskId = Integer.parseInt(entry);
                        currentSprint.addSprintTask(taskId);
                        proj.getProjectBacklog().getTask(taskId).allocateToSprint(currentSprint.getId());
                        Ui.showToUser(proj.getProjectBacklog().getTask(taskId).getTitle() + " added to sprint.\n");
                        LOGGER.log(Level.INFO, "AddSprintTaskCommand executed successfully"
                                + System.lineSeparator()
                                + proj.getProjectBacklog().getTask(taskId).getTitle());
                    } catch (NumberFormatException e) {
                        Ui.showError("Invalid parameters.");
                        LOGGER.log(Level.WARNING, "Provided invalid parameters: " + parameters);
                    }

                }
            } else {
                Ui.showError("Missing parameters.");
            }
        } else {
            checkReason();
        }
    }

    public void checkReason() {
        if (allSprint.size() == 0) {
            Ui.showToUserLn("You have yet to create your sprint.");
            return;
        }

        Sprint latestSprint = allSprint.getSprint(allSprint.size() - 1);
        if (DateTimeParser.diff(LocalDate.now(), proj.getEndDate()) == 0) {
            Ui.showToUserLn("Project already ended on " + proj.getEndDate());
            return;
        } else if (DateTimeParser.diff(LocalDate.now(), proj.getStartDate()) > 0) {
            Ui.showToUserLn("Project will start on " + proj.getStartDate());
            return;
        }

        if (DateTimeParser.diff(latestSprint.getEndDate(), LocalDate.now()) >= 0) {
            Ui.showToUserLn("Latest sprint ended on " + latestSprint.getEndDate());
            Ui.showToUserLn("Please create new sprint.");
            return;
        }

        Sprint current = allSprint.getSprint(0);
        if (DateTimeParser.diff(LocalDate.now(), current.getStartDate()) < 0) {
            Ui.showToUserLn("First sprint will start on " + current.getStartDate());
        }
    }

}
