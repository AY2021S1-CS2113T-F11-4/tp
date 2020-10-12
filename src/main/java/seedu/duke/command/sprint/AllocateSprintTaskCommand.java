package seedu.duke.command.sprint;

import seedu.duke.model.Project;
import seedu.duke.model.Sprint;
import seedu.duke.model.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class AllocateSprintTaskCommand extends SprintCommand {
    SprintList allSprint;

    public AllocateSprintTaskCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    public boolean execute(Project proj, Ui ui) {
        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            if (validateParams()) {
                String taskId = parameters.get("taskid");
                ArrayList<String> users = new ArrayList<>();
                for (int i = 0; i < parameters.size() - 1; i++) {
                    users.add(parameters.get(Integer.toString(i)));
                }

                int currentSprintNo = allSprint.getCurrentSprintIndex();
                Sprint currentSprint = allSprint.getSprint(currentSprintNo);
                currentSprint.allocateSprintTask(Integer.parseInt(taskId), users);
            }
        } else {
            checkReason(proj,ui);
        }
        return false;
    }

    private boolean validateParams() {
        return !parameters.get("taskid").isEmpty() && !parameters.get("0").isEmpty();
    }

    private void checkReason(Project proj, Ui ui) {
        if (allSprint.size() == 0) {
            ui.showToUser("You have yet to create your sprint.");
            return;
        }

        Sprint latestSprint = allSprint.getSprint(allSprint.size() - 1);
        if (DateTimeParser.diff(LocalDate.now(), proj.getEndDate()) == 0) {
            ui.showToUser("Project already ended on " + proj.getEndDate());
            return;
        } else if (DateTimeParser.diff(LocalDate.now(), proj.getStartDate()) > 0) {
            ui.showToUser("Project will start on " + proj.getStartDate());
            return;
        }

        if (DateTimeParser.diff(latestSprint.getEndDate(), LocalDate.now()) >= 0) {
            ui.showToUser("Latest sprint ended on " + latestSprint.getEndDate());
            ui.showToUser("Please create new sprint.");
            return;
        }

        Sprint current = allSprint.getSprint(0);
        if (DateTimeParser.diff(LocalDate.now(), current.getStartDate()) < 0) {
            ui.showToUser("First sprint will start on " + current.getStartDate());
        }
    }
}